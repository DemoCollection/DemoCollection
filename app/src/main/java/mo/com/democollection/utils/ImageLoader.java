package mo.com.democollection.utils;/**
 * Created by  on
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @创建者 MoMxMo
 * @创时间 2015/11/15:1:50
 * @描述 图片加载工具类
 * @项目名 DemoCollection
 * @版本 $Rev
 * @更新者 $Author
 * @更新时间 $Date
 * @更新描述 TODO
 */
public class ImageLoader {

    private ExecutorService executor = Executors.newFixedThreadPool(3);
    private ImageView mImageView;
    private static   ImageLoader imageLoader = null;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    Bitmap bitmap = (Bitmap) msg.obj;
                    if (mImageView != null) {
                        mImageView.setImageBitmap(bitmap);
                    }
                    break;
                default:
                    break;
            }
        }

    };

    private ImageLoader() {
        super();

    } //加载图片的异步方法，含有回调监听

    /**
     * 获取本类实例对象
     * @return
     */
    public static ImageLoader getInstance() {
        if (imageLoader == null) {
            imageLoader = new ImageLoader();
        }
        return imageLoader;
    }

    public  void loadImage(final ImageView view, final int resource, final int reqWidth, final int reqHeight, final BitmapLoadedListener callback) {
        mImageView = view;
        executor.execute(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = loadBitmapInBackground(resource, reqWidth, reqHeight);
//                putBitmapInMemey(path, bitmap);
                Message msg = mHandler.obtainMessage(1);
                msg.obj = bitmap;
                mHandler.sendMessage(msg);
            }
        });
    }

    private class BitmapLoadedListener {
        public void displayImage(ImageView view,Bitmap bitmap){
            view.setImageBitmap(bitmap);
        }
    }

    /**
     * 处理之后的图片
     *
     * @param resource   资源
     * @param reqWidth  设置的宽
     * @param reqHeight 设置的高
     * @return
     */
    private Bitmap loadBitmapInBackground(int resource, int reqWidth, int reqHeight) {

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;// 获得图片宽度和高度，图片不加载到内存中，图片很大也不会OOM
        Bitmap bm = BitmapFactory.decodeResource(UIUtils.getResources(), resource);

        int pic_height = bm.getHeight();
        int pic_width = bm.getWidth();

        int inSampleSize = 1;
        while (pic_height > reqHeight || pic_width > reqWidth) {
            pic_height = (int) (pic_height / reqHeight + .5f);
            inSampleSize++;
        }
        bm.recycle();
        bm = null;
        options.inJustDecodeBounds = false;// 加载到内存中
        options.inSampleSize = inSampleSize;
        return BitmapFactory.decodeResource(UIUtils.getResources(), resource, options);
    }
}
