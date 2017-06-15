package demo.push_not_demo;


import android.app.NotificationManager;
import android.graphics.PorterDuff;
import android.icu.util.GregorianCalendar;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;


import com.kunzisoft.switchdatetime.SwitchDateTimeDialogFragment;

import static android.support.v4.app.NotificationCompat.VISIBILITY_PUBLIC;


public class SetReminder extends AppCompatActivity {


    private SwitchDateTimeDialogFragment dateTimeFragment;
    Button b1,b2,b3;
    private static final String TAG_DATETIME_FRAGMENT = "TAG_DATETIME_FRAGMENT";
    NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_reminder);
        b1=(Button) findViewById(R.id.set_date_time);
        b2=(Button) findViewById(R.id.cancel);
        b3=(Button) findViewById(R.id.save);
        b1.setOnTouchListener(touch);
        b2.setOnTouchListener(touch);
        b3.setOnTouchListener(touch);

    }

    View.OnTouchListener touch= new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (v.getId()){
                case R.id.set_date_time:
                    if (event.getAction()==MotionEvent.ACTION_DOWN){
                        v.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                    }
                    else if (event.getAction()== MotionEvent.ACTION_UP){
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                        // Construct SwitchDateTimePicker
                        dateTimeFragment = (SwitchDateTimeDialogFragment) getSupportFragmentManager().findFragmentByTag(TAG_DATETIME_FRAGMENT);
                        if(dateTimeFragment == null) {
                            dateTimeFragment = SwitchDateTimeDialogFragment.newInstance(
                                    getString(R.string.label_datetime_dialog),
                                    getString(R.string.positive_button_datetime_picker),
                                    getString(R.string.negative_button_datetime_picker)
                            );
                        }
                        dateTimeFragment.show(getSupportFragmentManager(), TAG_DATETIME_FRAGMENT);
                    }
                break;
                case R.id.cancel:
                    if (event.getAction()==MotionEvent.ACTION_DOWN){
                        v.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                    }
                    else if (event.getAction()== MotionEvent.ACTION_UP){
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                        Intent intent = new Intent(SetReminder.this,HomeScreen.class);
                        startActivity(intent);
                    }
                break;
                case R.id.save:
                    if (event.getAction()==MotionEvent.ACTION_DOWN){
                        v.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                    }
                    else if (event.getAction()== MotionEvent.ACTION_UP){
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                        setAlarm(v);
                    }
                break;

            }

            return false;
        }
    };

    public void notbuilder() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("Test Title");
        builder.setContentText("Just testing Notifications for now");
        builder.setTicker("Reminder");
        builder.setSmallIcon(R.drawable.offer1);
        builder.setDefaults(Notification.DEFAULT_ALL);
        builder.setPriority(Notification.PRIORITY_HIGH);
        builder.setVibrate(new long[] { 50, 1000, 500, 1000, 1000 });
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        builder.setSound(alarmSound);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder.setVisibility(VISIBILITY_PUBLIC);
        }
        builder.setAutoCancel(true);
        Intent intent = new Intent(this,Reminder.class);
        TaskStackBuilder tstackbuilder = TaskStackBuilder.create(this);
        tstackbuilder.addParentStack(HomeScreen.class);
        tstackbuilder.addNextIntent(intent);
        PendingIntent pendingIntent = tstackbuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1,builder.build());
    }

    public void setAlarm(View view){
        Long alertTime = SystemClock.elapsedRealtime()+ 5000;
        Intent alertIntent= new Intent(this,AlertReceiver.class);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(alarmManager.ELAPSED_REALTIME_WAKEUP,alertTime,PendingIntent.getBroadcast(this,1,alertIntent,PendingIntent.FLAG_UPDATE_CURRENT));
        notbuilder();
    }

}