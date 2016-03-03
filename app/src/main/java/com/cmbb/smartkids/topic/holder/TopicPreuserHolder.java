package com.cmbb.smartkids.topic.holder;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.framework.api.TopicDetailModel;
import com.cmbb.smartkids.framework.utils.Fresco;
import com.cmbb.smartkids.framework.utils.date.JTimeTransform;
import com.cmbb.smartkids.framework.utils.date.RecentDateFormat;
import com.cmbb.smartkids.topic.adapter.TopicDetailAdapter;
import com.facebook.drawee.view.SimpleDraweeView;


/**
 * 项目名称：FragmentPager-demo
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/11/5 10:45
 */
public class TopicPreuserHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TopicDetailAdapter adapter;
    private int position = 0;
    private View root;
    private TextView tvTime, tvPraise;
    private ImageView ivPraise;
    private HorizontalScrollView hsv;
    private LinearLayout llContainer;

    private SimpleDraweeView spot01, spot02, spot03, spot04, spot05;

    public TopicPreuserHolder(View itemView) {
        super(itemView);
        this.root = itemView;
        tvTime = (TextView) itemView.findViewById(R.id.tv_community_time_third_item);
        tvPraise = (TextView) itemView.findViewById(R.id.tv_community_praise_third_item);
        ivPraise = (ImageView) itemView.findViewById(R.id.iv_community_praise_third_item);
        hsv = (HorizontalScrollView) itemView.findViewById(R.id.hsv_community_users_third_item);
        llContainer = (LinearLayout) itemView.findViewById(R.id.ll_community_users_third_item);
        spot01 = (SimpleDraweeView) itemView.findViewById(R.id.iv_community_header_spot_01);
        spot02 = (SimpleDraweeView) itemView.findViewById(R.id.iv_community_header_spot_02);
        spot03 = (SimpleDraweeView) itemView.findViewById(R.id.iv_community_header_spot_03);
        spot04 = (SimpleDraweeView) itemView.findViewById(R.id.iv_community_header_spot_04);
        spot05 = (SimpleDraweeView) itemView.findViewById(R.id.iv_community_header_spot_05);
    }

    public void setData(TopicDetailModel data, final TopicDetailAdapter adapter) {
        this.adapter = adapter;
        switch (data.getData().getIsSpot()) {
            case 0:
                ivPraise.setBackgroundResource(R.mipmap.btn_community_praise_normal);
                break;
            case 1:
                ivPraise.setBackgroundResource(R.mipmap.btn_community_praise_pressed);
                break;
        }
        ivPraise.setTag(data);
        ivPraise.setOnClickListener(this);
        tvTime.setText(new JTimeTransform(data.getData().getPublishDate()).toString(new RecentDateFormat()));
        tvPraise.setText(data.getData().getSpots() + "");
        switch (data.getData().getSpotList().size()) {
            case 0:
                llContainer.setVisibility(View.GONE);
                break;
            case 1:
                llContainer.setVisibility(View.VISIBLE);
                spot01.setVisibility(View.VISIBLE);
                spot02.setVisibility(View.GONE);
                spot03.setVisibility(View.GONE);
                spot04.setVisibility(View.GONE);
                spot05.setVisibility(View.GONE);
                spot01.setTag(data.getData().getSpotList().get(0).getId());
                spot01.setOnClickListener(this);
                if (!TextUtils.isEmpty(data.getData().getSpotList().get(0).getUserSmallImg())) {
                    Fresco.loadImage(spot01, data.getData().getSpotList().get(0).getUserSmallImg(), data.getData().getSpotList().get(0).getUserSmallWidth() + "", data.getData().getSpotList().get(0).getUserSmallHeight() + "");
                } else {
                    spot01.setImageURI(null);
                }
                break;
            case 2:
                llContainer.setVisibility(View.VISIBLE);
                spot01.setVisibility(View.VISIBLE);
                spot02.setVisibility(View.VISIBLE);
                spot03.setVisibility(View.GONE);
                spot04.setVisibility(View.GONE);
                spot05.setVisibility(View.GONE);
                spot01.setTag(data.getData().getSpotList().get(0).getCreateUserId());
                spot02.setTag(data.getData().getSpotList().get(1).getCreateUserId());
                spot01.setOnClickListener(this);
                spot02.setOnClickListener(this);
                if (!TextUtils.isEmpty(data.getData().getSpotList().get(0).getUserSmallImg())) {
                    Fresco.loadImage(spot01, data.getData().getSpotList().get(0).getUserSmallImg(), data.getData().getSpotList().get(0).getUserSmallWidth() + "", data.getData().getSpotList().get(0).getUserSmallHeight() + "");
                } else {
                    spot01.setImageURI(null);
                }
                if (!TextUtils.isEmpty(data.getData().getSpotList().get(1).getUserSmallImg())) {
                    Fresco.loadImage(spot02, data.getData().getSpotList().get(1).getUserSmallImg(), data.getData().getSpotList().get(1).getUserSmallWidth() + "", data.getData().getSpotList().get(1).getUserSmallHeight() + "");
                } else {
                    spot02.setImageURI(null);
                }
                break;
            case 3:
                llContainer.setVisibility(View.VISIBLE);
                spot01.setVisibility(View.VISIBLE);
                spot02.setVisibility(View.VISIBLE);
                spot03.setVisibility(View.VISIBLE);
                spot04.setVisibility(View.GONE);
                spot05.setVisibility(View.GONE);
                spot01.setTag(data.getData().getSpotList().get(0).getId());
                spot02.setTag(data.getData().getSpotList().get(1).getId());
                spot03.setTag(data.getData().getSpotList().get(2).getId());
                spot01.setOnClickListener(this);
                spot02.setOnClickListener(this);
                spot03.setOnClickListener(this);
                if (!TextUtils.isEmpty(data.getData().getSpotList().get(0).getUserSmallImg())) {
                    Fresco.loadImage(spot01, data.getData().getSpotList().get(0).getUserSmallImg(), data.getData().getSpotList().get(0).getUserSmallWidth() + "", data.getData().getSpotList().get(0).getUserSmallHeight() + "");
                } else {
                    spot01.setImageURI(null);
                }
                if (!TextUtils.isEmpty(data.getData().getSpotList().get(1).getUserSmallImg())) {
                    Fresco.loadImage(spot02, data.getData().getSpotList().get(1).getUserSmallImg(), data.getData().getSpotList().get(1).getUserSmallWidth() + "", data.getData().getSpotList().get(1).getUserSmallHeight() + "");
                } else {
                    spot02.setImageURI(null);
                }
                if (!TextUtils.isEmpty(data.getData().getSpotList().get(2).getUserSmallImg())) {
                    Fresco.loadImage(spot03, data.getData().getSpotList().get(2).getUserSmallImg(), data.getData().getSpotList().get(2).getUserSmallWidth() + "", data.getData().getSpotList().get(2).getUserSmallHeight() + "");
                } else {
                    spot03.setImageURI(null);
                }
                break;
            case 4:
                llContainer.setVisibility(View.VISIBLE);
                spot01.setVisibility(View.VISIBLE);
                spot02.setVisibility(View.VISIBLE);
                spot03.setVisibility(View.VISIBLE);
                spot04.setVisibility(View.VISIBLE);
                spot05.setVisibility(View.GONE);
                spot01.setTag(data.getData().getSpotList().get(0).getId());
                spot02.setTag(data.getData().getSpotList().get(1).getId());
                spot03.setTag(data.getData().getSpotList().get(2).getId());
                spot04.setTag(data.getData().getSpotList().get(3).getId());
                spot01.setOnClickListener(this);
                spot02.setOnClickListener(this);
                spot03.setOnClickListener(this);
                spot04.setOnClickListener(this);
                if (!TextUtils.isEmpty(data.getData().getSpotList().get(0).getUserSmallImg())) {
                    Fresco.loadImage(spot01, data.getData().getSpotList().get(0).getUserSmallImg(), data.getData().getSpotList().get(0).getUserSmallWidth() + "", data.getData().getSpotList().get(0).getUserSmallHeight() + "");
                } else {
                    spot01.setImageURI(null);
                }
                if (!TextUtils.isEmpty(data.getData().getSpotList().get(1).getUserSmallImg())) {
                    Fresco.loadImage(spot02, data.getData().getSpotList().get(1).getUserSmallImg(), data.getData().getSpotList().get(1).getUserSmallWidth() + "", data.getData().getSpotList().get(1).getUserSmallHeight() + "");
                } else {
                    spot02.setImageURI(null);
                }
                if (!TextUtils.isEmpty(data.getData().getSpotList().get(2).getUserSmallImg())) {
                    Fresco.loadImage(spot03, data.getData().getSpotList().get(2).getUserSmallImg(), data.getData().getSpotList().get(2).getUserSmallWidth() + "", data.getData().getSpotList().get(2).getUserSmallHeight() + "");
                } else {
                    spot03.setImageURI(null);
                }
                if (!TextUtils.isEmpty(data.getData().getSpotList().get(3).getUserSmallImg())) {
                    Fresco.loadImage(spot04, data.getData().getSpotList().get(3).getUserSmallImg(), data.getData().getSpotList().get(3).getUserSmallWidth() + "", data.getData().getSpotList().get(3).getUserSmallHeight() + "");
                } else {
                    spot04.setImageURI(null);
                }
                break;
            case 5:
                llContainer.setVisibility(View.VISIBLE);
                spot01.setVisibility(View.VISIBLE);
                spot02.setVisibility(View.VISIBLE);
                spot03.setVisibility(View.VISIBLE);
                spot04.setVisibility(View.VISIBLE);
                spot05.setVisibility(View.VISIBLE);
                spot01.setTag(data.getData().getSpotList().get(0).getId());
                spot02.setTag(data.getData().getSpotList().get(1).getId());
                spot03.setTag(data.getData().getSpotList().get(2).getId());
                spot04.setTag(data.getData().getSpotList().get(3).getId());
                spot05.setTag(data.getData().getSpotList().get(4).getId());
                spot01.setOnClickListener(this);
                spot02.setOnClickListener(this);
                spot03.setOnClickListener(this);
                spot04.setOnClickListener(this);
                spot05.setOnClickListener(this);
                if (!TextUtils.isEmpty(data.getData().getSpotList().get(0).getUserSmallImg())) {
                    Fresco.loadImage(spot01, data.getData().getSpotList().get(0).getUserSmallImg(), data.getData().getSpotList().get(0).getUserSmallWidth() + "", data.getData().getSpotList().get(0).getUserSmallHeight() + "");
                } else {
                    spot01.setImageURI(null);
                }
                if (!TextUtils.isEmpty(data.getData().getSpotList().get(1).getUserSmallImg())) {
                    Fresco.loadImage(spot02, data.getData().getSpotList().get(1).getUserSmallImg(), data.getData().getSpotList().get(1).getUserSmallWidth() + "", data.getData().getSpotList().get(1).getUserSmallHeight() + "");
                } else {
                    spot02.setImageURI(null);
                }
                if (!TextUtils.isEmpty(data.getData().getSpotList().get(2).getUserSmallImg())) {
                    Fresco.loadImage(spot03, data.getData().getSpotList().get(2).getUserSmallImg(), data.getData().getSpotList().get(2).getUserSmallWidth() + "", data.getData().getSpotList().get(2).getUserSmallHeight() + "");
                } else {
                    spot03.setImageURI(null);
                }
                if (!TextUtils.isEmpty(data.getData().getSpotList().get(3).getUserSmallImg())) {
                    Fresco.loadImage(spot04, data.getData().getSpotList().get(3).getUserSmallImg(), data.getData().getSpotList().get(3).getUserSmallWidth() + "", data.getData().getSpotList().get(3).getUserSmallHeight() + "");
                } else {
                    spot04.setImageURI(null);
                }
                if (!TextUtils.isEmpty(data.getData().getSpotList().get(4).getUserSmallImg())) {
                    Fresco.loadImage(spot05, data.getData().getSpotList().get(4).getUserSmallImg(), data.getData().getSpotList().get(4).getUserSmallWidth() + "", data.getData().getSpotList().get(4).getUserSmallHeight() + "");
                } else {
                    spot05.setImageURI(null);
                }
                break;
        }


        root.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (adapter.getOnEveryListener() != null) {
            adapter.getOnEveryListener().onItemClick(v, -1, -1);
        }
        switch (v.getId()) {
            case R.id.iv_community_header_spot_01:
            case R.id.iv_community_header_spot_02:
            case R.id.iv_community_header_spot_03:
            case R.id.iv_community_header_spot_04:
            case R.id.iv_community_header_spot_05:
                if (adapter.getOnPreUserListener() != null)
                    adapter.getOnPreUserListener().onItemClick(v, position, position);
                break;
            case R.id.iv_community_praise_third_item:
                if (adapter.getOnPraiseListener() != null)
                    adapter.getOnPraiseListener().onClick(v);
                break;
        }


    }
}
