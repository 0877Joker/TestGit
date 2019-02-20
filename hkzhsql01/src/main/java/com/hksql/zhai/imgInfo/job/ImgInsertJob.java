package com.hksql.zhai.imgInfo.job;

import com.hksql.zhai.imgInfo.HkRImageIdService;
import com.hksql.zhai.utils.DateUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ImgInsertJob implements Job {
    private HkRImageIdService hk = new HkRImageIdService();

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        try {
        hk.submitData("cms");
            Thread.sleep(3000);
        hk.submitData("magi");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
