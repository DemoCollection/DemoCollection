package mo.com.democollection.holder;/**
 * Created by  on
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import mo.com.democollection.R;
import mo.com.democollection.base.BaseHolder;
import mo.com.democollection.utils.ImageLoader;
import mo.com.democollection.utils.UIUtils;

/**
 * @创建者 MoMxMo
 * @创时间 2015/10/14:11:00
 * @描述 TODO
 * @项目名 DemoCollection
 * @版本 $Rev
 * @更新者 $Author
 * @更新时间 $Date
 * @更新描述 TODO
 */
public class HomeHolder extends BaseHolder<String> {

    private static final String TAG = "HomeHolder";
    private ImageView item_icon;
    private TextView tv;
    private int mPosition;
    private Bitmap bitmap;

    public HomeHolder(int position) {
        mPosition = position;
    }

    @Override
    protected void refreshHolderView(String data) {
        tv.setText(data);

        WindowManager wm = (WindowManager) UIUtils.getContext().getSystemService(Context.WINDOW_SERVICE);
        ImageLoader.getInstance().loadImage(item_icon,
                R.drawable.cheese_2,
                wm.getDefaultDisplay().getWidth(),
                wm.getDefaultDisplay().getHeight(),null);

        item_icon.setImageBitmap(bitmap);


    }

    @Override
    protected View initHolderView() {

        CardView mCardView = (CardView) View.inflate(UIUtils.getContext(), R.layout.item_home, null);

        item_icon = (ImageView) mCardView.findViewById(R.id.item_home_icon);
        tv = (TextView) mCardView.findViewById(R.id.item_tv_title);
        return mCardView;
    }
}
