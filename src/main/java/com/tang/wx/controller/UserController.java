package com.tang.wx.controller;

import com.tang.wx.service.UserService;
import com.tang.wx.utils.Res;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
