package notifier.bus.stand.register_stand_impl.db;

import notifier.bus.controller.model.pojo.Location;
import notifier.bus.stand.enums.DirectionEnum;
import notifier.bus.stand.register_stand_impl.model.BusDto;
import notifier.bus.stand.register_stand_impl.model.StationDto;
import notifier.bus.stand.register_stand_impl.model.pojo.Route;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Db {

    public List<BusDto> buses = new ArrayList();
    public Map<String, StationDto> stations = new HashMap<>();


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


    public static final String q1 = "Жандосова-Сулеймена";
    public static final String q2 = "Жандосова-Навои";
    public static final String q3 = "Жандосова-Темирязева";
    public static final String q4 = "Темирязева-Розыбакиева";
    public static final String q5 = "Темирязева-Жарокова";
    public static final String q6 = "Жарокова-Бухар жырау";
    public static final String q7 = "Бухар Жырау-Мусурепова";

    public Db() {
        initStations();
        initBuses();
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

        stations.put(q1, new StationDto(q1, new Location(43.217097, 76.876108)));
        stations.put(q2, new StationDto(q2, new Location(43.220058, 76.880488)));
        stations.put(q3, new StationDto(q3, new Location(43.224471, 76.887540)));
        stations.put(q4, new StationDto(q4, new Location(43.224660, 76.891672)));
        stations.put(q5, new StationDto(q5, new Location(43.225338, 76.901325)));
        stations.put(q6, new StationDto(q6, new Location(43.230512, 76.900526)));
        stations.put(q7, new StationDto(q7, new Location(43.231703, 76.918874)));
    }

    private void initBuses() {

        BusDto b1 = new BusDto();
        b1.from = stations.get(s1);
        b1.to = stations.get(s13);
        b1.id = 1;
        b1.number = 92;

        BusDto b1r = new BusDto();
        b1r.from = stations.get(s13);
        b1r.to = stations.get(s1);
        b1r.number = 92;
        b1r.direction = DirectionEnum.LEFT;
        b1r.id = 2;

        BusDto b2 = new BusDto();
        b2.from = stations.get(q1);
        b2.to = stations.get(q7);
        b2.number = 100;
        b2.id = 3;

        BusDto b2r = new BusDto();
        b2r.from = stations.get(q7);
        b2r.to = stations.get(q1);
        b2r.number = 100;
        b2r.id = 4;


        Route r1 = new Route(
                stations.get(s1),
                stations.get(s2),
                stations.get(s3),
                stations.get(s4),
                stations.get(s5),
                stations.get(s6),
                stations.get(s7),
                stations.get(s8),
                stations.get(s9),
                stations.get(s10),
                stations.get(s11),
                stations.get(s12),
                stations.get(s13)
                );

        Route r2 = new Route(
                stations.get(q1),
                stations.get(q2),
                stations.get(q3),
                stations.get(q4),
                stations.get(q5),
                stations.get(q6),
                stations.get(q7)
        );

        Route r1r = new Route(r1.size());
        Route r2r = new Route(r2.size());

        System.arraycopy(r1.waypoints, 0, r1r.waypoints, 0, r1.size());
        System.arraycopy(r2.waypoints, 0, r2r.waypoints, 0, r2.size());

        Collections.reverse(Arrays.asList(r1r.waypoints));
        Collections.reverse(Arrays.asList(r2r.waypoints));

        b1.route = r1;
        b1r.route = r1r;

        b2.route = r2;
        b2r.route = r2r;

        b1.route.print();
        b1r.route.print();
        b2.route.print();
        b2r.route.print();
    }
}
