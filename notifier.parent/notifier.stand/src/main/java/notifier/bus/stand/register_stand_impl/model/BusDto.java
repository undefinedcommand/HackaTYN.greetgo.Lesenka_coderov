package notifier.bus.stand.register_stand_impl.model;

import notifier.bus.controller.model.pojo.Location;
import notifier.bus.stand.enums.DirectionEnum;
import notifier.bus.stand.register_stand_impl.model.pojo.Route;

/**
 * Created by daniyar on 27/01/18.
 */
public class BusDto {
    public int id;
    public StationDto from;
    public StationDto to;

    public int number;
    public DirectionEnum direction = DirectionEnum.RIGHT;
    public Route route;
    public Location currentLocation;

    public int getNumber() {
        return number;
    }
}
