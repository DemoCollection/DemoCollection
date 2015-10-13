package mo.com.democollection.factory;/**
 * Created by  on
 */


import java.util.HashMap;
import java.util.Map;

import mo.com.democollection.base.BaseFragment;
import mo.com.democollection.fragment.AppFragment;
import mo.com.democollection.fragment.HomeFragment;
import mo.com.democollection.utils.LogUtils;


/**
 * @创建者 MoMxMo
 * @创时间 2015/10/6:19:42
 * @描述 Fragment创建工厂
 * @项目名 GooglePlay
 * @版本 $Rev
 * @更新者 $Author
 * @更新时间 $Date
 * @更新描述 TODO
 */
public class FragmentFactory {

    public static final int FRAGMENT_HOME = 0;
    public static final int FRAGMENT_APP = 1;
    private static final String TAG = "FragmentFactory";

    /*使用集合将Fragment缓存*/
   private static Map<Integer, BaseFragment> fragmentsMap = new HashMap<Integer, BaseFragment>();
    /**
     * 创建对应的Fragment
     *
     * @param position
     * @return
     */
    public static BaseFragment createFragment(int position) {
        //1.从缓存中获取
        if (fragmentsMap.containsKey(position)) {
            return fragmentsMap.get(position);
        }
        LogUtils.i(TAG, "加载" + position);
        BaseFragment fragment = null;
        switch (position) {
            case FRAGMENT_HOME:
                fragment = new HomeFragment();
                break;
            case FRAGMENT_APP:
                fragment = new AppFragment();
                break;
            default:
                break;
        }

        /**
         * 2.保存到缓存中
         */
        fragmentsMap.put(position, fragment);
        return fragment;
    }
}
