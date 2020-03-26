package com.cjl.miaosha.service;

import com.cjl.miaosha.dao.UserDao;
import com.cjl.miaosha.entity.User;
import com.cjl.miaosha.exception.GlobalException;
import com.cjl.miaosha.redis.RedisService;
import com.cjl.miaosha.redis.UserKey;
import com.cjl.miaosha.result.CodeMsg;
import com.cjl.miaosha.util.MD5Util;
import com.cjl.miaosha.util.UUIDUtil;
import com.cjl.miaosha.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.UUID;

/**
 * @author cjl
 * @date 2020/3/24 10:28
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RedisService redisService;

    public User getById(Long id){
       return userDao.getById(id);
    }

    public boolean login(HttpServletResponse response,LoginVo loginVo) {
        if(loginVo==null){
            throw new GlobalException(CodeMsg.SERVER_ERROR) ;
        }
        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();
        //判断手机号是否存在,此处将手机号作为id
        User user = getById(Long.parseLong(mobile));
        if(user==null){
            throw new GlobalException(CodeMsg.USER_NULL);
        }
        //验证密码
        String dbPass=user.getPassword();
        String saltDB = user.getSalt();
        String caclPass = MD5Util.formPassToDBPass(formPass, saltDB);
        if(!caclPass.equals(dbPass)){
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        //生成token和cookie
        String token=UUIDUtil.uuid();
        //String token="eae60fdb6ff040e3910448b7d74642e6";
        addCookie(response,token,user);
        return true;
    }

    public boolean register(User user) {
        if(user==null){
            throw new GlobalException(CodeMsg.USER_NULL) ;
        }
        User newUser = userDao.getById(user.getId());
        if(newUser!=null){
            throw new GlobalException(CodeMsg.USER_EXITS) ;
        }
        newUser=new User();
        newUser.setId(user.getId());
        newUser.setNickname(user.getNickname());
        String salt= UUID.randomUUID().toString().substring(1,5);
        //将用户输入的密码和固定的salt加密的结果和随机salt加密
        String password=MD5Util.formPassToDBPass(user.getPassword(),salt);
        newUser.setPassword(password);
        newUser.setSalt(salt);
        newUser.setHead("https://unsplash.it/100/100?image=27");
        newUser.setRegisterDate(new Date());
        newUser.setLoginCount(0);
        if(userDao.addUser(newUser)>0){
            return true;
        }else {
            return false;
        }
    }

    public User getByToken(HttpServletResponse response,String token) {
        if(StringUtils.isEmpty(token)){
            return null;
        }
        User user = redisService.get(UserKey.token, token, User.class);
        //延长有效期
        if(user!=null){
            addCookie(response,token,user);
        }
        return user;
    }

    private void addCookie(HttpServletResponse response,String token,User user){
        //将token和user作为键值对存入redis
        if(!redisService.exists(UserKey.token,token)){
            redisService.set(UserKey.token,token,user);
        }
        //生成cookie
        Cookie cookie=new Cookie("token",token);
        cookie.setMaxAge(UserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
