package htl.grieskirchen.mstadlbauer.gainz_projekt;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class Channels extends Application {
    public static final String CHANNEL_1_ID = "channel1" ;


    @Override
    public void onCreate() {
        super.onCreate();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel1 = new NotificationChannel(CHANNEL_1_ID, "Standort", NotificationManager.IMPORTANCE_DEFAULT);
            channel1.setDescription("In der Nähe eines Workouts");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
        }
    }
}
