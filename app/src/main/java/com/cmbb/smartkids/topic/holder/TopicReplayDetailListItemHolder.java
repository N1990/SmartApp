package com.cmbb.smartkids.topic.holder;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.framework.api.TopicReplayDetailListModel;
import com.cmbb.smartkids.framework.utils.TDevice;
import com.cmbb.smartkids.framework.utils.date.JTimeTransform;
import com.cmbb.smartkids.framework.utils.date.RecentDateFormat;
import com.cmbb.smartkids.framework.utils.log.Log;
import com.cmbb.smartkids.recyclerview.adapter.BaseViewHolder;
import com.cmbb.smartkids.topic.adapter.TopicReplayDetailListAdapter;

/**
 * 项目名称：FragmentPager-demo
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/11/5 10:43
 */
public class TopicReplayDetailListItemHolder extends BaseViewHolder<TopicReplayDetailListModel.DataEntity.RowsEntity> implements View.OnClickListener {
    private TextView tv;
    private ImageView iv;
    private TopicReplayDetailListAdapter adapter;


    public TopicReplayDetailListItemHolder(ViewGroup parent, TopicReplayDetailListAdapter adapter) {
        super(parent, R.layout.activity_replay_detail_list_item);
        this.adapter = adapter;
        tv = $(R.id.tv_rever_comment_name_item);
        iv = $(R.id.iv_rever_comment_delete_item);
    }


    /**
     * 详情加评论列表
     *
     * @param data
     */
    public void setData(TopicReplayDetailListModel.DataEntity.RowsEntity data) {
        tv.setTag(data.getImg());
        setReverContent(tv, data);
        switch (data.getUserBasicInfo().getIsLoginUser()) {
            case 1:
                iv.setVisibility(View.VISIBLE);
                iv.setTag(data.getId());
                iv.setOnClickListener(this);
                break;
            case 0:
                iv.setVisibility(View.GONE);
                break;
        }
    }

    private void setReverContent(TextView tv, TopicReplayDetailListModel.DataEntity.RowsEntity data) {
        String source1 = "";
        String name1 = data.getUserBasicInfo().getUserNike() + " : ";
        String title1 = data.getContents() + "  ";
        String time1 = new JTimeTransform(data.getCreateDate()).toString(new RecentDateFormat()) + "  ";
        source1 += name1 + title1 + time1;
        SpannableString ss1 = new SpannableString(source1);
        ss1.setSpan(new ForegroundColorSpan(Color.BLUE), 0, name1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss1.setSpan(new AbsoluteSizeSpan(12, true), name1.length() + title1.length(), name1.length() + title1.length() + time1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        Drawable drawable1 = tv.getContext().getResources().getDrawable(R.mipmap.btn_community_preview_bg);
        drawable1.setBounds(0, 0, TDevice.dip2px(16, tv.getContext()), TDevice.dip2px(16, tv.getContext()));
        if (!TextUtils.isEmpty(data.getImg())) {
            ImageSpan is1 = new ImageSpan(drawable1);
            ss1.setSpan(is1, ss1.length() - 1, ss1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            MyClickableSpan clickSpan1 = new MyClickableSpan();
            ss1.setSpan(clickSpan1, ss1.length() - 1, ss1.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        }
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        tv.setText(ss1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_rever_comment_delete_item:
                if (adapter.getOnReverDeleteListener() != null) {
                    adapter.getOnReverDeleteListener().onItemClick(v, 0, v.getTag());
                }
                break;
        }
    }

    class MyClickableSpan extends ClickableSpan {

        @Override
        public void onClick(View widget) {
            String tag = (String) widget.getTag();
            Log.e("ReverHolder", tag + "111");
            if (adapter.getOnReverPreListener() != null) {
                adapter.getOnReverPreListener().onItemClick(widget, 0, widget.getTag());
            }
        }
    }
}
