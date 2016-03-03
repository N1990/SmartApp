package com.cmbb.smartkids.user;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ContentProviderOperation;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.text.InputType;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.sdk.android.media.upload.UploadListener;
import com.alibaba.sdk.android.media.upload.UploadTask;
import com.alibaba.sdk.android.media.utils.FailReason;
import com.cmbb.smartkids.GuideActivity;
import com.cmbb.smartkids.R;
import com.cmbb.smartkids.framework.api.LogoutModel;
import com.cmbb.smartkids.framework.api.ModifyUserModel;
import com.cmbb.smartkids.framework.api.SecurityCodeModel;
import com.cmbb.smartkids.framework.api.okhttp.ImageUpload;
import com.cmbb.smartkids.framework.api.okhttp.OkHttpClientManager;
import com.cmbb.smartkids.framework.base.BaseActivity;
import com.cmbb.smartkids.framework.base.BaseApplication;
import com.cmbb.smartkids.framework.base.Constants;
import com.cmbb.smartkids.framework.db.DBAddressModel;
import com.cmbb.smartkids.framework.db.DBContent;
import com.cmbb.smartkids.framework.db.DBHelper;
import com.cmbb.smartkids.framework.utils.CustomListener;
import com.cmbb.smartkids.framework.utils.Fresco;
import com.cmbb.smartkids.framework.utils.SPCache;
import com.cmbb.smartkids.framework.utils.TDevice;
import com.cmbb.smartkids.framework.utils.log.Log;
import com.cmbb.smartkids.framework.widget.wheelview.CustomDialogBuilder;
import com.cmbb.smartkids.framework.widget.wheelview.LocationSelectorDialogBuilder;
import com.cmbb.smartkids.login.TimeCounter;
import com.cmbb.smartkids.photopicker.PhotoPickerActivity;
import com.cmbb.smartkids.photopicker.utils.PhotoPickerIntent;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MySetActivity extends BaseActivity implements LocationSelectorDialogBuilder.OnSaveLocationLister, LoaderManager.LoaderCallbacks<Cursor> {
    private final String TAG = MySetActivity.class.getSimpleName();
    private final int PIC_REQUEST_CODE = 1001;
    private final int MODIFY_INTRODUCE_REQUEST = 1002;
    private SimpleDraweeView ivHeader;
    private EditText etChange, etPhone, etVerify;
    private TextView tvNickname, tvPhone, tvLocal, tvSex, tvAddress, tvSign, tvBingQQ, tvBindWx;
    private CustomDialogBuilder builder = null;
    private String flag = "";// "账户是第三方  或是手机号"
    private LocationSelectorDialogBuilder locationBuilder;
    private DBHelper dbHelper;
    private ProgressDialog progressDialog;
    // 地址参数
    private String provinceId;
    private String cityId;
    private String areaId;
    private String introduce = "";
    private int male = 0; // 1 男 2 女
    private TimerRegister timeRegsiter;
    private TextView tvVerify;
    private String nickName, phone, address;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_set;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        initData();
        addListener();
    }

    private void initView() {
        ((TextView)findViewById(R.id.tv_title)).setText(R.string.person_information);
        ivHeader = (SimpleDraweeView) findViewById(R.id.iv_my_set_user_header);
        tvNickname = (TextView) findViewById(R.id.tv_my_set_user_nickname);
        tvPhone = (TextView) findViewById(R.id.tv_my_set_user_phone);
        tvLocal = (TextView) findViewById(R.id.tv_my_set_user_local);
        tvSex = (TextView) findViewById(R.id.tv_my_set_user_sex);
        tvAddress = (TextView) findViewById(R.id.tv_my_set_user_address);
        tvSign = (TextView) findViewById(R.id.tv_my_set_user_signature);
        timeRegsiter = new TimerRegister();
        IntentFilter filter = new IntentFilter(Constants.Broadcast.COUNT_TIME);
        registerReceiver(timeRegsiter, filter);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("正在加载...");
    }

    private void initData() {
        dbHelper = new DBHelper(this);
        getSupportLoaderManager().initLoader(0, null, this);
    }

    private void addListener() {
        findViewById(R.id.rl_my_set_user_header).setOnClickListener(this);
        findViewById(R.id.rl_my_set_user_nickname).setOnClickListener(this);
        findViewById(R.id.rl_my_set_user_phone).setOnClickListener(this);
        findViewById(R.id.rl_my_set_user_local).setOnClickListener(this);
        findViewById(R.id.rl_my_set_user_sex).setOnClickListener(this);
        findViewById(R.id.rl_my_set_user_introduce).setOnClickListener(this);
        findViewById(R.id.rl_my_set_user_address).setOnClickListener(this);
        findViewById(R.id.tv_my_set_user_change).setOnClickListener(this);
        findViewById(R.id.rl_my_set_user_delivery_address).setOnClickListener(this);
    }

    private void showAlertDialog(int flag, String tip, View view) {  // 0 改性别 1 改昵称 2 改手机号 3 绑定账号
        int width = TDevice.getScreenWidth(this) * 3 / 4;
        builder = CustomDialogBuilder.getInstance(this).setDialogWindows(width, ViewGroup.LayoutParams.WRAP_CONTENT).isCancelableOnTouchOutside(false);
        if (flag == 0) {
            builder.withTitle("更改性别")
                    .withMessageMiss(View.GONE)
                    .withCustomContentView(view, this)
                    .withCancelText("取消", new CustomListener.DialogListener() {
                        @Override
                        public void onClick(View v) {
                            builder.dismiss();
                        }
                    }).withComfirmText("确定", new CustomListener.DialogListener() {
                @Override
                public void onClick(View v) {
                    if (male != 0) {
                        changeMaleRequest();
                    } else {
                        showShortToast("性别未做任何修改...");
                    }
                }
            });
        } else if (flag == 1) {
            builder.withTitle("更改昵称")
                    .withMessageMiss(View.GONE)
                    .withCustomContentView(view, this)
                    .withCancelText("取消", new CustomListener.DialogListener() {
                        @Override
                        public void onClick(View v) {
                            builder.dismiss();
                        }
                    }).withComfirmText("确定", new CustomListener.DialogListener() {
                @Override
                public void onClick(View v) {
                    if (etChange != null && !TextUtils.isEmpty(etChange.getText().toString())) {
                        String nickName = etChange.getText().toString();
                        changeNameRequest(nickName);
                    } else {
                        showShortToast("昵称未做任何修改...");
                    }
                }
            });
        } else if (flag == 2) {
            builder.withTitle("更改手机号")
                    .withMessageMiss(View.GONE)
                    .withCustomContentView(view, this)
                    .withCancelText("取消", new CustomListener.DialogListener() {
                        @Override
                        public void onClick(View v) {
                            builder.dismiss();
                        }
                    }).withComfirmText("确定", new CustomListener.DialogListener() {
                @Override
                public void onClick(View v) {
                    String phone = etPhone.getText().toString();
                    String verify = etVerify.getText().toString();
                    if (TextUtils.isEmpty(phone) || !TDevice.isMobileNo(phone)) {
                        showShortToast("手机号码格式不正确");
                        return;
                    }
                    if (TextUtils.isEmpty(verify)) {
                        showShortToast("验证码不能为空");
                        return;
                    }
                    changePhoneRequest(phone, verify);
                }
            });
        } else if (flag == 3) {
            builder.withTitle("修改详细地址")
                    .withMessageMiss(View.GONE)
                    .withCustomContentView(view, this)
                    .withCancelText("取消", new CustomListener.DialogListener() {
                        @Override
                        public void onClick(View v) {
                            builder.dismiss();
                        }
                    }).withComfirmText("确定", new CustomListener.DialogListener() {
                @Override
                public void onClick(View v) {
                    if (etChange != null) {
                        String address = etChange.getText().toString();
                        if (!TextUtils.isEmpty(address)) {
                            changeAddressRequest(address);
                        } else {
                            showShortToast("请选择正确的地址");
                        }
                    }
                }
            });
        } else {
            builder.withTitle("绑定账号")
                    .withMessage(tip)
                    .withCancelText("取消", new CustomListener.DialogListener() {
                        @Override
                        public void onClick(View v) {
                            builder.dismiss();
                        }
                    }).withComfirmText("确定", new CustomListener.DialogListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
        builder.show();
    }


    /**
     * 昵称 地址 弹窗修改
     *
     * @param tip
     * @return
     */
    private View createEditView(int tag, String tip) {
        View view = getLayoutInflater().inflate(R.layout.view_edit, null);
        etChange = (EditText) view.findViewById(R.id.et_nickname);
        if (tag == 1) {
            etChange.setText(nickName);
        } else {
            etChange.setText(!TextUtils.isEmpty(address) ? address : "");
        }
        etChange.setHint(tip);
        etChange.setInputType(InputType.TYPE_CLASS_TEXT);
        return view;
    }


    /**
     * 修改手机号弹窗
     *
     * @param tip
     * @return
     */
    private View createPhoneView(String tip) {
        View view = getLayoutInflater().inflate(R.layout.view_modify_phone, null);
        etPhone = (EditText) view.findViewById(R.id.et_phone);
        ImageView ivClear = (ImageView) view.findViewById(R.id.iv_phone_clear);
        tvVerify = (TextView) view.findViewById(R.id.tv_verify);
        etVerify = (EditText) view.findViewById(R.id.et_verify);
        etPhone.setHint(tip);
        etPhone.setText(phone);
        etPhone.setInputType(InputType.TYPE_CLASS_NUMBER);
        tvVerify.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_my_set_user_header:
                PhotoPickerIntent intent = new PhotoPickerIntent(this);
                intent.setPhotoCount(1);
                startActivityForResult(intent, PIC_REQUEST_CODE);
                break;
            case R.id.rl_my_set_user_nickname:
                showAlertDialog(1, null, createEditView(1, "请输入您的昵称"));
                break;
            case R.id.rl_my_set_user_phone:
                showAlertDialog(2, null, createPhoneView("请输入您的手机号码"));
                break;
            case R.id.rl_my_set_user_local:
                if (locationBuilder == null) {
                    locationBuilder = LocationSelectorDialogBuilder.getInstance(this);
                    locationBuilder.setOnSaveLocationLister(this);
                }
                locationBuilder.show();
                break;
            case R.id.rl_my_set_user_address:
                showAlertDialog(3, null, createEditView(3, "请输入您的详细地址"));
                break;
            case R.id.rl_my_set_user_sex:
                View view = getLayoutInflater().inflate(R.layout.view_sex_selected, null);
                RadioGroup rg = (RadioGroup) view.findViewById(R.id.rg_sex);
                if (male == 2) {
                    ((RadioButton) view.findViewById(R.id.rb_sex_woman)).setChecked(true);
                } else {
                    ((RadioButton) view.findViewById(R.id.rb_sex_man)).setChecked(true);

                }
                rg.setOnCheckedChangeListener(rgListener);
                showAlertDialog(0, null, view);
                break;
            case R.id.rl_my_set_user_introduce:
//                Intent introduceIntent = new Intent(MySetActivity.this, ModifyIntroduceActivity.class);
//                introduceIntent.putExtra("introduce", introduce);
//                startActivityForResult(introduceIntent, MODIFY_INTRODUCE_REQUEST);
                break;
            case R.id.tv_verify:
                String phone = etPhone.getText().toString();
                if (TextUtils.isEmpty(phone) || !TDevice.isMobileNo(phone)) {
                    showShortToast("手机号码格式不正确");
                    return;
                }
                tvVerify.setEnabled(false);
                startService(new Intent(this, TimeCounter.class));
                SecurityCodeModel.getRegisterSecurityCodeRequest(phone, new OkHttpClientManager.ResultCallback<SecurityCodeModel>() {
                    @Override
                    public void onError(Request request, Exception e) {
                        showShortToast(e.toString());
                    }

                    @Override
                    public void onResponse(SecurityCodeModel response) {
                        showShortToast(response.getMsg());
                    }
                });
                break;
            case R.id.rl_my_set_user_delivery_address:
//                ManagerDeliveryAddressListActivity.skipFromActivity(MySetActivity.this);
                break;
            case R.id.tv_my_set_user_change:
                //  注销推送账号
                changeAccountRequest();
                break;
        }
    }

    private RadioGroup.OnCheckedChangeListener rgListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.rb_sex_man) {
                male = 1;
            } else if (checkedId == R.id.rb_sex_woman) {
                male = 2;
            }
        }
    };

    @Override
    public void onSaveLocation(String local, String province, String city, String area, String provinceId, String cityId, String areaId) {
        this.provinceId = provinceId;
        this.cityId = cityId;
        this.areaId = areaId;
        changeLocalRequest(province, city, area);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                setResult(RESULT_OK);
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        if (builder != null && builder.isShowing()) {
            builder.dismiss();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PIC_REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                final ArrayList<String> tempUrls = data.getStringArrayListExtra(PhotoPickerActivity.KEY_SELECTED_PHOTOS);
                Log.e(TAG, "pic path:" + tempUrls.get(0));
                progressDialog.show();
                ImageUpload.getInstance().uploadImages(MySetActivity.this, tempUrls, new UploadListener() {
                    @Override
                    public void onUploading(UploadTask uploadTask) {

                    }

                    @Override
                    public void onUploadFailed(UploadTask uploadTask, FailReason failReason) {
                        progressDialog.dismiss();
                        showShortToast(failReason.getMessage());
                    }

                    @Override
                    public void onUploadComplete(UploadTask uploadTask) {
                        progressDialog.dismiss();
                        try {
                            JSONObject object = new JSONObject(uploadTask.getResult().message);
                            String url = object.optString("url");
                            String width = object.optString("returnBody").split("_")[0];
                            String height = object.optString("returnBody").split("_")[1];
                            Log.e(TAG, "url : " + url + ", width : " + width + ", height : " + height);
                            Log.i("uploadTask", "uploadTask = " + tempUrls.size());
                            Log.i(TAG, "uploadTask = " + uploadTask.getResult().message);
                            handleUpdateRequest(url, width, height);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onUploadCancelled(UploadTask uploadTask) {

                    }
                });
            }
        } else if (requestCode == MODIFY_INTRODUCE_REQUEST && resultCode == RESULT_OK) {
            introduce = data.getStringExtra("introduce");
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    protected void onDestroy() {
        if (builder != null) {
            builder.setDialogDismiss();
        }
        if (locationBuilder != null)
            locationBuilder.setDialogDismiss();
        super.onDestroy();
    }


    /**
     * 修改昵称请求
     */
    private void changeNameRequest(final String name) {
        progressDialog.show();
        ModifyUserModel.sendModifyNickRequest(name, new OkHttpClientManager.ResultCallback<ModifyUserModel>() {
            @Override
            public void onError(Request request, Exception e) {
                progressDialog.dismiss();

            }

            @Override
            public void onResponse(ModifyUserModel response) {
                progressDialog.dismiss();
                if (response != null && response.getData() != null) {
                    ContentValues valus = new ContentValues();
                    valus.put(DBContent.DBUser.USER_NICK_NAME, response.getData().getUserNike());
                    getContentResolver().update(DBContent.DBUser.CONTENT_URI, valus, DBContent.DBUser.USER_ID + " = " + response.getData().getUserId(), null);
                    showShortToast("昵称修改成功");
                    etChange.setText("");
                } else {
                    showShortToast(response.getMsg());
                }
            }
        });
    }

    /**
     * 切换账户
     */
    private void changeAccountRequest() {
        progressDialog.show();
        LogoutModel.sendLogoutRequest(BaseApplication.token, new OkHttpClientManager.ResultCallback<LogoutModel>() {
            @Override
            public void onError(Request request, Exception e) {
                progressDialog.dismiss();
            }

            @Override
            public void onResponse(LogoutModel response) {
                progressDialog.dismiss();
                if (response != null) {
                    showShortToast(response.getMsg());
                    new Thread() {
                        @Override
                        public void run() {
                            try {
                                boolean flag = BaseApplication.mPushAgent.removeAlias(SPCache.getString(Constants.USER_ID, "") + "_" + TDevice.getDeviceId(MySetActivity.this), "service");
                                Log.e("Alias", "Alias remove = " + flag);
                                Log.e("Alias", "Alias remove id = " + SPCache.getString(Constants.USER_ID, ""));
                                if (flag) {
                                    SPCache.clear();
                                    //删除表
                                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                                    dbHelper.delete(db);
                                    Intent guid = new Intent(MySetActivity.this, GuideActivity.class);
                                    guid.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(guid);
                                    finish();
                                } else {
                                    showToast("注销失败");
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                } else {
                    showShortToast(response.getMsg());
                }
            }
        });
    }

    /**
     * 修改手机号请求
     */
    private void changePhoneRequest(final String phone, String verify) {
        progressDialog.show();
        ModifyUserModel.sendModifyPhoneRequest(phone, verify, new OkHttpClientManager.ResultCallback<ModifyUserModel>() {
            @Override
            public void onError(Request request, Exception e) {
                progressDialog.dismiss();
                showShortToast(e.toString());

            }

            @Override
            public void onResponse(ModifyUserModel response) {
                progressDialog.dismiss();
                if (response != null && response.getData() != null) {
                    ContentValues valus = new ContentValues();
                    valus.put(DBContent.DBUser.USER_PHONE, response.getData().getUserPhone());
                    getContentResolver().update(DBContent.DBUser.CONTENT_URI, valus, DBContent.DBUser.USER_ID + " = " + response.getData().getUserId(), null);
                    showShortToast("手机号码修改成功");
                    etPhone.setText("");
                    etVerify.setText("");
                } else {
                    showShortToast(response.getMsg());
                }
            }
        });
    }


    /**
     * 修改省市区
     */
    private void changeLocalRequest(final String province, final String city, final String area) {
        progressDialog.show();
        ModifyUserModel.sendModifyAddressReqeust(provinceId, cityId, areaId, new OkHttpClientManager.ResultCallback<ModifyUserModel>() {
            @Override
            public void onError(Request request, Exception e) {
                progressDialog.dismiss();
                showShortToast(e.toString());

            }

            @Override
            public void onResponse(ModifyUserModel response) {
                progressDialog.dismiss();
                if (response != null && response.getData() != null) {
                    ContentValues valus = new ContentValues();
                    valus.put(DBContent.DBUser.USER_PROVINCE_ID, response.getData().getProvince());
                    valus.put(DBContent.DBUser.USER_CITY_ID, response.getData().getCity());
                    valus.put(DBContent.DBUser.USER_AREA_ID, response.getData().getDistrict());
                    valus.put(DBContent.DBUser.USER_PROVINCE, province);
                    valus.put(DBContent.DBUser.USER_CITY, city);
                    valus.put(DBContent.DBUser.USER_AREA, area);
                    getContentResolver().update(DBContent.DBUser.CONTENT_URI, valus, DBContent.DBUser.USER_ID + " = " + response.getData().getUserId(), null);
                    showShortToast("地址修改成功");
                } else {
                    showShortToast(response.getMsg());
                }
            }
        });
    }

    /**
     * 修改详细地址请求
     */
    private void changeAddressRequest(final String address) {
        progressDialog.show();
        ModifyUserModel.sendModifyLocalRequest(address, new OkHttpClientManager.ResultCallback<ModifyUserModel>() {
            @Override
            public void onError(Request request, Exception e) {
                progressDialog.dismiss();
                showShortToast(e.toString());
            }

            @Override
            public void onResponse(ModifyUserModel response) {
                progressDialog.dismiss();
                if (response != null && response.getData() != null) {
                    ContentValues valus = new ContentValues();
                    valus.put(DBContent.DBUser.USER_ADDRESS, response.getData().getUserAddress());
                    getContentResolver().update(DBContent.DBUser.CONTENT_URI, valus, DBContent.DBUser.USER_ID + " = " + response.getData().getUserId(), null);
                    showShortToast("详细地址修改成功");
                    etChange.setText("");
                } else {
                    showShortToast(response.getMsg());
                }
            }
        });
    }

    /**
     * 修改性别请求
     */
    private void changeMaleRequest() {
        progressDialog.show();
        ModifyUserModel.sendModifyMaleRequest(male, new OkHttpClientManager.ResultCallback<ModifyUserModel>() {
            @Override
            public void onError(Request request, Exception e) {
                progressDialog.dismiss();
                showShortToast(e.toString());
            }

            @Override
            public void onResponse(ModifyUserModel response) {
                progressDialog.dismiss();
                if (response != null && response.getData() != null) {
                    ContentValues valus = new ContentValues();
                    valus.put(DBContent.DBUser.USER_MALE, Integer.valueOf(response.getData().getUserSex()));
                    getContentResolver().update(DBContent.DBUser.CONTENT_URI, valus, DBContent.DBUser.USER_ID + " = " + response.getData().getUserId(), null);
                    showShortToast("性别修改成功");
                } else {
                    showShortToast(response.getMsg());
                }
            }
        });
    }

    /**
     * 更改头像
     *
     * @param path
     * @param width
     * @param height
     */
    private void handleUpdateRequest(final String path, final String width, final String height) {
        ModifyUserModel.sendModifyHeaderImgRequest(path, width, height, new OkHttpClientManager.ResultCallback<ModifyUserModel>() {
            @Override
            public void onError(Request request, Exception e) {
                progressDialog.dismiss();
                showShortToast(e.toString());
            }

            @Override
            public void onResponse(ModifyUserModel response) {
                progressDialog.dismiss();
                ContentValues valus = new ContentValues();
                valus.put(DBContent.DBUser.USER_HEAD_IMG, response.getData().getUserSmallImg());
                valus.put(DBContent.DBUser.USER_IMG_WIDTH, Integer.valueOf(response.getData().getUserSmallWidth()));
                valus.put(DBContent.DBUser.USER_IMG_HEIGHT, Integer.valueOf(response.getData().getUserSmallHeight()));
                getContentResolver().update(DBContent.DBUser.CONTENT_URI, valus, DBContent.DBUser.USER_ID + " = " + response.getData().getUserId(), null);
                showShortToast(response.getMsg());
            }
        });
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, DBContent.DBUser.CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Log.i("cursor", "cursor = " + data.getCount());
        if (data != null && data.getCount() > 0 && data.moveToFirst()) {
            Fresco.loadImage(ivHeader, data.getString(data.getColumnIndex(DBContent.DBUser.USER_HEAD_IMG)));
            nickName = data.getString(data.getColumnIndex(DBContent.DBUser.USER_NICK_NAME));
            if (!TextUtils.isEmpty(nickName))
                tvNickname.setText(nickName);
            phone = data.getString(data.getColumnIndex(DBContent.DBUser.USER_PHONE));
            if (!TextUtils.isEmpty(phone))
                tvPhone.setText(phone);
            tvLocal.setText(getPCDName(data.getString(data.getColumnIndex(DBContent.DBUser.USER_AREA_ID))));
            address = data.getString(data.getColumnIndex(DBContent.DBUser.USER_ADDRESS));
            if (!TextUtils.isEmpty(address))
                tvAddress.setText(address);
            male = data.getInt(data.getColumnIndex(DBContent.DBUser.USER_MALE));
            if (male == 1) {
                tvSex.setText("男");
            } else if (male == 2) {
                tvSex.setText("女");
            }
            introduce = data.getString(data.getColumnIndex(DBContent.DBUser.USER_INTRODUCE));
            int isPopman = data.getInt(data.getColumnIndex(DBContent.DBUser.USER_IS_POPMAN));
            if (isPopman == 0) {
                findViewById(R.id.rl_my_set_user_introduce).setVisibility(View.GONE);
            } else {
                findViewById(R.id.rl_my_set_user_introduce).setVisibility(View.VISIBLE);
                tvSign.setText(introduce);
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    class TimerRegister extends BroadcastReceiver {
        int second = 0;

        @Override
        public void onReceive(Context context, Intent intent) {
            second = intent.getIntExtra("second", 0);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (second == 0) {
                        tvVerify.setText("获取验证码");
                        tvVerify.setTextColor(getResources().getColor(R.color.primaryColor));
                        tvVerify.setEnabled(true);
                    } else {
                        tvVerify.setEnabled(false);
                        tvVerify.setTextColor(Color.GRAY);
                        tvVerify.setText(second < 10 ? "获取验证码0" + second + "s" : "获取验证码" + second + "s");
                    }
                }
            });

        }
    }

    /**
     * 获取省市区中文名字
     *
     * @param areaId
     * @return
     */
    private String getPCDName(String areaId) {
        if (TextUtils.isEmpty(areaId))
            return "";
        String tempJson = TDevice.getJson(this, "address.json");
        String json = "{data:" + tempJson + "}";
        Gson gson = new Gson();
        DBAddressModel address = gson.fromJson(json, DBAddressModel.class);
        List<DBAddressModel.DataEntity> data = address.getData();
        ArrayList<ContentProviderOperation> ops = new ArrayList<>();
        for (DBAddressModel.DataEntity province : data) {
            for (DBAddressModel.DataEntity.CitiesEntity city : province.getCities()) {
                for (DBAddressModel.DataEntity.CitiesEntity.CountiesEntity area : city.getCounties()) {
                    if (areaId.equals(area.getId())) {
                        return province.getName() + " " + city.getName() + " " + area.getName();
                    }
                }
            }
        }
        return "";
    }

}
