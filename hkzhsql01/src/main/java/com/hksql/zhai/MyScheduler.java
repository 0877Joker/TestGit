package com.hksql.zhai;

import com.hksql.zhai.ali.job.AliApiJob;
import com.hksql.zhai.ali.job.AliSDKJob;
import com.hksql.zhai.goinee.job.GioneeRInsertJob;
import com.hksql.zhai.imgInfo.job.ImgInsertJob;
import com.hksql.zhai.imgInfo.job.ImgInsertJob01;
import com.hksql.zhai.oppo.job.OPPOInsertJob;
import com.hksql.zhai.rStatistics.rStatisticsImg.job.RStatisticsImgJob;
import com.hksql.zhai.rStatistics.rStatisticsInfo.job.RStatisticsJob;
import com.hksql.zhai.rStatistics.rStatisticsUpdate.job.RStatisticsUpdJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class MyScheduler {
    public static void main(String[] args) throws SchedulerException, InterruptedException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        //img更新
        JobDetail jobDetail = JobBuilder.newJob(ImgInsertJob.class)
                .withIdentity("job1", "group1").build();
        JobDetail jobDetail1 = JobBuilder.newJob(GioneeRInsertJob.class)
                .withIdentity("job2", "group2").build();
        JobDetail jobDetail2 = JobBuilder.newJob(OPPOInsertJob.class)
                .withIdentity("job3", "group3").build();

        JobDetail jobDetail3 = JobBuilder.newJob(AliApiJob.class)
                .withIdentity("job4", "group4").build();

        JobDetail jobDetail4 = JobBuilder.newJob(AliSDKJob.class)
                .withIdentity("job5", "group5").build();

        JobDetail jobDetail5 = JobBuilder.newJob(RStatisticsJob.class)
                .withIdentity("job6", "group6").build();

        JobDetail jobDetail6 = JobBuilder.newJob(ImgInsertJob01.class)
                .withIdentity("job7", "group7").build();

        JobDetail jobDetail7 = JobBuilder.newJob(RStatisticsImgJob.class).withIdentity("job8","group8").build();

        JobDetail jobDetail8 = JobBuilder.newJob(RStatisticsUpdJob.class).withIdentity("job9","group9").build();



//每小时一次
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("trigger1","triggerGroup1")
//                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 7-23 * * ?")).build();

        CronTrigger cronTrigger1 = TriggerBuilder.newTrigger().withIdentity("trigger2","triggerGroup2")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 5 3 * * ?")).build();

        CronTrigger cronTrigger2 = TriggerBuilder.newTrigger().withIdentity("trigger3","triggerGroup3")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 15 3 * * ?")).build();

        CronTrigger cronTrigger3 = TriggerBuilder.newTrigger().withIdentity("trigger4","triggerGroup4")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 25 3 * * ?")).build();

        CronTrigger cronTrigger4 = TriggerBuilder.newTrigger().withIdentity("trigger5","triggerGroup5")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 35 3 * * ?")).build();

        CronTrigger cronTrigger5 = TriggerBuilder.newTrigger().withIdentity("trigger6","triggerGroup6")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 45 3 * * ?")).build();

        CronTrigger cronTrigger6 = TriggerBuilder.newTrigger().withIdentity("trigger7","triggerGroup7")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 0 * * ?")).build();

        CronTrigger cronTrigger7 = TriggerBuilder.newTrigger().withIdentity("trigger8","triggerGroup8")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 55 3 * * ?")).build();

        CronTrigger cronTrigger8 = TriggerBuilder.newTrigger().withIdentity("trigger9","triggerGroup9")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 4 * * ?")).build();


//        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "triggerGroup1")
//                .startNow()//立即生效
//                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
//                        .withIntervalInSeconds(1)//每隔1s执行一次
//                        .repeatForever()).build();//一直执行
        scheduler.scheduleJob(jobDetail, cronTrigger);
        scheduler.scheduleJob(jobDetail1,cronTrigger1);
        scheduler.scheduleJob(jobDetail2,cronTrigger2);
        scheduler.scheduleJob(jobDetail3,cronTrigger3);
        scheduler.scheduleJob(jobDetail4,cronTrigger4);
        scheduler.scheduleJob(jobDetail5,cronTrigger5);
        scheduler.scheduleJob(jobDetail6,cronTrigger6);
        scheduler.scheduleJob(jobDetail7,cronTrigger7);
        scheduler.scheduleJob(jobDetail8,cronTrigger8);
        System.out.println("--------scheduler start ! ------------");
        scheduler.start();

//        TimeUnit.MINUTES.sleep(1);
//        scheduler.shutdown();
//        System.out.println("--------scheduler shutdown ! ------------");

    }
}
