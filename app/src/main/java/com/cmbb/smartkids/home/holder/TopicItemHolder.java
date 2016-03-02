package com.cmbb.smartkids.home.holder;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.framework.api.TopicListModel;
import com.cmbb.smartkids.framework.utils.Fresco;
import com.cmbb.smartkids.framework.utils.TDevice;
import com.cmbb.smartkids.framework.utils.date.JTimeTransform;
import com.cmbb.smartkids.framework.utils.date.RecentDateFormat;
import com.cmbb.smartkids.home.adapter.TopicAdapter;
import com.cmbb.smartkids.recyclerview.adapter.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/11/3 11:59
 */
public class TopicItemHolder extends BaseViewHolder<TopicListModel.DataEntity.RowsEntity> implements View.OnClickListener {
    private SimpleDraweeView ivHeader, iv1, iv2, iv3;
    private ImageView ivTag1, ivTag2;
    private TextView tvName, tvIdentity, tvTag, tvTitle, tvContent, tvTime, tvPerssion, tvPre;

    private TopicAdapter adapter;
    private View root;
    private int position;

    public TopicItemHolder(ViewGroup parent) {
        super(parent, R.layout.activity_topic_list_item);
        this.root = itemView;
        ivHeader = $(R.id.iv_community_user_header_item);
        iv1 = $(R.id.iv_community_content_first_item);
        iv2 = $(R.id.iv_community_content_second_item);
        iv3 = $(R.id.iv_community_content_third_item);
        ivTag1 = $(R.id.iv_community_title_item_one);
        ivTag2 = $(R.id.iv_community_title_item_two);
        tvName = $(R.id.tv_community_user_nickname_item);
        tvIdentity = $(R.id.tv_community_user_identity_item);
        tvTag = $(R.id.tv_community_tag_item);
        tvTitle = $(R.id.tv_community_title_item);
        tvContent = $(R.id.tv_community_content_item);
        tvTime = $(R.id.tv_community_time_item);
        tvPerssion = $(R.id.tv_community_care_item);
        tvPre = $(R.id.tv_community_comment_item);
    }

    public void setData(TopicListModel.DataEntity.RowsEntity data) {
        this.root.setOnClickListener(this);
        if (data == null) return;
        this.root.setTag(data);
        if (!TextUtils.isEmpty(data.getUserBasicInfo().getUserSmallImg())) {
            Fresco.loadImage(ivHeader, data.getUserBasicInfo().getUserSmallImg(), data.getUserBasicInfo().getUserSmallWidth() + "", data.getUserBasicInfo().getUserSmallHeight() + "");
        } else {
            ivHeader.setImageURI(null);
        }
        if (!TextUtils.isEmpty(data.getUserBasicInfo().getUserNike())) {
            tvName.setText(data.getUserBasicInfo().getUserNike());
        } else {
            tvName.setText(data.getUserBasicInfo().getUserPhone());
        }
        tvIdentity.setText(data.getUserBasicInfo().getUserRole().get(0).getEredarName());
        tvTag.setText(data.getTopicType());
        if (data.getIsStick() == 1) {
            ivTag1.setVisibility(View.VISIBLE);
        } else {
            ivTag1.setVisibility(View.GONE);
        }
        if (data.getIsEnssence() == 1) {
            ivTag2.setVisibility(View.VISIBLE);
        } else {
            ivTag2.setVisibility(View.GONE);
        }
        tvTitle.setText(data.getTitle());
        if (TextUtils.isEmpty(data.getContents())) {
            tvContent.setVisibility(View.GONE);
        } else {
            tvContent.setVisibility(View.VISIBLE);
            tvContent.setText(data.getContents());
        }
        if (data.getTopicImgList() != null && data.getTopicImgList().size() >= 3) {
            iv1.setVisibility(View.VISIBLE);
            iv2.setVisibility(View.VISIBLE);
            iv3.setVisibility(View.VISIBLE);
            Fresco.loadImage(iv1, data.getTopicImgList().get(0).getImg(), TDevice.dip2px(50, iv1.getContext()) + "");
            Fresco.loadImage(iv2, data.getTopicImgList().get(1).getImg(), TDevice.dip2px(50, iv2.getContext()) + "");
            Fresco.loadImage(iv3, data.getTopicImgList().get(2).getImg(), TDevice.dip2px(50, iv3.getContext()) + "");
        } else if (data.getTopicImgList() != null && data.getTopicImgList().size() == 2) {
            iv1.setVisibility(View.VISIBLE);
            iv2.setVisibility(View.VISIBLE);
            iv3.setVisibility(View.INVISIBLE);
            Fresco.loadImage(iv1, data.getTopicImgList().get(0).getImg(), TDevice.dip2px(50, iv1.getContext()) + "");
            Fresco.loadImage(iv2, data.getTopicImgList().get(1).getImg(), TDevice.dip2px(50, iv2.getContext()) + "");
        } else if (data.getTopicImgList() != null && data.getTopicImgList().size() == 1) {
            iv1.setVisibility(View.VISIBLE);
            iv2.setVisibility(View.INVISIBLE);
            iv3.setVisibility(View.INVISIBLE);
            Fresco.loadImage(iv1, data.getTopicImgList().get(0).getImg(), TDevice.dip2px(50, iv1.getContext()) + "");
        } else if (data.getTopicImgList() != null && data.getTopicImgList().size() == 0) {
            iv1.setVisibility(View.GONE);
            iv2.setVisibility(View.GONE);
            iv3.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(data.getPublishDate())) {
            tvTime.setVisibility(View.VISIBLE);
            tvTime.setText(new JTimeTransform(data.getPublishDate()).toString(new RecentDateFormat()));
        } else {
            tvTime.setVisibility(View.GONE);
        }
        tvPerssion.setText(data.getBrowseNumber() + "");
        tvPre.setText(data.getReplys() + "");
    }

    @Override
    public void onClick(View v) {
        /*if (adapter.getOnItemListener() != null)
            adapter.getOnItemListener().onItemClick(v, position, v.getTag());*/
    }
}
