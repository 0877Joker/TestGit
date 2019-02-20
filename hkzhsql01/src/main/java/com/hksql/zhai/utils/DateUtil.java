package com.hksql.zhai.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public String getdayDiff(int diff,int flag){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat df1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH,diff);
        if(flag == 1){
            return df1.format(cal.getTime());
        }else
            return df2.format(cal.getTime());
    }
}
