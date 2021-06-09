package htl.grieskirchen.mstadlbauer.gainz_projekt;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Workout_edit extends AppCompatActivity {

    private FloatingActionButton addExercise;
    private Button speichern;
    private Button cancel;
    private List<Uebungen> workout = new ArrayList<>();
    private Addworkout_listview_adapter adapter;
    private ListView uebungenlistview;
    private EditText name;
    private Workout original;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_edit);

        initview();
        initbuttons();

        datenauslesen();
    }

    private void initview() {
        uebungenlistview = findViewById(R.id.edit_listView);
        adapter = new Addworkout_listview_adapter(this, R.layout.addworkout_listview_adapter, workout);
        uebungenlistview.setAdapter(adapter);
        uebungenlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final View vDialog = getLayoutInflater().inflate(R.layout.addworkout_excercises, null);
                EditText nameed = vDialog.findViewById(R.id.addworkout_excercise_name);
                EditText saetzeed = vDialog.findViewById(R.id.addworkout_excercise_saetze);
                EditText whed = vDialog.findViewById(R.id.addworkout_excercise_wiederholungen);
                nameed.setText(workout.get(position).getName());
                saetzeed.setText(String.valueOf(workout.get(position).getSaetze()));
                whed.setText(String.valueOf(workout.get(position).getWh()));
                new AlertDialog.Builder(Workout_edit.this).setMessage("Übung").setCancelable(false).setView(vDialog).setPositiveButton("Save", ((dialog, which) -> editDialog(vDialog, position))).setNegativeButton("Cancle", null).show();
            }
        });
    }

    private void initbuttons() {
        addExercise = findViewById(R.id.addexcercise_floatingbutton_edit);
        addExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View vDialog = getLayoutInflater().inflate(R.layout.addworkout_excercises, null);
                new AlertDialog.Builder(Workout_edit.this).setMessage("Übung").setCancelable(false).setView(vDialog).setPositiveButton("Add", ((dialog, which) -> handleDialog(vDialog))).setNegativeButton("Cancle", null).show();

            }
        });


        speichern = findViewById(R.id.save_workout_btn_edit);
        speichern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText name_workout = findViewById(R.id.edit_nameWorkout);
                if (!name_workout.getText().toString().isEmpty()) {
                    String name = name_workout.getText().toString();
                    Workout workout = new Workout(name, Workout_edit.this.workout);
                    Intent intent = new Intent(Workout_edit.this, MainActivity.class);
                    intent.putExtra("workout", workout.toString());
                    setResult(RESULT_OK, intent);
                    finish();
                }
                Toast.makeText(Workout_edit.this, "Geben Sie einen Name für ihr Workout ein", Toast.LENGTH_SHORT);
            }
            //TODO zurück übergeben

        });
        cancel = findViewById(R.id.cancel_workout_btn_edit);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Workout_edit.this, MainActivity.class);
                intent.putExtra("workout", original.toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        name = findViewById(R.id.edit_nameWorkout);
    }

    private void datenauslesen() {

        Workout workout1;
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String workoutstring = bundle.getString("Workout");
        String[] workoutParts = workoutstring.split(";");
        workout1 = new Workout(workoutParts[0]);
        if (!workoutParts[1].isEmpty()) {
            workout1.setLastdate(workoutParts[1]);
        }
        for (int i = 2; i < workoutParts.length; i++) {
            String[] workoutUebung = workoutParts[i].split(",");
            workout1.addUebung(new Uebungen(workoutUebung[0], Integer.parseInt(workoutUebung[1]), Integer.parseInt(workoutUebung[2])));
        }
        workout.addAll(workout1.getUebungen());
        name.setText(workout1.getName());
        original = workout1;
    }

    public void editDialog(View vDialog, int postion) {
        EditText nameed = vDialog.findViewById(R.id.addworkout_excercise_name);
        EditText saetzeed = vDialog.findViewById(R.id.addworkout_excercise_saetze);
        EditText whed = vDialog.findViewById(R.id.addworkout_excercise_wiederholungen);
        String name;
        String saetze;
        String wh;
        if (!nameed.getText().toString().isEmpty() && !saetzeed.getText().toString().isEmpty() && !whed.getText().toString().isEmpty()) {
            name = nameed.getText().toString();
            saetze = saetzeed.getText().toString();
            wh = whed.getText().toString();
            workout.add(new Uebungen(name, Integer.valueOf(wh), Integer.valueOf(saetze)));
            workout.remove(postion);
            adapter.notifyDataSetChanged();
        } else {
            Toast.makeText(Workout_edit.this, "Alle Felder müssen gefüllt sein", Toast.LENGTH_SHORT).show();
        }
    }

    public void handleDialog(View vDialog) {
        EditText nameed = vDialog.findViewById(R.id.addworkout_excercise_name);
        EditText saetzeed = vDialog.findViewById(R.id.addworkout_excercise_saetze);
        EditText whed = vDialog.findViewById(R.id.addworkout_excercise_wiederholungen);
        String name;
        String saetze;
        String wh;
        if (!nameed.getText().toString().isEmpty() && !saetzeed.getText().toString().isEmpty() && !whed.getText().toString().isEmpty()) {
            name = nameed.getText().toString();
            saetze = saetzeed.getText().toString();
            wh = whed.getText().toString();
            workout.add(new Uebungen(name, Integer.valueOf(wh), Integer.valueOf(saetze)));
            adapter.notifyDataSetChanged();
        } else {
            Toast.makeText(Workout_edit.this, "Alle Felder müssen gefüllt sein", Toast.LENGTH_SHORT).show();
        }
    }
}