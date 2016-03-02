package com.cmbb.smartkids.framework.api;

import android.os.Parcel;
import android.os.Parcelable;

import com.cmbb.smartkids.framework.api.okhttp.OkHttpClientManager;
import com.cmbb.smartkids.framework.base.Api;
import com.cmbb.smartkids.framework.base.BaseApplication;
import com.cmbb.smartkids.framework.utils.TDevice;

import java.util.HashMap;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/9/10 下午5:18
 */
public class UserAssistModel implements Parcelable {


    private int status;
    private UserAssistInfoModel data;
    private String msg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public UserAssistInfoModel getData() {
        return data;
    }

    public void setData(UserAssistInfoModel data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.status);
        dest.writeParcelable(this.data, 0);
        dest.writeString(this.msg);
    }

    public UserAssistModel() {
    }

    protected UserAssistModel(Parcel in) {
        this.status = in.readInt();
        this.data = in.readParcelable(UserAssistInfoModel.class.getClassLoader());
        this.msg = in.readString();
    }

    public static final Creator<UserAssistModel> CREATOR = new Creator<UserAssistModel>() {
        public UserAssistModel createFromParcel(Parcel source) {
            return new UserAssistModel(source);
        }

        public UserAssistModel[] newArray(int size) {
            return new UserAssistModel[size];
        }
    };

    @Override
    public String toString() {
        return "UserAssistModel{" +
                "status=" + status +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }

    /**
     * 登陆接口
     *
     * @param platform      判断 登陆方式 0 表示手机号码登陆
     * @param loginAccount  手机号码
     * @param loginPassword 密码
     * @param openId        UID
     * @param userNike      昵称
     * @param callback      ResultCallback
     */
    public static void handleLoginRequest(int platform, String loginAccount, String loginPassword, String openId, String userNike, OkHttpClientManager.ResultCallback<UserAssistModel> callback) {
        HashMap<String, String> params = new HashMap<>();
        if (platform == 0) {
            params.put("loginAccount", loginAccount);
            params.put("loginPassword", loginPassword);
        } else {
            params.put("openId", openId);
            params.put("userNike", userNike);
        }
        params.put("device", 2 + "");
        params.put("deviceVersion", android.os.Build.VERSION.RELEASE);
        params.put("model", android.os.Build.MODEL);
        params.put("imei", TDevice.getDeviceId(BaseApplication.getContext()));
        params.put("applicationVersion", TDevice.getVersionName());
        params.put("thirdType", platform + "");
        OkHttpClientManager.postAsyn(Api.LOGIN_REQUEST, params, callback);
    }

    /**
     * 忘记密码
     *
     * @param loginAccount
     * @param loginPassword
     * @param callback
     */
    public static void handleForgetRequest(String loginAccount, String loginPassword, OkHttpClientManager.ResultCallback<UserAssistModel> callback) {
        HashMap<String, String> params = new HashMap<>();
        params.put("loginAccount", loginAccount);
        params.put("loginPassword", loginPassword);
        params.put("device", 2 + "");
        params.put("deviceVersion", android.os.Build.VERSION.RELEASE);
        params.put("model", android.os.Build.MODEL);
        params.put("imei", TDevice.getDeviceId(BaseApplication.getContext()));
        params.put("applicationVersion", TDevice.getVersionName());
        OkHttpClientManager.postAsyn(Api.FORGEST_PWD, params, callback);
    }

    /**
     * 注册请求
     *
     * @param userNike
     * @param loginAccount
     * @param loginPassword
     * @param province
     * @param cityId
     * @param district
     * @param callback
     */
    public static void handleRegisterRequest(String userNike, String loginAccount, String loginPassword, String province, String cityId, String district, OkHttpClientManager.ResultCallback<UserAssistModel> callback) {
        HashMap<String, String> params = new HashMap<>();
        params.put("loginAccount", loginAccount);
        params.put("loginPassword", loginPassword);
        params.put("userNike", userNike);
        params.put("province", province);
        params.put("cityId", cityId);
        params.put("district", district);
        params.put("device", 2 + "");
        params.put("deviceVersion", android.os.Build.VERSION.RELEASE);
        params.put("model", android.os.Build.MODEL);
        params.put("imei", TDevice.getDeviceId(BaseApplication.getContext()));
        params.put("applicationVersion", TDevice.getVersionName());
        params.put("thirdType", 0 + "");
        OkHttpClientManager.postAsyn(Api.REGISTER_REQUEST, params, callback);
    }
}
