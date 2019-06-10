package com.root.cognition.common.config;

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
    public static final String STRING_ZERO = "0";
    public static final String STRING_ONE = "1";

}
