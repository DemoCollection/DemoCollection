package mo.com.democollection.fragment;/**
 * Created by  on
 */

import android.graphics.Color;
import android.os.SystemClock;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import mo.com.democollection.R;
import mo.com.democollection.base.BaseFragment;
import mo.com.democollection.base.BaseHolder;
import mo.com.democollection.base.Loadingpager;
import mo.com.democollection.base.SuperAdapter;
import mo.com.democollection.holder.HomeHolder;
import mo.com.democollection.utils.Cheeses;
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
public class HomeFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "HomeFragment";
    private ListView mListView;
    private List<String> mListData;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    /**
     * 初始化加载数据
     *
     * @return
     */
    @Override
    protected Loadingpager.LoadedResult initData() {
        String[] dataStrings = Cheeses.sCheeseStrings;
        mListData = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            mListData.add("test" + i);
        }
        return Loadingpager.LoadedResult.SUCCESS;
    }

    /**
     * 初始化View
     *
     * @return
     */

    @Override
    public View initSuccessView() {

        View view = View.inflate(UIUtils.getContext(), R.layout.home_fragment, null);
        mListView = (ListView) view.findViewById(R.id.lv_home);
        mListView.setAdapter(new HomeFragmentAdapter(mListView, mListData));


        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
        //设置下拉刷新监听事件
        mSwipeRefreshLayout.setOnRefreshListener(this);
        //设置进度条的颜色
        mSwipeRefreshLayout.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);
        //设置圆形进度条大小
        mSwipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);
        //设置进度条背景颜色
        mSwipeRefreshLayout.setProgressBackgroundColorSchemeColor(Color.DKGRAY);
        //设置下拉多少距离之后开始刷新数据
        mSwipeRefreshLayout.setDistanceToTriggerSync(50);

        return view;
    }

    @Override
    public void onRefresh() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(2000);

                UIUtils.postTaskSafely(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });

            }
        });


    }

    private class HomeFragmentAdapter extends SuperAdapter<String> {
        public HomeFragmentAdapter(AbsListView adsListView, List<String> resData) {
            super(adsListView, resData);
        }

        @Override
        protected BaseHolder getSpecialHolder(int position) {
            return new HomeHolder(position);
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            super.onItemClick(parent, view, position, id);
        }
    }

}