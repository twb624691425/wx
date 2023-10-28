package com.tang.wx.service.impl;

import com.tang.wx.db.dao.TbUserMapper;
import com.tang.wx.db.pojo.TbUser;
import com.tang.wx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private TbUserMapper tbUserMapper;
    @Override
    public int saveUser(TbUser user) {
        return tbUserMapper.insert(user);
    }
}