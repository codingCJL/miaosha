package com.cjl.miaosha.controller;

import com.cjl.miaosha.entity.User;
import com.cjl.miaosha.redis.RedisService;
import com.cjl.miaosha.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/goods")
@Slf4j
public class GoodsController {
    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @RequestMapping("/to_list")
    public String Page(Model model, User user){
        model.addAttribute("user",user);
        return "goods_list";
    }

}
