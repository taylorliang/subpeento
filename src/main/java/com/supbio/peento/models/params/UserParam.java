package com.supbio.peento.models.params;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class UserParam {

    @ApiModelProperty(value = "用户名",dataType = "String")
    private String userName;
    @ApiModelProperty(value = "年龄",dataType = "String")
    private String age;
    @ApiModelProperty(value = "性别：0女，1男",dataType = "Integer")
    private Integer sex;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

}
