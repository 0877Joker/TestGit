package com.hksql.zhai.oppo.job;

import com.hksql.zhai.oppo.OPPOImgDao;
import com.hksql.zhai.publicDao.hkRResult.HkRResultDao;
import com.hksql.zhai.publicDao.hkRResult.HkRResultInfo;
import com.hksql.zhai.rStatistics.rStatisticsInfo.RStatisticService;
import com.hksql.zhai.utils.DateUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class OPPOInsertJob implements Job {

    private OPPOImgDao op1 = new OPPOImgDao();
    private HkRResultDao hkd = new HkRResultDao();
    private RStatisticService rs= new RStatisticService();

    private static final Logger logger = LoggerFactory.getLogger(OPPOInsertJob.class);
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String mydate1 = new DateUtil().getdayDiff(-2,2);
        String mydate2 = new DateUtil().getdayDiff(-2,1);

        List<HkRResultInfo> list = op1.queryActionByDate(10012,mydate1);
        if(list == null || list.size() <=0){
            logger.error("OPPO表数据为空");
            return;
        }
        hkd.insertRResult(list,10012);
        rs.submitData(mydate2,10012);
    }
}
