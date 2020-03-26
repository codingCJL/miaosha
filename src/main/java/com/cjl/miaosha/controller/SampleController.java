package com.cjl.miaosha.controller;

import com.cjl.miaosha.redis.RedisService;
import com.cjl.miaosha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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




}
