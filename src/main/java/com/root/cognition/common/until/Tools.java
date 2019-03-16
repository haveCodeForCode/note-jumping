package com.root.cognition.common.until;

/**
 * @author Worry
 * @version 2019/3/14
 */
public class Tools {
    /**
     * 检测字符串是否不为空(null,"","null")
     * @param s
     * @return 不为空则返回true，否则返回false
     */
    public static boolean notEmpty(String s){
        return s!=null && !"".equals(s) && !"null".equals(s);
    }

    /**
     * 检测字符串是否为空(null,"","null")
     * @param s
     * @return 为空则返回true，不否则返回false
     */
    public static boolean isEmpty(String s){
        return s==null || "".equals(s) || "null".equals(s);
    }

    /**
     * 检测字符串是否为空(null,"","null",whitespace)
     * @param s
     * @return 为空则返回true，不否则返回false
     */
    public static boolean isNullOrWhiteSpace(String s){
        return s==null || s.isEmpty() || s.equals("");
    }

}
