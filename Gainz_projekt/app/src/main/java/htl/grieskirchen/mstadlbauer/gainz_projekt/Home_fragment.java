package htl.grieskirchen.mstadlbauer.gainz_projekt;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ComponentActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.Settings;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.app.Activity.RESULT_OK;
import static android.os.Build.VERSION.SDK_INT;


public class Home_fragment extends Fragment {

    private List<Workout> workoutList = new ArrayList<>();
    private Home_fragment_listview_adapter adapter;
    private ListView home_fragment_listview;
    String fileName = "Workoutsfile";

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
        if(!checkPermission()) {
            requestPermission();
        }
        load();
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
     * Detail View = 32
     * Edit Workout = 36
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
                workout1.setAddresse(workoutParts[4]);

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

                workout1.setLat(Double.parseDouble(workoutParts[2]));
                workout1.setLon(Double.parseDouble(workoutParts[3]));
                workout1.setAddresse(workoutParts[4]);

                for (int i = 5; i < workoutParts.length; i++) {
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

                workout1.setLat(Double.parseDouble(workoutParts[2]));
                workout1.setLon(Double.parseDouble(workoutParts[3]));
                workout1.setAddresse(workoutParts[4]);

                for (int i = 5; i < workoutParts.length; i++) {
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
        save();
    }

    @Override
        public void onPause() {
        super.onPause();
        save();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        save();
    }

    @Override
    public void onResume() {
        super.onResume();
        load();
    }

    private void save() {
        String state = Environment.getExternalStorageState();
        if (!state.equals(Environment.MEDIA_MOUNTED)) return;
        File outFile = Environment.getExternalStorageDirectory();
        String path = outFile.getAbsolutePath();
        String fullPath = path + File.separator + fileName;
        try {
            PrintWriter out = new PrintWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(fullPath)));
            for (Workout detail:workoutList
                 ) {
                out.println(detail.toString() + "]");
            }
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void load() {
        String state = Environment.getExternalStorageState();
        if (!state.equals(Environment.MEDIA_MOUNTED)) return;
        File outFile = Environment.getExternalStorageDirectory();
        String path = outFile.getAbsolutePath();
        String fullPath = path + File.separator + fileName;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fullPath)));

            String line;
            StringBuffer buffer = new StringBuffer();

            while((line = in.readLine()) != null) {
                buffer.append(line);
            }
            String workoutdetails = buffer.toString();

            String[] workouttemp =workoutdetails.split("]");
            
            in.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private boolean checkPermission() {
        if (SDK_INT >= Build.VERSION_CODES.R) {
            return Environment.isExternalStorageManager();
        } else {
            int result = ContextCompat.checkSelfPermission(getContext(), READ_EXTERNAL_STORAGE);
            int result1 = ContextCompat.checkSelfPermission(getContext(), WRITE_EXTERNAL_STORAGE);
            return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
        }
    }

    private void requestPermission() {
        if (SDK_INT >= Build.VERSION_CODES.R) {
            try {
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setData(Uri.parse(String.format("package:%s", new Object[]{getActivity().getApplicationContext().getPackageName()})));
                startActivityForResult(intent, 2296);
            } catch (Exception e) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                startActivityForResult(intent, 2296);
            }
        } else {
            //below android 11
            ActivityCompat.requestPermissions(getActivity(), new String[]{WRITE_EXTERNAL_STORAGE}, 187);
        }
    }
}