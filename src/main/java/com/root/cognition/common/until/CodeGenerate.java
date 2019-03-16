package com.root.cognition.common.until;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author Worry
 * @version 2019/3/14
 */
public class CodeGenerate {

    /***
     * 根据传递的位数生成随机数据
     * @return long
     */
    public static long getRandomNumber(int bitCount) {
        long startValue = (long) Math.pow(10, bitCount - 1);
        long endValue = (long) Math.pow(10, bitCount);
        Random rnd = new Random();
        return (long) (Math.random() * (endValue - startValue) + startValue);
    }

    /**
     * 生成编码
     *
     * @param prefix 交易类型
     * @param num    第几个
     * @return 编码
     */
    public static String createNumCode(String prefix, int num) {
        //设置数字长度
        int numLength = 4;
        //声明当前时间年月日
        SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd");
        //创建字符串拼接放法
        StringBuilder cString = new StringBuilder();
        cString.append(prefix);
        cString.append(day.format(new Date()));
        cString.append(String.format("%0" + numLength + "d", num));
        //字符串转换
        String code = String.valueOf(cString);
        return code;
    }

}
