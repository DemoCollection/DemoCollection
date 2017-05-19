package mo.com.democollection.holder;/**
 * Created by  on
 */

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import mo.com.democollection.R;
import mo.com.democollection.base.BaseHolder;
import mo.com.democollection.model.Item;
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
public class HomeHolder extends BaseHolder<Item> {

    private static final String TAG = "HomeHolder";
    private ImageView item_icon;
    private TextView tv;
    private int mPosition;
    private Bitmap bitmap;
    private View mCardView;

    public HomeHolder(int position) {
        mPosition = position;
    }

    @Override
    protected void refreshHolderView(Item data) {
        tv.setText(data.title);
        ImageLoader.getInstance().displayImage("drawable://"+data.pic_id, item_icon);

//        item_icon.setImageResource(Cheeses.getRandomCheeseDrawable());
//        mCardView.setBackgroundResource(R.drawable.cheese_2);


    }

    @Override
    protected View initHolderView() {

        mCardView =  View.inflate(UIUtils.getContext(), R.layout.item_home2, null);
//        mCardView = (CardView) View.inflate(UIUtils.getContext(), R.layout.item_home2, null);
        item_icon = (ImageView) mCardView.findViewById(R.id.news_info_photo);
        tv = (TextView) mCardView.findViewById(R.id.tv_title);
        return mCardView;
    }
}
