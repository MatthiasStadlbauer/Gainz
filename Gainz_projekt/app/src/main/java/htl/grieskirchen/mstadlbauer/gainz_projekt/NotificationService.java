package htl.grieskirchen.mstadlbauer.gainz_projekt;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.provider.Settings;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.os.Build.VERSION.SDK_INT;

public class NotificationService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    private Thread worker;
    private NotificationManager notificationManager;
    private LocationManager locationManager;
    private ArrayList<Workout> workouts = new ArrayList<>();
    String fileName = "Workoutsfile";


    @Override
    public void onCreate() {
        worker = new Thread(this::workernotification);
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        worker.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        worker.interrupt();
        super.onDestroy();
    }

    private void workernotification() {
        while (!Thread.currentThread().isInterrupted()) {
            Location current = null;
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                current = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                if (current != null) {

                    load();

                    for (int i = 0; i < workouts.size(); i++) {
                        Workout workout = workouts.get(i);

                        Location location = new Location("");
                        location.setLongitude(workout.getLon());
                        location.setLatitude(workout.getLat());

                        //current ersetzen
                        double distance = current.distanceTo(location) / 1000;
                        if (distance < 1) {
                            locationWorkoutNotification(workout.getName(), i, workout);
                        }
                    }

                    try {
                        Thread.sleep(60000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    private void locationWorkoutNotification(String title, int id, Workout workout) {
     /*   Intent intent = new Intent(this, Workout_detail.class);
        intent.putExtra("Workout", workout.toString());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);*/


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, Channels.CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_notification_location)
                .setContentTitle(title)
                .setContentText("Sie befinden sich in der nähe von " + title + "Workout")
                .setWhen(System.currentTimeMillis())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);
        notificationManager.notify(id, builder.build());
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
                workouts.add(workout1);
            }
            in.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
