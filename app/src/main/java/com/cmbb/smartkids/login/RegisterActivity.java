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
import com.cmbb.smartkids.framework.utils.TDevice;
import com.cmbb.smartkids.framework.widget.wheelview.LocationSelectorDialogBuilder;
import com.cmbb.smartkids.home.HomeActivity;
import com.squareup.okhttp.Request;

import java.util.HashMap;

public class RegisterActivity extends BaseActivity implements LocationSelectorDialogBuilder.OnSaveLocationLister, View.OnClickListener {

    private static final String TAG = RegisterActivity.class.getSimpleName();
    private EditText etNickname, etCity, etPwd, etPwdComfirm;
    private ImageView ivNickname, ivPwd, ivPwdComfirm;
    private LocationSelectorDialogBuilder locationBuilder;
    // 地址参数
    private String provinceId;
    private String cityId;
    private String areaId;
    private DBHelper dbHelper;  //临时逻辑


    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        addListener();
    }

    private void initView() {
        ((TextView) findViewById(R.id.tv_title)).setText(R.string.activity_register);
        dbHelper = new DBHelper(this);
        etNickname = (EditText) findViewById(R.id.et_register_nickname);
        ivNickname = (ImageView) findViewById(R.id.iv_register_nickname);
        etCity = (EditText) findViewById(R.id.et_register_city);
        etPwd = (EditText) findViewById(R.id.et_register_pwd);
        ivPwd = (ImageView) findViewById(R.id.iv_register_pwd_clear);
        etPwdComfirm = (EditText) findViewById(R.id.et_registser_pwd_comfirm);
        ivPwdComfirm = (ImageView) findViewById(R.id.iv_register_pwd_comfirm_clear);

    }

    private void addListener() {
        findViewById(R.id.tv_register_finish).setOnClickListener(this);
        findViewById(R.id.tv_register_city).setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tv_register_city) {
            if (locationBuilder == null) {
                locationBuilder = LocationSelectorDialogBuilder.getInstance(this);
                locationBuilder.setOnSaveLocationLister(this);
            }
            locationBuilder.show();
        } else if (id == R.id.tv_register_finish) {
            String nickeName = etNickname.getText().toString();
            String city = etCity.getText().toString();
            String pwd = etPwd.getText().toString();
            String pwdComfirm = etPwdComfirm.getText().toString();
            if (TextUtils.isEmpty(nickeName)) {
                showShortToast("请输入您的昵称");
                return;
            }
            if (TextUtils.isEmpty(city)) {
                showShortToast("请选择您所在的城市");
                return;
            }
            if (TextUtils.isEmpty(pwd)) {
                showShortToast("请输入您的密码");
                return;
            }
            if (TextUtils.isEmpty(pwdComfirm)) {
                showShortToast("请再次输入您的密码");
                return;
            }
            if (!pwd.equals(pwdComfirm)) {
                showShortToast("您两次输入的密码不一致");
                return;
            }
            handleRequest(nickeName, pwd);
        }
    }


    private void handleRequest(String nickeName, String pwd) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("正在请求...");
        progressDialog.show();
        HashMap<String, String> body = new HashMap<>();
        body.put("loginAccount", getIntent().getStringExtra("phone"));
        body.put("loginPassword", pwd);
        body.put("userNike", nickeName);
        body.put("province", provinceId);
        body.put("cityId", cityId);
        body.put("district", areaId);
        body.put("device", 2 + "");
        body.put("deviceVersion", android.os.Build.VERSION.RELEASE);
        body.put("model", android.os.Build.MODEL);
        body.put("imei", TDevice.getDeviceId(this));
        body.put("applicationVersion", TDevice.getVersionName());
        body.put("thirdType", 0 + "");

        UserAssistModel.handleRegisterRequest(nickeName, getIntent().getStringExtra("phone"), pwd, provinceId, cityId, areaId, new OkHttpClientManager.ResultCallback<UserAssistModel>() {
            @Override
            public void onError(Request request, Exception e) {
                progressDialog.dismiss();
                showShortToast(e.toString());
            }

            @Override
            public void onResponse(UserAssistModel response) {
                if (response != null) {
                    showShortToast(response.getMsg());
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
                    intent.putExtra("umeng_id", user.getUserId() + "_" + TDevice.getDeviceId(RegisterActivity.this));
                    intent.putExtra("umeng_type", "service");
                    LocalBroadcastManager.getInstance(RegisterActivity.this).sendBroadcast(intent);
                    //写入数据库
                    ContentValues valus = new ContentValues();
                    valus.put(DBContent.DBUser.USER_ID, user.getUserId());
                    valus.put(DBContent.DBUser.USER_TOKEN, user.getToken());
                    valus.put(DBContent.DBUser.USER_HEAD_IMG, user.getUserSmallImg());
                    valus.put(DBContent.DBUser.USER_NICK_NAME, user.getUserNike());
                    valus.put(DBContent.DBUser.USER_MALE, user.getUserSex());
                    valus.put(DBContent.DBUser.USER_PHONE, user.getUserPhone());
                    valus.put(DBContent.DBUser.USER_PROVINCE_ID, user.getProvince());
                    valus.put(DBContent.DBUser.USER_CITY_ID, user.getCity());
                    valus.put(DBContent.DBUser.USER_AREA_ID, user.getDistrict());
                    valus.put(DBContent.DBUser.USER_IS_POPMAN, user.getIsEredar());
                    valus.put(DBContent.DBUser.USER_ADDRESS, user.getUserAddress());
                    valus.put(DBContent.DBUser.USER_INTRODUCE, user.getUserPresentation());

                    getContentResolver().insert(DBContent.DBUser.CONTENT_URI, valus);
                    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                    showShortToast("注册成功");
                    startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
                    finish();
                }
            }
        });
    }

    @Override
    public void onSaveLocation(String local, String province, String city, String area, String provinceId, String cityId, String areaId) {
        etCity.setText(local);
        this.provinceId = provinceId;
        this.cityId = cityId;
        this.areaId = areaId;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (locationBuilder != null)
            locationBuilder.setDialogDismiss();
    }

    public static void newIntent(Context context, String phone) {
        Intent intent = new Intent(context, RegisterActivity.class);
        intent.putExtra("phone", phone);
        context.startActivity(intent);
    }
}
