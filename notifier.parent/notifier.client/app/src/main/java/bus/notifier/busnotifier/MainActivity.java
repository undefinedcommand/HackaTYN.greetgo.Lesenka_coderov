package bus.notifier.busnotifier;

import android.content.Context;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import bus.notifier.busnotifier.Adapters.BusAdapter;
import bus.notifier.busnotifier.Models.Bus;
import bus.notifier.busnotifier.Scenes.RingScene;
import bus.notifier.busnotifier.Scenes.MapScene;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationViewEx bottomNavigationMenu;
    private Fragment selectedScene = null;
    private FloatingSearchView floatingSearchView;
    private ListView listBuses;
    private BusAdapter busAdapter;
    private ArrayList<Bus> listBusesArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);

        Log.e("error", FirebaseInstanceId.getInstance().getToken() + "");

        generalConfig();

        initializeVariables();

        handleProperties();

        handleEvents();
    }

    // общие настройки
    private void generalConfig() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    // инициализируем переменные, модели
    private void initializeVariables() {
        bottomNavigationMenu = (BottomNavigationViewEx) findViewById(R.id.bottom_navigation_menu);
        floatingSearchView = (FloatingSearchView) findViewById(R.id.floating_search_view);
        listBuses = (ListView) findViewById(R.id.bus_list);
        busAdapter = new BusAdapter(getApplicationContext(), listBusesArray);
        listBuses.setAdapter(busAdapter);
        selectedScene = new MapScene();
        changeScene(selectedScene);
    }

    // свойства элементов
    private void handleProperties() {
        bottomNavigationMenu.enableAnimation(false);
        bottomNavigationMenu.enableItemShiftingMode(false);
        bottomNavigationMenu.enableShiftingMode(false);
        bottomNavigationMenu.setTextVisibility(false);
        bottomNavigationMenu.setIconSize(20, 20);
    }

    // обрабатываем события, фрагменты
    private void handleEvents() {
        bottomNavigationMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.map_item:
                        selectedScene = new MapScene();
                        break;
                    case R.id.reminder_item:
                        selectedScene = new RingScene();
                        break;
                }
                changeScene(selectedScene);
                return true;
            }
        });
    }

    // меняем фрагменты при событий
    private void changeScene(Fragment selectedScene) {
        FragmentTransaction changeSceneView = getSupportFragmentManager().beginTransaction();
        changeSceneView.replace(R.id.main_viewer, selectedScene);
        changeSceneView.commit();
    }

    private void searchView() {
        floatingSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, final String newQuery) {
                if (newQuery.equals("")) {

                }
            }
        });
    }
}
