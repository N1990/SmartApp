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
 * 创建时间：15/11/19 上午11:19
 */
public class HomeBannerModel implements Parcelable {

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
        private String img;
        private int id;
        private double imgWidth;
        private String createDate;
        private double imgHeight;

        public void setImg(String img) {
            this.img = img;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setImgWidth(double imgWidth) {
            this.imgWidth = imgWidth;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public void setImgHeight(double imgHeight) {
            this.imgHeight = imgHeight;
        }

        public String getImg() {
            return img;
        }

        public int getId() {
            return id;
        }

        public double getImgWidth() {
            return imgWidth;
        }

        public String getCreateDate() {
            return createDate;
        }

        public double getImgHeight() {
            return imgHeight;
        }

        @Override
        public String toString() {
            return "DataEntity{" +
                    "img='" + img + '\'' +
                    ", id=" + id +
                    ", imgWidth=" + imgWidth +
                    ", createDate='" + createDate + '\'' +
                    ", imgHeight=" + imgHeight +
                    '}';
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.img);
            dest.writeInt(this.id);
            dest.writeDouble(this.imgWidth);
            dest.writeString(this.createDate);
            dest.writeDouble(this.imgHeight);
        }

        public DataEntity() {
        }

        protected DataEntity(Parcel in) {
            this.img = in.readString();
            this.id = in.readInt();
            this.imgWidth = in.readDouble();
            this.createDate = in.readString();
            this.imgHeight = in.readDouble();
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
        return "BannerModel{" +
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

    public HomeBannerModel() {
    }

    protected HomeBannerModel(Parcel in) {
        this.msg = in.readString();
        this.data = in.createTypedArrayList(DataEntity.CREATOR);
    }

    public static final Creator<HomeBannerModel> CREATOR = new Creator<HomeBannerModel>() {
        public HomeBannerModel createFromParcel(Parcel source) {
            return new HomeBannerModel(source);
        }

        public HomeBannerModel[] newArray(int size) {
            return new HomeBannerModel[size];
        }
    };


    /**
     * 获取主页广告接口
     *
     * @param token    Token
     * @param callback ResultCallback
     */
    public static void getHomeBannerRequest(String token, OkHttpClientManager.ResultCallback<HomeBannerModel> callback) {
        HashMap<String, String> params = new HashMap<>();
        params.put("token", token);
        OkHttpClientManager.postAsyn(Api.HOME_GET_AD, params, callback);
    }
}
