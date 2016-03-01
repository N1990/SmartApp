package com.cmbb.smartkids.framework.api;

import android.os.Parcel;
import android.os.Parcelable;

import com.cmbb.smartkids.framework.api.okhttp.OkHttpClientManager;
import com.cmbb.smartkids.framework.base.Api;

import java.util.HashMap;
import java.util.List;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/11/10 上午10:51
 */
public class TopicTypeModel implements Parcelable {


    private String msg;

    private List<DataEntity> data;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity implements Parcelable {
        private String name;
        private String value;

        public void setName(String name) {
            this.name = name;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "DataEntity{" +
                    "name='" + name + '\'' +
                    ", value='" + value + '\'' +
                    '}';
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.name);
            dest.writeString(this.value);
        }

        public DataEntity() {
        }

        protected DataEntity(Parcel in) {
            this.name = in.readString();
            this.value = in.readString();
        }

        public static final Creator<DataEntity> CREATOR = new Creator<DataEntity>() {
            public DataEntity createFromParcel(Parcel source) {
                return new DataEntity(source);
            }

            public DataEntity[] newArray(int size) {
                return new DataEntity[size];
            }
        };
    }

    @Override
    public String toString() {
        return "TopicTypeModel{" +
                "msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.msg);
        dest.writeTypedList(data);
    }

    public TopicTypeModel() {
    }

    protected TopicTypeModel(Parcel in) {
        this.msg = in.readString();
        this.data = in.createTypedArrayList(DataEntity.CREATOR);
    }

    public static final Creator<TopicTypeModel> CREATOR = new Creator<TopicTypeModel>() {
        public TopicTypeModel createFromParcel(Parcel source) {
            return new TopicTypeModel(source);
        }

        public TopicTypeModel[] newArray(int size) {
            return new TopicTypeModel[size];
        }
    };


    /**
     * 获取话题类型
     *
     * @param callback ResultCallback
     */
    public static void getTopicTypeRequest(OkHttpClientManager.ResultCallback<TopicTypeModel> callback) {
        HashMap<String, String> params = new HashMap<>();
        params.put("typeCode", "topicType");
        OkHttpClientManager.postAsyn(Api.TOPIC_TYPE, params, callback);
    }
}
