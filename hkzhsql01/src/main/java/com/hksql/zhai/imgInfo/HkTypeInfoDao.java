package com.hksql.zhai.imgInfo;

import com.hksql.zhai.utils.DBUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HkTypeInfoDao {

    private static Logger logger = LoggerFactory.getLogger(HkTypeInfoDao.class);

    public List<Integer> queryType(){
        DBUtil db = new DBUtil("test-magi");

        logger.info("开始寻找Type类");
        String sql = "SELECT type_id FROM hk_type_info;";

        ResultSet rs = db.select(sql);
        if(rs == null){
            logger.error("查询中断，请重试！");
            return null;
        }
        List<Integer> list = new ArrayList<>();
        try{
            while(rs.next()){
               list.add(rs.getInt("type_id"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            db.close();
        }
        logger.info("数据查询完成");
        return list;
    }

}
