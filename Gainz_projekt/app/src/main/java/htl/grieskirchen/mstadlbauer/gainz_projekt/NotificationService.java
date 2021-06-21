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
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class NotificationService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    private Thread worker;
    private NotificationManager notificationManager;
    private LocationManager locationManager;


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

                //TODO: Auslesen der Workouts und dann mit der Location vergleichen
                //TODO Notificationchannel noch anlegen und austesten der ganzen Notifications

                int l = 10;
                for (int i = 0; i < l; i++) {
                    Workout workout = new Workout("Test");
                    //current ersetzen
                    double distance = current.distanceTo(current) / 1000;
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

    private void locationWorkoutNotification(String title, int id, Workout workout) {
        Intent intent = new Intent(this, Workout_detail.class);
        intent.putExtra("Workout", workout.toString());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, Channels.CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_notification_location)
                .setContentTitle(title)
                .setContentText("Möchten Sie das " + title + " Workout starten ?")
                .setWhen(System.currentTimeMillis())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        notificationManager.notify(id, builder.build());
    }
}
