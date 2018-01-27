package notifier.bus.stand.register_stand_impl.db;

import notifier.bus.controller.model.pojo.Location;
import notifier.bus.stand.register_stand_impl.model.BusDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Db {

    public List<BusDto> buses = new ArrayList();

    public Db() {
        init();
    }

    private void init() {
        BusDto b1 = new BusDto();
        b1.from = new Location(10.11, 10.22);
        b1.to = new Location(20.22, 40.14);
        b1.number = 92;

        BusDto b2 = new BusDto();
        b2.from = new Location(10.11, 30.21);
        b2.to = new Location(1.11, 1.22);
        b2.number = 100;

        BusDto b3 = new BusDto();
        b3.to = new Location(10.11, 14.400);
        b3.from = new Location(10.40, 15.111);
        b3.number = 112;

        BusDto b4 = new BusDto();
        b4.from = new Location(16.13, 13.33);
        b4.to = new Location(18.33, 15.22);
        b4.number = 18;

        buses.add(b1);
        buses.add(b2);
        buses.add(b3);
        buses.add(b4);
    }
}
