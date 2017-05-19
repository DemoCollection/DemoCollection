package mo.com.democollection.anim;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import mo.com.democollection.R;

/**
 * Created by admin on 2017/2/15.
 */
public class ShoppingCartAdapter extends BaseAdapter implements View.OnClickListener{

    private int[] imgs;
    private FoodActionCallback foodActionCallback;
    private Context context;
    public ShoppingCartAdapter(Context context, int[] imgs, FoodActionCallback foodActionCallback) {
        this.context = context;
        this.imgs = imgs;
        this.foodActionCallback = foodActionCallback;

    }

    @Override
    public int getCount() {
        return this.imgs.length;
    }

    @Override
    public Object getItem(int position) {
        return this.imgs[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.shopping_cart_item, null);
            holder = new ViewHolder();
            holder.add = (ImageView) convertView.findViewById(R.id.add);
            holder.icon = (ImageView) convertView.findViewById(R.id.icon);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Picasso.with(context)
                .load(imgs[position])
                .fit().centerCrop()
                .into(holder.icon);
        holder.add.setOnClickListener(this);
       return convertView;
    }

    @Override
    public void onClick(View v) {
        if (foodActionCallback != null) {
            foodActionCallback.addAction(v);
        }
    }

    public static class ViewHolder{
        ImageView icon;
        ImageView add;
    }
    public interface FoodActionCallback {
        void addAction(View view);
    }
}
