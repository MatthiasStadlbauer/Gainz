package htl.grieskirchen.mstadlbauer.gainz_projekt;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class AddWorkout extends AppCompatActivity {

    private FloatingActionButton addExercise;
    private Button speichern;
    private Button cancel;
    List<Uebungen> workout = new ArrayList<>();
    Addworkout_listview_adapter adapter;
    ListView uebungenlistview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_workout);

        initButtons();

        uebungenlistview = findViewById(R.id.addWorkout_listview);
        adapter = new Addworkout_listview_adapter(this, R.layout.addworkout_listview_adapter, workout);
        uebungenlistview.setAdapter(adapter);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initButtons(){
        addExercise = findViewById(R.id.addexcercise_floatingbutton);
        addExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View vDialog = getLayoutInflater().inflate(R.layout.addworkout_excercises, null);
                new AlertDialog.Builder(AddWorkout.this).setMessage("Übung").setCancelable(false).setView(vDialog).setPositiveButton("Add", ((dialog, which) -> handleDialog(vDialog))).setNegativeButton("Cancle", null).show();
            }
        });

        speichern = findViewById(R.id.save_workout_btn);
        speichern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name_workout = findViewById(R.id.name_workout);
                if(!name_workout.getText().toString().isEmpty()){
                    String name = name_workout.getText().toString();
                    Workout workout = new Workout(name, AddWorkout.this.workout);

                    Intent intent = new Intent(AddWorkout.this, MainActivity.class);
                    intent.putExtra("workout", workout.toString());
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

        cancel = findViewById(R.id.cancel_workout_btn);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                /*Intent intent = new Intent(AddWorkout.this, MainActivity.class);
                startActivity(intent);*/
            }
        });

    }

    public void handleDialog(View vDialog){
        EditText nameed = vDialog.findViewById(R.id.addworkout_excercise_name);
        EditText saetzeed = vDialog.findViewById(R.id.addworkout_excercise_saetze);
        EditText whed = vDialog.findViewById(R.id.addworkout_excercise_wiederholungen);
        String name;
        String saetze;
        String wh;
        if(!nameed.getText().toString().isEmpty() && !saetzeed.getText().toString().isEmpty()&&!whed.getText().toString().isEmpty()) {
            name = nameed.getText().toString();
            saetze = saetzeed.getText().toString();
            wh = whed.getText().toString();
            workout.add(new Uebungen(name, Integer.valueOf(wh), Integer.valueOf(saetze)));
            adapter.notifyDataSetChanged();
        }
        else{
            Toast.makeText(AddWorkout.this, "Alle Felder müssen gefüllt sein", Toast.LENGTH_SHORT).show();
        }
    }



}