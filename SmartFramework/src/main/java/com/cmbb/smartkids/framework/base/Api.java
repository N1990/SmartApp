package com.cmbb.smartkids.framework.base;

/**
 * 项目名称：SmartApp
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：16/3/1 上午9:42
 */
public class Api {

    public static final String HOME_GET_AD = "smart/topic/getAD";  //主页广告位
    public static final String HOME_RECOMMENDED_EREDAR = "smart/recommonedEredar"; // 主页推荐达人
    public static final String HOME_GET_PAGE = "smart/services/getPage";  //主页热门服务 and 主页服务列表

    public static final String TOPIC_TYPE = "smart/system/dictList"; // 话题类型
    public static final String TOPIC_LIST = "smart/topic/getPage"; // 话题列表

    public static final String USER_INFO = "smart/personal/getPersonal"; //获取用户信息

    public static final String SIGN_ARRIVE_REQUEST = "smart/user/sign";// 签到

    public static final String LOGIN_REQUEST = "smart/login"; //登录
    public static final String REGISTER_REQUEST = "smart/register"; //登录
    public static final String FORGEST_PWD = "smart/forgetPWD"; //忘记密码

    public static final String REGISTER_VERIFY_CODE = "smart/getRegisterSecurityCode"; // 获取注册验证码
    public static final String VERIFY_CODE = "smart/getSecurityCode"; //获取验证码，除注册外
    public static final String REGISTER_NEXT_REQUEST = "smart/registerNext"; //注册下一步

    public static final String UPDATA_IMG_FOR_USER = "smart/personal/updateBackgroundImg"; //更换个人的图片
    public static final String HOME_MAIN_HOT_SERVICE = "smart/services/getPage";  //服务列表

    public static final String TOPIC_DETAIL = "smart/topic/detial"; // 话题详情
    public static final String TOPIC_REPLAY = "smart/topic/replyList"; //回复列表

    public static final String TOPIC_COLLECT = "smart/topic/collect"; // 话题收藏
    public static final String TOPIC_DELETE = "smart/topic/delete"; // 删除话题
    public static final String TOPIC_REPORT = "smart/topic/reportTopic"; // 举报话题

    public static final String TOPIC_SPOT = "smart/topic/spot"; // 点赞
    public static final String TOPIC_REPLYTOPIC = "smart/topic/replyTopic"; // 回复话题
    public static final String REPLAY_DETAIL = "smart/topic/getReplyDetial"; // 回复详情
    public static final String TOPIC_DELETEREPLY = "smart/topic/deleteReply"; //删除回复
    public static final String TOPIC_REPORTREPLY = "smart/topic/reportReply"; // 举报回复

    public static final String TOPIC_PUBLISH = "smart/topic/publish"; // 话题发布















    public static final String FEEDBACK_SUGGEST_REQUEST = "smart/feedback/opinion";//意见反馈
    public static final String MODIFY_USER_INFO = "smart/personal/updatePersonal"; // 修改用户信息
    public static final String CHANGE_USER_ACCOUNT = "smart/logout"; // 切换账号



}
