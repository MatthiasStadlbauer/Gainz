package htl.grieskirchen.mstadlbauer.gainz_projekt;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;


public class Home_fragment extends Fragment {

    List<Workout> workoutList = new ArrayList<>();

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

}