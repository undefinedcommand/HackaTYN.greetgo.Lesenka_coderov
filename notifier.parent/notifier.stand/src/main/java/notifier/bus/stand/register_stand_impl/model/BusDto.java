package notifier.bus.stand.register_stand_impl.model;

import notifier.bus.stand.register_stand_impl.model.pojo.Location;
import notifier.bus.stand.register_stand_impl.model.pojo.Route;

/**
 * Created by daniyar on 27/01/18.
 */
public class BusDto {
    public StationDto from;
    public StationDto to;

    public int number;
    public Route route;
    public Location currentLocation;
}
