package com.cjl.miaosha.controller;

import com.cjl.miaosha.entity.User;
import com.cjl.miaosha.redis.UserKey;
import com.cjl.miaosha.result.*;
import com.cjl.miaosha.redis.RedisService;
import com.cjl.miaosha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author cjl
 * @date 2020/3/24 10:02
 */
@Controller
public class SampleController {
    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @RequestMapping("/test/{id}")
    public String thymeleaf(@PathVariable("id") Integer id, Model model){
        User user=userService.getById(id);
        model.addAttribute("user",user);
        return "test";
    }

    @RequestMapping("/redis/get")
    @ResponseBody
    public Result<User> redisGet(){
        User user = redisService.get(UserKey.getById,"k1", User.class);
        return Result.success(user);
    }

    @RequestMapping("/redis/set")
    @ResponseBody
    public Result<Boolean> redisSet(){
        User user=new User();
        user.setId(1);
        user.setName("cjl");
        boolean flag = redisService.set(UserKey.getById,"k1",user);
        return Result.success(flag);
    }
}
