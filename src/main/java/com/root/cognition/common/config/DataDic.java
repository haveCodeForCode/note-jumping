package com.root.cognition.common.config;


/**
 * 项目常用变量
 *
 * @author LineInkBook
 */
public class DataDic {



    /**
     * 成功
     * 失败
     */
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


    /*** 部门根节点id */
    public static Long DEPT_ROOT_ID = 0L;
    /*** 村务信息 */
    public static String LOG_ERROR = "error";

}
