package notifier.bus.stand.scheduler;

import notifier.bus.controller.model.pojo.Location;
import notifier.bus.stand.geo_simulation.GeoHelper;
import notifier.bus.stand.register_stand_impl.db.Db;
import notifier.bus.stand.register_stand_impl.model.BusDto;
import notifier.bus.stand.register_stand_impl.model.StationDto;
import notifier.bus.stand.register_stand_impl.model.pojo.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by daniyar on 27/01/18.
 */
@Component
public class BusEmulationScheduler {

    @Autowired
    private Db db;

    Location[] currentLocations;
    Route[] routes;

    public int[] waypointCounters;

    public BusEmulationScheduler(Db db) {
        this.db = db;
        initCurrentLocations();
        initRoutes();
        waypointCounters = new int[this.db.buses.size()];
    }

    //logger
    private static final Logger log = LoggerFactory.getLogger(BusEmulationScheduler.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    //params

    public static final double SIMULATOR_MOVEMENT_SPEED = 0.000015; // ~0.05m - 0.1m per step
    public static final double ARRIVAL_RADIUS_IN_KM = 0.05 / 1000;  // 0.05m


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

    public void moveBuses(){
        System.out.println();
        List<BusDto> busDtoList = db.buses;
        for(int i = 0; i < busDtoList.size(); i++) {
            Location nextWaypoint = routes[i].waypoints[waypointCounters[i]].location;
            String nextLocation = routes[i].waypoints[waypointCounters[i]].name;
            if (GeoHelper.calcGeoDistanceInKm(currentLocations[i], nextWaypoint) < ARRIVAL_RADIUS_IN_KM) {
                waypointCounters[i]++;
                if (waypointCounters[i] > routes[i].waypoints.length-1) {
                    currentLocations[i] = new Location(currentLocations[i].latitude, currentLocations[i].longitude);
                    waypointCounters[i] = 0;
                }
                nextWaypoint = routes[i].waypoints[waypointCounters[i]].location;
                nextLocation = routes[i].waypoints[waypointCounters[i]].name;
            }

            log.info("Bus number: " + busDtoList.get(i).number + " Moving to " + nextLocation + ". Distance = " + GeoHelper.calcGeoDistanceInKm(currentLocations[i], nextWaypoint) * 1000 + "m");
            double angle = GeoHelper.calcAngleBetweenGeoLocationsInRadians(currentLocations[i], nextWaypoint);
            double newLat = currentLocations[i].latitude + Math.sin(angle) * SIMULATOR_MOVEMENT_SPEED;
            double newLon = currentLocations[i].longitude + Math.cos(angle) * SIMULATOR_MOVEMENT_SPEED;
            currentLocations[i] = new Location(newLat, newLon);

        }
    }

    @Scheduled(cron = "*/1 * * * * *")
    public void emulation() {
        moveBuses();
    }
}
