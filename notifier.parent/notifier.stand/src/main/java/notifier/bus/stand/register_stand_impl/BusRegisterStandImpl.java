package notifier.bus.stand.register_stand_impl;

import notifier.bus.controller.model.BusInfo;
import notifier.bus.controller.register.BusRegister;
import notifier.bus.stand.register_stand_impl.db.Db;
import notifier.bus.stand.register_stand_impl.model.BusDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
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
        List response  = db.buses.stream().filter(distinctByKey(BusDto::getNumber)).map(item -> {
            BusInfo busInfo = new BusInfo();
                    busInfo.from = item.from.name;
                    busInfo.to = item.to.name;
                    busInfo.number = item.number;
                    busInfo.id = item.id;
                    return busInfo;
                }
        ).collect(Collectors.toList());

        return response;
    }

    public <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

}
