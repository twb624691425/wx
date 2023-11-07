package com.tang.wx.service;

import com.tang.wx.db.pojo.TbUser;
import com.tang.wx.utils.Res;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public interface UserService {
    public Res registry(HashMap map);

    public Res getUserInfo(int id);

    public Res login(String nickname, String openId);

    public TbUser selectById(int id);

    public Set<String> getUserPermission(int id);

    public void testException();
}
