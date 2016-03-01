package com.cmbb.smartkids.framework.base;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.cmbb.smartkids.framework.R;
import com.cmbb.smartkids.framework.api.okhttp.OkHttpClientManager;
import com.cmbb.smartkids.framework.utils.ExitBroadcast;
import com.cmbb.smartkids.framework.utils.log.Log;
import com.cmbb.smartkids.recyclerview.SmartRecyclerView;
import com.umeng.analytics.MobclickAgent;
import com.umeng.message.PushAgent;


public abstract class SmartActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = SmartActivity.class.getSimpleName();
    private ActionBar actionbar;
    public static PushAgent mPushAgent;
    private BroadcastReceiver existReceiver;// EXIT

    protected SmartRecyclerView mSmartRecyclerView;

    protected RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//关闭横屏
        setContentView(getLayoutId());
        initActionBar();
        mPushAgent = PushAgent.getInstance(this);
        mPushAgent.onAppStart();
        initExit();
        init(savedInstanceState);
    }


    /**
     * 初始化actionbar
     */
    private void initActionBar() {
        try {
            Toolbar v = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(v);
            actionbar = getSupportActionBar();
            if (actionbar != null) {
                actionbar.setDisplayHomeAsUpEnabled(true);
                actionbar.setDisplayShowTitleEnabled(false);
            }
        } catch (NullPointerException e) {
            Log.e(TAG, "actionbar is null");
        }
    }


    protected abstract void init(Bundle savedInstanceState);

    protected abstract int getLayoutId();


    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * 程序退出
     */
    private void initExit() {
        existReceiver = new ExitBroadcast(this);
        IntentFilter filter = new IntentFilter(Constants.INTENT_ACTION_EXIT_APP);
        registerReceiver(existReceiver, filter);
    }


    protected void showToast(String tip) {
        Toast.makeText(this, tip, Toast.LENGTH_LONG).show();
    }

    protected void showShortToast(String tip) {
        Toast.makeText(this, tip, Toast.LENGTH_LONG).show();
    }


    /**
     * 取消请求
     */
    protected void cancelRequest() {
        OkHttpClientManager.mOkHttpClient.cancel(Constants.BASE_URL);
        finish();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(existReceiver);
    }
}
