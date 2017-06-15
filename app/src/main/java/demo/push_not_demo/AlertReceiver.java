package demo.push_not_demo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import static android.support.v4.app.NotificationCompat.VISIBILITY_PUBLIC;


public class AlertReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        createNotification(context,"Times up","5","Alert");
    }

    public void createNotification(Context context,String ttl,String txt,String tck){
        PendingIntent notifiintent = PendingIntent.getActivity(context,0, new Intent(context,SetReminder.class),0);

        NotificationCompat.Builder builder= new NotificationCompat.Builder(context);
            builder.setSmallIcon(R.drawable.offer1);
            builder.setContentTitle(ttl);
            builder.setContentText(txt);
            builder.setTicker(tck);
            builder.setDefaults(Notification.DEFAULT_SOUND);
            builder.setPriority(Notification.PRIORITY_HIGH);
            builder.setVibrate(new long[] { 50, 1000, 500, 1000, 1000 });
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder.setVisibility(VISIBILITY_PUBLIC);
            }
            builder.setContentIntent(notifiintent);


        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1,builder.build());
    }
}
