package mo.com.democollection.anim;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import mo.com.democollection.R;
import mo.com.democollection.anim.ShoppingCartAdapter.FoodActionCallback;
import mo.com.democollection.anim.model.Point;

public class ShoppingCartActivity extends AppCompatActivity implements FoodActionCallback {

    int[] imgs = {R.drawable.imag1,R.drawable.imag2,R.drawable.imag3,R.drawable.imag4,R.drawable.imag5,R.drawable.imag6
                    ,R.drawable.imag7,R.drawable.imag8,R.drawable.imag9,R.drawable.imag10};
    private View tv_good_fitting_num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        ListView mListveiw = (ListView) findViewById(R.id.listView);

        tv_good_fitting_num = findViewById(R.id.tv_good_fitting_num);
        mListveiw.setAdapter(new ShoppingCartAdapter(this,imgs,this));
    }
    @Override
    public void addAction(View view) {
        NXHoolderView nxHoolderView = new NXHoolderView(this);
        int position[] = new int[2];
        view.getLocationInWindow(position);  //获取view坐标的位置
        nxHoolderView.setStartPosition(new Point(position[0],position[1]));
        ViewGroup rootView = (ViewGroup) getWindow().getDecorView();
        rootView.addView(nxHoolderView);

        int endPosition[] = new int[2];
        tv_good_fitting_num.getLocationInWindow(endPosition);
        nxHoolderView.setEndPosition(new Point(endPosition[0], endPosition[1]));
        nxHoolderView.startBeizerAnimation();
    }
}
