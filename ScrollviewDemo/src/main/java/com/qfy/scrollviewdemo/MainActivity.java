package com.qfy.scrollviewdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private int mMaskColor;
    private View include_toolbar_expand;
    private View include_toolbar_collapse;
    private View v_toolbar_expand_mask;
    private View v_toolbar_collapse_mask;
    private View v_title_expand_mask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppBarLayout abl_bar = findViewById(R.id.abl_bar);
        abl_bar.addOnOffsetChangedListener(mOnOffsetChangedListener);
        mMaskColor = ContextCompat.getColor(this, R.color.colorMain);
        include_toolbar_expand = findViewById(R.id.include_toolbar_expand);
        include_toolbar_collapse = findViewById(R.id.include_toolbar_collpase);
        v_toolbar_expand_mask = findViewById(R.id.v_toolbar_expand_mask);
        v_toolbar_collapse_mask = findViewById(R.id.v_toolbar_collapse_mask);
        v_title_expand_mask = findViewById(R.id.v_title_expand_mask);
    }

    private AppBarLayout.OnOffsetChangedListener mOnOffsetChangedListener = new AppBarLayout.OnOffsetChangedListener() {
        @Override
        public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
            //AppBarLayout竖直方向偏移距离px
            int absVerticalOffset = Math.abs(verticalOffset);
            //AppBarLayout总的距离px
            int totalScrollRange = appBarLayout.getTotalScrollRange();
            //背景颜色转化成RGB的渐变色
            int argb = Color.argb(absVerticalOffset, Color.red(mMaskColor), Color.green(mMaskColor), Color.blue(mMaskColor));
            int argbDouble = Color.argb(absVerticalOffset * 2, Color.red(mMaskColor), Color.green(mMaskColor), Color.blue(mMaskColor));
            //appBarLayout上滑一半距离后小图标应该由渐变到全透明
            int title_small_offset = (200 - absVerticalOffset) < 0 ? 0 : 200 - absVerticalOffset;
            int title_small_argb = Color.argb(title_small_offset * 2, Color.red(mMaskColor),
                    Color.green(mMaskColor), Color.blue(mMaskColor));
            //appBarLayout上滑不到一半距离
            if (absVerticalOffset <= totalScrollRange / 2) {
                include_toolbar_expand.setVisibility(View.VISIBLE);
                include_toolbar_collapse.setVisibility(View.GONE);
                //为了和下面的大图标渐变区分,乘以2倍渐变
                v_toolbar_expand_mask.setBackgroundColor(argbDouble);
            } else {
                include_toolbar_expand.setVisibility(View.GONE);
                include_toolbar_collapse.setVisibility(View.VISIBLE);
                //appBarLayout上滑一半距离后小图标应该由渐变到全透明
                v_toolbar_collapse_mask.setBackgroundColor(title_small_argb);

            }
            //上滑时遮罩由全透明到半透明
            v_title_expand_mask.setBackgroundColor(argb);
        }
    };
}
