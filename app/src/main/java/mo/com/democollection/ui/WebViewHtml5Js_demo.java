package mo.com.democollection.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import mo.com.democollection.R;

/**
 * HTml5 js交互java测试
 */


public class WebViewHtml5Js_demo extends AppCompatActivity {
    private static final String LOG_TAG = "WebViewHtml5Js_demo";
    private WebView mWebView;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_web_view_html5_js_demo);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mo);
        ImageView imageView = new ImageView(this);
        imageView.setImageBitmap(bitmap);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(480,480);
        addContentView(imageView,params);

 /*       mWebView = (WebView) findViewById(R.id.wv_html_js);

        WebSettings mSettings = mWebView.getSettings();
        mSettings.setJavaScriptEnabled(true);
        mSettings.setSupportZoom(false);
        mWebView.setWebChromeClient(new MyWebChromeClient());
        mWebView.addJavascriptInterface(new JsToJava(), "stub");
        mWebView.addJavascriptInterface(new DemoJavaScriptInterface(), "demo");
        mWebView.loadUrl("file:///android_asset/demo.html");*/

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    class DemoJavaScriptInterface {

        DemoJavaScriptInterface() {
        }

        /**
         * This is not called on the UI thread. Post a runnable to invoke
         * loadUrl on the UI thread.
         */
        @JavascriptInterface
        public void clickOnAndroid() {
            Log.i(LOG_TAG, "clickOnAndroid--------------------------------");
            mHandler.post(new Runnable() {
                public void run() {
                    mWebView.loadUrl("javascript:wave()");
                }
            });

        }
    }

    private class JsToJava {

        @JavascriptInterface
        public void jsMethod(String paramFromJS) {
            Log.i("CDH", paramFromJS);
        }

        @JavascriptInterface
        public String toString() {
            return "0000000000000000fffffffffff";
        }
    }


    private class MyWebChromeClient extends WebChromeClient {

        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            Log.d(LOG_TAG, message);
            result.confirm();

            Toast.makeText(WebViewHtml5Js_demo.this, "哈哈哈哈", Toast.LENGTH_SHORT).show();
            return true;
        }
    }
}
