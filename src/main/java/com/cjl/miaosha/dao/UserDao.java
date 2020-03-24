package com.cjl.miaosha.dao;

import com.cjl.miaosha.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author cjl
 * @date 2020/3/24 10:26
 */
@Mapper
public interface UserDao {
    @Select("select * from user where id=#{id}")
    public User getById(@Param("id") Integer id);
}
