package com.tang.wx.controller;


import com.tang.wx.db.pojo.TbUser;
import com.tang.wx.service.UserService;
import com.tang.wx.utils.Res;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("test")
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
    @PostMapping("/saveUser")
    public Object saveUser(@RequestBody TbUser user) {
        userService.saveUser(user);
        return Res.ok();
    }
}
