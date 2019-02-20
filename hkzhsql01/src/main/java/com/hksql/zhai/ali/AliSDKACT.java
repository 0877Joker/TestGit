package com.hksql.zhai.ali;

import com.hksql.zhai.publicDao.hkRResult.HkRResultService;
import com.hksql.zhai.rStatistics.rStatisticsInfo.RStatisticService;
import org.apache.log4j.Logger;

public class AliSDKACT {
    private static Logger logger = Logger.getLogger(AliSDKACT.class);

    public static void main(String[] args){

        HkRResultService hk = new HkRResultService();
        RStatisticService rs = new RStatisticService();
        hk.submitData("10107",10107,args[0]);
        rs.submitData(args[0],10107);
        rs.submitData(args[0],0);
//        rs.submitData("20190116",0);
    }
}
