package com.tang.wx.db.dao;

import com.tang.wx.db.pojo.TbUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.Set;

/**
* @author Administrator
* @description 针对表【tb_user(用户表)】的数据库操作Mapper
* @createDate 2023-10-28 16:17:22
* @Entity com.tang.wx.db.pojo.TbUser
*/
@Mapper
public interface TbUserMapper {
    public boolean hasRootUser();

    public int insertUser(HashMap hashMap);

    public Integer increaseId();

    public Set<String> getUserPermission(int id);

    public TbUser selectByLogin(String nickname, String openId);

    public TbUser selectById(int id);
}
