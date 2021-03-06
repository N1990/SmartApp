package com.cmbb.smartkids.framework.base;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import com.cmbb.smartkids.framework.R;
import com.cmbb.smartkids.recyclerview.SmartRecyclerView;
import com.cmbb.smartkids.recyclerview.adapter.RecyclerArrayAdapter;


public abstract class SmartActivity extends BaseActivity implements RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnItemClickListener {
    private final String TAG = SmartActivity.class.getSimpleName();
    protected SmartRecyclerView mSmartRecyclerView;
    protected RecyclerArrayAdapter adapter;
    protected boolean openSwipeRefresh = true;


    protected void initRecyclerView() {
        if (initAdapter()) {
            mSmartRecyclerView = (SmartRecyclerView) findViewById(R.id.recyclerView);
            mSmartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mSmartRecyclerView.setAdapterWithProgress(adapter);
            adapter.setMore(R.layout.view_more, this);
            adapter.setNoMore(R.layout.view_nomore);
            adapter.setOnItemClickListener(this);
            /*adapter.setOnItemLongClickListener(new RecyclerArrayAdapter.OnItemLongClickListener() {
                @Override
                public boolean onItemClick(int position) {
                    adapter.remove(position);
                    return true;
                }
            });*/
            /*adapter.setError(R.layout.view_error).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adapter.resumeMore();
                }
            });*/

            if (openSwipeRefresh) {
                mSmartRecyclerView.setRefreshListener(this);
            }
        }
    }

    /**
     * 关闭刷新功能
     *
     * @param flag boolean
     */
    protected void openSwipeRefresh(boolean flag) {
        this.openSwipeRefresh = flag;
    }


    protected abstract boolean initAdapter();

    @Override
    public void onItemClick(int position) {

    }


}
