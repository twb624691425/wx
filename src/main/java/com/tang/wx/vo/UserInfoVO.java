package com.tang.wx.vo;

import com.tang.wx.db.pojo.TbUser;
import lombok.Data;

import java.util.Set;

@Data
public class UserInfoVO {
    private TbUser user;
    private Set<String> permissions;
}
