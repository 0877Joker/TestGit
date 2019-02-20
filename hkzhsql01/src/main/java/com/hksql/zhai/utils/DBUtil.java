package com.hksql.zhai.utils;

import org.apache.log4j.Logger;

import java.sql.*;
import java.util.List;


public class DBUtil {
	
	private Connection conn = null;
    private PreparedStatement ps = null;
	private ResultSet rs = null;
	private String dbDriver = null;
	private String dbConnectionURL = null;
	private String dbUsername = null;
	private String dbPassword = null;
	private static Logger logger = Logger.getLogger(DBUtil.class);
	
	
	public DBUtil(){
//		dbDriver = "com.mysql.jdbc.Driver";
//		dbConnectionURL = "jdbc:mysql://192.168.0.231:3306/hksocial?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&rewriteBatchedStatements=true";
//		dbUsername = "root";
//		dbPassword = "houduan@123";	
		
		dbDriver = "com.mysql.jdbc.Driver";
		dbConnectionURL = "jdbc:mysql://rdsqjynaieqmu2y.mysql.rds.aliyuncs.com:3306/hksocial?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&rewriteBatchedStatements=true";
		dbUsername = "hkbackstage";
		dbPassword = "FZ05xjmZgR";
	}
	
	public DBUtil(String type){
		if("magi".equals(type)){
			dbDriver = "com.mysql.jdbc.Driver";
			dbConnectionURL = "jdbc:mysql://rm-bp1q95106u4255c2p.mysql.rds.aliyuncs.com:3306/hk_magi?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&rewriteBatchedStatements=true";
			dbUsername = "hk_magi";
			dbPassword = "1q9510H!axhs";	
		}else if("cms".equals(type)){
			dbDriver = "com.mysql.jdbc.Driver";
			dbConnectionURL = "jdbc:mysql://rdsqjynaieqmu2y.mysql.rds.aliyuncs.com:3306/hkbackstage?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&rewriteBatchedStatements=true";
//			dbConnectionURL = "jdbc:mysql://127.0.0.1:19999/hkbackstage?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&rewriteBatchedStatements=true";
			dbUsername = "hkbackstage";
			dbPassword = "FZ05xjmZgR";
//			dbUsername = "root";
//			dbPassword = "houduan@123";
		}else if("test-magi".equals(type)){
			dbDriver = "com.mysql.jdbc.Driver";
			dbConnectionURL = "jdbc:mysql://192.168.0.231:3306/hk_magi?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&rewriteBatchedStatements=true";
			dbUsername = "root";
			dbPassword = "houduan@123";
		}else if("test-hkbackstage".equals(type)){
			dbDriver = "com.mysql.jdbc.Driver";
			dbConnectionURL = "jdbc:mysql://192.168.0.231:3306/hkbackstage?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&rewriteBatchedStatements=true";
			dbUsername = "root";
			dbPassword = "houduan@123";
		}else if("magi01".equals(type)){
			dbDriver = "com.mysql.jdbc.Driver";
			dbConnectionURL = "jdbc:mysql://127.0.0.1:19997/hk_magi?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&rewriteBatchedStatements=true";
			dbUsername = "hk_magi";
			dbPassword = "1q9510H!axhs";
		}else if("cms01".equals(type)){
			dbDriver = "com.mysql.jdbc.Driver";
			dbConnectionURL = "jdbc:mysql://127.0.0.1:19999/hkbackstage?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&rewriteBatchedStatements=true";
			dbUsername = "hkbackstage";
			dbPassword = "FZ05xjmZgR";
		}
		
//		dbDriver = "com.mysql.jdbc.Driver";
//		dbConnectionURL = "jdbc:mysql://rdsqjynaieqmu2y.mysql.rds.aliyuncs.com/hksocial?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&rewriteBatchedStatements=true";
//		dbUsername = "hkbackstage";
//		dbPassword = "FZ05xjmZgR";
		
	}

    
	public DBUtil(String dbDriver, String dbConnectionURL, String dbUsername,String dbPassword){
		this.dbDriver = dbDriver;
		this.dbConnectionURL = dbConnectionURL;
		this.dbUsername = dbUsername;
		this.dbPassword = dbPassword;
	}
	/**
	 * 功能：获取数据库连接
	 */
	public Connection getConnection() {
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbConnectionURL, dbUsername,	dbPassword);
			//logger.info("数据库连接成功");
		} catch (Exception e) {
			logger.error("Error: DbUtil.getConnection() 获得数据库链接失败.\r\n链接类型:"
					+ dbDriver + "\r\n链接URL:" + dbConnectionURL + "\r\n链接用户:"
					+ dbUsername + "\r\n链接密码:" + dbPassword, e);
		}
		return conn;
	}
	
	/**
	 * 功能：执行查询语句
	 */
    public ResultSet select(String sql) {  
    	//logger.info("Exec select sql:" + sql);
            try {
            	conn = getConnection();
				ps = conn.prepareStatement(sql);				
				rs = ps.executeQuery(sql);
			} catch (SQLException e) {				
				logger.error("查询数据异常:"+ e.getMessage());
			}
            return rs;
        
    }
    
    /**
	 * 功能：执行查询语句，获取记录数
	 */
	public int getRecordCount(String sql) {
		logger.info("Exec getRecordCount sql:" + sql);
		int counter = 0;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);				
			rs = ps.executeQuery(sql);
			while (rs.next()) {										
				counter++;
			}
		} catch (SQLException e) {
			logger.error("执行DbUtil.getRecordCount()方法发生异常，异常信息：", e);
		}finally {
     	   close();
        }
		  System.out.println("counter总数："+counter);
		return counter;
	}
    
    /**
   	 * 功能:针对单条记录执行更新操作(新增、修改、删除)
   	 */
   public int executeupdate(String sql) throws Exception {
	   logger.info("Exec update sql:" + sql);
           int num = 0;
           try {
               conn = getConnection();
               ps = conn.prepareStatement(sql);
               num = ps.executeUpdate();
           } catch (SQLException sqle) {
        	   logger.error("insert/update/delete  data Exception: " +
                   sqle.getMessage());
           } finally {
        	   close();
           }
           System.out.println("影响条数："+num);
           return num;
       }
    
       /**
   	 * 
   	 * 功能:批量执行SQL(update或delete)
   	 * 
   	 * @param sqlList
   	 *            sql语句集合
   	 */
   	public int executeBatch(List<String> sqlList) {
   		int result = 0;
   		for (String sql : sqlList) {
   			try {
				result += executeupdate(sql);
			} catch (Exception e) {
				  System.out.println("查询异常："+e.getMessage());
			}
   		}
   		System.out.println("executeBatch Result:"+result);
   		return result;
   	}  
    
    /**
	 * 功能:关闭数据库的连接
	 */
	public void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
			//logger.info("关闭数据库连接成功");
		} catch (Exception e) {
			logger.error("执行DbUtil.close()方法发生异常，异常信息：", e);
		}
	}



}
