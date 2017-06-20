package demo.push_not_demo;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import static demo.push_not_demo.SetReminder.counter;
import static demo.push_not_demo.SetReminder.hashMap;

public class Reminder extends AppCompatActivity{

    TextView txtv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reminder);
        txtv=(TextView) findViewById(R.id.reminder_textView);
        txtv.setText("Result page");
    }
}
