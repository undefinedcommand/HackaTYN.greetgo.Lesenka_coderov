package notifier.bus.stand.register_stand_impl;

import notifier.bus.controller.model.BusInfo;
import notifier.bus.controller.register.BusRegister;
import notifier.bus.stand.register_stand_impl.db.Db;
import notifier.bus.stand.register_stand_impl.model.BusDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by daniyar on 27/01/18.
 */
@Service
public class BusRegisterStandImpl implements BusRegister{

    private Db db;

    public BusRegisterStandImpl(Db db) {
        this.db = db;
    }

    @Override
    public List<BusInfo> getBusesList() {
        List<BusDto> busses = db.buses;

        List response  = busses.stream().map(item -> {
                    BusInfo busInfo = new BusInfo();
                    busInfo.from = item.from;
                    busInfo.to = item.to;
                    busInfo.number = item.number;
                    return busInfo;
                }
        ).collect(Collectors.toList());

        return response;
    }
}
