package com.hksql.zhai.publicDao.hkRResult;


import com.hksql.zhai.imgInfo.HkRImageIdinfoDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class HkRResultService {

    private static Logger logger = LoggerFactory.getLogger(HkRResultService.class);

    private HkRResultDao hk = new HkRResultDao();

    private HkRImageIdinfoDao img = new HkRImageIdinfoDao();
    public void submitData(String companyid1,Integer comapnyid2, String mydate){
        List<HkRResultInfo> list = hk.queryActionByDate(companyid1,comapnyid2,mydate) ;
        if(list == null || list.size() <= 0){
            logger.error(comapnyid2+"当日数值为空");
            return;
        }
        int result = hk.insertRResult(list,comapnyid2);
        if(result == 0){
            logger.error("今日录入数据为空");
        }
    }
}
