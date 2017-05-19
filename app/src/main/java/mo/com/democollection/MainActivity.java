package mo.com.democollection;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.ChangeTransform;
import android.transition.Transition;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.tinker.lib.util.TinkerLog;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;
import com.tinkerpatch.sdk.TinkerPatch;
import com.tinkerpatch.sdk.server.callback.ConfigRequestCallback;

import java.util.HashMap;

import cn.pedant.SweetAlert.SweetAlertDialog;
import mo.com.democollection.Fingerprint.FMAuthenticationCallback;
import mo.com.democollection.activity.DetailActivity;
import mo.com.democollection.base.BaseFragment;
import mo.com.democollection.base.Loadingpager;
import mo.com.democollection.factory.FragmentFactory;
import mo.com.democollection.utils.T;
import mo.com.democollection.utils.UIUtils;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, Toolbar.OnMenuItemClickListener {

    private static final String TAG = "MainActivity";
    private TabLayout mTabs;
    private ViewPager viewPager;
    private FrameLayout fl_search;
    private String[] mTitleList;
    private MainOnPageChangeListener mListener;
    private TabFragmentAdapter mFragmentAdapter;
    private DrawerLayout mDrawer;
    private NavigationView mNavigationView;
    private ImageView mUserHeader;
    private FloatingActionButton mFab;
    private Toolbar mToolbar;
    private ImageView iv;
    private TextView text;

    private AnimatedVectorDrawable searchToBar;
    private AnimatedVectorDrawable barToSearch;
    private float offset;
    private Interpolator interp;
    private int duration;
    private boolean expanded = false;

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
        mToolbar.setOnMenuItemClickListener(this);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.setDrawerListener(toggle);
        toggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    /**
     * start detail activity
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void startActivity(final View v, final int position) {

        View pic = v.findViewById(R.id.news_info_photo);
        View tv_title = v.findViewById(R.id.tv_title);
        View tv_cotent = v.findViewById(R.id.tv_cotent);
        View add_btn = this.findViewById(R.id.fab);

        // set share element transition animation for current activity
        Transition ts = new ChangeTransform();
        ts.setDuration(1000);
        getWindow().setExitTransition(ts);
        Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,
                Pair.create(pic, position + "transitionImg"),
                Pair.create(tv_title, position + "tv_title"),
                Pair.create(tv_cotent, position + "tv_cotent"),
                Pair.create(add_btn, "ShareBtn")).toBundle();

        // start activity with share element transition
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("pos", position);
        startActivity(intent, bundle);

    }

    /**
     * 设置监听事件的初始化
     */
    protected void initListener() {
        fl_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animate(view);
            }
        });

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(mFab, "你想弹出一个Dialog吗?", Snackbar.LENGTH_LONG)
                        .setAction("Yes", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(MainActivity.this);
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
 /*       mUserHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });*/

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
        viewPager = (ViewPager) findViewById(R.id.m_viewPager);
        fl_search = (FrameLayout) findViewById(R.id.fl_search);
        mDrawer = (DrawerLayout) findViewById(R.id.main_drawer);
        mUserHeader = (ImageView) findViewById(R.id.iv_user_head);
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mNavigationView = (NavigationView) findViewById(R.id.main_navigationview);
        iv = (ImageView) findViewById(R.id.search);
        text = (TextView) findViewById(R.id.text);

        searchToBar = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.anim_search_to_bar);
        barToSearch = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.anim_bar_to_search);

        interp = AnimationUtils.loadInterpolator(this, android.R.interpolator.linear_out_slow_in);
        duration = getResources().getInteger(R.integer.duration_bar);
        // iv is sized to hold the search+bar so when only showing the search icon, translate the
        // whole view left by half the difference to keep it centered
        offset = -71f * (int) getResources().getDisplayMetrics().scaledDensity;
        iv.setTranslationX(offset);

    }

    public void animate(View view) {
        if (!expanded) {
            iv.setImageDrawable(searchToBar);
            searchToBar.start();
            iv.animate().translationX(0f).setDuration(duration).setInterpolator(interp);
            text.animate().alpha(1f).setStartDelay(duration - 100).setDuration(100).setInterpolator(interp);
        } else {
            iv.setImageDrawable(barToSearch);
            barToSearch.start();
            iv.animate().translationX(offset).setDuration(duration).setInterpolator(interp);
            text.setAlpha(0f);
        }
        expanded = !expanded;
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        mDrawer.closeDrawer(GravityCompat.START);
        int id = menuItem.getItemId();
        switch (id) {
            case R.id.meun_requestPatch://请求补丁
                requestTinkerPatch();
                break;
            case R.id.meun_requestConfig://请求tinker confige配置信息
                requestConfig();
                break;
            case R.id.meun_cleanPatch://请求补丁
                TinkerPatch.with().cleanAll();
                break;
            case R.id.meun_fingerprint://指纹识别
                setFingerPrint();
                break;
            case R.id.meun_kill://退出
            {
                ShareTinkerInternals.killAllOtherProcess(getApplicationContext());
                android.os.Process.killProcess(android.os.Process.myPid());
            }
            break;

        }
        return true;
    }

    //请求补丁
    public void requestTinkerPatch() {
        TinkerPatch.with().fetchPatchUpdate(true);
    }

    //请求tinker confige配置信息
    public void requestConfig() {
        TinkerPatch.with().fetchDynamicConfig(new ConfigRequestCallback() {

            @Override
            public void onSuccess(HashMap<String, String> configs) {
                TinkerLog.w(TAG, "request config success, config:" + configs);
            }

            @Override
            public void onFail(Exception e) {
                TinkerLog.w(TAG, "request config failed, exception:" + e);
            }
        }, true);
    }

    /**
     * 设置指纹识别 6.0以上系统才支持
     */
    @TargetApi(Build.VERSION_CODES.M)
    private void setFingerPrint() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling  判断有没有指纹识别权限
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Toast.makeText(this, "没有指纹权限", Toast.LENGTH_SHORT).show();
            return;
        }
        FingerprintManager fingerprintmanager = getSystemService(FingerprintManager.class);
//        hasEnrolledFingerprints()判断是否录入至少一个指纹
//        isHardwareDetected() 判断是否有硬件支持
        if (fingerprintmanager.isHardwareDetected() && fingerprintmanager.hasEnrolledFingerprints()) {
            final SweetAlertDialog fingerprintDialog = new SweetAlertDialog(this, SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                    .setTitleText("指纹扫描")
                    .setContentText("请将手指放入感应区！")
                    .setCustomImage(R.drawable.zhiwen)
                    .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismissWithAnimation();
                        }
                    });
            fingerprintDialog.show();

            CancellationSignal cs = new CancellationSignal();  //主动取消控制
            fingerprintmanager.authenticate(null, cs, 0, new FMAuthenticationCallback(this, new FMAuthenticationCallback.FingerprintCallBack() {
                @Override
                public void success() {
                    fingerprintDialog.setTitleText("扫面成功")
                            .setConfirmText("进入")
                            .setConfirmClickListener(null)
                            .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                }

                @Override
                public void again() {
                    fingerprintDialog.setTitleText("扫面成功")
                            .setConfirmText("请再次扫描指纹")
                            .setContentText("请将手指放入感应区！")
                            .setCustomImage(R.drawable.zhiwen);
                }

                @Override
                public void failed() {
                    fingerprintDialog.setTitleText("扫面失败")
                            .setConfirmText("进入")
                            .setConfirmClickListener(null)
                            .changeAlertType(SweetAlertDialog.ERROR_TYPE);
                }
            }), null);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings: //设置
                T.s("设置");
                break;
            case R.id.action_about://关于
                T.s("关于");
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
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