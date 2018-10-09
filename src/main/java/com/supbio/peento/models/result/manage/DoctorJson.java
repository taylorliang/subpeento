package com.supbio.peento.models.result.manage;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by liangqiang on 2018/10/9.
 */
public class DoctorJson {

    @ApiModelProperty(value = "医生id")
    private String doctorId;

    @ApiModelProperty(value = "医生头像图片url")
    private String headUrl;

    @ApiModelProperty(value = "真实姓名")
    private String userName;

    @ApiModelProperty(value = "医生接收状态：0开，1关")
    private Integer acceptStatus;

    @ApiModelProperty(value = "是否推送首页：0是，1否")
    private Integer pushStatus;

    @ApiModelProperty(value = "医生标签")
    private String doctorTitle;

    @ApiModelProperty(value = "身份证号码")
    private String IDNumber;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "性别：0女，1男")
    private Integer sex;

    @ApiModelProperty(value = "认证状态：0待提交审核，1待审核，2已认证，3审核失败")
    private Integer status;

    @ApiModelProperty(value = "医院")
    private String hospital;

    @ApiModelProperty(value = "职称")
    private String title;

    @ApiModelProperty(value = "科室")
    private String office;

    @ApiModelProperty(value = "审核资料人员")
    private String checker;

    @ApiModelProperty(value = "提交认证时间")
    private String submitTime;

    @ApiModelProperty(value = "上传认证资料按钮是否显示：0显示，1不显示")
    private Integer isShow;

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAcceptStatus() {
        return acceptStatus;
    }

    public void setAcceptStatus(Integer acceptStatus) {
        this.acceptStatus = acceptStatus;
    }

    public Integer getPushStatus() {
        return pushStatus;
    }

    public void setPushStatus(Integer pushStatus) {
        this.pushStatus = pushStatus;
    }

    public String getDoctorTitle() {
        return doctorTitle;
    }

    public void setDoctorTitle(String doctorTitle) {
        this.doctorTitle = doctorTitle;
    }

    public String getIDNumber() {
        return IDNumber;
    }

    public void setIDNumber(String IDNumber) {
        this.IDNumber = IDNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        this.checker = checker;
    }

    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }
}
