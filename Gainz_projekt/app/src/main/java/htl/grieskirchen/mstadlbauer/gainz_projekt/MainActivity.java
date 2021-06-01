package htl.grieskirchen.mstadlbauer.gainz_projekt;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity  {

    /**
     * BottomNavigationBar
     */
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initialisierung der Views
        initViews();
    }

    /**
     * initialisierungen der Variablen
     */
    private void initViews()
    {
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
                case R.id.itemmainactivity: selectedFragment = new Home_fragment();
                   break;
                case R.id.itemdaten: selectedFragment = new Daten_fragment();
                    break;
                case R.id.itemhistory:selectedFragment = new History_fragment();
                    break;
                case R.id.itemchallenges: selectedFragment = new ChallengeView_fragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_container, selectedFragment).commit();
            return true;
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 28) {
            if(resultCode == RESULT_OK) {
                Bundle bundle = data.getExtras();
                String workout = bundle.getString("workout");

                String[] workoutParts = workout.split(";");
                Workout workout1 = new Workout(workoutParts[0]);

                if(!workoutParts[1].isEmpty()) {
                    workout1.setLastdate(workoutParts[1]);
                }

                for(int i = 2; i < workoutParts.length; i++) {
                    String[] workoutUebung = workoutParts[i].split(",");
                    workout1.addUebung(new Uebungen(workoutUebung[0], Integer.parseInt(workoutUebung[1]), Integer.parseInt(workoutUebung[2])));
                }
            }
        }
    }

}