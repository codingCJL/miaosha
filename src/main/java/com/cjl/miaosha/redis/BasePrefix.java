package com.cjl.miaosha.redis;

/**
 * @author cjl
 * @date 2020/3/24 14:59
 */
public abstract class BasePrefix implements KeyPrefix {
    private Integer expireSeconds;
    private String prefix;

    public BasePrefix(String prefix){
        this(0,prefix);
    }
    public BasePrefix(Integer expireSeconds, String prefix) {
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }

    @Override
    public int expireSeconds() {//默认0永不过期
        return expireSeconds;
    }

    @Override
    public String getPrefix() {
        String className = getClass().getSimpleName();
        return className+":"+prefix+":";
    }
}
