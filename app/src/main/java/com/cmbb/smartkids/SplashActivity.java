package com.cmbb.smartkids;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.cmbb.smartkids.framework.base.Constants;
import com.cmbb.smartkids.framework.utils.SPCache;
import com.cmbb.smartkids.framework.utils.log.Log;
import com.umeng.analytics.MobclickAgent;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;

public class SplashActivity extends AppCompatActivity {

    public PushAgent mPushAgent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_splash);
        mPushAgent = PushAgent.getInstance(this);
        mPushAgent.onAppStart();
        mPushAgent.enable(mRegisterCallback);
        PushAgent.getInstance(this).setMergeNotificaiton(false); //接收多条信息
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //判断Token
                String token = SPCache.getString(Constants.TOKEN, "");
                if (TextUtils.isEmpty(token)) {
                    GuideActivity.newIntent(SplashActivity.this);
                } else {
                    HomeActivity.newIntent(SplashActivity.this);
                }
                finish();
            }
        }, 2000);
    }


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


    // 友盟推送注册器
    private IUmengRegisterCallback mRegisterCallback = new IUmengRegisterCallback() {
        @Override
        public void onRegistered(String registrationId) {
            Log.e("mRegisterCallback", "token:" + mPushAgent.getRegistrationId());
        }
    };
}
