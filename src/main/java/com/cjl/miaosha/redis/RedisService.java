package com.cjl.miaosha.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author cjl
 * @date 2020/3/24 10:47
 */
@Service
public class RedisService {
    @Autowired
    JedisPool jedisPool;


    public <T> T get(KeyPrefix prefix,String key,Class<T> clazz){
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            //生成实际key
            String realKey=prefix.getPrefix()+key;
            String str = jedis.get(realKey);
            return stringToBean(str,clazz);
        }finally {
            returnToPool(jedis);
        }
    }
    public <T> boolean set(KeyPrefix prefix,String key,T value){
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            String str=beanToString(value);
            if(str==null||str.length()<=0){
                return false;
            }
            //生成实际key
            String realKey=prefix.getPrefix()+key;
            //生成过期时间
            Integer seconds=prefix.expireSeconds();
            if(seconds<=0){
                jedis.set(realKey,str);
            }else {
                jedis.setex(realKey,seconds,str);
            }

            return true;
        }finally {
            returnToPool(jedis);
        }
    }

    public <T> boolean exists(KeyPrefix prefix,String key){
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            //生成实际key
            String realKey=prefix.getPrefix()+key;
            return jedis.exists(realKey);
        }finally {
            returnToPool(jedis);
        }
    }
    public <T> Long incr(KeyPrefix prefix,String key){
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            //生成实际key
            String realKey=prefix.getPrefix()+key;
            return jedis.incr(realKey);
        }finally {
            returnToPool(jedis);
        }
    }

    public <T> Long decr(KeyPrefix prefix,String key){
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            //生成实际key
            String realKey=prefix.getPrefix()+key;
            return jedis.decr(realKey);
        }finally {
            returnToPool(jedis);
        }
    }

    private <T> String beanToString(T value) {
        if(value==null){
            return null;
        }
        Class<?> clazz=value.getClass();
        if(clazz==Integer.class){
            return ""+value;
        }else if(clazz==String.class){
            return (String)value;
        }else if(clazz==Long.class){
            return ""+value;
        }else {
            return JSON.toJSONString(value);
        }
    }

    private <T> T stringToBean(String str,Class<T> clazz) {
        if(str==null||str.length()<=0||clazz==null){
            return null;
        }
        if(clazz==Integer.class){
            return (T) Integer.valueOf(str);
        }else if(clazz==String.class){
            return (T)str;
        }else if(clazz==Long.class){
            return (T)Long.valueOf(str);
        }else {
            return JSON.toJavaObject(JSON.parseObject(str),clazz);
        }
    }

    private void returnToPool(Jedis jedis){
        if(jedis!=null){
            jedis.close();
        }
    }


}
