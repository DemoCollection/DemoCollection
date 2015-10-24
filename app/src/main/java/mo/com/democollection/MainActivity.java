package mo.com.democollection;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import java.text.SimpleDateFormat;
import java.util.Date;

import mo.com.democollection.activity.LoginActivity;
import mo.com.democollection.base.BaseFragment;
import mo.com.democollection.base.Loadingpager;
import mo.com.democollection.factory.FragmentFactory;
import mo.com.democollection.utils.UIUtils;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TabLayout mTabs;
    private ViewPager viewPager;
    private String[] mTitleList;
    private MainOnPageChangeListener mListener;
    private TabFragmentAdapter mFragmentAdapter;
    private DrawerLayout mDrawer;
    private NavigationView mNavigationView;
    private ImageView mUserHeader;
    private FloatingActionButton mFab;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initToolBar();
        initData();
        initListener();
    }

    protected void initToolBar() {
        mToolbar = (Toolbar) findViewById(R.id.id_toolbar);
        mToolbar.setTitleTextColor(UIUtils.getColor(R.color.window_background));

        SimpleDateFormat formatter = new SimpleDateFormat ("yyyy年MM月dd日 HH:mm:ss ");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        mToolbar.setTitle(str);


        setSupportActionBar(mToolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.setDrawerListener(toggle);
        toggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    /**
     * 设置监听事件的初始化
     */
    protected void initListener() {


        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(mFab, "你想弹出一个Dialog吗?", Snackbar.LENGTH_LONG)
                        .setAction("Yes", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                android.support.v7.app.AlertDialog.Builder builder = new AlertDialog.Builder(UIUtils.getContext());
                                builder.setMessage("让我们一起飞，我带着你，你带着钱，来一场说走就走的旅行")
                                        .setNegativeButton("取消", null)
                                        .setPositiveButton("确定", null)
                                        .setTitle("Material Design Dialog")
                                        .show();
                            }
                        }).setActionTextColor(getResources().getColor(R.color.blue)).show();
            }
        });

        /*点击头像进行登入*/
        mUserHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        mListener = new MainOnPageChangeListener();
        //设置Tab选择监听事件
        mTabs.setOnTabSelectedListener(mListener);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabs.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                BaseFragment fragment = FragmentFactory.createFragment(0);
                Loadingpager loadingPager = fragment.getLoadingPager();
                //触发加载数据
                loadingPager.triggerLoadData();
            }
        });
    }

    protected void initData() {
        //获取当行显示的标题
        mTitleList = UIUtils.getStringArray(R.array.main_titles);
        for (int i = 0; i < mTitleList.length; i++) {
            //设置tab的标题
            mTabs.addTab(mTabs.newTab().setText(mTitleList[i]));
        }
        //创建适配器
        mFragmentAdapter = new TabFragmentAdapter(getSupportFragmentManager(), mTitleList);
        viewPager.setAdapter(mFragmentAdapter);//给ViewPager设置适配器
        mTabs.setupWithViewPager(viewPager);//将TabLayout和ViewPager关联起来。
        mTabs.setTabsFromPagerAdapter(mFragmentAdapter);//给Tabs设置适配器
    }

    protected void initView() {
        mTabs = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        mDrawer = (DrawerLayout) findViewById(R.id.main_drawer);
        mUserHeader = (ImageView) findViewById(R.id.iv_user_head);
        mFab = (FloatingActionButton) findViewById(R.id.fab);

        mNavigationView = (NavigationView) findViewById(R.id.main_navigationview);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    public class TabFragmentAdapter extends FragmentStatePagerAdapter {

        private String[] mTitles;

        public TabFragmentAdapter(FragmentManager fm, String[] arr) {
            super(fm);
            mTitles = arr;
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = FragmentFactory.createFragment(position);
            if (fragment != null) {
                return fragment;
            }
            return null;
        }

        @Override
        public int getCount() {
            if (mTitles != null) {
                return mTitles.length;
            }
            return 0;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }
    }


    /**
     * 监听TabLayout的滑动
     */
    private class MainOnPageChangeListener implements TabLayout.OnTabSelectedListener {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            int position = tab.getPosition();
            viewPager.setCurrentItem(position);
            BaseFragment fragment = FragmentFactory.createFragment(position);
            if (fragment != null) {
                Loadingpager loadingPager = fragment.getLoadingPager();
                //触发加载数据
                loadingPager.triggerLoadData();
            }
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    }
}