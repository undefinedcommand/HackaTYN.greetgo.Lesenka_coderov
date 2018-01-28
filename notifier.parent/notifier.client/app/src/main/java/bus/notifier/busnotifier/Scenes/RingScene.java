package bus.notifier.busnotifier.Scenes;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

import bus.notifier.busnotifier.Adapters.BusAdapter;
import bus.notifier.busnotifier.Models.Bus;
import bus.notifier.busnotifier.R;

public class RingScene extends Fragment {

    private ListView listBuses;
    private BusAdapter busAdapter;
    private ArrayList<Bus> listBusesArray = new ArrayList<>();

    public RingScene() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ring_scene, container, false);
        listBusesArray.add(new Bus("42", 43.2f, 76.5f, "Байзакова", "Сайран"));
        listBusesArray.add(new Bus("42", 43.2f, 76.5f, "Байзакова", "Сайран"));
        listBuses = (ListView) view.findViewById(R.id.bus_list);
        busAdapter = new BusAdapter(getContext(), listBusesArray);
        listBuses.setAdapter(busAdapter);
        return view;
    }

}
