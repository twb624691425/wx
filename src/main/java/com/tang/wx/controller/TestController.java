package com.tang.wx.controller;


import com.tang.wx.utils.Res;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {
    @GetMapping("/hello")
    public String greetHello() {
        return "Hello World";
    }

    @GetMapping("/res")
    public Object res() {
        return Res.ok();
    }
}
