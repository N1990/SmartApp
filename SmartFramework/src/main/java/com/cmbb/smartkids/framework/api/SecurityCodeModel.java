package com.cmbb.smartkids.framework.api;

import android.os.Parcel;
import android.os.Parcelable;

import com.cmbb.smartkids.framework.api.okhttp.OkHttpClientManager;
import com.cmbb.smartkids.framework.base.Api;

import java.util.HashMap;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/9/9 下午2:26
 */
public class SecurityCodeModel implements Parcelable {


    private String status;
    private String msg;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.status);
        dest.writeString(this.msg);
    }

    public SecurityCodeModel() {
    }

    protected SecurityCodeModel(Parcel in) {
        this.status = in.readString();
        this.msg = in.readString();
    }

    public static final Creator<SecurityCodeModel> CREATOR = new Creator<SecurityCodeModel>() {
        public SecurityCodeModel createFromParcel(Parcel source) {
            return new SecurityCodeModel(source);
        }

        public SecurityCodeModel[] newArray(int size) {
            return new SecurityCodeModel[size];
        }
    };

    @Override
    public String toString() {
        return "SecurityCodeModel{" +
                "status='" + status + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }

    /**
     * 获取注册验证码
     *
     * @param loginAccount
     * @param callback
     */
    public static void getRegisterSecurityCodeRequest(String loginAccount, OkHttpClientManager.ResultCallback<SecurityCodeModel> callback) {
        HashMap<String, String> params = new HashMap<>();
        params.put("loginAccount", loginAccount);
        OkHttpClientManager.postAsyn(Api.REGISTER_VERIFY_CODE, params, callback);
    }

    /**
     * 获取除了 注册外的验证码
     *
     * @param loginAccount
     * @param callback
     */
    public static void getSecurityCodeRequest(String loginAccount, OkHttpClientManager.ResultCallback<SecurityCodeModel> callback) {
        HashMap<String, String> params = new HashMap<>();
        params.put("loginAccount", loginAccount);
        OkHttpClientManager.postAsyn(Api.VERIFY_CODE, params, callback);
    }

    /**
     * 获取验证码下一步
     *
     * @param loginAccount
     * @param securityCode
     * @param callback
     */
    public static void getSecurityCodeNextRequest(String loginAccount, String securityCode, OkHttpClientManager.ResultCallback<SecurityCodeModel> callback) {
        HashMap<String, String> params = new HashMap<>();
        params.put("loginAccount", loginAccount);
        params.put("securityCode", securityCode);
        OkHttpClientManager.postAsyn(Api.REGISTER_NEXT_REQUEST, params, callback);
    }

    /**
     * 更新个人图片
     *
     * @param imgPath
     * @param imgWidth
     * @param imgHeight
     * @param callback
     */
    public static void updateBackgroundImageRequest(String imgPath, String imgWidth, String imgHeight, OkHttpClientManager.ResultCallback<SecurityCodeModel> callback) {
        HashMap<String, String> params = new HashMap<>();
        params.put("imgPath", imgPath);
        params.put("imgWidth", imgWidth);
        params.put("imgHeight", imgHeight);
        OkHttpClientManager.postAsyn(Api.UPDATA_IMG_FOR_USER, params, callback);
    }


   /*   提交意见反馈接口
     *
     * @param contents    contents
     * @param callback ResultCallback
     */
    public static void sendSuggestFeekRequest(String contents, OkHttpClientManager.ResultCallback<SecurityCodeModel> callback) {
        HashMap<String, String> params = new HashMap<>();
        params.put("contents", contents);
        OkHttpClientManager.postAsyn(Api.FEEDBACK_SUGGEST_REQUEST, params, callback);
    }
}
