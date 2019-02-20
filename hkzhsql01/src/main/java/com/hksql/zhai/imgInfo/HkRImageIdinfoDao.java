package com.hksql.zhai.imgInfo;

import com.hksql.zhai.utils.DBUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HkRImageIdinfoDao {
    private static Logger logger = LoggerFactory.getLogger(HkRImageIdinfoDao.class);

    /**
     * 按创建日期查询图片信息
     */

    public List<HkRImageIdInfo> queryImgAll(){
        DBUtil db = new DBUtil("cms");
        String sql = "SELECT hci.image_id  r_image_id,hci.group_title_zh r_title,hci.group_content_zh r_content,hiti.type_id r_type_id,hkty.type_name_zh r_type_name,  hci.releasetime r_release_time,hci.createtime r_create_time, hci.createuser r_createuser,hci.updateuser r_updateuser FROM hk_company_image hci \n" +
                "LEFT JOIN hk_image_group_type_relation hiti \n" +
                "ON hiti.image_group_id = hci.image_id\n" +
                "JOIN hk_type_info hkty ON hiti.type_id = hkty.type_id\n" +
                "WHERE 1=1\n" +
                "AND hci.company_id = 10137\n" +
                "AND hci.`status` = '1'\n" +
                "AND hci.group_id = 0;";
        ResultSet rs = db.select(sql);
        if(rs == null){
            logger.error("图片数据为空！");
            return null;
        }
        List<HkRImageIdInfo> list = new ArrayList<>();
        try{
            while (rs.next()){
                HkRImageIdInfo tem = new HkRImageIdInfo();
                tem.setRImageId(rs.getString("r_image_id"));
                tem.setRTitle(rs.getString("r_title"));
                tem.setRContent(rs.getString("r_content"));
                tem.setRTypeId(rs.getInt("r_type_id"));
                tem.setRTypeName(rs.getString("r_type_name"));
                tem.setRReleaseTime(rs.getTimestamp("r_release_time"));
                tem.setRCreateTime(rs.getTimestamp("r_create_time"));
                tem.setRCreateUser(rs.getString("r_createuser"));
                tem.setRUpdateUser(rs.getString("r_updateuser"));
                list.add(tem);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            db.close();
        }
        logger.info("[imginfo]查询hk_company_image表下数据："+list.size());
        return list;
    }

    public List<HkRImageIdInfo>  queryImtgInfoById(String updateTime,Integer companyid){
        DBUtil db = new DBUtil("cms");
        String sql = "SELECT hci.image_id  r_image_id,hci.group_title_zh r_title,hci.group_content_zh r_content,hiti.type_id r_type_id,hkty.type_name_zh r_type_name,  hci.releasetime r_release_time,hci.createtime r_create_time, hci.createuser r_createuser,hci.updateuser r_updateuser FROM hk_company_image hci \n" +
                "LEFT JOIN hk_image_group_type_relation hiti \n" +
                "ON hiti.image_group_id = hci.image_id\n" +
                "JOIN hk_type_info hkty ON hiti.type_id = hkty.type_id\n" +
                "WHERE 1=1\n" +
                "AND hci.company_id = "+companyid+"\n" +
                "AND hci.`status` = '1'\n" +
                "AND hci.group_id = 0\n" +
                "AND hci.updatetime > '"+updateTime+"' ;";
        ResultSet rs = db.select(sql);
        List<HkRImageIdInfo> list = new ArrayList<>();
        try{
            while (rs.next()){
                HkRImageIdInfo tem = new HkRImageIdInfo();
                tem.setRImageId(rs.getString("r_image_id"));
                tem.setRTitle(rs.getString("r_title"));
                tem.setRContent(rs.getString("r_content"));
                tem.setRTypeId(rs.getInt("r_type_id"));
                tem.setRTypeName(rs.getString("r_type_name"));
                tem.setRReleaseTime(rs.getTimestamp("r_release_time"));
                tem.setRCreateTime(rs.getTimestamp("r_create_time"));
                tem.setRCreateUser(rs.getString("r_createuser"));
                tem.setRUpdateUser(rs.getString("r_updateuser"));
                list.add(tem);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            db.close();
        }
        logger.info("[imginfo]查询hk_company_image表下数据："+list.size());
        return list;
    }

    /**
     * 向hk_r_imginfo表中插入数据
     */
    public int insertImgList(List<HkRImageIdInfo> list,String ku){
        DBUtil db = new DBUtil(ku);
        Connection conn = null;
        PreparedStatement ps = null;
        int len1 = list.size();
        //MySql的JDBC连接的url中要加rewriteBatchedStatements参数，并保证5.1.13以上版本的驱动，才能实现高性能的批量插入。
        //优化插入性能，用JDBC的addBatch方法，但是注意在连接字符串加上面写的参数。
        //例如： String connectionUrl="jdbc:mysql://192.168.1.100:3306/test?rewriteBatchedStatements=true" ;
        String sql = "REPLACE INTO hk_r_image_info(r_image_id, r_title, r_content, r_type_id, r_type_name, r_release_time, r_create_time, r_createuser, r_updateuser) VALUES (?,?,?,?,?,?,?,?,?)";

        try{
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);

            //优化插入第一步       设置手动提交
            conn.setAutoCommit(false);
            HkRImageIdInfo tem = null;
            int len = list.size();
            for(int i = 0 ; i<len ; i++){
                tem = list.get(i);
                ps.setString(1,tem.getRImageId());
                ps.setString(2,tem.getRTitle());
                ps.setString(3,tem.getRContent());
                ps.setInt(4,tem.getRTypeId());
                ps.setString(5,tem.getRTypeName());
                ps.setTimestamp(6,tem.getRReleaseTime());
                ps.setTimestamp(7,tem.getRCreateTime());
                ps.setString(8,tem.getRCreateUser());
                ps.setString(9,tem.getRUpdateUser());
                ps.addBatch();
                //200条200条往里边插入
                if((i!=0 && i%200==0) || i==len-1){
                    ps.executeBatch();
                    conn.commit();
                    ps.clearBatch();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }finally{
            db.close();
//            list.clear();
        }
        logger.info("图片信息插入成功："+ len1);
        return len1;
    }

    public int imgcount(String ku){
        DBUtil db = new DBUtil(ku);
        String sql = "SELECT COUNT(1) FROM  hk_r_image_info";
        ResultSet rs = db.select(sql);
        int count = 0;
        try{
            while (rs.next()){
               count = rs.getInt("COUNT(1)");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            db.close();
        }
        logger.info("Hk_r_img数量为："+count);
        return count;
    }

    public List<String> selectAllImg(){
        DBUtil db = new DBUtil("magi");
        String sql = "SELECT r_image_id FROM hk_r_image_info;";
        ResultSet rs = db.select(sql);
        List<String> list = new ArrayList<>();
        try{
            while (rs.next()){
                String tem = rs.getString("r_image_id");
                list.add(tem);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            db.close();
        }
        logger.info("获取所有的图片数据！");
        return list;
    }

//    public int getMaxId(String ku){
//        DBUtil db = new DBUtil(ku);
//        String sql = "SELECT MAX(r_id) time1 FROM  hk_r_image_info";
//        ResultSet rs = db.select(sql);
//        int rid = 0;
//        try{
//            while (rs.next()){
//                rid = rs.getInt("time1");
//            }
//        }catch (SQLException e){
//            e.printStackTrace();
//        }finally {
//            db.close();
//        }
//        logger.info("最大id为："+rid);
//        return rid;
//    }
}
