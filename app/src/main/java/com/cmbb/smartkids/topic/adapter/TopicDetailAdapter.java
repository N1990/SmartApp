package com.cmbb.smartkids.topic.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.framework.api.TopicDetailModel;
import com.cmbb.smartkids.framework.api.TopicReplayModel;
import com.cmbb.smartkids.framework.utils.CustomListener;
import com.cmbb.smartkids.topic.holder.TopicHeaderHolder;
import com.cmbb.smartkids.topic.holder.TopicPreuserHolder;
import com.cmbb.smartkids.topic.holder.TopicReplayListHolder;
import com.cmbb.smartkids.topic.holder.TopicTeletextHolder;

/**
 * 项目名称：FragmentPager-demo
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/11/5 10:14
 */
public class TopicDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int HEADER = -1;
    private final int TELETEXT = -2;
    private final int PREUSERS = -3;
    private final int COMMENTS = -4;
    private TopicDetailModel detailData;
    private TopicReplayModel replayData;
    private CustomListener.ItemClickListener onTeletextListener;
    private View.OnClickListener onHeaderListener;
    private View.OnClickListener onPraiseListener;
    private CustomListener.ItemClickListener onCommentHeaderListener;
    private CustomListener.ItemClickListener onPreUserListener;
    private CustomListener.ItemClickListener onReverListener;
    private CustomListener.ItemClickListener onMoreListener;
    private CustomListener.ItemClickListener onMoreReverListener;
    private CustomListener.ItemClickListener onReverDeepListener;
    private CustomListener.ItemClickListener onPreCommentListener;
    private CustomListener.ItemClickListener onPreReverListener;
    private CustomListener.ItemClickListener onReverDelListener;
    private CustomListener.ItemClickListener onReverHeaderListener;
    private CustomListener.ItemClickListener onReverNickListener;
    private CustomListener.ItemClickListener onEveryListener;


    public void setDate(TopicDetailModel detailData, TopicReplayModel replayData) {
        if (detailData != null) {
            this.detailData = detailData;
        } else {
            this.detailData = new TopicDetailModel();
        }

        if (replayData != null) {
            this.replayData = replayData;
        } else {
            this.replayData = new TopicReplayModel();
        }
        notifyDataSetChanged();
    }


    public void updateData(TopicReplayModel replayData) {
        if (replayData != null) {
            this.replayData = replayData;
        } else {
            return;
        }
        notifyDataSetChanged();
    }


    public TopicDetailModel getDetailData() {
        return detailData;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEADER;
        } else if (position > 0 && position <= detailData.getData().getTopicImgList().size()) {
            return TELETEXT;
        } else if (position == detailData.getData().getTopicImgList().size() + 1) {
            return PREUSERS;
        } else {
            return COMMENTS;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEADER) {
            View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_topic_detail_head_01, parent, false);
            return new TopicHeaderHolder(root);
        } else if (viewType == TELETEXT) {
            View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_topic_detail_list_item, parent, false);
            return new TopicTeletextHolder(root);
        } else if (viewType == PREUSERS) {
            View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_topic_detail_spot, parent, false);
            return new TopicPreuserHolder(root);
        } else {
            View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_topic_detail_replay_list_item, parent, false);
            return new TopicReplayListHolder(root);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TopicHeaderHolder) {
            ((TopicHeaderHolder) holder).setData(detailData, this, position);
        } else if (holder instanceof TopicTeletextHolder) {
            int pos = position - 1;
            ((TopicTeletextHolder) holder).setData(detailData, this, pos);
        } else if (holder instanceof TopicPreuserHolder) {
            int pos = position - detailData.getData().getTopicImgList().size() - 1;
            ((TopicPreuserHolder) holder).setData(detailData, this);
        } else if (holder instanceof TopicReplayListHolder) {
            int pos = position - detailData.getData().getTopicImgList().size() - 2;
            ((TopicReplayListHolder) holder).setData(replayData, this, pos);
        }
    }

    @Override
    public int getItemCount() {
        if (detailData.getData() != null) {
            return replayData.getData().getRows().size() + 2 + detailData.getData().getTopicImgList().size();
        } else {
            return 2 + detailData.getData().getTopicImgList().size();
        }
    }

    public CustomListener.ItemClickListener getOnEveryListener() {
        return onEveryListener;
    }

    public void setOnEveryListener(CustomListener.ItemClickListener onEveryListener) {
        this.onEveryListener = onEveryListener;
    }

    public CustomListener.ItemClickListener getOnTeletextListener() {
        return onTeletextListener;
    }

    public void setOnTeletextListener(CustomListener.ItemClickListener onTeletextListener) {
        this.onTeletextListener = onTeletextListener;
    }


    public CustomListener.ItemClickListener getOnPreUserListener() {
        return onPreUserListener;
    }

    public void setOnPreUserListener(CustomListener.ItemClickListener onPreUserListener) {
        this.onPreUserListener = onPreUserListener;
    }

    public View.OnClickListener getOnPraiseListener() {
        return onPraiseListener;
    }

    public void setOnPraiseListener(View.OnClickListener onPraiseListener) {
        this.onPraiseListener = onPraiseListener;
    }

    public CustomListener.ItemClickListener getOnReverListener() {
        return onReverListener;
    }

    public void setOnReverListener(CustomListener.ItemClickListener onReverListener) {
        this.onReverListener = onReverListener;
    }

    public CustomListener.ItemClickListener getOnMoreListener() {
        return onMoreListener;
    }

    public void setOnMoreListener(CustomListener.ItemClickListener onMoreListener) {
        this.onMoreListener = onMoreListener;
    }

    public CustomListener.ItemClickListener getOnMoreReverListener() {
        return onMoreReverListener;
    }

    public void setOnMoreReverListener(CustomListener.ItemClickListener onMoreReverListener) {
        this.onMoreReverListener = onMoreReverListener;
    }

    public CustomListener.ItemClickListener getOnReverDeepListener() {
        return onReverDeepListener;
    }

    public void setOnReverDeepListener(CustomListener.ItemClickListener onReverDeepListener) {
        this.onReverDeepListener = onReverDeepListener;
    }

    public CustomListener.ItemClickListener getOnPreCommentListener() {
        return onPreCommentListener;
    }

    public void setOnPreCommentListener(CustomListener.ItemClickListener onPreCommentListener) {
        this.onPreCommentListener = onPreCommentListener;
    }

    public CustomListener.ItemClickListener getOnPreReverListener() {
        return onPreReverListener;
    }

    public void setOnPreReverListener(CustomListener.ItemClickListener onPreReverListener) {
        this.onPreReverListener = onPreReverListener;
    }

    public CustomListener.ItemClickListener getOnReverDelListener() {
        return onReverDelListener;
    }

    public void setOnReverDelListener(CustomListener.ItemClickListener onReverDelListener) {
        this.onReverDelListener = onReverDelListener;
    }

    public CustomListener.ItemClickListener getOnReverHeaderListener() {
        return onReverHeaderListener;
    }

    public void setOnReverHeaderListener(CustomListener.ItemClickListener onReverHeaderListener) {
        this.onReverHeaderListener = onReverHeaderListener;
    }

    public CustomListener.ItemClickListener getOnCommentHeaderListener() {
        return onCommentHeaderListener;
    }

    public void setOnCommentHeaderListener(CustomListener.ItemClickListener onCommentHeaderListener) {
        this.onCommentHeaderListener = onCommentHeaderListener;
    }

    public View.OnClickListener getOnHeaderListener() {
        return onHeaderListener;
    }

    public void setOnHeaderListener(View.OnClickListener onHeaderListener) {
        this.onHeaderListener = onHeaderListener;
    }

    public CustomListener.ItemClickListener getOnReverNickListener() {
        return onReverNickListener;
    }

    public void setOnReverNickListener(CustomListener.ItemClickListener onReverNickListener) {
        this.onReverNickListener = onReverNickListener;
    }


}
