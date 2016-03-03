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
 * 创建时间：15/11/19 下午7:11
 */
public class SpotModel implements Parcelable {


    private TopicDetailModel.DataEntity.SpotListEntity data;


    private String msg;

    public void setData(TopicDetailModel.DataEntity.SpotListEntity data) {
        this.data = data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public TopicDetailModel.DataEntity.SpotListEntity getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "SpotModel{" +
                "data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.data, 0);
        dest.writeString(this.msg);
    }

    public SpotModel() {
    }

    protected SpotModel(Parcel in) {
        this.data = in.readParcelable(TopicDetailModel.DataEntity.SpotListEntity.class.getClassLoader());
        this.msg = in.readString();
    }

    public static final Creator<SpotModel> CREATOR = new Creator<SpotModel>() {
        public SpotModel createFromParcel(Parcel source) {
            return new SpotModel(source);
        }

        public SpotModel[] newArray(int size) {
            return new SpotModel[size];
        }
    };


    /**
     * 点赞
     *
     * @param topicId
     * @param isSpot
     * @param token
     * @param callback
     */
    public static void handlerSpotRequest(int topicId, final int isSpot, String token, OkHttpClientManager.ResultCallback<SpotModel> callback) {
        HashMap<String, String> params = new HashMap<>();
        if (topicId == -1) return;
        params.put("id", topicId + "");
        switch (isSpot) {
            case 0:
                params.put("isSpot", 1 + "");
                break;
            case 1:
                params.put("isSpot", 0 + "");
                break;
        }
        params.put("token", token);
        OkHttpClientManager.postAsyn(Api.TOPIC_SPOT, params, callback);
    }
}
