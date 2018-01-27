package notifier.bus.stand.scheduler;

import notifier.bus.controller.model.pojo.Location;
import notifier.bus.stand.firebase.Firebase;
import notifier.bus.stand.firebase.SendNotifications;
import notifier.bus.stand.register_stand_impl.db.Db;
import notifier.bus.stand.register_stand_impl.model.BusDto;
import notifier.bus.stand.register_stand_impl.model.StationDto;
import notifier.bus.stand.register_stand_impl.model.enums.DirectionEnum;
import notifier.bus.stand.register_stand_impl.model.pojo.Route;
import notifier.bus.stand.utils.DistanceHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by daniyar on 27/01/18.
 */
@Component
public class BusEmulationScheduler {

    @Autowired
    private Db db;

    @Autowired
    private SendNotifications firebase;

    Location[] currentLocations;
    Route[] routes;

    public int[] waypointCounters;
    boolean[] flags;

    public BusEmulationScheduler(Db db) {
        this.db = db;
        initCurrentLocations();
        initRoutes();
        waypointCounters = new int[this.db.buses.size()];
        flags = new boolean[db.buses.size()];
    }

    //logger
    private static final Logger log = LoggerFactory.getLogger(BusEmulationScheduler.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");


    private void initCurrentLocations() {
        this.currentLocations = new Location[db.buses.size()];
        for (int i = 0; i < db.buses.size(); i++) {
            this.currentLocations[i] = db.buses.get(i).from.location;
        }
    }

    private void initRoutes() {
        routes = new Route[db.buses.size()];
        for(int i = 0; i < db.buses.size(); i++) {
            routes[i] = db.buses.get(i).route;
        }
    }


    @Scheduled(cron = "*/6 * * * * *")
    public void emulation() {
        move();
    }

    private void move() {
        DistanceHelper helper = new DistanceHelper();

        for(int i = 0; i < db.buses.size(); i++) {
            BusDto busDto = db.buses.get(i);
            String currentStation = routes[i].waypoints[waypointCounters[i]].name;

            busDto.currentLocation = routes[i].waypoints[waypointCounters[i]].location;
            if(!flags[i]) {
                waypointCounters[i]++;
            } else {
                --waypointCounters[i];
            }

            int routesLength = routes[i].size();
            if(waypointCounters[i] == routesLength) {
                changeDirection(busDto);
                flags[i] = true;

                waypointCounters[i] = routesLength-1;
                busDto.currentLocation = routes[i].waypoints[waypointCounters[i]].location;
                busDto.recievedNotification = false;
            }

            if(waypointCounters[i] == -1) {
                flags[i] = false;
                changeDirection(busDto);
                waypointCounters[i] = 0;
                busDto.currentLocation = routes[i].waypoints[waypointCounters[i]].location;
                busDto.recievedNotification = false;
            }



            String nextStation = routes[i].waypoints[waypointCounters[i]].name;
            log.info("Bus " + busDto.number + ". Current station: " + currentStation+ ". Moving to: " + nextStation);
        }


        Location currentLocation = new Location(43.237027, 76.915544);
        for(int i = 0; i < db.buses.size(); i++) {
            BusDto busDto = db.buses.get(i);
            double distanceInMeters = helper.distance(currentLocation.latitude, currentLocation.longitude,
                    busDto.currentLocation.latitude, busDto.currentLocation.longitude, 'K') * 1000;

            log.info(distanceInMeters + "m with " + busDto.number);
            if(distanceInMeters <= 700 && !busDto.recievedNotification) {
                busDto.recievedNotification = true;
                firebase.send_FCM_Notification(
                        "fRAQosWL050:APA91bGSFtfbFdbq6ZKFunq63Z88_7tdOKrCktOrGH8udKKE8f5kZJWJQwy_DVlBhsoL6PO-BFqF-zaOK1-mTj1JJwRxitBbRwoGkfTXVHZ7uphhMylndeGpNLCXAwoqbPm48Z3uNF1m",
                        "AAAA9BPV9oU:APA91bEucwbWZ5Lh_RBywHRmzMAI6NsI66O1kd4dZ7jPqLWVguo2HSJG6-MdSqOBu1Gaew3aCOke3xkVk-fH_E_oTSttjIYv5kBz94sQWNWWxzfwOzbbXJAKqu3cMEpY6jTJL8nvVseL",
                        "Автобус " + busDto.number + " меньше 700м от вас!",
                        busDto.currentLocation
                );
            }
        }
    }

    private void changeDirection(BusDto busDto) {
        busDto.direction = busDto.direction == DirectionEnum.RIGHT ? DirectionEnum.LEFT : DirectionEnum.RIGHT;
    }
}
