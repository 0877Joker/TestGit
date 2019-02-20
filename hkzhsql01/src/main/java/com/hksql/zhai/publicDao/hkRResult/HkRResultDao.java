package com.hksql.zhai.publicDao.hkRResult;

import com.hksql.zhai.imgInfo.HkRImageIdinfoDao;
import com.hksql.zhai.utils.DBUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HkRResultDao {


    private static Logger logger = LoggerFactory.getLogger(HkRImageIdinfoDao.class);

    /**
     * 查询根据companyid查询分类
     */

    public List<HkRResultInfo> queryActionByDate(String companyid1, Integer companyid2, String mydate){
        DBUtil db = new DBUtil("magi");
        logger.info("数据开始查询，请耐心等待，请耐心等待");
        String sql = "SELECT  t.imgid, t.mydate,exp_pv,exp_uv,\n" +
                "t.click_pv click_pv1,t.click_uv click_uv1,IFNULL(r.img_pv,0) click_pv2,IFNULL(r.img_uv,0) click_uv2,NOW() date1,'zhaiyao' u1,NOW() date2,'zhaiyao' u2\n" +
                "FROM \n" +
                "hk_r_image_info h\n" +
                "JOIN\n" +
                "(SELECT imgid,mydate,  \n" +
                "SUM(IFNULL(exp_pv1,0) + IFNULL(exp_pv2,0)) exp_pv, \n" +
                "SUM(IFNULL(exp_uv1,0) + IFNULL(exp_uv2,0)) exp_uv, \n" +
                "SUM(IFNULL(click_pv,0)) click_pv, \n" +
                "SUM(IFNULL(click_uv,0)) click_uv   \n" +
                "FROM company_tickling_imgcount_data \n" +
                "WHERE mydate = "+mydate+" AND companyid = "+companyid2+"\n" +
                "GROUP BY mydate , imgid) t\n" +
                "ON  h.r_image_id = t.imgid\n" +
                "LEFT JOIN\n" +
                "(SELECT imgid2, mydate, SUM(img_pv) img_pv , SUM(img_uv) img_uv\n" +
                "\tFROM redirect_imgcount_data_img \n" +
                "\tWHERE mydate = "+mydate+" AND companyid in ("+companyid1+")\n" +
                "\tGROUP BY mydate,imgid2\n" +
                ")  r \n" +
                "ON r.imgid2 = t.imgid AND r.mydate = t.mydate;\n";

        ResultSet rs = db.select(sql);
        if(rs == null){
            logger.error(companyid2+"今日数据为空!");
            return null;
        }
        List<HkRResultInfo> list = new ArrayList<>();
        logger.info("数据查询完成，正在录入，请耐心等待");
        try{
            while (rs.next()){
                HkRResultInfo tem = new HkRResultInfo();
                tem.settImageId(rs.getString("imgid"));
                tem.settLogDate(rs.getInt("mydate"));
                tem.settCPv(rs.getBigDecimal("exp_pv"));
                tem.settCUv(rs.getBigDecimal("exp_uv"));
                tem.settCPvClick(rs.getBigDecimal("click_pv1"));
                tem.settCuvClick(rs.getBigDecimal("click_uv1"));
                tem.settSPv(rs.getBigDecimal("click_pv2"));
                tem.settSUv(rs.getBigDecimal("click_uv2"));
                tem.settCreateTime(rs.getTimestamp("date1"));
                tem.settCreateUser(rs.getString("u1"));
                tem.settUpdateTime(rs.getTimestamp("date2"));
                tem.settUpdateUser(rs.getString("u2"));

                list.add(tem);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            db.close();
        }
        logger.info("数据查询完成,总共："+list.size());
        return list;
    }


    public int insertRResult(List<HkRResultInfo> list,int companyid){
        DBUtil db = new DBUtil("cms");

        Connection conn = null;
        PreparedStatement ps = null;

        //MySql的JDBC连接的url中要加rewriteBatchedStatements参数，并保证5.1.13以上版本的驱动，才能实现高性能的批量插入。
        //优化插入性能，用JDBC的addBatch方法，但是注意在连接字符串加上面写的参数。
        //例如： String connectionUrl="jdbc:mysql://192.168.1.100:3306/test?rewriteBatchedStatements=true" ;
        String sql = "REPLACE INTO hk_r_result_info_"+companyid+"(t_image_id, t_log_date, t_c_pv, t_c_uv, t_c_pv_click, t_c_uv_click, t_s_pv, t_s_uv, t_createtime, t_createuser, t_updatetime, t_updateuser) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        int len1 = list.size();
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);

            //优化插入第一步 手动提交
            conn.setAutoCommit(false);

            HkRResultInfo tem = null;
            int len = list.size();
            for(int i = 0; i< len ; i++){
                tem = list.get(i);
                ps.setString(1,tem.gettImageId());
                ps.setInt(2,tem.gettLogDate());
                ps.setBigDecimal(3,tem.gettCPv());
                ps.setBigDecimal(4,tem.gettCUv());
                ps.setBigDecimal(5,tem.gettCPvClick());
                ps.setBigDecimal(6,tem.gettCuvClick());
                ps.setBigDecimal(7,tem.gettSPv());
                ps.setBigDecimal(8,tem.gettSUv());
                ps.setTimestamp(9,tem.gettCreateTime());
                ps.setString(10,tem.gettCreateUser());
                ps.setTimestamp(11,tem.gettUpdateTime());
                ps.setString(12,tem.gettUpdateUser());

                ps.addBatch();

                if((i!=0 && i%200==0) || i==len-1){//可以设置不同的大小；如50，100，200，500，1000等等
                    ps.executeBatch();
                    //优化插入第三步       提交，批量插入数据库中。
                    conn.commit();
                    ps.clearBatch();        //提交后，Batch清空。
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }finally {
            db.close();
//            list.clear();
        }
        logger.info(companyid+"数据录入成功！");
        return len1;
    }

}
