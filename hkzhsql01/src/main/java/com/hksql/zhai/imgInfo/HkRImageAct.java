package com.hksql.zhai.imgInfo;

import java.sql.Date;
import java.sql.Timestamp;

public class HkRImageAct {

    public static void main(String[] args){
//        new HkRImageIdService().submitData(new Timestamp(new Date()),10137);
        HkRImageIdService hk = new HkRImageIdService();
        hk.submitData("test-magi");
        hk.submitData("magi01");
    }
}
