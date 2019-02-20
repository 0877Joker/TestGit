package com.hksql.zhai.ali.job;

import com.hksql.zhai.publicDao.hkRResult.HkRResultService;
import com.hksql.zhai.rStatistics.rStatisticsInfo.RStatisticService;
import com.hksql.zhai.utils.DateUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class AliApiJob implements Job {
    private HkRResultService hk = new HkRResultService();
    private RStatisticService rs = new RStatisticService();
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String mydate = new DateUtil().getdayDiff(-2,1);
        hk.submitData("10028",10028,mydate);
        rs.submitData(mydate,10028);
    }
}
