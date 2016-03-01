package com.cmbb.smartkids.framework.base;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.cmbb.smartkids.framework.R;
import com.cmbb.smartkids.framework.api.okhttp.OkHttpClientManager;
import com.cmbb.smartkids.framework.utils.ExitBroadcast;
import com.cmbb.smartkids.framework.utils.log.Log;
import com.umeng.message.PushAgent;

/**
 * 项目名称：SmartApp
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：16/3/1 下午3:44
 */
public abstract class BaseActivity extends AppCompatActivity {
    private final String TAG = BaseActivity.class.getSimpleName();

    protected ActionBar toolbar;
    public static PushAgent mPushAgent;
    private BroadcastReceiver existReceiver;// EXIT

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//关闭横屏
        setContentView(getLayoutId());
        initActionBar();
        mPushAgent = PushAgent.getInstance(this);
        mPushAgent.onAppStart();
        initExit();
        initRecyclerView();
        init(savedInstanceState);
    }

    protected void initRecyclerView() {
    }


    /**
     * 初始化actionbar
     */
    private void initActionBar() {
        try {
            Toolbar v = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(v);
            toolbar = getSupportActionBar();
            if (toolbar != null) {
                toolbar.setDisplayHomeAsUpEnabled(true);
                toolbar.setDisplayShowTitleEnabled(false);
            }
        } catch (NullPointerException e) {
            Log.e(TAG, "actionbar is null");
        }
    }


    protected abstract void init(Bundle savedInstanceState);

    protected abstract int getLayoutId();

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
