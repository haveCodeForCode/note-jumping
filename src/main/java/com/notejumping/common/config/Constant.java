package com.notejumping.common.config;

/**
 * 项目持久变量（不可动的变量）
 *
 * @author taoya
 */
public class Constant {

    /*** Menu菜单 首字母*/
    public static String MENU_INITIALS = "MU";

    /**
     * 自动去除表前缀
     */
    public static String AUTO_REOMVE_PRE = "true";
    /**
     * 停止计划任务
     */
    public static String STATUS_RUNNING_STOP = "stop";
    /**
     * 开启计划任务
     */
    public static String STATUS_RUNNING_START = "start";
    /**
     * 通知公告阅读状态-未读
     */
    public static String OA_NOTIFY_READ_NO = "0";
    /**
     * 通知公告阅读状态-已读
     */
    public static int OA_NOTIFY_READ_YES = 1;
    /**
     * 部门根节点id
     */
    public static Long DEPT_ROOT_ID = 0L;


    /**
     * 缓存方式
     */
    public static String CACHE_TYPE_REDIS = "redis";

    public static String LOG_ERROR = "error";



    /**
     * 删除标记（0：正常；1：删除；2：审核；）
     *
     * @ DEL_FLAG
     */
    public static final String DEL_FLAG_NORMAL = "0";
    public static final String DEL_FLAG_DELETE = "1";
    public static final String DEL_FLAG_AUDIT = "2";


    /**
     * 常用数字
     */
    public static final int INT_ZERO = 0;
    public static final int INT_ONE = 1;
    public static final int INT_TWO = 2;
    public static final int INT_THREE = 3;
    public static final int INT_FOUR = 4;
    public static final String STRING_ZERO = "0";
    public static final String STRING_ONE = "1";
    public static final String STRING_TWO = "2";
    public static final String STRING_THREE = "3";
    public static final String STRING_FOUR = "4";
    public static final String STRING_SEVEN = "7";



    /**
     * 成功
     * 失败
     */
    public static final String OK ="OK";
    public static final String SUCCESS = "success";
    public static final String FAIL = "fail";

    /**
     * MD5加密
     * MD5解密
     */
    public static final String ENCRY = "encry";
    public static final String DECRY = "decry";

    /**
     * 返回数据参数
     * -2:账户系统异常
     * 1:正常
     * -1:系统异常
     * 0:权限不足
     * 2:信息缺失
     * 3:已过期,已操作
     */
    public static final int RETURN_STATUS_PAYBUG = -5;
    public static final int RETURN_STATUS_ACCOUNTBUG = -2;
    public static final int RETURN_STATUS_NORMAL = 1;
    public static final int RETURN_STATUS_SYSBUG = -1;
    public static final int RETURN_STATUS_AUTHBUG = 0;
    public static final int RETURN_STATUS_INFOBUG = 2;
    public static final int RETURN_STATUS_OVERCONTROLBUG = 3;

    //发送短信类型
    /**主动自由发布短信**/
    public static final String FREE_SMS = "free_send_sms";
    /**注册信息**/
    public static final String REGISTER_SMS = "register_send_sms";
    /**验证信息**/
    public static final String VERIFICATION_SMS = "Verification_send_sms";
    /**定时业务短信 **/
    public static final String service_SMS = "Verification_send_sms";
}
