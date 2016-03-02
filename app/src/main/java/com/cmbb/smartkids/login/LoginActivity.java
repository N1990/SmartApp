package com.cmbb.smartkids.login;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.framework.api.UserAssistInfoModel;
import com.cmbb.smartkids.framework.api.UserAssistModel;
import com.cmbb.smartkids.framework.api.okhttp.OkHttpClientManager;
import com.cmbb.smartkids.framework.base.BaseActivity;
import com.cmbb.smartkids.framework.base.BaseApplication;
import com.cmbb.smartkids.framework.base.Constants;
import com.cmbb.smartkids.framework.db.DBContent;
import com.cmbb.smartkids.framework.db.DBHelper;
import com.cmbb.smartkids.framework.utils.SPCache;
import com.cmbb.smartkids.framework.utils.ShareUtils;
import com.cmbb.smartkids.framework.utils.TDevice;
import com.cmbb.smartkids.framework.utils.log.Log;
import com.cmbb.smartkids.home.HomeActivity;
import com.squareup.okhttp.Request;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.controller.listener.SocializeListeners;
import com.umeng.socialize.exception.SocializeException;
import com.umeng.socialize.sso.UMSsoHandler;

import java.util.Map;

/**
 * 项目名称：SmartApp
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：16/3/1 下午3:43
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private final String TAG = LoginActivity.class.getSimpleName();

    private ImageView ivLoginPhoneClear;
    private ImageView ivLoginPwdClear;
    private TextView tvLoginSubmit;
    private TextView tvForget;
    private LinearLayout llLoginBottom;
    private ImageView ivLoginWx;
    private ImageView ivLoginQq;
    private ImageView ivLoginSina;
    private EditText etLoginPhone;
    private EditText etLoginPwd;

    ProgressDialog progressDialog;

    private UMSocialService mController;
    private SHARE_MEDIA media;
    private String uid;
    private String userName;
    private DBHelper dbHelper;  //临时逻辑
    private int platform = 0;

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        dbHelper = new DBHelper(this);
        mController = ShareUtils.instanceOf(this);
    }

    private void initView() {
        ((TextView) findViewById(R.id.tv_title)).setText("登陆");
        ivLoginPhoneClear = (ImageView) findViewById(R.id.iv_login_phone_clear);
        ivLoginPwdClear = (ImageView) findViewById(R.id.iv_login_pwd_clear);
        tvLoginSubmit = (TextView) findViewById(R.id.tv_login_submit);
        tvForget = (TextView) findViewById(R.id.tv_forget);
        llLoginBottom = (LinearLayout) findViewById(R.id.ll_login_bottom);
        ivLoginWx = (ImageView) findViewById(R.id.iv_login_wx);
        ivLoginQq = (ImageView) findViewById(R.id.iv_login_qq);
        ivLoginSina = (ImageView) findViewById(R.id.iv_login_sina);
        etLoginPhone = (EditText) findViewById(R.id.et_login_phone);
        etLoginPwd = (EditText) findViewById(R.id.et_login_pwd);
        tvLoginSubmit.setOnClickListener(this);
        tvForget.setOnClickListener(this);
        ivLoginWx.setOnClickListener(this);
        ivLoginQq.setOnClickListener(this);
        ivLoginSina.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login_submit:
                platform = 0;
                String phone = etLoginPhone.getText().toString().trim();
                String pwd = etLoginPwd.getText().toString().trim();
                handleLogin(phone, pwd);
                break;
            case R.id.tv_forget:
                break;
            case R.id.iv_login_wx:
                platform = 2;
                media = SHARE_MEDIA.WEIXIN;
                ShareUtils.addWXPlatform();
                authToPlatform();
                break;
            case R.id.iv_login_qq:
                platform = 1;
                media = SHARE_MEDIA.QQ;
                ShareUtils.addQQQZonePlatform();
                authToPlatform();
                break;
            case R.id.iv_login_sina:
                platform = 3;
                media = SHARE_MEDIA.SINA;
                authToPlatform();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        progressDialog.dismiss();
    }

    private void handleLogin(String phone, String pwd) {
        if (platform == 0) {
            if (TextUtils.isEmpty(phone)) {
                showToast("请输入手机号码");
                return;
            }
            if (!TDevice.isMobileNo(phone)) {
                showToast("请输入正确的手机号码");
                return;
            }
            if (TextUtils.isEmpty(pwd)) {
                showToast("请输入密码");
                return;
            }
        }
        progressDialog = ProgressDialog.show(this, "提示", "正在登陆中");

        UserAssistModel.handleLoginRequest(platform, phone, pwd, uid, userName, new OkHttpClientManager.ResultCallback<UserAssistModel>() {
            @Override
            public void onError(Request request, Exception e) {
                progressDialog.dismiss();
                showShortToast(e.toString());
            }

            @Override
            public void onResponse(UserAssistModel response) {
                if (response != null) {
                    //删除表
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    dbHelper.delete(db);
                    UserAssistInfoModel user = response.getData();
                    SPCache.putString(Constants.TOKEN, user.getToken());
                    SPCache.putString(Constants.USER_ID, user.getUserId() + "");
                    // 更改Application里面的值
                    BaseApplication.token = user.getToken();
                    // 发送信息注册Alias
                    Intent intent = new Intent(BaseApplication.PUSH_ALIAS_ITENTACTION);
                    intent.putExtra("umeng_id", user.getUserId() + "_" + TDevice.getDeviceId(LoginActivity.this));
                    intent.putExtra("umeng_type", "service");
                    LocalBroadcastManager.getInstance(LoginActivity.this).sendBroadcast(intent);
                    //写入数据库
                    ContentValues valus = new ContentValues();
                    valus.put(DBContent.DBUser.USER_TOKEN, user.getToken());
                    valus.put(DBContent.DBUser.USER_ID, user.getUserId());
                    valus.put(DBContent.DBUser.USER_HEAD_IMG, user.getUserSmallImg());
                    valus.put(DBContent.DBUser.USER_NICK_NAME, user.getUserNike());
                    valus.put(DBContent.DBUser.USER_MALE, user.getUserSex());
                    valus.put(DBContent.DBUser.USER_PHONE, user.getUserPhone());
                    valus.put(DBContent.DBUser.USER_PROVINCE_ID, user.getProvince());
                    valus.put(DBContent.DBUser.USER_CITY_ID, user.getCity());
                    valus.put(DBContent.DBUser.USER_AREA_ID, user.getDistrict());
                    valus.put(DBContent.DBUser.USER_IS_POPMAN, user.getIsEredar());
                    valus.put(DBContent.DBUser.USER_INTRODUCE, user.getUserPresentation());
                    valus.put(DBContent.DBUser.USER_ADDRESS, user.getUserAddress());
                    getContentResolver().insert(DBContent.DBUser.CONTENT_URI, valus);
                    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    finish();
                }
            }
        });
    }

    /**
     * 第三方平台授权
     */
    private void authToPlatform() {
        mController.doOauthVerify(this, media, new SocializeListeners.UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {
                Log.e(TAG, "i come here");
            }

            @Override
            public void onComplete(Bundle bundle, SHARE_MEDIA share_media) {
                if (bundle != null && !TextUtils.isEmpty(bundle.getString("uid"))) {
                    uid = bundle.getString("uid");
                    Log.e("bundle", "bundle = " + bundle.toString());
                    mController.getPlatformInfo(LoginActivity.this, media, uMDataListener);
                } else {
                    showShortToast("授权失败");
                }
            }

            @Override
            public void onError(SocializeException e, SHARE_MEDIA share_media) {
                showShortToast("授权失败");
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media) {
                showShortToast("已取消授权");
            }
        });
    }

    /**
     * 第三方登录
     * 获取来自第三方用户的信息
     */
    private SocializeListeners.UMDataListener uMDataListener = new SocializeListeners.UMDataListener() {
        @Override
        public void onStart() {

        }

        @Override
        public void onComplete(int status, Map<String, Object> info) {
            if (media == SHARE_MEDIA.SINA || media == SHARE_MEDIA.QQ) {
                userName = info.get("screen_name").toString();
            } else if (media == SHARE_MEDIA.WEIXIN) {
                userName = info.get("nickname").toString();
            }
            SPCache.putString(Constants.SharePreference.OPEN_ID, uid);
            SPCache.putString(Constants.SharePreference.SCREEN_NAME, userName);
            handleLogin(null, null);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMSsoHandler ssoHandler = mController.getConfig().getSsoHandler(requestCode);
        if (ssoHandler != null) {
            ssoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
    }

    public static void newIntent(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }
}
