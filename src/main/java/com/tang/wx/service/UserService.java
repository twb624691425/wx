package com.tang.wx.service;

import com.tang.wx.db.pojo.TbUser;
import com.tang.wx.utils.Res;

import java.util.HashMap;
import java.util.Map;

public interface UserService {
    public Res registry(HashMap map);

    public Res getUserInfo(int id);

    public void testException();
}
