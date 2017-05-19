package mo.com.democollection.activity;

import android.animation.Animator;
import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.transition.Explode;
import android.transition.Fade;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.Window;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import mo.com.democollection.R;
import mo.com.democollection.fragment.HomeFragment;
import mo.com.democollection.model.Item;

/**
 * Created by admin on 2017/3/1.
 */

public class DetailActivity extends Activity {

    ImageView pic;
    TextView tv_title;
    TextView tv_cotent;
    private BottomSheetBehavior<View> bottomSheetBehavior;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set Explode enter transition animation for current activity
        //另外的三种Activity过渡动画
//        的实现：
//
//        explode(分解) ———从屏幕中间进或出，移动视图。
//
//        slide(滑动)———从屏幕边缘进或出，移动视图。
//
//        fade(淡出)———通过改变屏幕上视图的不透明度达到添加或移除的效果
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setEnterTransition(new Explode().setDuration(300));
        getWindow().setExitTransition(new Fade());

        setContentView(R.layout.detail_layout2);

        pic = (ImageView) findViewById(R.id.detail_pic);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_cotent = (TextView) findViewById(R.id.tv_cotent);
        FloatingActionButton btn = (FloatingActionButton) findViewById(R.id.detail_btn);
        int position = getIntent().getIntExtra("pos", 0);
        // set detail info
        pic.setTransitionName(position + "transitionImg");
        tv_title.setTransitionName(position + "tv_title");
//        tv_cotent.setTransitionName(position + "tv_cotent");

        List<Item> data = HomeFragment.mListData;
        Item item = data.get(position);

        tv_title.setText(item.title);
        pic.setImageResource(item.pic_id);
        // set action bar title

        bottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.tab_layout));
        // floating action button
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                } else {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }

                // set first animation
                Animator animator = createAnimation(pic, true);
                animator.start();
                animator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
            }
        });
    }

    /**
     * create CircularReveal animation with first and second sequence
     */
    public Animator createAnimation(View v, Boolean isFirst) {

        Animator animator;

        if (isFirst) {
            animator = ViewAnimationUtils.createCircularReveal(
                    v,
                    v.getWidth() / 2,
                    v.getHeight() / 2,
                    v.getWidth(),
                    0);
        } else {
            animator = ViewAnimationUtils.createCircularReveal(
                    v,
                    v.getWidth() / 2,
                    v.getHeight() / 2,
                    0,
                    v.getWidth());
        }

        animator.setInterpolator(new DecelerateInterpolator());
        animator.setDuration(500);
        return animator;
    }


}


