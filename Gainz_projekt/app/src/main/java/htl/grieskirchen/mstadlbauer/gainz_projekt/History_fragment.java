package htl.grieskirchen.mstadlbauer.gainz_projekt;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.os.Build.VERSION.SDK_INT;


public class History_fragment extends Fragment {

    /**
     * historylistview = Listview mit Name und Datum des Workouts
     * adapter = listviewadapter
     * history = sammlung der Workouts
     */
    private ListView historylistview;

    private List<Workout> history = new ArrayList<>();
    String fileName = "Workoutsfile";
    private Histroy_fragment_listviewadapter hfl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history_fragment, container, false);
        // Inflate the layout for this fragment
        init(view);
        return view;
    }

    /**
     * Initialisierung der listview und des adapters
     *
     * @param view
     */
    private void init(View view) {
        Collections.sort(history, new Comparator<Workout>() {
            @Override
            public int compare(Workout o1, Workout o2) {
                if (o1.getLasttimedate().equals("null"))
                    return -1;

                if (LocalDate.parse(o1.getLasttimedate()).isAfter(LocalDate.parse(o2.getLasttimedate()))) {
                    return 1;
                } else if (LocalDate.parse(o1.getLasttimedate()).isEqual(LocalDate.parse(o2.getLasttimedate()))) {
                    return 0;
                }
                return -1;
            }
        });
        historylistview = view.findViewById(R.id.history_fragment_ListView);
        hfl = new Histroy_fragment_listviewadapter(view.getContext(), R.layout.history_fragment_listviewadapter, history);
        historylistview.setAdapter(hfl);
        if (!checkPermission()) {
            requestPermission();
        }
        load();
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

            while ((line = in.readLine()) != null) {
                buffer.append(line);
            }
            String workoutdetails = buffer.toString();

            String[] workoutstemp = workoutdetails.split("]");
            for (String workouttemp : workoutstemp
            ) {
                String[] workoutParts = workouttemp.split(";");
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
                history.add(workout1);
                hfl.notifyDataSetChanged();
            }
            in.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onStart() {
        super.onStart();
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