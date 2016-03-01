package com.cmbb.smartkids.home;

import android.os.Bundle;

import com.cmbb.smartkids.framework.base.SmartActivity;

/**
 * 项目名称：SmartApp
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：16/3/1 下午4:08
 */
public class MeActivity extends SmartActivity {
    @Override
    protected boolean initAdapter() {
        return false;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onRefresh() {

    }
}
