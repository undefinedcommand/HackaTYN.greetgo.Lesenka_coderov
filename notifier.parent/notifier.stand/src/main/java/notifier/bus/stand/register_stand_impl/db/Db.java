package notifier.bus.stand.register_stand_impl.db;

import notifier.bus.stand.register_stand_impl.model.BusDto;
import notifier.bus.stand.register_stand_impl.model.StationDto;
import notifier.bus.stand.register_stand_impl.model.pojo.Location;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Db {

    public List<BusDto> buses = new ArrayList();
    public Map<String, StationDto> stations = new HashMap<>();
    public List<BusDto> activeBusses = new ArrayList<>();


    public static final String s1 = "Абая-Утеген Батыра";
    public static final String s2 = "Абая-Большая Алматинка";
    public static final String s3 = "Абая-Тилендиева";
    public static final String s4 = "Абая-Брусиловского";
    public static final String s5 = "Абая-Тургут озала";
    public static final String s6 = "Абая-Розыбакиева";
    public static final String s7 = "Абая-Гагарина";
    public static final String s8 = "Абая-Жорокова";
    public static final String s9 = "Абая-Ауэзова";
    public static final String s10 = "Абая-Манаса";
    public static final String s11 = "Абая-Байзака";
    public static final String s12 = "Байзака-Сатпаева";
    public static final String s13 = "Тайская лавка Алматы";
    public static final String s14 = "Сатпаева Шагабутдинова";


    public Db() {
        initStations();
        initBuses();
        initActiveBusses();
    }

    private void initActiveBusses() {
//        stations.put()
    }

    private void initStations() {
        stations.put(s1, new StationDto(s1, new Location(43.231706, 76.869333)));
        stations.put(s2, new StationDto(s2, new Location(43.233208, 76.871237)));
        stations.put(s3, new StationDto(s3, new Location(43.236048, 76.875841)));
        stations.put(s4, new StationDto(s4, new Location(43.237084, 76.878696)));
        stations.put(s5, new StationDto(s5, new Location(43.237766, 76.882901)));
        stations.put(s6, new StationDto(s6, new Location(43.238182, 76.889821)));
        stations.put(s7, new StationDto(s7, new Location(43.238627, 76.894483)));
        stations.put(s8, new StationDto(s8, new Location(43.238891, 76.899594)));
        stations.put(s9, new StationDto(s9, new Location(43.239252, 76.904143)));
        stations.put(s10, new StationDto(s10, new Location(43.239574, 76.908610)));
        stations.put(s11, new StationDto(s11, new Location(43.240060, 76.915315)));
        stations.put(s12, new StationDto(s12, new Location(43.237228, 76.913931)));
        stations.put(s13, new StationDto(s13, new Location(43.236126, 76.916463)));
        stations.put(s14, new StationDto(s14, new Location(43.236707, 76.922857)));
    }

    private void initBuses() {

        BusDto b1 = new BusDto();
        b1.from = stations.get(s1);
        b1.to = stations.get(s13);
        b1.number = 92;

        BusDto b1r = new BusDto();
        b1r.from = stations.get(s13);
        b1r.to = stations.get(s1);
        b1r.number = 92;

        BusDto b2 = new BusDto();
        b2.from = stations.get(s3);
        b2.to = stations.get(s10);
        b2.number = 100;

//        BusDto b3 = new BusDto();
//        b3.to = new Location(10.11, 14.400);
//        b3.from = new Location(10.40, 15.111);
//        b3.number = 112;
//
//        BusDto b4 = new BusDto();
//        b4.from = new Location(16.13, 13.33);
//        b4.to = new Location(18.33, 15.22);
//        b4.number = 18;

        buses.add(b1);
        buses.add(b2);
//        buses.add(b3);
//        buses.add(b4);
    }
}
