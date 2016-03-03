package com.cmbb.smartkids.topic.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.cmbb.smartkids.framework.api.TopicReplayDetailListModel;
import com.cmbb.smartkids.framework.utils.CustomListener;
import com.cmbb.smartkids.recyclerview.adapter.BaseViewHolder;
import com.cmbb.smartkids.recyclerview.adapter.RecyclerArrayAdapter;
import com.cmbb.smartkids.topic.holder.TopicReplayDetailListItemHolder;


/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/8/24 13:31
 */
public class TopicReplayDetailListAdapter extends RecyclerArrayAdapter<TopicReplayDetailListModel.DataEntity.RowsEntity> {
    private CustomListener.ItemClickListener onReverPreListener;
    private CustomListener.ItemClickListener onReverDeleteListener;

    public CustomListener.ItemClickListener getOnReverDeleteListener() {
        return onReverDeleteListener;
    }

    public void setOnReverDeleteListener(CustomListener.ItemClickListener onReverDeleteListener) {
        this.onReverDeleteListener = onReverDeleteListener;
    }

    public CustomListener.ItemClickListener getOnReverPreListener() {
        return onReverPreListener;
    }

    public void setOnReverPreListener(CustomListener.ItemClickListener onReverPreListener) {
        this.onReverPreListener = onReverPreListener;
    }

    public TopicReplayDetailListAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new TopicReplayDetailListItemHolder(parent, this);
    }
}
