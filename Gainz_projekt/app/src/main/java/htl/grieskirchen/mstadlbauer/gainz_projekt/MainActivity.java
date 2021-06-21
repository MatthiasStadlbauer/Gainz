package htl.grieskirchen.mstadlbauer.gainz_projekt;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /**
     * BottomNavigationBar
     */
    private BottomNavigationView bottomNavigationView;
    private SharedPreferences.OnSharedPreferenceChangeListener preferenceChangeListener;


    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        preferenceChangeListener = (sharedPrefs, key) -> prefernceChanged(sharedPrefs, key);
        prefs.registerOnSharedPreferenceChangeListener(preferenceChangeListener);
        //initialisierung der Views
        initViews();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel1 = new NotificationChannel(Channels.CHANNEL_1_ID, "Standort", NotificationManager.IMPORTANCE_DEFAULT);
            channel1.setDescription("In der Nähe eines Workouts");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
        }
        startService();
        //stopNotifications();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_preferences) {
            Intent intent = new Intent(this, MySettingsActivity.class);
            startActivityForResult(intent, 102);
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * initialisierungen der Variablen
     */
    private void initViews() {
        //bottom NavigationView
        this.bottomNavigationView = findViewById(R.id.main_bottomnavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_container, new Home_fragment()).commit();
    }

    /**
     * Wenn man auf die einzelenen Buttons in der BottomNavigationbar
     */
    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.itemmainactivity:
                    selectedFragment = new Home_fragment();
                    break;
                case R.id.itemdaten:
                    selectedFragment = new Daten_fragment();
                    break;
                case R.id.itemhistory:
                    selectedFragment = new History_fragment();
                    break;
                case R.id.itemchallenges:
                    selectedFragment = new ChallengeView_fragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_container, selectedFragment).commit();
            return true;
        }
    };

    public void startService() {
        if (prefs.getBoolean("notification_switch_preference", true)) {
            Intent service = new Intent(this, NotificationService.class);
            startService(service);
        }
    }

    private void prefernceChanged(SharedPreferences sharedPrefs, String key){
        if(sharedPrefs.getBoolean(key, true)){
            startService();
        }
        else{
            stopNotifications();
        }
    }

    private void stopNotifications() {
        if (!prefs.getBoolean("notification_switch_preference", true)) {
            Intent service = new Intent(this, NotificationService.class);
            stopService(service);
        }
    }
}