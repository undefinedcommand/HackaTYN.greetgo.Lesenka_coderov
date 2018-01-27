package notifier.bus.stand.register_stand_impl.model;

import notifier.bus.controller.model.pojo.Location;

public class StationDto {
    public String name;
    public Location location;

    public StationDto(String name, Location location) {
        this.name = name;
        this.location = location;
    }

    public StationDto(String name, double lat, double lon) {
        this.name = name;
        this.location = new Location(lat, lon);
    }

}
