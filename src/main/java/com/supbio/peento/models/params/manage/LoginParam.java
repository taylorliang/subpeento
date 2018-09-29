package com.supbio.peento.models.params.manage;

import io.swagger.annotations.ApiModelProperty;

public class LoginParam {

    @ApiModelProperty(value = "用户名(手机号)", required = true)
    private String userName;

    @ApiModelProperty(value = "密码", required = true)
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
