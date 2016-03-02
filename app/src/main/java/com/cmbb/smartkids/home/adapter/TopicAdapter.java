package com.cmbb.smartkids.home.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.cmbb.smartkids.framework.api.ServiceListModel;
import com.cmbb.smartkids.home.holder.TopicItemHolder;
import com.cmbb.smartkids.recyclerview.adapter.BaseViewHolder;
import com.cmbb.smartkids.recyclerview.adapter.RecyclerArrayAdapter;


/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/8/24 13:31
 */
public class TopicAdapter extends RecyclerArrayAdapter<ServiceListModel.DataEntity.RowsEntity> {

    public TopicAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new TopicItemHolder(parent);
    }
}
