package notifier.bus.stand.geo_simulation;

import notifier.bus.controller.model.pojo.Location;
import notifier.bus.stand.register_stand_impl.model.StationDto;
import notifier.bus.stand.register_stand_impl.model.pojo.Route;

public class GPSsimulator {
    public static final double initialLatitude = 48.138083;
    public static final double initialLongitude = 11.561102;
    public static final double SIMULATOR_MOVEMENT_SPEED = 0.000015; // ~0.05m - 0.1m per step
    public static final double ARRIVAL_RADIUS_IN_KM = 0.05 / 1000;  // 0.05m

    public Location currentLocation = new Location(initialLatitude, initialLongitude);

    public int waypointCounter = 0;

    public Route simulatedRoute = new Route(
            new StationDto("123", 48.137413, 11.561020),
            new StationDto("3456", 48.137370, 11.564539),
            new StationDto("aaa", 48.137449, 11.565000),
            new StationDto("bbb", 48.137578, 11.565311));

    public void move(){
        Location nextWaypoint = simulatedRoute.waypoints[waypointCounter].location;
        if (GeoHelper.calcGeoDistanceInKm(currentLocation, nextWaypoint) < ARRIVAL_RADIUS_IN_KM) {
            waypointCounter++;
            if (waypointCounter > simulatedRoute.waypoints.length-1) {
                currentLocation = new Location(initialLatitude, initialLongitude);
                waypointCounter = 0;
            }
            nextWaypoint = simulatedRoute.waypoints[waypointCounter].location;
        }
        System.out.println("Moving to next wayPoint"  + ". Distance = " + GeoHelper.calcGeoDistanceInKm(currentLocation, nextWaypoint) * 1000 + "m");
        double angle = GeoHelper.calcAngleBetweenGeoLocationsInRadians(currentLocation, nextWaypoint);
        double newLat = currentLocation.latitude + Math.sin(angle) * SIMULATOR_MOVEMENT_SPEED;
        double newLon = currentLocation.longitude + Math.cos(angle) * SIMULATOR_MOVEMENT_SPEED;
        currentLocation = new Location(newLat, newLon);
    }

    public static void main(String[] args) {
        GPSsimulator gpsSimulator = new GPSsimulator();
        for (int i = 0; i < 500; i++) { // testing 500 steps of the simulator
            gpsSimulator.move();
        }
    }
}
