package demo.push_not_demo;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.Drive;

public class HomeScreen extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;
    private String distr_mail="info@stavropoulos-sa.com";
    ImageButton b1,b2,b3,b4,b5,b6,b7;
    String call_center ="tel:2164005500";
    String giannakari="tel:6994350881";
    String papadomihelakis="tel:6981700066";
    String rubel="tel:6943573757";
    GoogleApiClient mGoogleApiClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */,
                        this /* OnConnectionFailedListener */)
                .addApi(Drive.API)
                .addScope(Drive.SCOPE_FILE)
                .build();
        mToolbar=(Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("Distributor");
        setSupportActionBar(mToolbar);
        mDrawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        mToggle= new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        b1=(ImageButton)findViewById(R.id.CallButton);
        b2=(ImageButton)findViewById(R.id.EmailButton);
        b3=(ImageButton)findViewById(R.id.ReminderButton);
        b4=(ImageButton)findViewById(R.id.offerbutton1);
        b5=(ImageButton)findViewById(R.id.offerbutton2);
        b6=(ImageButton)findViewById(R.id.offerbutton3);
        b7=(ImageButton)findViewById(R.id.offerbutton4);
        b1.setOnTouchListener(touch);
        b2.setOnTouchListener(touch);
        b3.setOnTouchListener(touch);
        b4.setOnTouchListener(touch);
        b5.setOnTouchListener(touch);
        b6.setOnTouchListener(touch);
        b7.setOnTouchListener(touch);
    }

    //button gia to sidemenu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }




    //effect gia button kai click listener
    View.OnTouchListener touch = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (v.getId()){
                case R.id.CallButton:
                    if (event.getAction()==MotionEvent.ACTION_DOWN){
                        v.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                    }
                    else if (event.getAction()==MotionEvent.ACTION_UP){
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                        new MaterialDialog.Builder(HomeScreen.this)
                                .title("Call a representative")
                                .items(R.array.sales)
                                .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                                    @Override
                                    public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                        switch (which){
                                            case 0:
                                            Intent intent=new Intent(Intent.ACTION_DIAL);
                                            intent.setData(Uri.parse(call_center));
                                            startActivity(intent);
                                            break;
                                            case 1:
                                                intent=new Intent(Intent.ACTION_DIAL);
                                                intent.setData(Uri.parse(giannakari));
                                                startActivity(intent);
                                                break;
                                            case 2:
                                                intent=new Intent(Intent.ACTION_DIAL);
                                                intent.setData(Uri.parse(papadomihelakis));
                                                startActivity(intent);
                                                break;
                                            case 3:
                                                intent=new Intent(Intent.ACTION_DIAL);
                                                intent.setData(Uri.parse(rubel));
                                                startActivity(intent);
                                                break;
                                        }

                                        return true;
                                    }
                                })
                                .positiveText("Call")
                                .show();


                    }

                    break;
                case R.id.EmailButton:
                    if (event.getAction()==MotionEvent.ACTION_DOWN){
                        v.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                    }
                    else if (event.getAction()==MotionEvent.ACTION_UP){
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                        Intent intent=new Intent(Intent.ACTION_SEND);
                        intent.setType("message/rfc822");
                        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{distr_mail});
                        startActivity(Intent.createChooser(intent, "Send Email"));
                    }
                    break;
                case R.id.ReminderButton:
                    if (event.getAction()==MotionEvent.ACTION_DOWN){
                        v.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                    }
                    else if (event.getAction()== MotionEvent.ACTION_UP){
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                        Intent intent= new Intent(HomeScreen.this,SetReminder.class);
                        startActivity(intent);
                    }
                    break;
                case R.id.offerbutton1:
                    if (event.getAction()==MotionEvent.ACTION_DOWN){
                        v.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                    }
                    else if (event.getAction()== MotionEvent.ACTION_UP){
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                    }
                    break;
                case R.id.offerbutton2:
                    if (event.getAction()==MotionEvent.ACTION_DOWN){
                        v.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                    }
                    else if (event.getAction()== MotionEvent.ACTION_UP){
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                    }
                    break;
                case R.id.offerbutton3:
                    if (event.getAction()==MotionEvent.ACTION_DOWN){
                        v.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                    }
                    else if (event.getAction()== MotionEvent.ACTION_UP){
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                    }
                    break;
                case R.id.offerbutton4:
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


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
};

