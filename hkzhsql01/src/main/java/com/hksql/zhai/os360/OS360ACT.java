package com.hksql.zhai.os360;

import com.hksql.zhai.publicDao.hkRResult.HkRResultService;
import com.hksql.zhai.rStatistics.rStatisticsInfo.RStatisticService;

public class OS360ACT {
    public static void main(String[] args){

        HkRResultService hk = new HkRResultService();
        RStatisticService rs = new RStatisticService();
        hk.submitData("10085,10125",10085,args[0]);
        rs.submitData(args[0],10085);
    }
}
