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
 * 创建时间：15/11/10 下午3:51
 */
public class TopicReplayModel implements Parcelable {


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
            private int id = -1;
            private String contents;

            private UserBasicInfoEntity userBasicInfo;
            private int replysFloor;
            private String img;
            private String imgWidth;
            private String createDate;
            private String imgHeight;
            private int childCount;

            private List<ChildReplyEntity> childReply;

            public void setId(int id) {
                this.id = id;
            }

            public void setContents(String contents) {
                this.contents = contents;
            }

            public void setUserBasicInfo(UserBasicInfoEntity userBasicInfo) {
                this.userBasicInfo = userBasicInfo;
            }

            public int getChildCount() {
                return childCount;
            }

            public void setChildCount(int childCount) {
                this.childCount = childCount;
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

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public void setImgHeight(String imgHeight) {
                this.imgHeight = imgHeight;
            }

            public void setChildReply(List<ChildReplyEntity> childReply) {
                this.childReply = childReply;
            }

            public int getId() {
                return id;
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

            public String getCreateDate() {
                return createDate;
            }

            public String getImgHeight() {
                return imgHeight;
            }

            public List<ChildReplyEntity> getChildReply() {
                return childReply;
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


                public int getIsLoginUser() {
                    return isLoginUser;
                }

                public void setIsLoginUser(int isLoginUser) {
                    this.isLoginUser = isLoginUser;
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

                public String getUserBigWidth() {
                    return userBigWidth;
                }

                public String getUserBigHeight() {
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

            public static class ChildReplyEntity implements Parcelable {
                private int id;
                private String contents;
                /**
                 * userId : 8
                 * recommoned : 1
                 * userNike : fysq
                 * userSex : 1
                 * userBirthday :
                 * backgroundImg : http://smart.image.alimmdn.com/app/test/2015-11-6/image_581076c1bbb4457b89d851364b2bcea0
                 * userBigImg :
                 * userBigWidth :
                 * userBigHeight :
                 * userSmallImg : http://smart.image.alimmdn.com/app/test/2015-10-26/image_4ac31e00b9d44dcb8f94352c640f0bc0
                 * userSmallWidth : 720
                 * userSmallHeight : 1280
                 * loginAccountType : 0
                 * loginTime : 2015-11-06 17:47:07
                 * loginAccount : 15901718791
                 * token :
                 * isShutup : 0
                 * shutupTime :
                 * isBanned : 0
                 * userAddress : shshwhw
                 * userPhone : 15901718791
                 * userPhoneVersion :
                 * province : 120000
                 * provinceText :
                 * district : 120101
                 * districtText :
                 * city : 120100
                 * cityText :
                 * userLevel : 1
                 * userPresentation :
                 * backImgWidth : 720
                 * backImgHeight : 1280
                 * goldCount : 12
                 * integralCount : 2
                 * attentionCount : 1
                 * userRole : [{"eredarCode":0,"eredarName":"萌宝用户"}]
                 * fans : 0
                 * isAttention : 0
                 * isEredar : 0
                 */

                private UserBasicInfoEntity userBasicInfo;
                private int replysFloor;
                private String img;
                private String imgWidth;
                private String createDate;
                private String imgHeight;

                public void setId(int id) {
                    this.id = id;
                }

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

                public void setCreateDate(String createDate) {
                    this.createDate = createDate;
                }

                public void setImgHeight(String imgHeight) {
                    this.imgHeight = imgHeight;
                }

                public int getId() {
                    return id;
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

                public String getCreateDate() {
                    return createDate;
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


                    public int getIsLoginUser() {
                        return isLoginUser;
                    }

                    public void setIsLoginUser(int isLoginUser) {
                        this.isLoginUser = isLoginUser;
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

                    public String getUserBigWidth() {
                        return userBigWidth;
                    }

                    public String getUserBigHeight() {
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
                    return "ChildReplyEntity{" +
                            "id=" + id +
                            ", contents='" + contents + '\'' +
                            ", userBasicInfo=" + userBasicInfo +
                            ", replysFloor=" + replysFloor +
                            ", img='" + img + '\'' +
                            ", imgWidth='" + imgWidth + '\'' +
                            ", createDate='" + createDate + '\'' +
                            ", imgHeight='" + imgHeight + '\'' +
                            '}';
                }


                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeInt(this.id);
                    dest.writeString(this.contents);
                    dest.writeParcelable(this.userBasicInfo, 0);
                    dest.writeInt(this.replysFloor);
                    dest.writeString(this.img);
                    dest.writeString(this.imgWidth);
                    dest.writeString(this.createDate);
                    dest.writeString(this.imgHeight);
                }

                public ChildReplyEntity() {
                }

                protected ChildReplyEntity(Parcel in) {
                    this.id = in.readInt();
                    this.contents = in.readString();
                    this.userBasicInfo = in.readParcelable(UserBasicInfoEntity.class.getClassLoader());
                    this.replysFloor = in.readInt();
                    this.img = in.readString();
                    this.imgWidth = in.readString();
                    this.createDate = in.readString();
                    this.imgHeight = in.readString();
                }

                public static final Creator<ChildReplyEntity> CREATOR = new Creator<ChildReplyEntity>() {
                    public ChildReplyEntity createFromParcel(Parcel source) {
                        return new ChildReplyEntity(source);
                    }

                    public ChildReplyEntity[] newArray(int size) {
                        return new ChildReplyEntity[size];
                    }
                };
            }

            @Override
            public String toString() {
                return "RowsEntity{" +
                        "id=" + id +
                        ", contents='" + contents + '\'' +
                        ", userBasicInfo=" + userBasicInfo +
                        ", replysFloor=" + replysFloor +
                        ", img='" + img + '\'' +
                        ", imgWidth='" + imgWidth + '\'' +
                        ", createDate='" + createDate + '\'' +
                        ", imgHeight='" + imgHeight + '\'' +
                        ", childReply=" + childReply +
                        '}';
            }


            public RowsEntity() {
            }


            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.id);
                dest.writeString(this.contents);
                dest.writeParcelable(this.userBasicInfo, 0);
                dest.writeInt(this.replysFloor);
                dest.writeString(this.img);
                dest.writeString(this.imgWidth);
                dest.writeString(this.createDate);
                dest.writeString(this.imgHeight);
                dest.writeInt(this.childCount);
                dest.writeTypedList(childReply);
            }

            protected RowsEntity(Parcel in) {
                this.id = in.readInt();
                this.contents = in.readString();
                this.userBasicInfo = in.readParcelable(UserBasicInfoEntity.class.getClassLoader());
                this.replysFloor = in.readInt();
                this.img = in.readString();
                this.imgWidth = in.readString();
                this.createDate = in.readString();
                this.imgHeight = in.readString();
                this.childCount = in.readInt();
                this.childReply = in.createTypedArrayList(ChildReplyEntity.CREATOR);
            }

            public static final Creator<RowsEntity> CREATOR = new Creator<RowsEntity>() {
                public RowsEntity createFromParcel(Parcel source) {
                    return new RowsEntity(source);
                }

                public RowsEntity[] newArray(int size) {
                    return new RowsEntity[size];
                }
            };
        }


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
    }

    @Override
    public String toString() {
        return "CommunityReplayModel{" +
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

    public TopicReplayModel() {
    }

    protected TopicReplayModel(Parcel in) {
        this.data = in.readParcelable(DataEntity.class.getClassLoader());
        this.msg = in.readString();
    }

    public static final Creator<TopicReplayModel> CREATOR = new Creator<TopicReplayModel>() {
        public TopicReplayModel createFromParcel(Parcel source) {
            return new TopicReplayModel(source);
        }

        public TopicReplayModel[] newArray(int size) {
            return new TopicReplayModel[size];
        }
    };


    /**
     * 获取回复列表
     *
     * @param id
     * @param replyType
     * @param pageNo
     * @param numberOfPerPage
     * @param token
     * @param callback
     */
    public static void getReplayListRequest(int id, int replyType, int pageNo, int numberOfPerPage, String token, OkHttpClientManager.ResultCallback<TopicReplayModel> callback) {
        HashMap<String, String> params = new HashMap<>();
        if (id == -1) return;
        params.put("id", id + "");
        params.put("replyType", 1 + "");
        params.put("pageNo", String.valueOf(pageNo));
        params.put("numberOfPerPage", String.valueOf(numberOfPerPage));
        params.put("token", token);
        OkHttpClientManager.postAsyn(Api.TOPIC_REPLAY, params, callback);
    }


    /**
     * 收藏话题
     *
     * @param topicId
     * @param isCollect
     * @param token
     * @param callback
     */
    public static void handleCollectionRequest(int topicId, final int isCollect, String token, OkHttpClientManager.ResultCallback<TopicReplayModel> callback) {
        HashMap<String, String> params = new HashMap<>();
        if (topicId == -1) return;
        params.put("id", topicId + "");
        switch (isCollect) {
            case 0:
                params.put("isCollect", 1 + "");
                break;
            case 1:
                params.put("isCollect", 0 + "");
                break;
        }
        params.put("token", token);
        OkHttpClientManager.postAsyn(Api.TOPIC_COLLECT, params, callback);
    }

    /**
     * 删除话题
     *
     * @param topicId
     * @param token
     * @param callback
     */
    public static void handleDeleteRequest(int topicId, String token, OkHttpClientManager.ResultCallback<TopicReplayModel> callback) {
        HashMap<String, String> params = new HashMap<>();
        if (topicId == -1) return;
        params.put("id", topicId + "");
        params.put("isDelete", 1 + "");
        params.put("token", token);
        OkHttpClientManager.postAsyn(Api.TOPIC_DELETE, params, callback);
    }

    /**
     * 举报
     *
     * @param topicId
     * @param token
     * @param callback
     */
    public static void handleReportRequest(int topicId, String token, OkHttpClientManager.ResultCallback<TopicReplayModel> callback) {
        HashMap<String, String> params = new HashMap<>();
        if (topicId == -1) return;
        params.put("id", topicId + "");
        params.put("isDelete", 1 + "");
        params.put("token", token);
        OkHttpClientManager.postAsyn(Api.TOPIC_REPORT, params, callback);
    }


    /**
     * 回复话题
     *
     * @param topicId
     * @param replyType
     * @param contents
     * @param img
     * @param imgWidth
     * @param imgHeight
     * @param token
     * @param callback
     */
    public static void handleReplayRequest(int topicId, int replyType, String contents, String img, String imgWidth, String imgHeight, String token, OkHttpClientManager.ResultCallback<TopicReplayModel> callback) {
        HashMap<String, String> params = new HashMap<>();
        if (topicId == -1) return;
        params.put("topicId", topicId + "");
        params.put("replyType", replyType + "");
        if (!TextUtils.isEmpty(contents))
            params.put("contents", contents);
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
