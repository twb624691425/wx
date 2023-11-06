package com.tang.wx.controller;

import com.tang.wx.service.UserService;
import com.tang.wx.utils.Res;
import com.tang.wx.vo.LoginFormVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/user")
@Api(tags = { "用户模块" })
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/registry")
    @ApiOperation("注册用户")
    public Res registry(@RequestBody HashMap map) {
        return userService.registry(map);
    }

    @PostMapping("/login")
    @ApiOperation("登录")
    public Res login(@RequestBody LoginFormVO loginFormVO) {
        return userService.login(loginFormVO.getNickname(), loginFormVO.getOpenId());
    }

    @ApiOperation("获取用户信息")
    @GetMapping("/getUserInfo")
    public Res getUserInfo(@RequestParam(value = "id", required = true) int id) {
        return userService.getUserInfo(id);
    }

}
