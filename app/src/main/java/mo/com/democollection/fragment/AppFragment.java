package mo.com.democollection.fragment;/**
 * Created by  on
 */

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import mo.com.democollection.base.BaseFragment;
import mo.com.democollection.base.Loadingpager;
import mo.com.democollection.utils.UIUtils;

/**
 * @创建者 MoMxMo
 * @创时间 2015/10/6:19:45
 * @描述
 * @项目名 GooglePlay
 * @版本 $Rev
 * @更新者 $Author
 * @更新时间 $Date
 * @更新描述 TODO
 */
public class AppFragment extends BaseFragment {
    private static final String TAG = "HomeFragment";
    /**
     * 初始化加载数据
     *
     * @return
     */
    @Override
    protected Loadingpager.LoadedResult initData() {

        return Loadingpager.LoadedResult.SUCCESS;
    }

    /**
     * 初始化View
     *
     * @return
     */

    @Override
    public View initSuccessView() {

        TextView tv = new TextView(UIUtils.getContext());
        tv.setText(this.getClass().getSimpleName());
        tv.setGravity(Gravity.CENTER);
        return tv;
    }

}