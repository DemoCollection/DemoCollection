package mo.com.democollection.Fingerprint;

import android.annotation.TargetApi;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.widget.Toast;

/**
 * Created by admin on 2017/2/28.
 */

@TargetApi(Build.VERSION_CODES.M)
public class FMAuthenticationCallback extends FingerprintManager.AuthenticationCallback {
    private Context context;
    private FingerprintCallBack callBack;
    public FMAuthenticationCallback(Context context,FingerprintCallBack callBack) {
        this.context = context;
        this.callBack = callBack;
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        //指纹验证成功
        Toast.makeText(context, "指纹验证成功", Toast.LENGTH_SHORT).show();
        callBack.success();
    }
    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {
        //指纹验证失败，不可再验
        Toast.makeText(context, "指纹验证失败，不可再验"+ errString, Toast.LENGTH_SHORT).show();
        callBack.again();
    }
    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
        //指纹验证失败，可再验，可能手指过脏，或者移动过快等原因。
        Toast.makeText(context, "指纹验证失败，可再验"+ helpString, Toast.LENGTH_SHORT).show();
        callBack.failed();
    }
    @Override
    public void onAuthenticationFailed() {
        //指纹验证失败，指纹识别失败，可再验，该指纹不是系统录入的指纹。
        Toast.makeText(context, "onAuthenticationFailed():不能识别", Toast.LENGTH_SHORT).show();
        callBack.failed();
    }

    public interface FingerprintCallBack{
        void success();
        void again();
        void failed();
    }
}
