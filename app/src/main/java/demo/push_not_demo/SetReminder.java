package demo.push_not_demo;


import android.graphics.PorterDuff;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;
import com.kunzisoft.switchdatetime.SwitchDateTimeDialogFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;



public class SetReminder extends AppCompatActivity {

    private SwitchDateTimeDialogFragment dateTimeFragment;
    Button b1,b2,b3;
    int hours,minutes,year,month,day;

    EditText editText;
    public static int counter=0;
    public static HashMap<Integer,String> hashMap;
    private static final String TAG_DATETIME_FRAGMENT = "TAG_DATETIME_FRAGMENT";

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
                        dateTimeFragment.setOnButtonClickListener(new SwitchDateTimeDialogFragment.OnButtonClickListener() {

                            @Override
                            public void onPositiveButtonClick(Date date) {
                                year=dateTimeFragment.getYear()-7;
                                month=dateTimeFragment.getMonth()+2;
                                day=dateTimeFragment.getDay()-4;
                                hours=dateTimeFragment.getHourOfDay();
                                minutes=dateTimeFragment.getMinute();
                            }

                            @Override
                            public void onNegativeButtonClick(Date date) {

                            }
                        });
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
                        finish();
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
                        if(TextUtils.isEmpty(editText.getText().toString())){
                            Toast.makeText(SetReminder.this,"Please write a description for your message",Toast.LENGTH_LONG).show();
                            editText.setError("This field can not be blank");
                        }
                        else {
                            Calendar beginTime = Calendar.getInstance();
                            beginTime.set(year, month, day, hours, minutes);
                            Calendar endTime = beginTime;
                            endTime.set(year, month, day, hours, minutes+60*60*1000);
                            Intent intent = new Intent(Intent.ACTION_INSERT);
                            intent.setData(CalendarContract.Events.CONTENT_URI);
                            intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,beginTime.getTimeInMillis());
                            intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,endTime.getTimeInMillis());
                            intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY , false);
                            intent.putExtra("title", "Message");
                            intent.putExtra("description", editText.getText().toString());
                            intent.putExtra("date","");
                            startActivity(intent);
                            finish();
                        }
                    }
                break;
            }
            return false;
        }
    };


}