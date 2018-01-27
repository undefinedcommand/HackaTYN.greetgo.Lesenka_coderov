package notifier.bus.stand.register_stand_impl.model.pojo;

public class Route {
    public Location[] waypoints;

    public Route(Location... waypoints) {
        this.waypoints = waypoints;
    }
}
