package com.cjl.miaosha.controller;

import com.cjl.miaosha.result.CodeMsg;
import com.cjl.miaosha.result.Result;
import com.cjl.miaosha.service.UserService;
import com.cjl.miaosha.util.ValidatorUtil;
import com.cjl.miaosha.vo.LoginVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {
    @Autowired
    private UserService userService;

    @RequestMapping("/Page")
    public String LoginPage(){
        return "login";
    }


    @PostMapping("/do_login")
    @ResponseBody
    public Result<Boolean> do_login(HttpServletResponse response, @Valid LoginVo loginVo){
        log.info(loginVo.toString());
        //登录
        userService.login(response,loginVo);
        return Result.success(true);
    }
}
