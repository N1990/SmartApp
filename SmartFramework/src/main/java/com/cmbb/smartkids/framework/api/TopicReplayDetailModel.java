package com.cmbb.smartkids.framework.api;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.cmbb.smartkids.framework.api.okhttp.OkHttpClientManager;
import com.cmbb.smartkids.framework.base.Api;

import java.util.HashMap;
import java.util.List;

/**
 * 项目名称：LovelyBaby
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/11/13 下午2:08
 */
public class TopicReplayDetailModel implements Parcelable {


    private DataEntity data;

    private String msg;

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataEntity getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }

    public static class DataEntity implements Parcelable {
        private String contents;


        private UserBasicInfoEntity userBasicInfo;
        private int replysFloor;
        private String img;
        private String imgWidth;
        private String updateDate;
        private int id;
        private int isDelete;
        private int updateUserId;
        private int parentId;
        private int isReport;
        private String createDate;
        private int createUserId;
        private String imgHeight;

        public void setContents(String contents) {
            this.contents = contents;
        }

        public void setUserBasicInfo(UserBasicInfoEntity userBasicInfo) {
            this.userBasicInfo = userBasicInfo;
        }

        public void setReplysFloor(int replysFloor) {
            this.replysFloor = replysFloor;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public void setImgWidth(String imgWidth) {
            this.imgWidth = imgWidth;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setIsDelete(int isDelete) {
            this.isDelete = isDelete;
        }

        public void setUpdateUserId(int updateUserId) {
            this.updateUserId = updateUserId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public void setIsReport(int isReport) {
            this.isReport = isReport;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public void setCreateUserId(int createUserId) {
            this.createUserId = createUserId;
        }

        public void setImgHeight(String imgHeight) {
            this.imgHeight = imgHeight;
        }

        public String getContents() {
            return contents;
        }

        public UserBasicInfoEntity getUserBasicInfo() {
            return userBasicInfo;
        }

        public int getReplysFloor() {
            return replysFloor;
        }

        public String getImg() {
            return img;
        }

        public String getImgWidth() {
            return imgWidth;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public int getId() {
            return id;
        }

        public int getIsDelete() {
            return isDelete;
        }

        public int getUpdateUserId() {
            return updateUserId;
        }

        public int getParentId() {
            return parentId;
        }

        public int getIsReport() {
            return isReport;
        }

        public String getCreateDate() {
            return createDate;
        }

        public int getCreateUserId() {
            return createUserId;
        }

        public String getImgHeight() {
            return imgHeight;
        }

        public static class UserBasicInfoEntity implements Parcelable {
            private int userId;
            private int recommoned;
            private String userNike;
            private int userSex;
            private String userBirthday;
            private String backgroundImg;
            private String userBigImg;
            private String userBigWidth;
            private String userBigHeight;
            private String userSmallImg;
            private double userSmallWidth;
            private double userSmallHeight;
            private int loginAccountType;
            private String loginTime;
            private String loginAccount;
            private String token;
            private int isShutup;
            private String shutupTime;
            private int isBanned;
            private String userAddress;
            private String userPhone;
            private String userPhoneVersion;
            private int province;
            private String provinceText;
            private int district;
            private String districtText;
            private int city;
            private String cityText;
            private int userLevel;
            private String userPresentation;
            private double backImgWidth;
            private double backImgHeight;
            private int goldCount;
            private int integralCount;
            private int attentionCount;
            private int fans;
            private int isAttention;
            private int isEredar;
            private int isLoginUser;
            /**
             * eredarCode : 0
             * eredarName : 萌宝用户
             */

            private List<UserRoleEntity> userRole;

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public void setRecommoned(int recommoned) {
                this.recommoned = recommoned;
            }

            public void setUserNike(String userNike) {
                this.userNike = userNike;
            }

            public void setUserSex(int userSex) {
                this.userSex = userSex;
            }

            public void setUserBirthday(String userBirthday) {
                this.userBirthday = userBirthday;
            }

            public void setBackgroundImg(String backgroundImg) {
                this.backgroundImg = backgroundImg;
            }

            public void setUserBigImg(String userBigImg) {
                this.userBigImg = userBigImg;
            }

            public void setUserBigWidth(String userBigWidth) {
                this.userBigWidth = userBigWidth;
            }

            public void setUserBigHeight(String userBigHeight) {
                this.userBigHeight = userBigHeight;
            }

            public void setUserSmallImg(String userSmallImg) {
                this.userSmallImg = userSmallImg;
            }

            public void setUserSmallWidth(double userSmallWidth) {
                this.userSmallWidth = userSmallWidth;
            }

            public void setUserSmallHeight(double userSmallHeight) {
                this.userSmallHeight = userSmallHeight;
            }

            public void setLoginAccountType(int loginAccountType) {
                this.loginAccountType = loginAccountType;
            }

            public void setLoginTime(String loginTime) {
                this.loginTime = loginTime;
            }

            public void setLoginAccount(String loginAccount) {
                this.loginAccount = loginAccount;
            }

            public void setToken(String token) {
                this.token = token;
            }

            public void setIsShutup(int isShutup) {
                this.isShutup = isShutup;
            }

            public void setShutupTime(String shutupTime) {
                this.shutupTime = shutupTime;
            }

            public void setIsBanned(int isBanned) {
                this.isBanned = isBanned;
            }

            public void setUserAddress(String userAddress) {
                this.userAddress = userAddress;
            }

            public void setUserPhone(String userPhone) {
                this.userPhone = userPhone;
            }

            public void setUserPhoneVersion(String userPhoneVersion) {
                this.userPhoneVersion = userPhoneVersion;
            }

            public int getIsLoginUser() {
                return isLoginUser;
            }

            public void setIsLoginUser(int isLoginUser) {
                this.isLoginUser = isLoginUser;
            }

            public void setProvince(int province) {
                this.province = province;
            }

            public void setProvinceText(String provinceText) {
                this.provinceText = provinceText;
            }

            public void setDistrict(int district) {
                this.district = district;
            }

            public void setDistrictText(String districtText) {
                this.districtText = districtText;
            }

            public void setCity(int city) {
                this.city = city;
            }

            public void setCityText(String cityText) {
                this.cityText = cityText;
            }

            public void setUserLevel(int userLevel) {
                this.userLevel = userLevel;
            }

            public void setUserPresentation(String userPresentation) {
                this.userPresentation = userPresentation;
            }

            public void setBackImgWidth(double backImgWidth) {
                this.backImgWidth = backImgWidth;
            }

            public void setBackImgHeight(double backImgHeight) {
                this.backImgHeight = backImgHeight;
            }

            public void setGoldCount(int goldCount) {
                this.goldCount = goldCount;
            }

            public void setIntegralCount(int integralCount) {
                this.integralCount = integralCount;
            }

            public void setAttentionCount(int attentionCount) {
                this.attentionCount = attentionCount;
            }

            public void setFans(int fans) {
                this.fans = fans;
            }

            public void setIsAttention(int isAttention) {
                this.isAttention = isAttention;
            }

            public void setIsEredar(int isEredar) {
                this.isEredar = isEredar;
            }

            public void setUserRole(List<UserRoleEntity> userRole) {
                this.userRole = userRole;
            }

            public int getUserId() {
                return userId;
            }

            public int getRecommoned() {
                return recommoned;
            }

            public String getUserNike() {
                return userNike;
            }

            public int getUserSex() {
                return userSex;
            }

            public String getUserBirthday() {
                return userBirthday;
            }

            public String getBackgroundImg() {
                return backgroundImg;
            }

            public String getUserBigImg() {
                return userBigImg;
            }

            public String getUserBigWidth() {
                return userBigWidth;
            }

            public String getUserBigHeight() {
                return userBigHeight;
            }

            public String getUserSmallImg() {
                return userSmallImg;
            }

            public double getUserSmallWidth() {
                return userSmallWidth;
            }

            public double getUserSmallHeight() {
                return userSmallHeight;
            }

            public int getLoginAccountType() {
                return loginAccountType;
            }

            public String getLoginTime() {
                return loginTime;
            }

            public String getLoginAccount() {
                return loginAccount;
            }

            public String getToken() {
                return token;
            }

            public int getIsShutup() {
                return isShutup;
            }

            public String getShutupTime() {
                return shutupTime;
            }

            public int getIsBanned() {
                return isBanned;
            }

            public String getUserAddress() {
                return userAddress;
            }

            public String getUserPhone() {
                return userPhone;
            }

            public String getUserPhoneVersion() {
                return userPhoneVersion;
            }

            public int getProvince() {
                return province;
            }

            public String getProvinceText() {
                return provinceText;
            }

            public int getDistrict() {
                return district;
            }

            public String getDistrictText() {
                return districtText;
            }

            public int getCity() {
                return city;
            }

            public String getCityText() {
                return cityText;
            }

            public int getUserLevel() {
                return userLevel;
            }

            public String getUserPresentation() {
                return userPresentation;
            }

            public double getBackImgWidth() {
                return backImgWidth;
            }

            public double getBackImgHeight() {
                return backImgHeight;
            }

            public int getGoldCount() {
                return goldCount;
            }

            public int getIntegralCount() {
                return integralCount;
            }

            public int getAttentionCount() {
                return attentionCount;
            }

            public int getFans() {
                return fans;
            }

            public int getIsAttention() {
                return isAttention;
            }

            public int getIsEredar() {
                return isEredar;
            }

            public List<UserRoleEntity> getUserRole() {
                return userRole;
            }

            public static class UserRoleEntity implements Parcelable {
                private int eredarCode;
                private String eredarName;

                public void setEredarCode(int eredarCode) {
                    this.eredarCode = eredarCode;
                }

                public void setEredarName(String eredarName) {
                    this.eredarName = eredarName;
                }

                public int getEredarCode() {
                    return eredarCode;
                }

                public String getEredarName() {
                    return eredarName;
                }

                @Override
                public String toString() {
                    return "UserRoleEntity{" +
                            "eredarCode=" + eredarCode +
                            ", eredarName='" + eredarName + '\'' +
                            '}';
                }


                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeInt(this.eredarCode);
                    dest.writeString(this.eredarName);
                }

                public UserRoleEntity() {
                }

                protected UserRoleEntity(Parcel in) {
                    this.eredarCode = in.readInt();
                    this.eredarName = in.readString();
                }

                public static final Creator<UserRoleEntity> CREATOR = new Creator<UserRoleEntity>() {
                    public UserRoleEntity createFromParcel(Parcel source) {
                        return new UserRoleEntity(source);
                    }

                    public UserRoleEntity[] newArray(int size) {
                        return new UserRoleEntity[size];
                    }
                };
            }

            @Override
            public String toString() {
                return "UserBasicInfoEntity{" +
                        "userId=" + userId +
                        ", recommoned=" + recommoned +
                        ", userNike='" + userNike + '\'' +
                        ", userSex=" + userSex +
                        ", userBirthday='" + userBirthday + '\'' +
                        ", backgroundImg='" + backgroundImg + '\'' +
                        ", userBigImg='" + userBigImg + '\'' +
                        ", userBigWidth='" + userBigWidth + '\'' +
                        ", userBigHeight='" + userBigHeight + '\'' +
                        ", userSmallImg='" + userSmallImg + '\'' +
                        ", userSmallWidth=" + userSmallWidth +
                        ", userSmallHeight=" + userSmallHeight +
                        ", loginAccountType=" + loginAccountType +
                        ", loginTime='" + loginTime + '\'' +
                        ", loginAccount='" + loginAccount + '\'' +
                        ", token='" + token + '\'' +
                        ", isShutup=" + isShutup +
                        ", shutupTime='" + shutupTime + '\'' +
                        ", isBanned=" + isBanned +
                        ", userAddress='" + userAddress + '\'' +
                        ", userPhone='" + userPhone + '\'' +
                        ", userPhoneVersion='" + userPhoneVersion + '\'' +
                        ", province=" + province +
                        ", provinceText='" + provinceText + '\'' +
                        ", district=" + district +
                        ", districtText='" + districtText + '\'' +
                        ", city=" + city +
                        ", cityText='" + cityText + '\'' +
                        ", userLevel=" + userLevel +
                        ", userPresentation='" + userPresentation + '\'' +
                        ", backImgWidth=" + backImgWidth +
                        ", backImgHeight=" + backImgHeight +
                        ", goldCount=" + goldCount +
                        ", integralCount=" + integralCount +
                        ", attentionCount=" + attentionCount +
                        ", fans=" + fans +
                        ", isAttention=" + isAttention +
                        ", isEredar=" + isEredar +
                        ", userRole=" + userRole +
                        '}';
            }


            public UserBasicInfoEntity() {
            }


            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.userId);
                dest.writeInt(this.recommoned);
                dest.writeString(this.userNike);
                dest.writeInt(this.userSex);
                dest.writeString(this.userBirthday);
                dest.writeString(this.backgroundImg);
                dest.writeString(this.userBigImg);
                dest.writeString(this.userBigWidth);
                dest.writeString(this.userBigHeight);
                dest.writeString(this.userSmallImg);
                dest.writeDouble(this.userSmallWidth);
                dest.writeDouble(this.userSmallHeight);
                dest.writeInt(this.loginAccountType);
                dest.writeString(this.loginTime);
                dest.writeString(this.loginAccount);
                dest.writeString(this.token);
                dest.writeInt(this.isShutup);
                dest.writeString(this.shutupTime);
                dest.writeInt(this.isBanned);
                dest.writeString(this.userAddress);
                dest.writeString(this.userPhone);
                dest.writeString(this.userPhoneVersion);
                dest.writeInt(this.province);
                dest.writeString(this.provinceText);
                dest.writeInt(this.district);
                dest.writeString(this.districtText);
                dest.writeInt(this.city);
                dest.writeString(this.cityText);
                dest.writeInt(this.userLevel);
                dest.writeString(this.userPresentation);
                dest.writeDouble(this.backImgWidth);
                dest.writeDouble(this.backImgHeight);
                dest.writeInt(this.goldCount);
                dest.writeInt(this.integralCount);
                dest.writeInt(this.attentionCount);
                dest.writeInt(this.fans);
                dest.writeInt(this.isAttention);
                dest.writeInt(this.isEredar);
                dest.writeInt(this.isLoginUser);
                dest.writeTypedList(userRole);
            }

            protected UserBasicInfoEntity(Parcel in) {
                this.userId = in.readInt();
                this.recommoned = in.readInt();
                this.userNike = in.readString();
                this.userSex = in.readInt();
                this.userBirthday = in.readString();
                this.backgroundImg = in.readString();
                this.userBigImg = in.readString();
                this.userBigWidth = in.readString();
                this.userBigHeight = in.readString();
                this.userSmallImg = in.readString();
                this.userSmallWidth = in.readDouble();
                this.userSmallHeight = in.readDouble();
                this.loginAccountType = in.readInt();
                this.loginTime = in.readString();
                this.loginAccount = in.readString();
                this.token = in.readString();
                this.isShutup = in.readInt();
                this.shutupTime = in.readString();
                this.isBanned = in.readInt();
                this.userAddress = in.readString();
                this.userPhone = in.readString();
                this.userPhoneVersion = in.readString();
                this.province = in.readInt();
                this.provinceText = in.readString();
                this.district = in.readInt();
                this.districtText = in.readString();
                this.city = in.readInt();
                this.cityText = in.readString();
                this.userLevel = in.readInt();
                this.userPresentation = in.readString();
                this.backImgWidth = in.readDouble();
                this.backImgHeight = in.readDouble();
                this.goldCount = in.readInt();
                this.integralCount = in.readInt();
                this.attentionCount = in.readInt();
                this.fans = in.readInt();
                this.isAttention = in.readInt();
                this.isEredar = in.readInt();
                this.isLoginUser = in.readInt();
                this.userRole = in.createTypedArrayList(UserRoleEntity.CREATOR);
            }

            public static final Creator<UserBasicInfoEntity> CREATOR = new Creator<UserBasicInfoEntity>() {
                public UserBasicInfoEntity createFromParcel(Parcel source) {
                    return new UserBasicInfoEntity(source);
                }

                public UserBasicInfoEntity[] newArray(int size) {
                    return new UserBasicInfoEntity[size];
                }
            };
        }

        @Override
        public String toString() {
            return "DataEntity{" +
                    "contents='" + contents + '\'' +
                    ", userBasicInfo=" + userBasicInfo +
                    ", replysFloor=" + replysFloor +
                    ", img=" + img +
                    ", imgWidth='" + imgWidth + '\'' +
                    ", updateDate='" + updateDate + '\'' +
                    ", id=" + id +
                    ", isDelete=" + isDelete +
                    ", updateUserId=" + updateUserId +
                    ", parentId=" + parentId +
                    ", isReport=" + isReport +
                    ", createDate='" + createDate + '\'' +
                    ", createUserId=" + createUserId +
                    ", imgHeight='" + imgHeight + '\'' +
                    '}';
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.contents);
            dest.writeParcelable(this.userBasicInfo, 0);
            dest.writeInt(this.replysFloor);
            dest.writeString(this.img);
            dest.writeString(this.imgWidth);
            dest.writeString(this.updateDate);
            dest.writeInt(this.id);
            dest.writeInt(this.isDelete);
            dest.writeInt(this.updateUserId);
            dest.writeInt(this.parentId);
            dest.writeInt(this.isReport);
            dest.writeString(this.createDate);
            dest.writeInt(this.createUserId);
            dest.writeString(this.imgHeight);
        }

        public DataEntity() {
        }

        protected DataEntity(Parcel in) {
            this.contents = in.readString();
            this.userBasicInfo = in.readParcelable(UserBasicInfoEntity.class.getClassLoader());
            this.replysFloor = in.readInt();
            this.img = in.readParcelable(Object.class.getClassLoader());
            this.imgWidth = in.readString();
            this.updateDate = in.readString();
            this.id = in.readInt();
            this.isDelete = in.readInt();
            this.updateUserId = in.readInt();
            this.parentId = in.readInt();
            this.isReport = in.readInt();
            this.createDate = in.readString();
            this.createUserId = in.readInt();
            this.imgHeight = in.readString();
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
        return "ReplayDetailModel{" +
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

    public TopicReplayDetailModel() {
    }

    protected TopicReplayDetailModel(Parcel in) {
        this.data = in.readParcelable(DataEntity.class.getClassLoader());
        this.msg = in.readString();
    }

    public static final Creator<TopicReplayDetailModel> CREATOR = new Creator<TopicReplayDetailModel>() {
        public TopicReplayDetailModel createFromParcel(Parcel source) {
            return new TopicReplayDetailModel(source);
        }

        public TopicReplayDetailModel[] newArray(int size) {
            return new TopicReplayDetailModel[size];
        }
    };

    /**
     * 回复ID
     *
     * @param replyId
     * @param token
     * @param callback
     */
    public static void getReplayDetailRequest(int replyId, String token, OkHttpClientManager.ResultCallback<TopicReplayDetailModel> callback) {
        HashMap<String, String> params = new HashMap<>();
        if (replyId == -1) return;
        params.put("replyId", replyId + "");
        params.put("token", token);
        OkHttpClientManager.postAsyn(Api.REPLAY_DETAIL, params, callback);
    }


    /**
     * 删除回复
     *
     * @param replyId
     * @param token
     * @param callback
     */
    public static void getReplayDeleteRequest(int replyId, String token, OkHttpClientManager.ResultCallback<TopicReplayDetailModel> callback) {
        HashMap<String, String> params = new HashMap<>();
        if (replyId == -1) return;
        params.put("replyId", replyId + "");
        params.put("isDelete", 1 + "");
        params.put("token", token);
        OkHttpClientManager.postAsyn(Api.TOPIC_DELETEREPLY, params, callback);
    }


    /**
     * 举报回复
     *
     * @param replyId
     * @param token
     * @param callback
     */
    public static void getReplayReportRequest(int replyId, String token, OkHttpClientManager.ResultCallback<TopicReplayDetailModel> callback) {
        HashMap<String, String> params = new HashMap<>();
        if (replyId == -1) return;
        params.put("replyId", replyId + "");
        params.put("isReport", 1 + "");
        params.put("token", token);
        OkHttpClientManager.postAsyn(Api.TOPIC_REPORTREPLY, params, callback);
    }


    /**
     * 回复回复的回复
     * @param parentId
     * @param contents
     * @param img
     * @param imgWidth
     * @param imgHeight
     * @param token
     * @param callback
     */
    public static void handReplayRequest(int parentId, String contents, String img, String imgWidth, String imgHeight, String token, OkHttpClientManager.ResultCallback<TopicReplayDetailModel> callback) {
        HashMap<String, String> params = new HashMap<>();
        if (parentId == -1) return;
        params.put("parentId", parentId + "");
        params.put("replyType", 1 + "");
        if (!TextUtils.isEmpty(contents)) {
            params.put("contents", contents);
        }
        if (!TextUtils.isEmpty(img))
            params.put("img", img);
        if (!TextUtils.isEmpty(imgWidth))
            params.put("imgWidth", imgWidth);
        if (!TextUtils.isEmpty(imgHeight))
            params.put("imgHeight", imgHeight);
        params.put("token", token);
        OkHttpClientManager.postAsyn(Api.TOPIC_REPLYTOPIC, params, callback);
    }
}
