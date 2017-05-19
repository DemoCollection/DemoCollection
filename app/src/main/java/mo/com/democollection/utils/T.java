package mo.com.democollection.utils;

import android.widget.Toast;

import mo.com.democollection.base.SampleApplicationLike;

/**
 * Toast工具类
 */

public class T {
    private static Toast toast;
    public static void s(String content) {
        if (toast == null) {
            toast = Toast.makeText(SampleApplicationLike.getContext(), content, Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        toast.show();
    }
    public static void l(String content) {
        if (toast == null) {
            toast = Toast.makeText(SampleApplicationLike.getContext(), content, Toast.LENGTH_LONG);
        } else {
            toast.setText(content);
            toast.setDuration(Toast.LENGTH_LONG);
        }
        toast.show();
    }
}
