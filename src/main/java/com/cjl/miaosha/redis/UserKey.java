package com.cjl.miaosha.redis;

/**
 * @author cjl
 * @date 2020/3/24 15:06
 */
public class UserKey extends BasePrefix {

    private UserKey(String prefix) {
        super(prefix);
    }

    public static UserKey getById=new UserKey("id");
    public static UserKey getByName=new UserKey("name");

}
