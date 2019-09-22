package com.root.cognition.common.until;

import com.root.cognition.common.config.Constant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Worry
 * @version 2019/9/22
 */
public class DateUntils {

    /**
     *计算年月/type 0:年 1：月 2 天 3 小时 4  分钟
     */
    public static String getYearMonthToYYDDHHmmSS(int type, String date, int num) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date1= df.parse(date);
            cal.setTime(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (type == Constant.INT_ZERO){
            cal.add(Calendar.YEAR, num);
        }else
        if (type == Constant.INT_ONE){
            cal.add(Calendar.MONTH, num);
        }else
        if (type == Constant.INT_TWO){
            cal.add(Calendar.DATE, num);
        }else
        if (type == Constant.INT_THREE){
            cal.add(Calendar.HOUR, num);
        }else
        if (type == Constant.INT_FOUR){
            cal.add(Calendar.MINUTE, num);
        }
        return df.format(cal.getTime());
    }

}
