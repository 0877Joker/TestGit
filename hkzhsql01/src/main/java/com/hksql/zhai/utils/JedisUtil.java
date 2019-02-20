package com.hksql.zhai.utils;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by mya on 2018/7/21.
 */
public class JedisUtil implements Serializable {

	private static final long serialVersionUID = -3922847182363649772L;
	private static JedisPool jedisPool = null;

    public static JedisPool getPool() {
        if (jedisPool==null) {
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            jedisPoolConfig.setMaxTotal(1024);
            jedisPoolConfig.setMaxIdle(200);
            jedisPoolConfig.setNumTestsPerEvictionRun(1024);
            jedisPoolConfig.setMaxWaitMillis(6000000);
            jedisPoolConfig.setTestOnBorrow(true);
            jedisPoolConfig.setTestOnReturn(true);
            jedisPoolConfig.setJmxEnabled(true);
            jedisPoolConfig.setBlockWhenExhausted(false);
            jedisPool = new JedisPool(jedisPoolConfig, "r-bp1bfa130794b214.redis.rds.aliyuncs.com",6379,600000,"r-bp1bfa130794b214:Sj5620jY");
            //jedisPool = new JedisPool(jedisPoolConfig, "192.168.0.236",6379,600000,"123",15);

        }
        return jedisPool;
    }
    public static void rpushPipe(String domain, String [] imageIds) {
        Jedis jedis = null;
        try {
            jedis = getPool().getResource();
            Pipeline pipe = jedis.pipelined();
            for (String i : imageIds) {
                pipe.rpush(domain, i+"");
            }
            pipe.sync();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.print("rpushPipe error.");
        } finally {
            if(null != jedis){jedis.close();}
        }
    }

    public static void rpushPipe(String domain, String [] imageIds, boolean isDelete, int expire) {
        Jedis jedis = null;
        try {
            jedis = getPool().getResource();
            Pipeline pipe = jedis.pipelined();
            if (isDelete) {
                pipe.del(domain);
            }
            for (String i : imageIds) {
                pipe.rpush(domain, i+"");
            }
            pipe.expire(domain, expire);
            pipe.sync();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.print("rpushPipe error.");
        } finally {
            if(null != jedis){jedis.close();}
        }
    }

    public static void setValue(String domain, String value, int expire) {
        Jedis jedis = null;
        try {
            jedis = getPool().getResource();
            jedis.set(domain,value);
            jedis.expire(domain, expire);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.print("setValue error.");
        } finally {
            if(null != jedis){jedis.close();}
        }
    }
    
    public static void delKey(String domain) {
        Jedis jedis = null;
        try {
            jedis = getPool().getResource();
            jedis.del(domain);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.print("delKey error.");
        } finally {
            if(null != jedis){jedis.close();}
        }
    }
    
    public static void setSortSet(String domain, Map<String,Double> value, int expire) {
        Jedis jedis = null;
        try {
            jedis = getPool().getResource();
            jedis.zadd(domain, value);
            jedis.expire(domain, expire);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.print("setSortSet error.");
        } finally {
            if(null != jedis){jedis.close();}
        }
    }
    
    public static void setSortSet(String domain, Map<String,Double> value) {
        Jedis jedis = null;
        try {
            jedis = getPool().getResource();
            jedis.zadd(domain, value);

        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.print("setSortSet error.");
        } finally {
            if(null != jedis){jedis.close();}
        }
    }
    
    
	public static void main(String[] args) {

List<String> aa=new ArrayList<String>();
List<String> bb=new ArrayList<String>();
aa.add("11");
aa.add("22");
aa.add("33");
aa.add("44");

bb.add("44");
bb.add("00");
bb.add("11");
bb.addAll(aa);

System.out.println(bb);
Tools.removeDuplicate(bb);
 
		System.out.println(bb);
	}

}
