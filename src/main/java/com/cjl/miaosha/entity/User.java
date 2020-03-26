package com.cjl.miaosha.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author cjl
 * @date 2020/3/26 10:06
 */
@Data
public class User {
    private Long id;
    private String nickname;
    private String password;
    private String salt;
    private String head;
    private Date registerDate;
    private Date lastLoginDate;
    private Integer loginCount;
}
