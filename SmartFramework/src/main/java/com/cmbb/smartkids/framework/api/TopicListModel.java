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
 * 创建人：javon
 * 创建时间：2015/11/10 14:41
 */
public class TopicListModel implements Parcelable {


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
        private int page;
        private int records;
        private int total;
        private String userdata;

        private List<RowsEntity> rows;

        public void setPage(int page) {
            this.page = page;
        }

        public void setRecords(int records) {
            this.records = records;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public void setUserdata(String userdata) {
            this.userdata = userdata;
        }

        public void setRows(List<RowsEntity> rows) {
            this.rows = rows;
        }

        public int getPage() {
            return page;
        }

        public int getRecords() {
            return records;
        }

        public int getTotal() {
            return total;
        }

        public String getUserdata() {
            return userdata;
        }

        public List<RowsEntity> getRows() {
            return rows;
        }

        public static class RowsEntity implements Parcelable {
            private int id;
            private int browseNumber;
            private String title;
            private int isStick;
            private int replys;
            private String topicType;
            private String contents;
            private UserBasicInfoEntity userBasicInfo;
            private int isEnssence;
            private String publishDate;

            private List<TopicImgListEntity> topicImgList;

            public void setId(int id) {
                this.id = id;
            }

            public void setBrowseNumber(int browseNumber) {
                this.browseNumber = browseNumber;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setIsStick(int isStick) {
                this.isStick = isStick;
            }

            public void setReplys(int replys) {
                this.replys = replys;
            }

            public void setTopicType(String topicType) {
                this.topicType = topicType;
            }

            public void setContents(String contents) {
                this.contents = contents;
            }

            public void setUserBasicInfo(UserBasicInfoEntity userBasicInfo) {
                this.userBasicInfo = userBasicInfo;
            }

            public void setIsEnssence(int isEnssence) {
                this.isEnssence = isEnssence;
            }

            public void setPublishDate(String publishDate) {
                this.publishDate = publishDate;
            }

            public void setTopicImgList(List<TopicImgListEntity> topicImgList) {
                this.topicImgList = topicImgList;
            }

            public int getId() {
                return id;
            }

            public int getBrowseNumber() {
                return browseNumber;
            }

            public String getTitle() {
                return title;
            }

            public int getIsStick() {
                return isStick;
            }

            public int getReplys() {
                return replys;
            }

            public String getTopicType() {
                return topicType;
            }

            public String getContents() {
                return contents;
            }

            public UserBasicInfoEntity getUserBasicInfo() {
                return userBasicInfo;
            }

            public int getIsEnssence() {
                return isEnssence;
            }

            public String getPublishDate() {
                return publishDate;
            }

            public List<TopicImgListEntity> getTopicImgList() {
                return topicImgList;
            }


            public static class UserBasicInfoEntity implements Parcelable {
                private int userId;
                private int recommoned;
                private String userNike;
                private int userSex;
                private String userBirthday;
                private String backgroundImg;
                private String userBigImg;
                private int userBigWidth;
                private int userBigHeight;
                private String userSmallImg;
                private int userSmallWidth;
                private int userSmallHeight;
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
                private int backImgWidth;
                private int backImgHeight;
                private int goldCount;
                private int integralCount;
                private int attentionCount;
                private int fans;
                private int isAttention;
                private int isEredar;


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

                public void setUserBigWidth(int userBigWidth) {
                    this.userBigWidth = userBigWidth;
                }

                public void setUserBigHeight(int userBigHeight) {
                    this.userBigHeight = userBigHeight;
                }

                public void setUserSmallImg(String userSmallImg) {
                    this.userSmallImg = userSmallImg;
                }

                public void setUserSmallWidth(int userSmallWidth) {
                    this.userSmallWidth = userSmallWidth;
                }

                public void setUserSmallHeight(int userSmallHeight) {
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

                public void setBackImgWidth(int backImgWidth) {
                    this.backImgWidth = backImgWidth;
                }

                public void setBackImgHeight(int backImgHeight) {
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

                public int getUserBigWidth() {
                    return userBigWidth;
                }

                public int getUserBigHeight() {
                    return userBigHeight;
                }

                public String getUserSmallImg() {
                    return userSmallImg;
                }

                public int getUserSmallWidth() {
                    return userSmallWidth;
                }

                public int getUserSmallHeight() {
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

                public int getBackImgWidth() {
                    return backImgWidth;
                }

                public int getBackImgHeight() {
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

                /**
                 * eredarCode : 0
                 * eredarName : 萌宝用户
                 */

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

                    @Override
                    public String toString() {
                        return "UserRoleEntity{" +
                                "eredarCode=" + eredarCode +
                                ", eredarName='" + eredarName + '\'' +
                                '}';
                    }
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
                    dest.writeInt(this.userBigWidth);
                    dest.writeInt(this.userBigHeight);
                    dest.writeString(this.userSmallImg);
                    dest.writeInt(this.userSmallWidth);
                    dest.writeInt(this.userSmallHeight);
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
                    dest.writeInt(this.backImgWidth);
                    dest.writeInt(this.backImgHeight);
                    dest.writeInt(this.goldCount);
                    dest.writeInt(this.integralCount);
                    dest.writeInt(this.attentionCount);
                    dest.writeInt(this.fans);
                    dest.writeInt(this.isAttention);
                    dest.writeInt(this.isEredar);
                    dest.writeTypedList(userRole);
                }

                public UserBasicInfoEntity() {
                }

                protected UserBasicInfoEntity(Parcel in) {
                    this.userId = in.readInt();
                    this.recommoned = in.readInt();
                    this.userNike = in.readString();
                    this.userSex = in.readInt();
                    this.userBirthday = in.readString();
                    this.backgroundImg = in.readString();
                    this.userBigImg = in.readString();
                    this.userBigWidth = in.readInt();
                    this.userBigHeight = in.readInt();
                    this.userSmallImg = in.readString();
                    this.userSmallWidth = in.readInt();
                    this.userSmallHeight = in.readInt();
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
                    this.backImgWidth = in.readInt();
                    this.backImgHeight = in.readInt();
                    this.goldCount = in.readInt();
                    this.integralCount = in.readInt();
                    this.attentionCount = in.readInt();
                    this.fans = in.readInt();
                    this.isAttention = in.readInt();
                    this.isEredar = in.readInt();
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
                            ", userBigWidth=" + userBigWidth +
                            ", userBigHeight=" + userBigHeight +
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
            }


            public static class TopicImgListEntity implements Parcelable {
                private String img;
                private String contents;
                private int imgHeight;
                private int imgWidth;

                public void setImg(String img) {
                    this.img = img;
                }

                public void setContents(String contents) {
                    this.contents = contents;
                }

                public void setImgHeight(int imgHeight) {
                    this.imgHeight = imgHeight;
                }

                public void setImgWidth(int imgWidth) {
                    this.imgWidth = imgWidth;
                }

                public String getImg() {
                    return img;
                }

                public String getContents() {
                    return contents;
                }

                public int getImgHeight() {
                    return imgHeight;
                }

                public int getImgWidth() {
                    return imgWidth;
                }


                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.img);
                    dest.writeString(this.contents);
                    dest.writeInt(this.imgHeight);
                    dest.writeInt(this.imgWidth);
                }

                public TopicImgListEntity() {
                }

                protected TopicImgListEntity(Parcel in) {
                    this.img = in.readString();
                    this.contents = in.readString();
                    this.imgHeight = in.readInt();
                    this.imgWidth = in.readInt();
                }

                public static final Creator<TopicImgListEntity> CREATOR = new Creator<TopicImgListEntity>() {
                    public TopicImgListEntity createFromParcel(Parcel source) {
                        return new TopicImgListEntity(source);
                    }

                    public TopicImgListEntity[] newArray(int size) {
                        return new TopicImgListEntity[size];
                    }
                };

                @Override
                public String toString() {
                    return "TopicImgListEntity{" +
                            "img='" + img + '\'' +
                            ", contents='" + contents + '\'' +
                            ", imgHeight=" + imgHeight +
                            ", imgWidth=" + imgWidth +
                            '}';
                }
            }


            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.id);
                dest.writeInt(this.browseNumber);
                dest.writeString(this.title);
                dest.writeInt(this.isStick);
                dest.writeInt(this.replys);
                dest.writeString(this.topicType);
                dest.writeString(this.contents);
                dest.writeParcelable(this.userBasicInfo, 0);
                dest.writeInt(this.isEnssence);
                dest.writeString(this.publishDate);
                dest.writeTypedList(topicImgList);
            }

            public RowsEntity() {
            }

            protected RowsEntity(Parcel in) {
                this.id = in.readInt();
                this.browseNumber = in.readInt();
                this.title = in.readString();
                this.isStick = in.readInt();
                this.replys = in.readInt();
                this.topicType = in.readString();
                this.contents = in.readString();
                this.userBasicInfo = in.readParcelable(UserBasicInfoEntity.class.getClassLoader());
                this.isEnssence = in.readInt();
                this.publishDate = in.readString();
                this.topicImgList = in.createTypedArrayList(TopicImgListEntity.CREATOR);
            }

            public static final Creator<RowsEntity> CREATOR = new Creator<RowsEntity>() {
                public RowsEntity createFromParcel(Parcel source) {
                    return new RowsEntity(source);
                }

                public RowsEntity[] newArray(int size) {
                    return new RowsEntity[size];
                }
            };

            @Override
            public String toString() {
                return "RowsEntity{" +
                        "id=" + id +
                        ", browseNumber=" + browseNumber +
                        ", title='" + title + '\'' +
                        ", isStick=" + isStick +
                        ", replys=" + replys +
                        ", topicType='" + topicType + '\'' +
                        ", contents='" + contents + '\'' +
                        ", userBasicInfo=" + userBasicInfo +
                        ", isEnssence=" + isEnssence +
                        ", publishDate='" + publishDate + '\'' +
                        ", topicImgList=" + topicImgList +
                        '}';
            }
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.page);
            dest.writeInt(this.records);
            dest.writeInt(this.total);
            dest.writeString(this.userdata);
            dest.writeTypedList(rows);
        }

        public DataEntity() {
        }

        protected DataEntity(Parcel in) {
            this.page = in.readInt();
            this.records = in.readInt();
            this.total = in.readInt();
            this.userdata = in.readString();
            this.rows = in.createTypedArrayList(RowsEntity.CREATOR);
        }

        public static final Creator<DataEntity> CREATOR = new Creator<DataEntity>() {
            public DataEntity createFromParcel(Parcel source) {
                return new DataEntity(source);
            }

            public DataEntity[] newArray(int size) {
                return new DataEntity[size];
            }
        };

        @Override
        public String toString() {
            return "DataEntity{" +
                    "page=" + page +
                    ", records=" + records +
                    ", total=" + total +
                    ", userdata='" + userdata + '\'' +
                    ", rows=" + rows +
                    '}';
        }
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

    public TopicListModel() {
    }

    protected TopicListModel(Parcel in) {
        this.data = in.readParcelable(DataEntity.class.getClassLoader());
        this.msg = in.readString();
    }

    public static final Creator<TopicListModel> CREATOR = new Creator<TopicListModel>() {
        public TopicListModel createFromParcel(Parcel source) {
            return new TopicListModel(source);
        }

        public TopicListModel[] newArray(int size) {
            return new TopicListModel[size];
        }
    };

    @Override
    public String toString() {
        return "TopicListModel{" +
                "data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }

    /**
     * 获取话题列表 （根据分类）
     *
     * @param topicType       话题类型
     * @param pageNo          页数
     * @param numberOfPerPage 页面显示数量
     * @param callback        ResultCallback
     */
    public static void getTopicListRequest(String topicType, int pageNo, int numberOfPerPage, String token,OkHttpClientManager.ResultCallback<TopicListModel> callback) {
        HashMap<String, String> params = new HashMap<>();
        if (!TextUtils.isEmpty(topicType)) {
            params.put("topicType", topicType);
        }
        params.put("pageNo", String.valueOf(pageNo));
        params.put("numberOfPerPage", String.valueOf(numberOfPerPage));
        params.put("token", token);
        OkHttpClientManager.postAsyn(Api.TOPIC_LIST, params, callback);
    }
}
