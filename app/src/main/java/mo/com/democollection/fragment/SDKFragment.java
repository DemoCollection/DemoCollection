package mo.com.democollection.fragment;/**
 * Created by  on
 */

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import mo.com.democollection.R;
import mo.com.democollection.base.BaseFragment;
import mo.com.democollection.base.Loadingpager;
import mo.com.democollection.sdk_activity.SDK_BaiduMap_Activity;
import mo.com.democollection.sdk_activity.SDK_BaiduYun_Activity;
import mo.com.democollection.utils.UIUtils;

/**
 * @创建者 MoMxMo
 * @创时间 2015/10/6:19:45
 * @描述 第三方平台接入
 * @项目名 GooglePlay
 * @版本 $Rev
 * @更新者 $Author
 * @更新时间 $Date
 * @更新描述 TODO
 */
public class SDKFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    private static final String TAG = "HomeFragment";
    private List<String> mListData;
    private String[] mAppList;
    private int[] icon;
    private SDKAdapter mAdapter;

    /**
     * 初始化加载数据
     *
     * @return
     */
    @Override
    protected Loadingpager.LoadedResult initData() {
        mAppList = UIUtils.getResources().getStringArray(R.array.sdk_list);
        icon = new int[]{R.drawable.finder, R.drawable.movies,
                R.drawable.antivirus, R.drawable.email,
                R.drawable.steam, R.drawable.minecraft, R.drawable.minecraft
                , R.drawable.minecraft, R.drawable.minecraft, R.drawable.minecraft};

        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
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

        View view = View.inflate(UIUtils.getContext(), R.layout.sdk_gridview, null);
        GridView mGv = (GridView) view.findViewById(R.id.app_collection);
        mAdapter = new SDKAdapter();
        mGv.setAdapter(mAdapter);
        mGv.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        switch (position) {
            case 0:
                /*百度云网盘*/
                preActivity(SDK_BaiduYun_Activity.class);

                break;
            case 1:
                 /*百度地图*/
                preActivity(SDK_BaiduMap_Activity.class);
                break;
            case 2:
                break;
            case 3:
                break;
        }

    }

    /**
     * 跳转到下一个页面
     *
     * @param clazz
     */
    private void preActivity(Class clazz) {
        Intent intent = new Intent(UIUtils.getContext(), clazz);
        startActivity(intent);
    }

    private class SDKAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mAppList.length;
        }

        @Override
        public Object getItem(int position) {
            return mAppList[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = View.inflate(UIUtils.getContext(), R.layout.item_app, null);
                holder = new ViewHolder();
                holder.mIcon = (ImageView) convertView.findViewById(R.id.iv_icon);
                holder.mTitle = (TextView) convertView.findViewById(R.id.app_name);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.mIcon.setImageResource(icon[position]);
            holder.mTitle.setText(mAppList[position]);
            return convertView;
        }
    }

    private class ViewHolder {
        private ImageView mIcon;
        private TextView mTitle;
    }


}