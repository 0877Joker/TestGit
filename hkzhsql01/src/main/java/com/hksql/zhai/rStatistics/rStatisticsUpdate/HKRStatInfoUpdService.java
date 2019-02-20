package com.hksql.zhai.rStatistics.rStatisticsUpdate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class HKRStatInfoUpdService {

    private HKRStatInfoUpdDao upd = new HKRStatInfoUpdDao();

    private static Logger logger;
    static{
        logger = LoggerFactory.getLogger(HKRStatInfoUpdService.class);
    }

    public static void main(String[] args) {
        new HKRStatInfoUpdService().dataLoad("20190205");
    }

    public void dataLoad(String mydate){
        int len = 0;
        List<HKRStatInfoUpdInfo> list0 = upd.queryList(mydate);

//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        if(list0 == null || list0.size() == 0 ){
            logger.error("查询数据0条！");
        }else{
            len = list0.size();
        }
        len = upd.insertRResult(list0);
        logger.info("按编辑人插入数据"+len+"条");
    }
}
