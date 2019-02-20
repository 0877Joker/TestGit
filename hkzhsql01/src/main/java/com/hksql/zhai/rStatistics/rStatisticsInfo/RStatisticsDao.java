package com.hksql.zhai.rStatistics.rStatisticsInfo;

import com.hksql.zhai.utils.DBUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RStatisticsDao {
    private static Logger logger = LoggerFactory.getLogger(RStatisticsDao.class);

    private static final Map<Integer,String> myMap;
    static {
        myMap = new HashMap<>();
        myMap.put(0,"全部");
        myMap.put(10004,"金立");
        myMap.put(10012,"OPPO");
        myMap.put(10028,"阿里个性化");
        myMap.put(10107,"阿里SDK");
        myMap.put(10085,"360");
    }

    public List<RStatisticsInfo> queryStatistic(String logDate, Integer companyid){
        DBUtil db = new DBUtil("cms");
        String sql = "";
        if(companyid.equals(new Integer(0))){
            sql = "SELECT h.mydate,h.type_id,h.type_name_zh type_name,"+companyid+" comid,'"+myMap.get(companyid)+"' com_name,IFNULL(k.exp,0) exp,IFNULL(k.click,0) click,IFNULL(k.rateclick,0) rateclick,NOW() date1,'zhaiyao' u1,NOW() date2,'zhaiyao' u2 FROM\n" +
                    "(SELECT type_id,type_name_zh , "+logDate+" mydate FROM hk_type_info) h\n" +
                    "LEFT JOIN \n" +
                    "(SELECT r.t_log_date,i.r_type_id,SUM(r.t_c_pv) exp,SUM(r.t_s_pv) click ,IF(SUM(r.t_c_pv) = 0,0,SUM(r.t_s_pv)/SUM(r.t_c_pv)) rateclick \n" +
                    "FROM (\n" +
                    "SELECT t_log_date,t_image_id,t_c_pv,t_s_pv FROM hk_r_result_info_10004 WHERE t_log_date = "+logDate+"\n" +
                    "UNION ALL\n" +
                    "SELECT t_log_date,t_image_id,t_c_pv,t_s_pv FROM hk_r_result_info_10012 WHERE t_log_date = "+logDate+"\n" +
                    "UNION ALL\n" +
                    "SELECT t_log_date,t_image_id,t_c_pv,t_s_pv FROM hk_r_result_info_10028 WHERE t_log_date = "+logDate+"\n" +
                    "UNION ALL\n" +
                    "SELECT t_log_date,t_image_id,t_c_pv,t_s_pv FROM hk_r_result_info_10085 WHERE t_log_date = "+logDate+"\n" +
                    "UNION ALL\n" +
                    "SELECT t_log_date,t_image_id,t_c_pv,t_s_pv FROM hk_r_result_info_10107 WHERE t_log_date = "+logDate+"\n" +
                    ") r  JOIN hk_r_image_info i ON r.t_image_id = i.r_image_id \n" +
                    "WHERE r.t_log_date = "+logDate+" GROUP BY r.t_log_date,i.r_type_id HAVING rateclick < 1) k ON h.type_id = k.r_type_id;";
        }else {
            sql = "SELECT h.mydate,h.type_id,h.type_name_zh type_name," + companyid + " comid,'" + myMap.get(companyid) + "' com_name,IFNULL(k.exp,0) exp,IFNULL(k.click,0) click,IFNULL(k.rateclick,0) rateclick,NOW() date1,'zhaiyao' u1,NOW() date2,'zhaiyao' u2 FROM\n" +
                    "(SELECT type_id,type_name_zh , " + logDate + " mydate FROM hk_type_info) h\n" +
                    "LEFT JOIN \n" +
                    "(SELECT r.t_log_date,i.r_type_id,SUM(r.t_c_pv) exp,SUM(r.t_s_pv) click ,IF(SUM(r.t_c_pv) = 0,0,SUM(r.t_s_pv)/SUM(r.t_c_pv)) rateclick FROM hk_r_result_info_"+companyid+" r  JOIN hk_r_image_info i ON r.t_image_id = i.r_image_id \n" +
                    "WHERE r.t_log_date = " + logDate + " GROUP BY r.t_log_date,i.r_type_id HAVING rateclick < 1) k ON h.type_id = k.r_type_id";
        }
        ResultSet rs = db.select(sql);
        List<RStatisticsInfo> list = new ArrayList<>();
        if(rs == null){
            logger.error(companyid+"表插入结果时查询出错！");
            return null;
        }
        int len1 = list.size();
        try{
            while(rs.next()){
               RStatisticsInfo tem = new RStatisticsInfo();
               tem.setMydate(rs.getInt("mydate"));
               tem.setTypeId(rs.getInt("type_id"));
               tem.setTypeName(rs.getString("type_name"));
               tem.setCompanyId(rs.getInt("comid"));
               tem.setCompanyName(rs.getString("com_name"));
               tem.setExp(rs.getLong("exp"));
               tem.setClick(rs.getLong("click"));
               tem.setRateClick(rs.getBigDecimal("rateclick"));
               tem.setCreateTime(rs.getTimestamp("date1"));
               tem.setCreateUser(rs.getString("u1"));
               tem.setUpdateTime(rs.getTimestamp("date2"));
               tem.setUpdateUser(rs.getString("u2"));

               list.add(tem);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            db.close();
        }
        logger.info("录入数据查询完成");
        return list;
    }


    public int insertrateList(List<RStatisticsInfo> list){
        DBUtil db = new DBUtil("cms");
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = "REPLACE INTO hk_r_statistics_info(statistics_date, statistics_type_id, statistics_type_name, statistics_company_id, statistics_company_name, statistics_exp,statistics_click,statistics_rate, statistics_createtime, statistics_createuser, statistics_updatetime, statistics_updateuser) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        int len1 = list.size();
        try{
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);

            conn.setAutoCommit(false);
            RStatisticsInfo tem = null;
            int len = list.size();
            for(int i = 0 ; i<len ; i++){
                tem = list.get(i);
               ps.setInt(1,tem.getMydate());
               ps.setInt(2,tem.getTypeId());
               ps.setString(3,tem.getTypeName());
               ps.setInt(4,tem.getCompanyId());
               ps.setString(5,tem.getCompanyName());
               ps.setLong(6,tem.getExp());
               ps.setLong(7,tem.getClick());
               ps.setBigDecimal(8,tem.getRateClick());
               ps.setTimestamp(9,tem.getCreateTime());
               ps.setString(10,tem.getCreateUser());
               ps.setTimestamp(11,tem.getUpdateTime());
               ps.setString(12,tem.getUpdateUser());

                ps.addBatch();
                if((i!=0 && i%200==0) || i==len-1){
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
//            list.clear();
        }

        return len1;
    }

}
