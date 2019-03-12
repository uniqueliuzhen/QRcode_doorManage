package com.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.User;
import com.freamwork.Result;
import com.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 列表
     */
    @RequestMapping("/queryPage")
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params,@RequestParam("pageSize") Integer pageSize,@RequestParam("pageNum")Integer pageNum){
        Result result = userService.queryPage(params,pageSize,pageNum);
        return result;
    }

    /**
     * 登录
     */
    @RequestMapping("/login")
    @ResponseBody
    public Result login(@RequestParam("account") String account,@RequestParam("password")String password){
		Result result = userService.login(account, password);
        return result;
    }

    @RequestMapping("/regist")
    @ResponseBody
    public Result regist(@RequestBody User user){
    	Result result = userService.regist(user);
    	return result;
    }
}
