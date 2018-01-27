package bus.notifier.busnotifier;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import bus.notifier.busnotifier.Scenes.FavoritesScene;
import bus.notifier.busnotifier.Scenes.MapScene;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationViewEx bottomNavigationMenu;
    private Fragment selectedScene = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeVariables();

        handleProperties();

        handleEvents();
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
                    case R.id.favorites_item:
                        selectedScene = new FavoritesScene();
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
