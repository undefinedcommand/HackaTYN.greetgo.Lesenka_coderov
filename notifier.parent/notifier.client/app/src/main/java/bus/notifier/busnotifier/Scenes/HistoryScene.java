package bus.notifier.busnotifier.Scenes;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bus.notifier.busnotifier.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryScene extends Fragment {


    public HistoryScene() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history_scene, container, false);
    }

}
