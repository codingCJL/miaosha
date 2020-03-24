package com.cjl.miaosha.redis;

/**
 * @author cjl
 * @date 2020/3/24 14:53
 * 通用key封装
 */
public interface KeyPrefix {
    public int expireSeconds();

    public String getPrefix();
}
