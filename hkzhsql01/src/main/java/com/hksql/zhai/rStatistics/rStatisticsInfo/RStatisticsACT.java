package com.hksql.zhai.rStatistics.rStatisticsInfo;

public class RStatisticsACT {
    public static void main(String[] args){

        new RStatisticService().submitData(args[0],0);
        System.out.println("成功！");
    }
}
