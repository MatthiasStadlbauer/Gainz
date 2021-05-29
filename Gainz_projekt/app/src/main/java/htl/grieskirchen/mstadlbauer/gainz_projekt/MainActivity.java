package htl.grieskirchen.mstadlbauer.gainz_projekt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity  {

    /**
     * BottomNavigatioBar
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

}