package com.cmbb.smartkids.home.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.framework.api.HomeBannerModel;
import com.cmbb.smartkids.framework.utils.Fresco;
import com.cmbb.smartkids.framework.utils.TDevice;
import com.cmbb.smartkids.framework.widget.rollingviewpager.RollPagerView;
import com.cmbb.smartkids.framework.widget.rollingviewpager.adapter.LoopPagerAdapter;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：SmartApp
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：16/3/2 上午9:38
 */
public class BannerLoopAdapter extends LoopPagerAdapter implements View.OnClickListener {
    private List<HomeBannerModel.DataEntity> imgs;

    public BannerLoopAdapter(RollPagerView viewPager, List<HomeBannerModel.DataEntity> dataEntities) {
        super(viewPager);
        if (dataEntities == null) {
            this.imgs = new ArrayList<>();
        } else {
            this.imgs = dataEntities;
        }
    }

    public void update(List<HomeBannerModel.DataEntity> dataEntities) {
        if (dataEntities != null && dataEntities.size() >= 0) {
            imgs.clear();
            imgs.addAll(dataEntities);
            notifyDataSetChanged();
        }
    }

    @Override
    public View getView(ViewGroup container, int position) {
        SimpleDraweeView iv = (SimpleDraweeView) LayoutInflater.from(container.getContext()).inflate(R.layout.activity_home_banner_image, null);
        HomeBannerModel.DataEntity img = imgs.get(position);
        Fresco.loadImage(iv, img.getImg(), String.valueOf(TDevice.dip2px(180, container.getContext())));
        iv.setTag(position);
        iv.setOnClickListener(this);
        return iv;
    }

    @Override
    public int getRealCount() {
        return imgs.size();
    }

    @Override
    public void onClick(View v) {

    }
}
