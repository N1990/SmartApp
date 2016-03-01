package com.cmbb.smartkids;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cmbb.smartkids.framework.base.Constants;
import com.cmbb.smartkids.login.LoginActivity;
import com.cmbb.smartkids.login.VerifyActivity;
import com.cmbb.smartkids.photopicker.widget.indication.CirclePageIndicator;
import com.umeng.analytics.MobclickAgent;

import java.util.Timer;
import java.util.TimerTask;

public class GuideActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = GuideActivity.class.getSimpleName();
    private Boolean isQuit = false;// 退出应用标识符
    private Timer timer = new Timer();// 程序退出定时器
    private ViewPager vp;
    private CirclePageIndicator cpiGuid;
    private boolean skipFlag;
    private LinearLayout ll_bottom;


    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_guide);
        initView();
    }

    private void initView() {
        ll_bottom = (LinearLayout) findViewById(R.id.ll_bottom);
        vp = (ViewPager) findViewById(R.id.vp_guid);
        cpiGuid = (CirclePageIndicator) findViewById(R.id.cpi_guid);
        SimpleAdapter adapter = new SimpleAdapter();
        vp.setAdapter(adapter);
        cpiGuid.setViewPager(vp);
        cpiGuid.setSnap(true);
        findViewById(R.id.tv_guid_login).setOnClickListener(this);
        findViewById(R.id.tv_guid_register).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_guid_login:
                startActivity(new Intent(GuideActivity.this, LoginActivity.class));
                break;
            case R.id.tv_guid_register:
                Intent intent = new Intent(GuideActivity.this, VerifyActivity.class);
                intent.putExtra("tag", "register");
                startActivity(intent);
                break;
        }
    }

    private int[] imgs = {R.mipmap.guide_bac_01, R.mipmap.guide_bac_02, R.mipmap.guide_bac_03};

    class SimpleAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imgs.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView iv = new ImageView(container.getContext());
            iv.setAdjustViewBounds(true);
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            iv.setAdjustViewBounds(false);
            int img = imgs[position];
            iv.setImageResource(img);
            container.addView(iv);
            return iv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
        }
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

    @Override
    public void onBackPressed() {
        if (!isQuit) {
            isQuit = true;
            Toast.makeText(GuideActivity.this, getString(R.string.quit_app), Toast.LENGTH_SHORT).show();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    isQuit = false;
                }
            };
            timer.schedule(task, 2000);
        } else {
            Intent intent = new Intent(Constants.INTENT_ACTION_EXIT_APP);
            sendBroadcast(intent);
        }
    }

    public static void newIntent(Context context) {
        Intent intent = new Intent(context, GuideActivity.class);
        context.startActivity(intent);
    }

}
