package htl.grieskirchen.mstadlbauer.gainz_projekt;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ComponentActivity;
import androidx.fragment.app.Fragment;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;


public class Home_fragment extends Fragment {

    private List<Workout> workoutList = new ArrayList<>();
    private Home_fragment_listview_adapter adapter;
    private ListView home_fragment_listview;

    /**
     * Button für Hinzufügen der Workouts
     */
    private FloatingActionButton addWorkoutButton;

    /**
     * Home fragment
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_fragment, container, false);
        initButtons(view);
        initListview(view);
        return view;
    }

    /**
     * initialisierung der Listview
     */
    private void initListview(View view) {
        adapter = new Home_fragment_listview_adapter(view.getContext(), R.layout.home_fragment_listview_adapter, workoutList);
        home_fragment_listview = view.findViewById(R.id.main_listview);
        home_fragment_listview.setAdapter(adapter);
        home_fragment_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), Workout_detail.class);
                intent.putExtra("Workout", workoutList.get(position).toString());
                workoutList.remove(position);
                startActivityForResult(intent, 32);
            }
        });
        registerForContextMenu(home_fragment_listview);
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

    /**
     * Rückgabe der AddWorkout requestCode = 28
     *
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 28) {
            if (resultCode == RESULT_OK) {
                Bundle bundle = data.getExtras();
                String workout = bundle.getString("workout");

                String[] workoutParts = workout.split(";");
                Workout workout1 = new Workout(workoutParts[0]);

                if (!workoutParts[1].isEmpty()) {
                    workout1.setLastdate(workoutParts[1]);
                }

                workout1.setLat(Double.parseDouble(workoutParts[2]));
                workout1.setLon(Double.parseDouble(workoutParts[3]));

                for (int i = 5; i < workoutParts.length; i++) {
                    String[] workoutUebung = workoutParts[i].split(",");
                    workout1.addUebung(new Uebungen(workoutUebung[0], Integer.parseInt(workoutUebung[1]), Integer.parseInt(workoutUebung[2])));
                }

                workoutList.add(workout1);
                adapter.notifyDataSetChanged();
            }
        }
        if(requestCode == 32){
            if(resultCode == RESULT_OK){
                Bundle bundle = data.getExtras();
                String workout = bundle.getString("workout");

                String[] workoutParts = workout.split(";");
                Workout workout1 = new Workout(workoutParts[0]);

                if (!workoutParts[1].isEmpty()) {
                    workout1.setLastdate(workoutParts[1]);
                }

                for (int i = 2; i < workoutParts.length; i++) {
                    String[] workoutUebung = workoutParts[i].split(",");
                    workout1.addUebung(new Uebungen(workoutUebung[0], Integer.parseInt(workoutUebung[1]), Integer.parseInt(workoutUebung[2])));
                }

                workoutList.add(workout1);
                adapter.notifyDataSetChanged();
            }
        }
        if(requestCode == 36)
        {
            if(resultCode == RESULT_OK)
            {
                Bundle bundle = data.getExtras();
                String workout = bundle.getString("workout");
                String[] workoutParts = workout.split(";");
                Workout workout1 = new Workout(workoutParts[0]);

                if (!workoutParts[1].isEmpty()) {
                    workout1.setLastdate(workoutParts[1]);
                }

                for (int i = 2; i < workoutParts.length; i++) {
                    String[] workoutUebung = workoutParts[i].split(",");
                    workout1.addUebung(new Uebungen(workoutUebung[0], Integer.parseInt(workoutUebung[1]), Integer.parseInt(workoutUebung[2])));
                }
                workoutList.add(workout1);
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        int viewId = v.getId();
        if (viewId == R.id.main_listview) {
            getActivity().getMenuInflater().inflate(R.menu.home_fragment_kontextmenue, menu);
        }

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Workout workoutitem = null;
        if (info != null) {
            long id = info.id;
            int pos = info.position;
            workoutitem = info != null ? (Workout) home_fragment_listview.getAdapter().getItem(pos) : null;
            if (item.getItemId() == R.id.delete_workout) {
                workoutList.remove(workoutitem);
                adapter.notifyDataSetChanged();
            }
        }

        if (item.getItemId() == R.id.edit_workout) {
            if(workoutitem != null)
            {
            Intent intent = new Intent(getActivity(), Workout_edit.class);
            intent.putExtra("Workout", workoutitem.toString());
            workoutList.remove(workoutitem);
            startActivityForResult(intent, 36);
            }
        }
        return super.onContextItemSelected(item);
    }


    @Override
    public void onStop() {
        super.onStop();
        speichern();
    }


    private void speichern(){

    }
}