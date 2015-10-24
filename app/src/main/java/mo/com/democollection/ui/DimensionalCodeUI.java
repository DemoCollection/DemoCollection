package mo.com.democollection.ui;/**
 * Created by  on
 */

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.Hashtable;

import mo.com.democollection.R;
import mo.com.democollection.zxing.MipcaActivityCapture;
import mo.com.democollection.utils.UIUtils;

/**
 * @创建者 MoMxMo
 * @创时间 2015/10/22:23:49
 * @描述 二维码相关操作
 * @项目名 DemoCollection
 * @版本 $Rev
 * @更新者 $Author
 * @更新时间 $Date
 * @更新描述 TODO
 */
public class DimensionalCodeUI extends AppCompatActivity {

    public static final int SCANNIN_GREQUEST_CODE = 0;
    private TextView mResult;
    private ImageView iv_code;
    private EditText mUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dimesional_code_ui);
        mResult = (TextView) findViewById(R.id.result);
        iv_code = (ImageView) findViewById(R.id.iv_code);
        mUrl = (EditText) findViewById(R.id.et_url);
    }

    /**
     * 扫描二维码
     */
    public void scanCode(View view) {

        Toast.makeText(UIUtils.getContext(), "此功能还未实现", Toast.LENGTH_SHORT).show();


        Intent intent = new Intent();
        intent.setClass(UIUtils.getContext(), MipcaActivityCapture.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivityForResult(intent, SCANNIN_GREQUEST_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SCANNIN_GREQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    //显示扫描到的内容
                    mResult.setText(bundle.getString("result"));
                    //显示
                    iv_code.setImageBitmap((Bitmap) data.getParcelableExtra("bitmap"));
                }
                break;
        }
    }


    /**
     * 生成二维码
     */
    public void createCode(View view) {
        try {

            int QR_WIDTH = 180;
            int QR_HEIGHT = 180;

            String url = mUrl.getText().toString().trim();
            //判断URL合法性
            if (url == null || "".equals(url) || url.length() < 1) {
                Toast.makeText(UIUtils.getContext(), "请输入内容才能生成二维码", Toast.LENGTH_SHORT).show();
                return;
            }
            Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            //图像数据转换，使用了矩阵转换
            BitMatrix bitMatrix = new QRCodeWriter().encode(url, BarcodeFormat.QR_CODE, QR_WIDTH, QR_HEIGHT, hints);
            int[] pixels = new int[QR_WIDTH * QR_HEIGHT];
            //下面这里按照二维码的算法，逐个生成二维码的图片，
            //两个for循环是图片横列扫描的结果
            for (int y = 0; y < QR_HEIGHT; y++) {
                for (int x = 0; x < QR_WIDTH; x++) {
                    if (bitMatrix.get(x, y)) {
                        pixels[y * QR_WIDTH + x] = 0xff000000;
                    } else {
                        pixels[y * QR_WIDTH + x] = 0xffffffff;
                    }
                }
            }
            //生成二维码图片的格式，使用ARGB_8888
            Bitmap bitmap = Bitmap.createBitmap(QR_WIDTH, QR_HEIGHT, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, QR_WIDTH, 0, 0, QR_WIDTH, QR_HEIGHT);
            //显示到一个ImageView上面
            iv_code.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }


}
