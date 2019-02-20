package com.hksql.zhai.imgInfo;

import com.hksql.zhai.publicDao.hkRResult.HkRResultService;
import com.hksql.zhai.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class HkRImageIdService {

    private static Logger logger = LoggerFactory.getLogger(HkRResultService.class);

    private HkRImageIdinfoDao hk = new HkRImageIdinfoDao();

    private DateUtil dateUtil = new DateUtil();

    public void submitData(String ku){

        int count = hk.imgcount(ku);



        List<HkRImageIdInfo> list = null;
        if(count == 0){
           list = hk.queryImgAll();
        }else{
//            int rid = hk.getMaxId(ku);
            String date = dateUtil.getdayDiff(0,2)+ " 00:00:00";
            list = hk.queryImtgInfoById(date, 10137);
        }
        if(list != null && list.size() > 0){
            int result = hk.insertImgList(list,ku);
            if(result < 0){
                logger.error("10137数据插入失败！");
            }
        }

    }

    public void submitData1(String ku){

        int count = hk.imgcount(ku);



        List<HkRImageIdInfo> list = null;
        if(count == 0){
            list = hk.queryImgAll();
        }else{
//            int rid = hk.getMaxId(ku);
            String date = dateUtil.getdayDiff(-1,2)+ " 00:00:00";
            list = hk.queryImtgInfoById(date, 10137);
        }
        if(list != null && list.size() > 0){
            int result = hk.insertImgList(list,ku);
            if(result < 0){
                logger.error("10137数据插入失败！");
            }
        }

    }
}
