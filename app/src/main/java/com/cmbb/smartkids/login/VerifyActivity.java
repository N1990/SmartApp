package com.cmbb.smartkids.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.framework.api.SecurityCodeModel;
import com.cmbb.smartkids.framework.api.okhttp.OkHttpClientManager;
import com.cmbb.smartkids.framework.base.BaseActivity;
import com.cmbb.smartkids.framework.utils.TDevice;
import com.squareup.okhttp.Request;

import java.util.HashMap;

/**
 * 项目名称：SmartApp
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：16/3/1 下午3:43
 */
public class VerifyActivity extends BaseActivity implements View.OnClickListener {
    private final String TAG = VerifyActivity.class.getSimpleName();
    private ImageView ivPhoneClear;
    private EditText etPhone, etCode;
    private TextView tvCode, tvSubmit;
    private String tag; // register  forget
    private TimeCounter timeCount;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_verify;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initViews();
        initData();
        addListener();
    }

    private void addListener() {
        tvCode.setOnClickListener(this);
        tvSubmit.setOnClickListener(this);
    }

    private void initData() {
        Bundle bundle = null;
        if (getIntent() != null && (bundle = getIntent().getExtras()) != null) {
            tag = bundle.getString("tag");
            if ("register".equals(tag)) {
                setTitle(getString(R.string.activity_register));
                ((TextView) findViewById(R.id.tv_title)).setText(R.string.activity_register);
                tvSubmit.setText("下一步");
            } else if ("forget".equals(tag)) {
                setTitle(getString(R.string.activity_forget_pwd));
                ((TextView) findViewById(R.id.tv_title)).setText(R.string.activity_forget_pwd);
                tvSubmit.setText("下一步");
            }
        } else {
            showShortToast("数据传输出错...");
        }
    }


    private void initViews() {
        etPhone = (EditText) findViewById(R.id.et_verify_phone);
        ivPhoneClear = (ImageView) findViewById(R.id.iv_verify_phone_clear);
        etCode = (EditText) findViewById(R.id.et_verify_code);
        tvCode = (TextView) findViewById(R.id.tv_verify_phone_code);
        tvSubmit = (TextView) findViewById(R.id.tv_verify_submit);
        tvCode.setText("获取验证码");
        timeCount = new TimeCounter(60000, 1000, tvCode);
    }


    @Override
    public void onClick(View v) {
        String phone = etPhone.getText().toString();
        if (TextUtils.isEmpty(phone)) {
            showShortToast("手机号码不能为空");
            return;
        }
        if (!TDevice.isMobileNo(phone)) {
            showShortToast("请输入正确的手机号码");
            return;
        }
        switch (v.getId()) {
            case R.id.tv_verify_phone_code:
                tvCode.setEnabled(false);
                if ("register".equals(tag)) {
                    // 注册验证码

                    SecurityCodeModel.getRegisterSecurityCodeRequest(phone, new OkHttpClientManager.ResultCallback<SecurityCodeModel>() {
                        @Override
                        public void onError(Request request, Exception e) {
                            showShortToast(e.toString());
                            tvCode.setEnabled(true);
                        }

                        @Override
                        public void onResponse(SecurityCodeModel response) {
                            if (response != null) {
                                showShortToast(response.getMsg());
                                tvCode.setEnabled(false);
                                timeCount.start();
                            }
                        }
                    });
                } else if (tag.equals("forget")) {

                    SecurityCodeModel.getSecurityCodeRequest(phone, new OkHttpClientManager.ResultCallback<SecurityCodeModel>() {
                        @Override
                        public void onError(Request request, Exception e) {
                            showShortToast(e.toString());
                            tvCode.setEnabled(true);
                        }

                        @Override
                        public void onResponse(SecurityCodeModel response) {
                            if (response != null) {
                                showShortToast(response.getMsg());
                                tvCode.setEnabled(false);
                                timeCount.start();
                            }
                        }
                    });
                }
                break;
            case R.id.tv_verify_submit:
                String verify = etCode.getText().toString();
                if (TextUtils.isEmpty(verify)) {
                    showShortToast("验证码不能为空");
                    return;
                }
                handleSumbit(verify, phone);
                break;
        }
    }

    private void handleSumbit(String code, final String phone) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("正在请求中...");
        progressDialog.show();
        HashMap<String, String> body = new HashMap<>();
        body.put("loginAccount", phone);
        body.put("securityCode", code);

        SecurityCodeModel.getSecurityCodeNextRequest(phone, code, new OkHttpClientManager.ResultCallback<SecurityCodeModel>() {
            @Override
            public void onError(Request request, Exception e) {
                progressDialog.dismiss();
                showShortToast(e.toString());
            }

            @Override
            public void onResponse(SecurityCodeModel response) {
                progressDialog.dismiss();
                if (response != null) {
                    if ("login".equals(tag)) { // 第三方登录

                    } else if ("register".equals(tag)) { // 注册
                        RegisterActivity.newIntent(VerifyActivity.this, phone);
                        finish();
                    } else if ("forget".equals(tag)) { // 忘记密码
                        ForgetPwdActivity.newIntent(VerifyActivity.this, phone);
                        finish();
                    }
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * @param context Context
     * @param tag     register:注册 forget:忘记密码
     */
    public static void newIntent(Context context, String tag) {
        Intent intent = new Intent(context, VerifyActivity.class);
        intent.putExtra("tag", tag);
        context.startActivity(intent);
    }

}
