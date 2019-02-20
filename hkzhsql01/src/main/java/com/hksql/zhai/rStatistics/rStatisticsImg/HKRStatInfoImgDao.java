package com.hksql.zhai.rStatistics.rStatisticsImg;

import com.hksql.zhai.utils.DBUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HKRStatInfoImgDao {
    private static Logger logger = LoggerFactory.getLogger(HKRStatInfoImgDao.class);

    public List<HKRStatInfoImgInfo> queryList(Integer type,String mydate){
        DBUtil db = new DBUtil("cms");
        logger.info("分类数据开始查询，请耐心等待！");
        String sql = "SELECT r_image_id,"+mydate+" mydate,0 company_id, '全部' company_name,r_title,r_type_id,r_type_name,exp,click, ROUND(click/exp,4) rate FROM\n" +
                " (SELECT r_image_id , r_title,r_type_id,r_type_name FROM hk_r_image_info WHERE r_type_id = "+type+") r\n" +
                " JOIN\n" +
                " (SELECT t.t_image_id,SUM(t.t_c_pv) exp,SUM(t.t_s_pv) click ,SUM(t.t_c_pv_click) c_click FROM\n" +
                " (\n" +
                " SELECT t_image_id,t_c_pv,t_s_pv ,t_c_pv_click FROM hk_r_result_info_10004 WHERE t_log_date = "+mydate+"\n" +
                " UNION ALL\n" +
                " SELECT t_image_id,t_c_pv,t_s_pv ,t_c_pv_click FROM hk_r_result_info_10012 WHERE t_log_date = "+mydate+"\n" +
                " UNION ALL\n" +
                " SELECT t_image_id,t_c_pv,t_s_pv ,t_c_pv_click FROM hk_r_result_info_10028 WHERE t_log_date = "+mydate+"\n" +
                " UNION ALL\n" +
                " SELECT t_image_id,t_c_pv,t_s_pv ,t_c_pv_click FROM hk_r_result_info_10085 WHERE t_log_date = "+mydate+"\n" +
                " UNION ALL\n" +
                " SELECT t_image_id,t_c_pv,t_s_pv ,t_c_pv_click FROM hk_r_result_info_10107 WHERE t_log_date = "+mydate+") t\n" +
                "WHERE t.t_c_pv_click > t.t_s_pv\n"+
                " GROUP BY t.t_image_id\n" +
                " ) q ON r.r_image_id = q.t_image_id\n" +
                " ORDER BY rate DESC\n" +
                " LIMIT 10\n";
        ResultSet rs = db.select(sql);
        List<HKRStatInfoImgInfo> list = new ArrayList<>();

        HKRStatInfoImgInfo tem = null;
        try{
            if(rs.next()){
                do {
                    tem = new HKRStatInfoImgInfo();
                    tem.setStatisticsImgId(rs.getString("r_image_id"));
                    tem.setStatisticsDate(rs.getInt("mydate"));
                    tem.setStatisticsCompanyId(rs.getInt("company_id"));
                    tem.setStatisticsCompanyName(rs.getString("company_name"));
                    tem.setStatisticsTitle(rs.getString("r_title"));
                    tem.setStatisticsTypeId(rs.getInt("r_type_id"));
                    tem.setStatisticsTypeName(rs.getString("r_type_name"));
                    tem.setStatisticsExp(rs.getLong("exp"));
                    tem.setStatisticsClick(rs.getLong("click"));
                    tem.setStatisticsRate(rs.getBigDecimal("rate"));
                    list.add(tem);
                }while(rs.next());
            }else{
                logger.info(type+"类型"+mydate+"日数据为空，请注意！");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            db.close();
        }
        logger.info(type+"类型数据查询完成");
        return list;
    }



    public int insertRResult(List<HKRStatInfoImgInfo> list ){
        DBUtil db = new DBUtil("cms");
        Connection conn = null;
        PreparedStatement ps = null;

        if(list == null || list.size() == 0){
            logger.error("插入数据为空！");
            return -1;
        }

        String sql = "REPLACE INTO hk_r_statistics_info_img(statistics_img_id,statistics_date,statistics_company_id,statistics_company_name,statistics_title,statistics_type_id,statistics_type_name,statistics_exp,statistics_click,statistics_rate) VALUES (?,?,?,?,?,?,?,?,?,?)";
        int len1 = list.size();
        try{
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            conn.setAutoCommit(false);
            HKRStatInfoImgInfo tem = null;
            for(int i = 0 ;i< len1;i++){
                tem = list.get(i);
                ps.setString(1,tem.getStatisticsImgId());
                ps.setInt(2,tem.getStatisticsDate());
                ps.setInt(3,tem.getStatisticsCompanyId());
                ps.setString(4,tem.getStatisticsCompanyName());
                ps.setString(5,tem.getStatisticsTitle());
                ps.setInt(6,tem.getStatisticsTypeId());
                ps.setString(7,tem.getStatisticsTypeName());
                ps.setLong(8,tem.getStatisticsExp());
                ps.setLong(9,tem.getStatisticsClick());
                ps.setBigDecimal(10,tem.getStatisticsRate());
                ps.addBatch();

                if((i!=0 && i%200 == 0)|| i == len1-1){
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
        logger.info("录入数据成功！");
        return len1;
    }
}
