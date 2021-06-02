package htl.grieskirchen.mstadlbauer.gainz_projekt;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;


public class Home_fragment extends Fragment {

    List<Workout> workoutList = new ArrayList<>();
    Home_fragment_listview_adapter adapter;
    ListView home_fragemtn_listview;

    /**
     * Button für Hinzufügen der Workouts
     */
    FloatingActionButton addWorkoutButton;


    /**
     * Home fragment
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_fragment, container, false);
        initButtons(view);
        adapter = new Home_fragment_listview_adapter(view.getContext(), R.layout.home_fragment_listview_adapter, workoutList);
        home_fragemtn_listview = view.findViewById(R.id.main_listview);
        home_fragemtn_listview.setAdapter(adapter);
        return view;
    }

    /**
     * initialisierung der Buttons
     */
    private void initButtons(View view) {
        addWorkoutButton = view.findViewById(R.id.main_addworkout_floatingbutton);
        addWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddWorkout.class);
                startActivityForResult(intent, 28);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
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

                    workoutList.add(workout1);
                    adapter.notifyDataSetChanged();
                }
            }
    }
}