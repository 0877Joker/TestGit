package com.hksql.zhai.rStatistics.rStatisticsImg.job;

import com.hksql.zhai.rStatistics.rStatisticsImg.HKRStatInfoImgService;
import com.hksql.zhai.utils.DateUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class RStatisticsImgJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        HKRStatInfoImgService hk = new HKRStatInfoImgService();
        String mydate = new DateUtil().getdayDiff(-2,1);
        new HKRStatInfoImgService().initType(mydate);
    }
}
