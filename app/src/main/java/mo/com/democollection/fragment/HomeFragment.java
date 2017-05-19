package mo.com.democollection.fragment;
/**
 * Created by  on
 */

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import mo.com.democollection.MainActivity;
import mo.com.democollection.R;
import mo.com.democollection.base.BaseFragment;
import mo.com.democollection.base.BaseHolder;
import mo.com.democollection.base.Loadingpager;
import mo.com.democollection.base.SuperAdapter;
import mo.com.democollection.holder.HomeHolder;
import mo.com.democollection.model.Item;
import mo.com.democollection.temp.OnlineTransDemo;
import mo.com.democollection.temp.RSAHelper;
import mo.com.democollection.temp.RSAKeyUtil;
import mo.com.democollection.temp.SignUtil_lj;
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
public class HomeFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener {

    private static final String TAG = "HomeFragment";
    private ListView mListView;
    public static List<Item> mListData;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    /**
     * 初始化加载数据
     *
     * @return
     */
    @Override
    protected Loadingpager.LoadedResult initData() {
        String[] dataStrings = Cheeses.sCheeseStrings;
        mListData = new ArrayList<Item>();
        for (int i = 0; i < dataStrings.length; i++) {
            mListData.add(new Item(Cheeses.getRandomCheeseDrawable(), dataStrings[i], ""));
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
        mListView.setOnItemClickListener(this);

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
        //设置下拉刷新监听事件
        mSwipeRefreshLayout.setOnRefreshListener(this);
        //设置进度条的颜色
        mSwipeRefreshLayout.setColorSchemeColors(R.color.primary, R.color.primary, R.color.colorAccent);
        //设置圆形进度条大小
        mSwipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);
        //设置进度条背景颜色
        mSwipeRefreshLayout.setProgressBackgroundColorSchemeColor(Color.YELLOW);
        //设置下拉多少距离之后开始刷新数据
        mSwipeRefreshLayout.setDistanceToTriggerSync(50);

        return view;
    }

    @Override
    public void onRefresh() {
//        testSign();
//        try {
////            tem();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        UIUtils.postTaskSafely(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 2000);
    }

    public void testSign() {
        Map<String, String> map = new HashMap<>();
        map.put("bankCode", "30050000");
        map.put("seqNo", "264338");
        map.put("txTime", "092944");
        map.put("channel", "000002");
        map.put("retCode", "JX900015");
        map.put("version", "10");
        map.put("retMsg", "FES系统验签失败");
        map.put("accountId", "6212461270000004701");
        map.put("instCode", "00770001");
        map.put("txCode", "balanceQuery");
        map.put("txDate", "20170307");

        //生成待签名字符串
        String requestMapMerged = OnlineTransDemo.mergeMap(map);
        //生成签名
        String sign = SignUtil_lj.sign(getContext(), requestMapMerged);

        boolean is = SignUtil_lj.verify(getContext(), sign, requestMapMerged);

        Log.e("加密sign-----", "" + sign);
    }

    public void tem() throws Exception {
        Map<String, String> map = new HashMap<>();
//        map.put("version","10");
//        map.put("instCode","00770001");
//        map.put("bankCode","30050000");
//        map.put("txDate","20170307");
//        map.put("txTime","092944");
//        map.put("channel","000002");
//        map.put("seqNo","264338");
//        map.put("txCode","balanceQuery");
//        map.put("accountId","6212461270000004701");
/*
        map.put("bankCode","30050000");
        map.put("seqNo","264338");
        map.put("txTime","092944");
        map.put("channel","000002");
        map.put("retCode","JX900015");
        map.put("version","10");
        map.put("retMsg","FES系统验签失败");
        map.put("accountId","6212461270000004701");
        map.put("instCode","00770001");
        map.put("txCode","balanceQuery");
        map.put("txDate","20170307");
*/

        String data = "{\n" +
                "  \"bankCode\": \"30050000\",\n" +
                "  \"acctUse\": \"\",\n" +
                "  \"seqNo\": \"264338\",\n" +
                "  \"txTime\": \"092944\",\n" +
                "  \"sign\": \"una720aM4VoHBkotEZvNRDnDS/wcSEeULlAb1TDz9xp6lvTO6vSa+WQn2mhYSGtoDL/3sjQvMFGgpj2yD5gRdE/CShaH/e837n+Qyp5IECkBL3Kp/IldcL+y3610tPzvF0mtJglep7OMLQGJnFfE4yy467kALODVoKIFtsAdbqo=\",\n" +
                "  \"channel\": \"000002\",\n" +
                "  \"retCode\": \"JX900101\",\n" +
                "  \"version\": \"10\",\n" +
                "  \"retMsg\": \"账户不存在\",\n" +
                "  \"availBal\": \"\",\n" +
                "  \"currBal\": \"\",\n" +
                "  \"accountId\": \"6212461270000004701\",\n" +
                "  \"withdrawFlag\": \"\",\n" +
                "  \"name\": \"\",\n" +
                "  \"instCode\": \"00770001\",\n" +
                "  \"txCode\": \"balanceQuery\",\n" +
                "  \"accType\": \"\",\n" +
                "  \"txDate\": \"20170307\"\n" +
                "}";

        JSONObject obj = new JSONObject(data);
        Iterator it = obj.keys();
        while (it.hasNext()) {
            String key = (String) it.next();
            String value = obj.getString(key);
            map.put(key, value);
        }


        //生成待签名字符串

        String srcData = OnlineTransDemo.mergeMap(map);
//        String srcData = "30050000264338092944000002JX90010110账户不存在621246127000000470100770001balanceQuery20170307";
//        String srcData = "5800300500002015092216161600000700000220150922161616O1013503211989100612341442977591026";
//        String srcData = "30050000230251094447000002JX90001510FES系统验签失败621246127000000470100770001balanceQuery20170306";

        //RSAHelper signHelper = new RSAHelper(new File("S:/work/dev/openssl/_cedit2go/certs/signature.crt"));
        //signHelper.setPublicCertFile("S:/work/dev/openssl/_cedit2go/certs/signature.crt");
        //signHelper.setPrivatePKCS12File("file:///S:/work/dev/openssl/_cedit2go/certs/signature.p12", "credit2go");
        InputStream open = getContext().getAssets().open("damailicai_sit.p12");
        RSAKeyUtil ru = new RSAKeyUtil(open, "damailicai_sit@2016");
        Log.e("内容-----", "" + srcData);

        RSAHelper signHelper = new RSAHelper(ru.getPrivateKey());
        String signText = signHelper.sign(srcData);
        Log.e("签名-----", "" + signText);

//        InputStream crt = getContext().getAssets().open("damailicai_sit.crt");
        InputStream crt = getContext().getAssets().open("fdep.crt");
        RSAKeyUtil ru2 = new RSAKeyUtil(crt);

//        signText = "IzOoJsRnPuNvs2B+HbwTOaEsE96SLa9fd6/9/w4G5yavCFXYUI8X0saX6w+IFXHlCgRd0WMagIdBucufNIeayac7v/ogPB//+2aLrJaNwmAA4FK9YNjvC+p6UdAPXz2YaE+FoQIg0hZxTLUGKPibuIjVV/A95Egzdc8jeCOdPEo=";
        signText = "una720aM4VoHBkotEZvNRDnDS/wcSEeULlAb1TDz9xp6lvTO6vSa+WQn2mhYSGtoDL/3sjQvMFGgpj2yD5gRdE/CShaH/e837n+Qyp5IECkBL3Kp/IldcL+y3610tPzvF0mtJglep7OMLQGJnFfE4yy467kALODVoKIFtsAdbqo=";
        signHelper = new RSAHelper(ru2.getPublicKey());
        boolean v = signHelper.verify(srcData, signText);
//        System.out.println("验签: "+v);
        Log.e("验签-----", "" + v);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ((MainActivity) getActivity()).startActivity(view, position);
    }

    private class HomeFragmentAdapter extends SuperAdapter<Item> {
        public HomeFragmentAdapter(AbsListView adsListView, List<Item> resData) {
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