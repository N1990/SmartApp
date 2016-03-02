package com.cmbb.smartkids.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.framework.widget.rollingviewpager.RollPagerView;
import com.cmbb.smartkids.framework.widget.rollingviewpager.adapter.LoopPagerAdapter;

/**
 * 项目名称：SmartApp
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：16/3/2 上午9:38
 */
public class GuideLoopAdapter extends LoopPagerAdapter {

    private int[] imgs = {
            R.mipmap.guide_bac_01,
            R.mipmap.guide_bac_02,
            R.mipmap.guide_bac_03
    };

    public GuideLoopAdapter(RollPagerView viewPager) {
        super(viewPager);
    }

    @Override
    public View getView(ViewGroup container, int position) {
        ImageView view = new ImageView(container.getContext());
        view.setImageResource(imgs[position]);
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return view;
    }

    @Override
    public int getRealCount() {
        return imgs.length;
    }

}
