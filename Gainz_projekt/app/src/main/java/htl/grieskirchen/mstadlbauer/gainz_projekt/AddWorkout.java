package htl.grieskirchen.mstadlbauer.gainz_projekt;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpRetryException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class AddWorkout extends AppCompatActivity {


    /**
     * FloatingActionButton = zum Hinzufügen einer Uebung
     * speichern = speichern des Workouts
     * cancel = Abruch des erstellens eines Wokouts
     * workout = List aller Uebungen in einem Workout
     * adapter = adapter für das Anzeigen der Uebungen
     * uebungenlistview = Anzeigen der Uebungen
     */
    private FloatingActionButton addExercise;
    private Button speichern;
    private Button cancel;
    private List<Uebungen> workout = new ArrayList<>();
    private Addworkout_listview_adapter adapter;
    private ListView uebungenlistview;


    //Location
    private LocationManager locationManager;
    private final int RQ_ACCESS_FINE_LOCATION = 12345;
    private boolean isGpsAllowed = false;
    private LocationListener locationListener;
    private String workoutaddress;
    private boolean done = false;


    //On Create

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_workout);

        initButtons();

        uebungenlistview = findViewById(R.id.addWorkout_listview);
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
                new AlertDialog.Builder(AddWorkout.this).setMessage("Übung").setCancelable(false).setView(vDialog).setPositiveButton("Save", ((dialog, which) -> editDialog(vDialog, position))).setNegativeButton("Cancle", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initGps();
    }

    private void initGps() {
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        String permission = Manifest.permission.ACCESS_FINE_LOCATION;
        if (ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{permission}, RQ_ACCESS_FINE_LOCATION);
        } else {
            gpsIsGranted();
        }
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
            }
        };

    }

    private void initButtons() {
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
                if (!name_workout.getText().toString().isEmpty()) {
                    Location location = btnClickUpdateCoordinates(new View(getApplicationContext()));
                    Locationiq lq = new Locationiq();
                    lq.execute(location.getLatitude(), location.getLongitude());
                   /* String name = name_workout.getText().toString();
                    Workout workout = new Workout(name, AddWorkout.this.workout);
                    workout.setLon(location.getLongitude());
                    workout.setLat(location.getLatitude());
                    workout.setAddresse(workoutaddress);
                    Intent intent = new Intent(AddWorkout.this, MainActivity.class);
                    intent.putExtra("workout", workout.toString());
                    setResult(RESULT_OK, intent);
                    finish();*/
                }
                Toast.makeText(AddWorkout.this, "Geben Sie einen Name für ihr Workout ein", Toast.LENGTH_SHORT);
            }
        });
        cancel = findViewById(R.id.cancel_workout_btn);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

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
            Toast.makeText(AddWorkout.this, "Alle Felder müssen gefüllt sein", Toast.LENGTH_SHORT).show();
        }
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
            Toast.makeText(AddWorkout.this, "Alle Felder müssen gefüllt sein", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode != RQ_ACCESS_FINE_LOCATION) return;
        if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Permission ACCESS_FINE_LOCATION denied!", Toast.LENGTH_SHORT);
        } else {
            gpsIsGranted();
        }
    }

    private void gpsIsGranted() {
        isGpsAllowed = true;
    }

    @SuppressLint("MissingPermission")
    @Override
    protected void onResume() {
        super.onResume();
        if (isGpsAllowed) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 0, locationListener);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isGpsAllowed) locationManager.removeUpdates(locationListener);
    }


    @SuppressLint("MissingPermission")
    public Location btnClickUpdateCoordinates(View view) {
        if (isGpsAllowed) {
            return locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }
        return null;
    }


    private class Locationiq extends AsyncTask<Double, Integer, String> {

        @Override
        protected String doInBackground(Double... doubles) {

            double lat = doubles[0];
            double lon = doubles[1];


            String sJson = "";
            try {
                HttpsURLConnection connection =
                        (HttpsURLConnection) new URL("https://eu1.locationiq.com/v1/reverse.php?key=pk.1627b327355066744e8601bac7e85491&lat=" + lat  + "&lon=" + lon + "&format=json").openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-Type", "application/json");
                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(connection.getInputStream()));
                    sJson = readResponseStream(reader);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sJson;
        }

        private String readResponseStream(BufferedReader reader) throws IOException {
            StringBuilder stringBuilder = new StringBuilder();
            String line = "";
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        }


        @Override
        protected void onPostExecute(String s) {
            String temp = outputRepoNames(s);
            workoutaddress = temp;
            done = true;
            EditText name_workout = findViewById(R.id.name_workout);
            Location location = btnClickUpdateCoordinates(new View(getApplicationContext()));
            String name = name_workout.getText().toString();
            Workout workout = new Workout(name, AddWorkout.this.workout);
            workout.setLon(location.getLongitude());
            workout.setLat(location.getLatitude());
            workout.setAddresse(workoutaddress);
            Intent intent = new Intent(AddWorkout.this, MainActivity.class);
            intent.putExtra("workout", workout.toString());
            setResult(RESULT_OK, intent);
            finish();
            super.onPostExecute(s);
        }

        private String outputRepoNames(String s) {
            StringBuilder stringBuilder = new StringBuilder();
            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONObject address = jsonObject.getJSONObject("address");
                stringBuilder.append(address.optString("road"));
                stringBuilder.append(",");
                stringBuilder.append(address.optString("house_number"));
                stringBuilder.append(",");
                stringBuilder.append(address.optString("postcode"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return stringBuilder.toString();
        }
    }



}