package com.tang.wx.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LoginFormVO {
    @ApiModelProperty("用户名")
    private String nickname;
    @ApiModelProperty("密码")
    private String openId;
}
