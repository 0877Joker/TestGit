package com.hksql.zhai.rStatistics.rStatisticsInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RStatisticService {

    private RStatisticsDao sta = new RStatisticsDao();

    private static final Integer temp = 10107;

    private static Logger logger = LoggerFactory.getLogger(RStatisticService.class);

    public static void main(String[] args) {
        RStatisticService tem = new RStatisticService();
        tem.submitData("20190209",10004);
        tem.submitData("20190209",10028);
        tem.submitData("20190209",0);
    }

    public void submitData(String mydate,Integer companyid){
        List<RStatisticsInfo> list = sta.queryStatistic(mydate,companyid);
        if(list == null || list.size()<=0){
            logger.error(companyid+"当日没有数据！");
            return ;
        }
        int result = sta.insertrateList(list);
        if(result == 0){
            logger.error(companyid+"表格中未得出数据！");
        }
    }
}
