package mo.com.democollection.app_activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import mo.com.democollection.R;

public class NetWork_Activity extends AppCompatActivity {

    String url = "http://httpbin.org/post";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_network);
    }

    /**
     * HttpClient  post表单的请求方式
     * @param view
     */
    public void keyValuePost(View view){
/*        new Thread(new Runnable() {
            @Override
            public void run() {
                DefaultHttpClient client = new DefaultHttpClient();
                HttpPost post = new HttpPost(url);

            }
        }).start();*/

    }

}
