package com.hksql.zhai.oppo;

import com.hksql.zhai.publicDao.hkRResult.HkRResultDao;
import com.hksql.zhai.publicDao.hkRResult.HkRResultInfo;
import com.hksql.zhai.rStatistics.rStatisticsInfo.RStatisticService;
import com.hksql.zhai.utils.DateUtil;
import org.apache.log4j.Logger;

import java.util.List;


public class OPPOImgACT {
    private static Logger logger = Logger.getLogger(OPPOImgACT.class);
    private HkRResultDao da = new HkRResultDao();
    private OPPOImgDao op = new OPPOImgDao();

    public static void main(String[] args){
        String mydate1 = new DateUtil().getdayDiff(-2,2);
        String mydate2 = new DateUtil().getdayDiff(-2,1);
           new OPPOImgACT().submitData(mydate1);
        RStatisticService rs = new RStatisticService();
        rs.submitData(mydate2,10012);
        System.out.println("成功！");
    }

    public void submitData(String mydate){
        List<HkRResultInfo> list = op.queryActionByDate(10012,mydate);
        if(list == null || list.size() <= 0 ){
            logger.error("OPPO当日数据为空！");
            return ;
        }
        int result = da.insertRResult(list,10012);
        if(result == 0){
            logger.error("今日录入数据为空");
        }
    }

}
