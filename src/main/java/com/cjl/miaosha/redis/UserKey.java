package com.cjl.miaosha.redis;

/**
 * @author cjl
 * @date 2020/3/24 15:06
 */
public class UserKey extends BasePrefix {

    public static final int TOKEN_EXPIRE_TIME=3600*24*2;
    private UserKey(Integer expireSeconds,String prefix) {
        super(expireSeconds,prefix);
    }

    public static UserKey token=new UserKey(TOKEN_EXPIRE_TIME,"token");

}
