package com.supbio.peento.models.entityenum;

/**
 * Created by liangqiang on 2018/10/9.
 */
public enum DoctorStatusEnum {

    /**
     * 0-待提交审核
     */
    WAITSUBMIT(0,"待提交审核"),
    /**
     * 1-待审核
     */
    WAITCHECK(1,"待审核"),
    /**
     * 2-已认证
     */
    CERTIFIED(2,"已认证"),
    /**
     * 3-审核失败
     */
    CHECKFAIL(3,"审核失败");

    private int code;
    private String msg;

    private DoctorStatusEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
