package demo.push_not_demo;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Message extends AppCompatActivity{

    public static String msg;
    TextView txtv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message);
        txtv=(TextView) findViewById(R.id.message);
        if (getIntent().getExtras()==null){
            txtv.setText(msg);
        }
        else {
            msg=getIntent().getExtras().getString("activity");
            txtv.setText(msg);
        }
    }
}
