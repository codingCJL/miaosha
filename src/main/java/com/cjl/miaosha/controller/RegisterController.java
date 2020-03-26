package com.cjl.miaosha.controller;

import com.cjl.miaosha.entity.User;
import com.cjl.miaosha.result.CodeMsg;
import com.cjl.miaosha.result.Result;
import com.cjl.miaosha.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
@Slf4j
public class RegisterController {
    @Autowired
    private UserService userService;

    @RequestMapping("/Page")
    public String RegisterPage(){
        return "register";
    }


    @PostMapping("/do_register")
    @ResponseBody
    public Result<Boolean> do_login(@RequestParam("id") Long id,
                                    @RequestParam("nickname") String nickname,
                                    @RequestParam("password") String password){
        User user=new User();
        user.setId(id);
        user.setNickname(nickname);
        user.setPassword(password);
        //注册
        if(userService.register(user)){
            return Result.success(true);
        }
       return Result.error(CodeMsg.SERVER_ERROR);
    }
}
