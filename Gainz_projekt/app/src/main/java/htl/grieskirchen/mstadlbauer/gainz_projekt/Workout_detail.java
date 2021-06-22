package htl.grieskirchen.mstadlbauer.gainz_projekt;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Workout_detail extends AppCompatActivity {

    private List<Uebungen> uebungen = new ArrayList<>();
    private String workout_name;
    private ListView listView;


    //erfüllt die gleiche Aufgabe
    Addworkout_listview_adapter adapter;
    Button start;
    Button end;
    LocalTime workoutstart = null;
    LocalTime workoutende = null;
    LocalDate lasttimedone = null;
    boolean clickedonstart = false;

    Workout workout1;
    Workout_detail workout_detail = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_detail);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String workoutstring = bundle.getString("Workout");
        String[] workoutParts = workoutstring.split(";");
        workout1 = new Workout(workoutParts[0]);
        if (!workoutParts[1].isEmpty()) {
            workout1.setLastdate(workoutParts[1]);
        }
        workout1.setLat(Double.parseDouble(workoutParts[2]));
        workout1.setLon(Double.parseDouble(workoutParts[3]));
        workout1.setAddresse(workoutParts[4]);

        for (int i = 5; i < workoutParts.length; i++) {
            String[] workoutUebung = workoutParts[i].split(",");
            workout1.addUebung(new Uebungen(workoutUebung[0], Integer.parseInt(workoutUebung[1]), Integer.parseInt(workoutUebung[2])));
        }


        TextView name_workout = findViewById(R.id.activity_workout_detail_name);
        name_workout.setText(workout1.getName());


        initlv();
        initButtons();


    }

    private void initlv() {
        adapter = new Addworkout_listview_adapter(this, R.layout.addworkout_listview_adapter, uebungen);
        listView = findViewById(R.id.activity_workout_detail_listview);
        listView.setAdapter(adapter);
        uebungen.addAll(workout1.getUebungen());
        adapter.notifyDataSetChanged();
    }

    private void initButtons() {
        start = findViewById(R.id.activity_workout_detail_start);
        end = findViewById(R.id.activity_workout_detail_abbrechen);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickedonstart == false) {
                    clickedonstart = true;
                    start.setText("Beenden");
                    workoutstart = LocalTime.now();
                    lasttimedone = LocalDate.now();
                } else {
                    workoutende = LocalTime.now();
                    String dauer = "";
                    dauer += "Das Workout hat : " + String.valueOf(workoutende.getHour() - workoutstart.getHour()) + " Stunden " + String.valueOf(workoutende.getMinute() - workoutstart.getMinute()) + " Minuten gedauert";
                    new AlertDialog.Builder(workout_detail).setMessage(dauer + "\n Wollen Sie das Workout stoppen").setPositiveButton("Ja", ((dialog, which) -> {
                        handleDialog();
                    })).setNegativeButton("Nein", null).show();
                }

            }
        });

        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(workout_detail, MainActivity.class);
                intent.putExtra("workout", workout1.toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private void handleDialog() {
        start.setText("Starten");
        clickedonstart = false;
        workout1.setLastdate(LocalDate.now().toString());
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("workout", workout1.toString());
        setResult(RESULT_OK, intent);
        finish();
    }

}