package demo.push_not_demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;


public class webv extends AppCompatActivity {
    public static String url;
    WebView webv1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);

        webv1=(WebView) findViewById(R.id.webview1);
        webv1.getSettings().setLoadsImagesAutomatically(true);
        webv1.getSettings().setJavaScriptEnabled(true);
        webv1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webv1.loadUrl(url);

    }
}
