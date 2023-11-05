package com.tang.wx.controller;


import com.tang.wx.db.pojo.TbUser;
import com.tang.wx.service.UserService;
import com.tang.wx.utils.Res;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("test")
@Api(value = "测试web接口", tags = { "测试模块" })
public class TestController {
    @Autowired
    private UserService userService;
    @GetMapping("/hello")
    public String greetHello() {
        return "Hello World";
    }

    @GetMapping("/res")
    public Object res() {
        return Res.ok();
    }

    @GetMapping("/swagger")
    @ApiOperation("最简单的测试方法")
    public Object swaggerUI() {
        return Res.ok();
    }

    @GetMapping("/exception")
    public Object mockException() {
        userService.testException();
        return Res.ok();
    }
}
