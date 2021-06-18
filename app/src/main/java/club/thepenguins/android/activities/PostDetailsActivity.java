package club.thepenguins.android.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import club.thepenguins.android.R;

public class PostDetailsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);
        // TextView textView = findViewById(R.id.postd);


        Intent i = getIntent();
        String post = i.getExtras().getString("content");

        Log.d("PostDetails ", "onCreate: " + post);

        /*

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textView.setText(Html.fromHtml(post, Html.FROM_HTML_MODE_COMPACT));
        } else {
            textView.setText(Html.fromHtml(post));
        }


         */

        WebView myWebView = (WebView) findViewById(R.id.webview);

        myWebView.loadDataWithBaseURL(null, post, "text/html", "UTF-8", null);
        myWebView.getSettings().setUserAgentString("Android");
        myWebView.getSettings().setBuiltInZoomControls(true);
        // myWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
        //myWebView.getSettings().getLoadWithOverviewMode();
        //myWebView.getSettings().setUseWideViewPort(true);

    }
}