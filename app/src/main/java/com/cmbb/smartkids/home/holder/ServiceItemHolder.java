package com.cmbb.smartkids.home.holder;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.framework.api.ServiceListModel;
import com.cmbb.smartkids.framework.utils.Fresco;
import com.cmbb.smartkids.framework.utils.TDevice;
import com.cmbb.smartkids.framework.utils.log.Log;
import com.cmbb.smartkids.home.adapter.ServiceAdapter;
import com.cmbb.smartkids.recyclerview.adapter.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/9/6 17:15
 */
public class ServiceItemHolder extends BaseViewHolder<ServiceListModel.DataEntity.RowsEntity> implements View.OnClickListener {
    private final String TAG = ServiceItemHolder.class.getSimpleName();
    private ServiceAdapter adapter;
    private RelativeLayout llHomeServiceItem;
    private SimpleDraweeView ivHomeServiceItem;
    private TextView tvTitleHomeServiceItem;
    private TextView tvPriceHomeServiceItem;
    private TextView tvCityHomeServiceItem;
    private TextView tvPreviewHomeServiceItem;
    private TextView tvTimeHomeServiceItem;
    private TextView tvTagHomeServiceItem;
    private int position;

    public ServiceItemHolder(ViewGroup parent) {
        super(parent, R.layout.activity_service_list_item);
        llHomeServiceItem = $(R.id.ll_home_service_item);
        ivHomeServiceItem = $(R.id.iv_home_service_item);
        tvTitleHomeServiceItem = $(R.id.tv_title_home_service_item);
        tvPriceHomeServiceItem = $(R.id.tv_price_home_service_item);
        tvPreviewHomeServiceItem = $(R.id.tv_preview_home_service_item);
        tvCityHomeServiceItem = $(R.id.tv_city_home_service_item);
        tvTimeHomeServiceItem = $(R.id.tv_time_home_service_item);
        tvTagHomeServiceItem = $(R.id.tv_home_service_tag_item);

    }

    public void setData(ServiceListModel.DataEntity.RowsEntity row) {
        Log.e(TAG, "setData position : " + position);
        this.adapter = adapter;
        this.position = position;
        llHomeServiceItem.setTag(row);
        llHomeServiceItem.setOnClickListener(this);
        Fresco.loadImage(ivHomeServiceItem, row.getServicesImg(), String.valueOf(TDevice.dip2px(90, ivHomeServiceItem.getContext())));
        tvTitleHomeServiceItem.setText(row.getTitle());
        if (!TextUtils.isEmpty(row.getPrice())) {
            double price = Double.valueOf(row.getPrice());
            tvPriceHomeServiceItem.setText(price != 0 ? "￥" + row.getPrice() : "免费");
        }
        tvPreviewHomeServiceItem.setText(row.getBrowseNumber() + "");
        tvTagHomeServiceItem.setVisibility(View.VISIBLE);
        int statusValue = row.getStatus();
        ServiceStatus status = ServiceStatus.getStatusByValue(statusValue);
        switch (status) {
            case WEI_KAI_SHI:
                tvTagHomeServiceItem.setVisibility(View.GONE);
                break;
            case YI_KAI_SHI:
                tvTagHomeServiceItem.setText("已开始");
                break;
            case YU_DING_ZHONG:
                tvTagHomeServiceItem.setText("报名中");
                break;
            case YI_JIE_SHU:
                tvTagHomeServiceItem.setText("已过期");
                break;
        }
        tvCityHomeServiceItem.setVisibility(View.VISIBLE);
        if (TextUtils.isEmpty(row.getCityText()))
            tvCityHomeServiceItem.setVisibility(View.GONE);
        tvCityHomeServiceItem.setText(row.getCityText());
        try {
            String applyStartTime = TDevice.DataToString(row.getApplyStartTime(), "MM/dd");
            String applyEndTime = TDevice.DataToString(row.getApplyStartTime(), "MM/dd");
            tvTimeHomeServiceItem.setText(applyStartTime + "-" + applyEndTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        /*if (adapter.getOnItemClick() != null)
            adapter.getOnItemClick().onItemClick(v, position, llHomeServiceItem.getTag());*/
    }
}
