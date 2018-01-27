package bus.notifier.busnotifier;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import bus.notifier.busnotifier.Scenes.RingScene;
import bus.notifier.busnotifier.Scenes.MapScene;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationViewEx bottomNavigationMenu;
    private Fragment selectedScene = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);

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
}
