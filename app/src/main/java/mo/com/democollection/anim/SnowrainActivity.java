package mo.com.democollection.anim;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import mo.com.democollection.R;
import mo.com.democollection.anim.flakeview.FlakeView;

public class SnowrainActivity extends AppCompatActivity {
    private TextView tvPlay;
    private FlakeView flakeView;
    private LinearLayout lyContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.snowrain_layout);
        tvPlay = (TextView) findViewById(R.id.tv_play);
        lyContent = (LinearLayout) findViewById(R.id.ly_content);
        flakeView = new FlakeView(this);
        lyContent.addView(flakeView);
        tvPlay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                lyContent.setVisibility(View.VISIBLE);
                handlerRain.postDelayed(runnableRain, 0);
            }
        });
    }

    Handler handlerRain = new Handler();
    Runnable runnableRain = new Runnable() {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            flakeView.addFlakes(15);
            handlerRain.postDelayed(runnableRain, 2000);
            if(flakeView.getNumFlakes() > 70)
            {
                handlerRain.removeCallbacks(runnableRain);
            }
        }
    };
}
