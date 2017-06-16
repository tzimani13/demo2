package demo.push_not_demo;


import android.content.Context;
import android.graphics.PorterDuff;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.SystemClock;
import android.widget.EditText;


import com.kunzisoft.switchdatetime.SwitchDateTimeDialogFragment;

import java.util.GregorianCalendar;
import java.util.HashMap;


public class SetReminder extends AppCompatActivity {


    private SwitchDateTimeDialogFragment dateTimeFragment;
    Button b1,b2,b3;
    EditText editText;
    public static int id;
    public static HashMap<Integer,String> hashMap;
    private static final String TAG_DATETIME_FRAGMENT = "TAG_DATETIME_FRAGMENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_reminder);
        id=0;
        hashMap= new HashMap<Integer, String>();
        b1=(Button) findViewById(R.id.set_date_time);
        b2=(Button) findViewById(R.id.cancel);
        b3=(Button) findViewById(R.id.save);
        b1.setOnTouchListener(touch);
        b2.setOnTouchListener(touch);
        b3.setOnTouchListener(touch);
        editText=(EditText) findViewById(R.id.reminder_edit);
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
                        cancelalarm();
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
                        id=id+1;
                        hashMap.put(id,editText.getText().toString());
                        alarmservice();
                    }
                break;
            }
            return false;
        }
    };

    public void alarmservice(){
        Intent intent = new Intent(this,AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(alarmManager.ELAPSED_REALTIME_WAKEUP,SystemClock.elapsedRealtime()+ 5000,pendingIntent);
    }

    public void cancelalarm(){
        Intent intent = new Intent(this,AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
    }

}