package com.hksql.zhai.rStatistics.rStatisticsUpdate.job;

import com.hksql.zhai.rStatistics.rStatisticsUpdate.HKRStatInfoUpdService;
import com.hksql.zhai.utils.DateUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class RStatisticsUpdJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String mydate = new DateUtil().getdayDiff(-2,1);
        new HKRStatInfoUpdService().dataLoad(mydate);
    }
}
