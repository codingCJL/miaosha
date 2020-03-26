package com.cjl.miaosha.dao;

import com.cjl.miaosha.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author cjl
 * @date 2020/3/24 10:26
 */
@Mapper
public interface UserDao {
    @Select("select * from miaosha_user where id=#{id}")
    User getById(@Param("id") Long id);

    @Insert("insert into miaosha_user(id,nickname,password,salt,head,register_date,last_login_date,login_count) "+
            "values(#{id},#{nickname},#{password},#{salt},#{head},#{registerDate},#{lastLoginDate},#{loginCount})")
    int addUser(User newUser);
}
