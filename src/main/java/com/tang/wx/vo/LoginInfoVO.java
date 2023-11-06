package com.tang.wx.vo;

import com.tang.wx.db.pojo.TbUser;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginInfoVO {
    @ApiModelProperty("登录令牌")
    private String token;
    @ApiModelProperty("用户信息")
    private TbUser userInfo;
}
