package bus.notifier.busnotifier.Scenes;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bus.notifier.busnotifier.R;

public class FavoritesScene extends Fragment {

    public FavoritesScene() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorites_scene, container, false);
    }

}
