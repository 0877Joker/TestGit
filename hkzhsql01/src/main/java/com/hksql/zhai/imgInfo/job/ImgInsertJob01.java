package com.hksql.zhai.imgInfo.job;

import com.hksql.zhai.imgInfo.HkRImageIdService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ImgInsertJob01 implements Job {
    private HkRImageIdService hk = new HkRImageIdService();

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        try {
            hk.submitData1("cms");
            Thread.sleep(3000);
            hk.submitData1("magi");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
