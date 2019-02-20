package com.hksql.zhai;

import com.hksql.zhai.imgInfo.HkRImageIdInfo;
import com.hksql.zhai.imgInfo.HkRImageIdService;
import com.hksql.zhai.imgInfo.HkRImageIdinfoDao;
import com.hksql.zhai.utils.DateUtil;

import java.util.List;

public class Test {


    public static void main(String[] args){
//         HkRImageIdinfoDao test = new HkRImageIdinfoDao();
        HkRImageIdService test1 = new HkRImageIdService();
////         List<HkRImageIdInfo> list = test.queryImtgInfoByCreateTime("2019-01-08 11:40:18",10137);
////         int t = test.insertImgList(list);
////        System.out.println(t);
        test1.submitData("test-magi");
//        DateUtil dateUtil = new DateUtil();
//        System.out.println(dateUtil.getdayDiff(-1,2));
//        System.out.println(dateUtil.getdayDiff(-7,1));
    }
}
