package com.tang.wx.controller;


import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.Bucket;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import com.tang.wx.db.pojo.TbUser;
import com.tang.wx.service.UserService;
import com.tang.wx.utils.Res;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.qcloud.cos.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

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

    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public Res upload(@RequestParam String id, @RequestParam MultipartFile file) throws IOException {
        String secretId = "AKIDX0HOMVVPApJemsxuiMqANTNAoKtV5gK4";
        String secretKey = "pYFwW2SsLmKcs2BNVZZUOG970vnZElIk";
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        Region region = new Region("ap-guangzhou");
        ClientConfig clientConfig = new ClientConfig(region);
        clientConfig.setHttpProtocol(HttpProtocol.https);
        COSClient cosClient = new COSClient(cred, clientConfig);
//        List<Bucket> buckets = cosClient.listBuckets();
//        System.out.println("size = " + buckets.size());
//        for (Bucket bucket: buckets) {
//            System.out.println("存储桶：" + bucket.getName());
//        }
        String bucketName = "free-1322237639";
        String originFileName = file.getOriginalFilename();
        assert originFileName != null;
        String suffix = originFileName.substring(originFileName.lastIndexOf("."));
        File newFile = File.createTempFile(originFileName, suffix);
        file.transferTo(newFile);
        String key = "photo/" + new Date().getTime() + suffix;
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, newFile);
        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
        System.out.println(putObjectResult.getMetadata());
        cosClient.shutdown();
        return Res.ok();
    }
}

















