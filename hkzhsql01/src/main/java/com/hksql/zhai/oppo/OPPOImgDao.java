package com.hksql.zhai.oppo;

import com.hksql.zhai.imgInfo.HkRImageIdinfoDao;
import com.hksql.zhai.publicDao.hkRResult.HkRResultInfo;
import com.hksql.zhai.utils.DBUtil;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OPPOImgDao {

    private static Logger logger = Logger.getLogger(HkRImageIdinfoDao.class);
    /**
     * 查询根据companyid查询分类
     */

    public List<HkRResultInfo> queryActionByDate( Integer companyid1, String mydate){
        DBUtil db = new DBUtil("magi");
        String sql = "SELECT r.imgid2  imgid,DATE_FORMAT(t.log_date,'%Y%m%d') mydate, t.play_pv exp_pv,t.play_uv exp_uv,t.click_pv click_pv1,t.click_uv click_uv1,IFNULL(r.img_pv,0) click_pv2,IFNULL(r.img_uv,0) click_uv2, NOW() date1,'zhaiyao' u1,NOW() date2,'zhaiyao' u2  \n" +
                "FROM hk_log_result t \n" +
                "LEFT JOIN redirect_imgcount_data_img r\n" +
                "ON t.image_id = r.imgid2 AND r.mydate = DATE_FORMAT(t.log_date,'%Y%m%d')\n" +
                "WHERE r.companyid = "+companyid1+" AND t.log_date = '"+mydate+"'";
        ResultSet rs = db.select(sql);
        List<HkRResultInfo> list = new ArrayList<>();
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
        logger.info("数据查询完成");
        return list;
    }
}
