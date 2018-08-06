package sg.edu.rp.c346.rpwebsites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Main2Activity extends AppCompatActivity {

    WebView wb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        wb = findViewById(R.id.webView);

        Intent intentRecieved = getIntent();
        String recieved = intentRecieved.getStringExtra("URL");
        wb.setWebViewClient(new WebViewClient());
        //wb.getSettings().setJavaScriptEnabled(true);
        wb.loadUrl(recieved);
    }
}
