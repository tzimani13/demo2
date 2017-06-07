package demo.push_not_demo;


import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.kunzisoft.switchdatetime.SwitchDateTimeDialogFragment;


public class SetReminder extends AppCompatActivity {


    private SwitchDateTimeDialogFragment dateTimeFragment;
    Button b1,b2,b3;
    private static final String TAG_DATETIME_FRAGMENT = "TAG_DATETIME_FRAGMENT";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_reminder);
        b1=(Button) findViewById(R.id.set_date_time);
        b2=(Button) findViewById(R.id.delete);
        b3=(Button) findViewById(R.id.save);
        b1.setOnTouchListener(touch);
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
                case R.id.delete:
                    if (event.getAction()==MotionEvent.ACTION_DOWN){
                        v.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                    }
                    else if (event.getAction()== MotionEvent.ACTION_UP){
                        v.getBackground().clearColorFilter();
                        v.invalidate();
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
                    }
                break;

            }

            return false;
        }
    };

}
