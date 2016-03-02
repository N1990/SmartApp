package com.cmbb.smartkids.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.framework.api.TopicListModel;
import com.cmbb.smartkids.framework.api.TopicTypeModel;
import com.cmbb.smartkids.framework.api.okhttp.OkHttpClientManager;
import com.cmbb.smartkids.framework.base.BaseApplication;
import com.cmbb.smartkids.framework.base.SmartActivity;
import com.cmbb.smartkids.framework.utils.CustomListener;
import com.cmbb.smartkids.home.adapter.TopicAdapter;
import com.cmbb.smartkids.home.adapter.TopicTypeAdapter;
import com.cmbb.smartkids.recyclerview.SmartRecyclerView;
import com.squareup.okhttp.Request;

/**
 * 项目名称：SmartApp
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：16/3/1 下午4:17
 */
public class HomeTopicActivity extends SmartActivity implements View.OnClickListener {

    //Bottom
    private TextView tvHome;
    private TextView tvService;
    private TextView tvTopic;
    private TextView tvMe;
    private TextView tvMore;


    private int pager = 0;
    private int pagerSize = 10;

    PopupWindow mSmartPopupWindow;
    private TopicTypeAdapter topicTypeAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_topic;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        ((TextView) findViewById(R.id.tv_title)).setText("全部话题");
        findViewById(R.id.tv_title).setOnClickListener(this);
        toolbar.setDisplayHomeAsUpEnabled(false);
        initBottom();
        initRecyclerView();
        initPopup();
        onRefresh();
    }

    private void initBottom() {
        tvHome = (TextView) findViewById(R.id.tv_home);
        tvService = (TextView) findViewById(R.id.tv_service);
        tvTopic = (TextView) findViewById(R.id.tv_topic);
        tvTopic.setSelected(true);
        tvMe = (TextView) findViewById(R.id.tv_me);
        tvMore = (TextView) findViewById(R.id.tv_more);
        tvHome.setOnClickListener(this);
        tvService.setOnClickListener(this);
        tvTopic.setOnClickListener(this);
        tvMe.setOnClickListener(this);
        tvMore.setOnClickListener(this);
    }

    SmartRecyclerView popuRecyclerView;

    private void initPopup() {
        View view = LayoutInflater.from(this).inflate(R.layout.popup_topic_type, null);
        popuRecyclerView = (SmartRecyclerView) view.findViewById(R.id.popu_recycler);
        popuRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        topicTypeAdapter = new TopicTypeAdapter(this);
        topicTypeAdapter.setOnItemListener(onTypeListener);
        popuRecyclerView.setAdapter(topicTypeAdapter);
        // 初始化全部数据
        TopicTypeModel.DataEntity allTopic = new TopicTypeModel.DataEntity();
        allTopic.setName("全部话题");
        allTopic.setValue(null);
        topicTypeAdapter.add(allTopic);
        TopicTypeModel.getTopicTypeRequest(new OkHttpClientManager.ResultCallback<TopicTypeModel>() {
            @Override
            public void onError(Request request, Exception e) {
                showShortToast(e.toString());
            }

            @Override
            public void onResponse(TopicTypeModel response) {
                if (response != null) {
                    topicTypeAdapter.addAll(response.getData());
                }
            }
        });
        mSmartPopupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mSmartPopupWindow.setOutsideTouchable(true);
        mSmartPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mSmartPopupWindow.setTouchable(true);
    }

    private CustomListener.ItemClickListener onTypeListener = new CustomListener.ItemClickListener() {
        @Override
        public void onItemClick(View v, int position, Object object) {
            topicTypeAdapter.setSelectIndex(position);
            TopicTypeModel.DataEntity data = (TopicTypeModel.DataEntity) object;
            ((TextView) findViewById(R.id.tv_title)).setText(data.getName());
            mSmartPopupWindow.dismiss();
            onRefresh();
        }
    };

    @Override
    protected boolean initAdapter() {
        adapter = new TopicAdapter(this);
        return true;
    }


    @Override
    public void onLoadMore() {
        pager++;
        TopicListModel.getTopicListRequest(topicTypeAdapter.getSelectData().getValue(), pager, pagerSize, BaseApplication.token, new OkHttpClientManager.ResultCallback<TopicListModel>() {
            @Override
            public void onError(Request request, Exception e) {
                showShortToast(e.toString());
            }

            @Override
            public void onResponse(TopicListModel response) {
                if (response != null) {
                    adapter.addAll(response.getData().getRows());
                }
            }
        });
    }

    @Override
    public void onRefresh() {
        pager = 0;
        TopicListModel.getTopicListRequest(topicTypeAdapter.getSelectData().getValue(), pager, pagerSize, BaseApplication.token, new OkHttpClientManager.ResultCallback<TopicListModel>() {
            @Override
            public void onError(Request request, Exception e) {
                showShortToast(e.toString());
            }

            @Override
            public void onResponse(TopicListModel response) {
                if (response != null) {
                    if (response.getData().getRows().size() > 0) {
                        adapter.clear();
                    }
                    adapter.addAll(response.getData().getRows());
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_home:
                HomeActivity.newIntent(this);
                break;
            case R.id.tv_service:
                HomeServiceActivity.newIntent(this);
                break;
            case R.id.tv_topic:
                HomeTopicActivity.newIntent(this);
                break;
            case R.id.tv_me:
                HomeMeActivity.newIntent(this);
                break;
            case R.id.tv_more:
                HomeMoreActivity.newIntent(this);
                break;
            case R.id.tv_title:
                mSmartPopupWindow.showAsDropDown(findViewById(R.id.tv_title));
                break;
        }
    }

    public static void newIntent(Context context) {
        Intent intent = new Intent(context, HomeTopicActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(intent);
    }
}
