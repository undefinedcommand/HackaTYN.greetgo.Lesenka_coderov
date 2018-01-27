package notifier.bus.stand.register_stand_impl.model.pojo;

import notifier.bus.controller.model.pojo.Location;

public class Route {
    public Location[] waypoints;

    public Route(Location... waypoints) {
        this.waypoints = waypoints;
    }
}
