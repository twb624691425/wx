package com.tang.wx.service.impl;

import com.tang.wx.db.dao.TbUserMapper;
import com.tang.wx.db.pojo.TbUser;
import com.tang.wx.service.UserService;
import com.tang.wx.utils.CustomException;
import com.tang.wx.utils.Res;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private TbUserMapper tbUserMapper;


    @Override
    public Res registry(HashMap map) {
        if ((map.get("registryCode").equals("000000")) && this.hasRootUser()) {
            return Res.error("已注册管理员");
        } else {
            Integer id = tbUserMapper.increaseId();
            HashMap sqlMap = new HashMap();
            sqlMap.putAll(map);
            sqlMap.put("id", id == null ? 1 : id + 1);
            sqlMap.put("createTime", new Date());
            sqlMap.put("nickname", map.get("nickname") != null && !map.get("nickname").equals("") ? map.get("nickname") : "未命名");
            sqlMap.put("root", map.get("registryCode").equals("000000") ? 1 : 0);
            sqlMap.put("role", map.get("registryCode").equals("000000") ? "[0]" : "[3]");
            this.insertUser(sqlMap);
            return Res.ok().put("msg", "注册成功");
        }
    }

    @Override
    public Res getUserInfo(int id) {
        Set<String> permissions = tbUserMapper.getUserPermission(id);
        return Res.ok().put("data", permissions);
    }

    public void testException() {
       throw new CustomException("手动mock的异常");
    }

    private boolean hasRootUser() {
        boolean bool = tbUserMapper.hasRootUser();
        System.out.println("bool = " + bool);
        return bool;
    }

    public int insertUser(HashMap map) {
        return tbUserMapper.insertUser(map);
    }


}
