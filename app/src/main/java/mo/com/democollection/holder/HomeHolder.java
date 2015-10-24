package mo.com.democollection.holder;/**
 * Created by  on
 */

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import mo.com.democollection.R;
import mo.com.democollection.base.BaseHolder;
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

    private ImageView item_icon;
    private TextView tv;
    private  int mPosition;

    public HomeHolder(int position) {
        mPosition = position;
    }

    @Override
    protected void refreshHolderView(String data) {
        tv.setText(data);

        item_icon.setImageResource(R.drawable.mo);
    }

    @Override
    protected View initHolderView() {

        View view = View.inflate(UIUtils.getContext(), R.layout.item_home, null);
        item_icon = (ImageView) view.findViewById(R.id.item_home_icon);
        tv = (TextView) view.findViewById(R.id.item_tv_title);
        return view;
    }
}
