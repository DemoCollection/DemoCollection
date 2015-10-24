package mo.com.democollection.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.widget.ImageView;

import mo.com.democollection.R;

public class MusicUI extends AppCompatActivity {

    private ImageView mIcon;
    private float mDownY;
    private float mDownX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_ui);

        mIcon = (ImageView) findViewById(R.id.iv_icon);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = event.getX();
                mDownY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float mMoveX = event.getX();
                float mMoveY = event.getY();

                float x = mMoveX - mDownX;
                float y = mMoveY - mDownY;
            /*    RelativeLayout.LayoutParams params;
                params = new RelativeLayout.LayoutParams(48,48);
                params.height = (int)y;
                params.width = (int)x;

                mIcon.setLayoutParams(params);*/

                mIcon.setLeft((int) (event.getRawX()-mIcon.getWidth()/2.0f));
                mIcon.setTop((int) (event.getRawY() -mIcon.getHeight()/2.0f));
                break;

            case MotionEvent.ACTION_UP:
                break;

            default:
                break;
        }
        return super.onTouchEvent(event);
    }
}
