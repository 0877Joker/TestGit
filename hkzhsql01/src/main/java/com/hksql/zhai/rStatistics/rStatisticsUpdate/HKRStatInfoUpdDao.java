package com.hksql.zhai.rStatistics.rStatisticsUpdate;

import com.hksql.zhai.utils.DBUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HKRStatInfoUpdDao {

    private static Logger logger = LoggerFactory.getLogger(HKRStatInfoUpdDao.class);

    public List<HKRStatInfoUpdInfo> queryList(String mydate){
        DBUtil db = new DBUtil("cms");

        logger.info("开始按编辑人进行查询");
        String sql = "SELECT "+mydate+" mydate,r_updateuser,SUM(exp) exp,SUM(click) click, ROUND(SUM(click)/SUM(exp),4) rate , NOW() statistics_createtime ,'zhaiyao' statistics_createuser FROM \n" +
                "(SELECT r_image_id,r_updateuser  FROM hk_r_image_info ) u \n" +
                "JOIN \n" +
                "(SELECT t_image_id,SUM(t_c_pv) exp , SUM(t_s_pv) click, SUM(t_c_pv_click) click_c FROM\n" +
                "(SELECT t_image_id,t_c_pv,t_s_pv ,t_c_pv_click FROM hk_r_result_info_10004 WHERE t_log_date = "+mydate+"\n" +
                " UNION ALL\n" +
                " SELECT t_image_id,t_c_pv,t_s_pv ,t_c_pv_click FROM hk_r_result_info_10012 WHERE t_log_date = "+mydate+"\n" +
                " UNION ALL\n" +
                " SELECT t_image_id,t_c_pv,t_s_pv ,t_c_pv_click FROM hk_r_result_info_10028 WHERE t_log_date = "+mydate+"\n" +
                " UNION ALL\n" +
                " SELECT t_image_id,t_c_pv,t_s_pv ,t_c_pv_click FROM hk_r_result_info_10085 WHERE t_log_date = "+mydate+"\n" +
                " UNION ALL\n" +
                " SELECT t_image_id,t_c_pv,t_s_pv ,t_c_pv_click FROM hk_r_result_info_10107 WHERE t_log_date = "+mydate+") h \n" +
                " GROUP BY h.t_image_id\n" +
                " HAVING click < click_c) t\n" +
                "  on u.r_image_id = t.t_image_id\n" +
                "\tGROUP BY r_updateuser\n" +
                "\tORDER BY rate desc";
        ResultSet rs = db.select(sql);

        List<HKRStatInfoUpdInfo> list = new ArrayList<>();

        HKRStatInfoUpdInfo tem = null;

        try{
            while (rs.next()) {
                tem = new HKRStatInfoUpdInfo();
                tem.setStatisticsDate(rs.getInt("mydate"));
                tem.setStatisticsUpdateUser(rs.getString("r_updateuser"));
                tem.setStatisticsExp(rs.getLong("exp"));
                tem.setStatisticsClick(rs.getLong("click"));
                tem.setStatisticsRate(rs.getBigDecimal("rate"));
                tem.setStatisticsCreateTime(rs.getTimestamp("statistics_createtime"));
                tem.setStatisticsCreateUser(rs.getString("statistics_createuser"));
                list.add(tem);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            db.close();
        }
        logger.info("编辑人查询完成，准备录入！");
        return list;
    }

    public int insertRResult(List<HKRStatInfoUpdInfo> list){
        DBUtil db = new DBUtil("cms");
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = "REPLACE INTO hk_r_statistics_info_updateuser(statistics_date,statistics_updateuser,statistics_exp,statistics_click,statistics_rate,statistics_createtime,statistics_createuser) VALUES(?,?,?,?,?,?,?)";

        if(list == null || list.size() == 0 ){
            logger.error("数据为空，请检验");
            return -1;
        }
        int len = list.size();

        try{
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            conn.setAutoCommit(false);
            HKRStatInfoUpdInfo tem = null;
            for(int i = 0 ;i<len ; i++){
                tem = list.get(i);
                ps.setInt(1,tem.getStatisticsDate());
                ps.setString(2,tem.getStatisticsUpdateUser());
                ps.setLong(3,tem.getStatisticsExp());
                ps.setLong(4,tem.getStatisticsClick());
                ps.setBigDecimal(5,tem.getStatisticsRate());
                ps.setTimestamp(6,tem.getStatisticsCreateTime());
                ps.setString(7,tem.getStatisticsCreateUser());
                ps.addBatch();

                if((i!=0 && i%200 == 0) || i == len -1 ){
                    ps.executeBatch();
                    conn.commit();
                    ps.clearBatch();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }finally {
            db.close();
        }
        logger.info("数据录入成功！");
        return len;
    }
}
