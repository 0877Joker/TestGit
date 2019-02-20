package com.hksql.zhai.goinee;

import com.hksql.zhai.publicDao.hkRResult.HkRResultService;
import com.hksql.zhai.rStatistics.rStatisticsInfo.RStatisticService;
import org.apache.log4j.Logger;


public class GioneeImgACT {

    private static Logger logger = Logger.getLogger(GioneeImgACT.class);

    public static void main(String[] args){



        HkRResultService hk = new HkRResultService();
        RStatisticService rs = new RStatisticService();
        hk.submitData("10004,10135",10004,args[0]);
        System.out.println("成功！");
        rs.submitData(args[0],10004);
        System.out.println("成功！");
    }

}
