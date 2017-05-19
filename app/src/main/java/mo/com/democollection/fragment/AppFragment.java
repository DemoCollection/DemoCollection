package mo.com.democollection.fragment;/**
 * Created by  on
 */

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import mo.com.democollection.MainActivity;
import mo.com.democollection.R;
import mo.com.democollection.anim.ShoppingCartActivity;
import mo.com.democollection.anim.SnowrainActivity;
import mo.com.democollection.app_activity.MovieActivity;
import mo.com.democollection.app_activity.NetWork_Activity;
import mo.com.democollection.base.BaseFragment;
import mo.com.democollection.base.Loadingpager;
import mo.com.democollection.cardIo.CardIoActivity;
import mo.com.democollection.timeselector.TimeSeletorSample;
import mo.com.democollection.ui.DimensionalCodeUI;
import mo.com.democollection.ui.GalleryDemo;
import mo.com.democollection.ui.MusicUI;
import mo.com.democollection.ui.SenSorUI;
import mo.com.democollection.ui.WebMoviUI;
import mo.com.democollection.ui.WebViewHtml5Js_demo;
import mo.com.democollection.utils.UIUtils;
import mo.com.democollection.wave.WaveviewSample;


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
public class AppFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    private GridView mGv;
    private static final String TAG = "HomeFragment";
    private String[] mAppList;
    private AppListAdapter mAdapter;
    private int[] icon;

    /**
     * 初始化加载数据
     *
     * @return
     */
    @Override
    protected Loadingpager.LoadedResult initData() {

        mAppList = UIUtils.getResources().getStringArray(R.array.app_list);

        icon = new int[]{R.drawable.finder, R.drawable.movies,
                R.drawable.antivirus, R.drawable.email,
                R.drawable.steam, R.drawable.minecraft
                , R.drawable.minecraft, R.drawable.minecraft,
                R.drawable.minecraft, R.drawable.minecraft,
                R.drawable.minecraft, R.drawable.finder,
                R.drawable.finder, R.drawable.finder,
                R.drawable.minecraft, R.drawable.steam
                , R.drawable.minecraft};
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

        View view = View.inflate(UIUtils.getContext(), R.layout.app_gridview, null);
        mGv = (GridView) view.findViewById(R.id.app_collection);
        mAdapter = new AppListAdapter();
        mGv.setAdapter(mAdapter);
        mGv.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                /*网页视频*/
                preActivity(WebMoviUI.class);
                break;
            case 1:
                /*手机视频*/
                preActivity(MovieActivity.class);
                break;

            case 2:
                /*音乐*/
                preActivity(MusicUI.class);
                break;

            case 3:
                /*通知*/
                ToggleNotification();
                break;

            case 4:
                /*分享*/
                shared();
                break;
            case 5:
                /*二维码*/
                preActivity(DimensionalCodeUI.class);
                break;
            case 6:
                /*传感器的使用*/
                preActivity(SenSorUI.class);
                break;
            case 7:
                /*数据加密*/
//                preActivity(EncryptUI.class);
                break;
            case 8:
                /*HttpClient网络数据访问*/
                preActivity(NetWork_Activity.class);
                break;
            case 9:
                /*html5 js网页交互*/
                preActivity(WebViewHtml5Js_demo.class);
                break;
            case 10:
                /*android画廊*/
                preActivity(GalleryDemo.class);
                break;
            case 11:
               /*下雪*/
                preActivity(SnowrainActivity.class);
                break;
            case 12:
              /*购物车*/
                preActivity(ShoppingCartActivity.class);

                break;
            case 13:
              /*银行卡号码扫描*/
                preActivity(CardIoActivity.class);
                break;
            case 14:
              /*水波球*/
                preActivity(WaveviewSample.class);
                break;
            case 15:
              /*时间选择日期*/
                preActivity(TimeSeletorSample.class);
                break;
            default:
                break;
        }
    }

    public PendingIntent getDefalutIntent(int flags) {
        Intent intent = new Intent(UIUtils.getContext(), MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(UIUtils.getContext(), 1, intent, flags);
        return pendingIntent;
    }

    /**
     * 状态栏通知
     */
    private void ToggleNotification() {

        if (Build.VERSION.SDK_INT >= 16) {
            //高版本代码
            //从系统服务中 得到通知管理者
            NotificationManager manager = (NotificationManager) UIUtils.getContext().
                    getSystemService(UIUtils.getContext().NOTIFICATION_SERVICE);
            //定义一个通知：标题。内容。大图片。小图片
     /*       Notification notification = new Notification.Builder(UIUtils.getContext())
                    .setContentTitle("我是标题")
                    .setContentText("我是内容")
                    .setSmallIcon(R.drawable.mo)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.mo))
                    .build();*/

            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(UIUtils.getContext());
            mBuilder.setContentTitle("测试标题")//设置通知栏标题
                    .setContentText("测试内容") //设置通知栏显示内容
                    .setContentIntent(getDefalutIntent(Notification.FLAG_AUTO_CANCEL)) //设置通知栏点击意图
                    //	.setNumber(number) //设置通知集合的数量
                    .setTicker("测试通知来啦") //通知首次出现在通知栏，带上升动画效果的
                    .setWhen(System.currentTimeMillis())//通知产生的时间，会在通知信息里显示，一般是系统获取到的时间
                    .setPriority(Notification.PRIORITY_DEFAULT) //设置该通知优先级
                    //	.setAutoCancel(true)//设置这个标志当用户单击面板就可以让通知将自动取消
                    .setOngoing(false)//ture，设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐)或以某种方式正在等待,因此占用设备(如一个文件下载,同步操作,主动网络连接)
                    .setDefaults(Notification.DEFAULT_VIBRATE)//向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合
                    //Notification.DEFAULT_ALL  Notification.DEFAULT_SOUND 添加声音 // requires VIBRATE permission
                    .setSmallIcon(R.drawable.mo);//设置通知小ICON

            Notification notification = mBuilder.build();
            notification.flags = Notification.FLAG_AUTO_CANCEL;


            //显示通知
            manager.notify(1, notification);
        } else {
/*            //低版本兼容的通知
            NotificationManager manager = (NotificationManager) UIUtils.getContext().
                    getSystemService(UIUtils.getContext().NOTIFICATION_SERVICE);
            Notification notification =
                    new Notification(R.mipmap.ic_launcher,      //状态栏显示的图标
                            "你有一条新的短信信息",           //状态栏显示的信息
                            System.currentTimeMillis());
        //指定跳转的界面
            Intent intent = new Intent();
            intent.setClassName("com.android.mms", "com.android.mms.ui.ConversationList");
        //定义一个PengdingIntent 对象，里面指定点击之后，跳转到什么界面
            PendingIntent contentIntent = PendingIntent.getActivities(UIUtils.getContext(), 1, new Intent[]{intent}, 0);

            //把通知拖拉下来之后显示的内容以及点击之后的去向
            notification.setLatestEventInfo(UIUtils.getContext(),"9958","尊敬的张先生," +
                    "您的尾号8976的VIP金卡收到了一笔网银转账交易，金额为：3,000,000.00 ,当前的活期余额为: 860,800,900.00," +
                    "感谢您使用中国农业银行.【农业银行深圳支行】",contentIntent);
            //显示通知
            manager.notify(1,notification);*/
        }

    }


    /**
     * 分享
     */
    private void shared() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, "这是分享的内容");
        intent.setType("text/plain");
        startActivity(Intent.createChooser(intent, "分享到"));
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

    private class AppListAdapter extends BaseAdapter {

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