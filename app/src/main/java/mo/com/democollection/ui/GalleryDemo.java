package mo.com.democollection.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import mo.com.democollection.R;

/**
 * Grallery测试
 */
public class GalleryDemo extends AppCompatActivity {

    private Gallery mGallery;
    private int[] arr = {R.drawable.cheese_1, R.drawable.cheese_4, R.drawable.cheese_2, R.drawable.cheese_3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_demo);
        mGallery = (Gallery) findViewById(R.id.gallery_photo);
        mGallery.setAdapter(new MyAdapter());
    }

    private class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return arr.length;
        }

        @Override
        public Object getItem(int position) {
            return arr[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView mIv = new ImageView(GalleryDemo.this);
            mIv.setImageResource(arr[position]);
            mIv.setBackgroundColor(0xFFFFFFFF);
            mIv.setScaleType(ImageView.ScaleType.CENTER);// 设置对齐方式
            mIv.setLayoutParams(new Gallery.LayoutParams(Gallery.LayoutParams.WRAP_CONTENT,
                    Gallery.LayoutParams.WRAP_CONTENT));
            return mIv;
        }
    }

}
