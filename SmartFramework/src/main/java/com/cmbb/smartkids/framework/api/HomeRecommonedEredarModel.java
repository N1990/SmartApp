package com.cmbb.smartkids.framework.api;

import android.os.Parcel;
import android.os.Parcelable;

import com.cmbb.smartkids.framework.api.okhttp.OkHttpClientManager;
import com.cmbb.smartkids.framework.base.Api;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/9/11 上午9:57
 */
public class HomeRecommonedEredarModel implements Parcelable {

    private int status;
    private String msg;
    private ArrayList<EredarModel> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrayList<EredarModel> getData() {
        return data;
    }

    public void setData(ArrayList<EredarModel> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RecommonedEredarRootModel{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.status);
        dest.writeString(this.msg);
        dest.writeTypedList(data);
    }

    public HomeRecommonedEredarModel() {
    }

    protected HomeRecommonedEredarModel(Parcel in) {
        this.status = in.readInt();
        this.msg = in.readString();
        this.data = in.createTypedArrayList(EredarModel.CREATOR);
    }

    public static final Creator<HomeRecommonedEredarModel> CREATOR = new Creator<HomeRecommonedEredarModel>() {
        public HomeRecommonedEredarModel createFromParcel(Parcel source) {
            return new HomeRecommonedEredarModel(source);
        }

        public HomeRecommonedEredarModel[] newArray(int size) {
            return new HomeRecommonedEredarModel[size];
        }
    };

    /**
     * 获取主页推荐达人
     * @param callback ResultCallback
     */
    public static void getHomeRecommendEredarRequest(OkHttpClientManager.ResultCallback<HomeRecommonedEredarModel> callback) {
        HashMap<String, String> bodyEredar = new HashMap<>();
        bodyEredar.put("isRecommoned", "1");
        OkHttpClientManager.postAsyn(Api.HOME_RECOMMENDED_EREDAR, bodyEredar, callback);
    }
}
