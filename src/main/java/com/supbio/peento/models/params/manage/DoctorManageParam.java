package com.supbio.peento.models.params.manage;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by liangqiang on 2018/10/9.
 */
public class DoctorManageParam {

    public static class DoctorCheck extends PageParam{

        @ApiModelProperty(value = "真实姓名", required = true)
        private String userName;

        @ApiModelProperty(value = "科室", required = true)
        private String office;

        @ApiModelProperty(value = "职称", required = true)
        private String title;

        @ApiModelProperty(value = "提交认证开始时间(yyyy-MM-dd HH:mm:ss)", required = true)
        private String submitStartTime;

        @ApiModelProperty(value = "提交认证结束时间(yyyy-MM-dd HH:mm:ss)", required = true)
        private String submitEndTime;

        @ApiModelProperty(value = "认证次数", required = true)
        private String number;

        @ApiModelProperty(value = "认证状态：0待提交审核，1待审核，2已认证，3审核失败，4所有医生", required = true)
        private Integer status;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getOffice() {
            return office;
        }

        public void setOffice(String office) {
            this.office = office;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSubmitStartTime() {
            return submitStartTime;
        }

        public void setSubmitStartTime(String submitStartTime) {
            this.submitStartTime = submitStartTime;
        }

        public String getSubmitEndTime() {
            return submitEndTime;
        }

        public void setSubmitEndTime(String submitEndTime) {
            this.submitEndTime = submitEndTime;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }
    }

    public static class ChangeDoctorStatus{

        @ApiModelProperty(value = "医生id")
        private String doctorId;

        @ApiModelProperty(value = "医生接收状态：0开，1关")
        private String acceptStatus;

        @ApiModelProperty(value = "是否推送首页：0是，1否")
        private String pushStatus;

        @ApiModelProperty(value = "医生标签")
        private String doctorTitle;

        public String getDoctorId() {
            return doctorId;
        }

        public void setDoctorId(String doctorId) {
            this.doctorId = doctorId;
        }

        public String getAcceptStatus() {
            return acceptStatus;
        }

        public void setAcceptStatus(String acceptStatus) {
            this.acceptStatus = acceptStatus;
        }

        public String getPushStatus() {
            return pushStatus;
        }

        public void setPushStatus(String pushStatus) {
            this.pushStatus = pushStatus;
        }

        public String getDoctorTitle() {
            return doctorTitle;
        }

        public void setDoctorTitle(String doctorTitle) {
            this.doctorTitle = doctorTitle;
        }
    }

}
