package com.hksql.zhai.ali;


import com.hksql.zhai.publicDao.hkRResult.HkRResultService;
import com.hksql.zhai.rStatistics.rStatisticsInfo.RStatisticService;


public class AliImgACT {

    public static void main(String[] args){

        HkRResultService hk = new HkRResultService();
        RStatisticService rs = new RStatisticService();
        hk.submitData("10028",10028,args[0]);
        rs.submitData(args[0],10028);
    }
}
