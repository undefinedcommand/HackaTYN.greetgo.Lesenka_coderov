package notifier.bus.stand.register_stand_impl.model.pojo;

import notifier.bus.stand.register_stand_impl.model.StationDto;

public class Route {
    public StationDto[] waypoints;

    public Route(StationDto... waypoints) {
        this.waypoints = waypoints;
    }

    public Route(int size) {
        waypoints = new StationDto[size];
    }

    public int size() {
        return waypoints.length;
    }


    public void print() {
        for (StationDto s: waypoints) {
            System.out.print(s.name + " ");
        }
    }

}
