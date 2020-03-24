package com.cjl.miaosha.redis;

/**
 * @author cjl
 * @date 2020/3/24 15:08
 */
public class OrderKey extends BasePrefix {
    public OrderKey(Integer expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
}
