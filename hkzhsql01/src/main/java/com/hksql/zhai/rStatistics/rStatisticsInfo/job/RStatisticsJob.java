package com.hksql.zhai.rStatistics.rStatisticsInfo.job;

import com.hksql.zhai.rStatistics.rStatisticsInfo.RStatisticService;
import com.hksql.zhai.utils.DateUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class RStatisticsJob implements Job {

    private RStatisticService hk = new RStatisticService();
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String mydate = new DateUtil().getdayDiff(-2,1);
        hk.submitData(mydate,0);
    }
}
