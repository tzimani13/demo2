package demo.push_not_demo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.EditText;

import static android.support.v4.app.NotificationCompat.DEFAULT_SOUND;
import static android.support.v4.app.NotificationCompat.PRIORITY_HIGH;
import static android.support.v4.app.NotificationCompat.VISIBILITY_PUBLIC;
import static demo.push_not_demo.SetReminder.id;


public class AlertReceiver extends BroadcastReceiver {

    public static int id1;

    @Override
    public void onReceive(Context context, Intent intent) {

        id1=id;
        createNotification(context);
    }

    private void createNotification(Context context) {
        NotificationCompat.Builder notification = new NotificationCompat.Builder(context)
            .setAutoCancel(true)
            .setSmallIcon(R.drawable.offer1)
            .setContentTitle("Notification Title")
            .setContentText("Tap to see your reminder")
            .setPriority(PRIORITY_HIGH)
            .setVibrate(new long[] { 50, 1000, 500, 1000, 1000 })
            .setDefaults(DEFAULT_SOUND)
            .setVisibility(VISIBILITY_PUBLIC);

        Intent intent = new Intent(context,Reminder.class);
        PendingIntent pendingintent = PendingIntent.getActivity(context,1,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pendingintent);
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification ntfc =  notification.build();
        nm.notify(id,ntfc);
    }


}
