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



}
