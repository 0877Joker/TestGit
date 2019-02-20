package com.hksql.zhai.rStatistics.rStatisticsImg;

import com.hksql.zhai.imgInfo.HkTypeInfoDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class HKRStatInfoImgService {

    public static void main(String[] args){
        new HKRStatInfoImgService().initType("20190211");
    }

    private static Logger logger = LoggerFactory.getLogger(HKRStatInfoImgService.class);

    public void initType(String mydate){
        List<Integer>  type0 = new HkTypeInfoDao().queryType();
        HKRStatInfoImgDao imgInfo = new HKRStatInfoImgDao();
        List<HKRStatInfoImgInfo> list = new ArrayList<>();
        Integer temType;
        List<HKRStatInfoImgInfo> tem = null;
        for(int i = 0 ; i < type0.size() ; i++){
            temType = type0.get(i);
            tem = imgInfo.queryList(temType,mydate);
            if(tem!=null){
                list.addAll(tem);
            }
        }
        int len = list.size();
        if(len==0){
            len = -1;
        }else{
            len = imgInfo.insertRResult(list);
        }
        if(len == 0){
            logger.error("录入0条数据，请检查当天数据是否存在问题");
        }else{
            logger.info("当天录入数据数为"+len+"条");
        }
    }
}
