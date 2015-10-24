package mo.com.democollection.ui;/**
 * Created by  on
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import mo.com.democollection.R;

/**
 * @创建者 MoMxMo
 * @创时间 2015/10/22:23:28
 * @描述 TODO
 * @项目名 DemoCollection
 *
 * @版本 $Rev
 * @更新者 $Author
 * @更新时间 $Date
 * @更新描述 TODO
 */
public class WebMoviUI extends AppCompatActivity{

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_movi_activity);

        mWebView = (WebView) findViewById(R.id.wv_paly);
        mWebView.loadUrl("http://v.youku.com/v_show/id_XNDkzMzc0NTY=.html?from=y1.7-1.2");
        mWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
                //
                view.loadUrl(url);

                return true;
            }
        });

        /*设置js*/
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);


        /*设置支持插件*/

        /*判断页面加载过程*/
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO Auto-generated method stub
                if (newProgress == 100) {
                    // 网页加载完成

                } else {
                    // 加载中

                }
            }
        });


        /*缓存的使用*/
        /*优先使用缓存*/
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);


        /*//不使用缓存*/
        /*mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);*/
    }

    //改写物理按键——返回的逻辑
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWebView.canGoBack()) {
                mWebView.goBack();//返回上一页面
                return true;
            } else {
                System.exit(0);//退出程序
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
