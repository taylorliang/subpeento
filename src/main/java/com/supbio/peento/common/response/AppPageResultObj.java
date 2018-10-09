package com.supbio.peento.common.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AppPageResultObj<T> extends AppResultObj<T> {

    /**
     * 分页信息
     */
    @JsonProperty("page_info")
    private PageInfo pageInfo;


    public AppPageResultObj() {

    }

    public AppPageResultObj(T data) {
        setData(data);
    }

    public AppPageResultObj(T data, PageInfo pageInfo) {
        setData(data);
        setPageInfo(pageInfo);
    }


    public AppPageResultObj(T data, Integer currentPage, Integer totalPage, Long totalCount) {
        setData(data);
        this.pageInfo = new PageInfo();
        this.pageInfo.setTotal(totalCount);
        this.pageInfo.setHasNext((currentPage + 1) < totalPage);
        this.pageInfo.setTotalPage(totalPage);
    }

    public AppPageResultObj(T data, PageParameter pp) {
        setData(data);
        this.pageInfo = new PageInfo();
        pageInfo.setTotalPage((int) pp.getTotalPage());
        pageInfo.setTotal(pp.getTotalCount());
        pageInfo.setHasNext((pp.getCurrentPage() + 1) < pp.getTotalPage());
        setPageInfo(pageInfo);
    }

    public AppPageResultObj(int code, String msg) {
        setCode(code);
        setMsg(msg);
    }

    public AppPageResultObj(int code, String msg, T data, PageInfo pageInfo) {
        setCode(code);
        setMsg(msg);
        setData(data);
        setPageInfo(pageInfo);
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    /**
     * 接口调用成功，传入需要返回的data数据
     *
     * @return
     */
    public static <T> AppPageResultObj<T> success(T data, PageInfo pageInfo) {
        return new AppPageResultObj<T>(data, pageInfo);
    }

    public static <T> AppPageResultObj<T> success(T data, Integer currentPage, Integer totalPage, Long totalCount) {
        return new AppPageResultObj<T>(data, currentPage, totalPage, totalCount);
    }
    public static <T> AppPageResultObj<T> success(T data, PageParameter pp) {
        return new AppPageResultObj<T>(data, pp);
    }

    /**
     * 接口调用成功，传入需要返回的data数据
     *
     * @param data
     * @return
     */
    public static <T> AppPageResultObj<T> success(T data) {
        return new AppPageResultObj<T>(data);
    }


    /**
     * 接口调用失败：不允许未登录用户调用此接口
     *
     * @return
     */
    public static <T> AppPageResultObj<T> notAllow() {
        return new AppPageResultObj<T>(AppResultObj.CODE_NOT_ALLOW, "未经许可的用户", null, null);
    }

    /**
     * 接口调用失败：参数错误
     *
     * @return
     */
    public static <T> AppPageResultObj<T> parameterError() {
        return new AppPageResultObj<T>(AppResultObj.CODE_PARAM_ERR, "参数解析错误", null, null);
    }

    /**
     * 接口调用失败：参数错误
     *
     * @return
     */
    public static <T> AppPageResultObj<T> parameterError(String msg) {
        return new AppPageResultObj<T>(AppResultObj.CODE_PARAM_ERR, msg, null, null);
    }

    /**
     * 接口调用失败：服务器异常
     *
     * @return
     */
    public static <T> AppPageResultObj<T> serverError() {
        return new AppPageResultObj<T>(AppResultObj.CODE_SERVER_ERR, "服务器异常", null, null);
    }

    /**
     * 接口调用失败：服务器异常
     *
     * @return
     */
    public static <T> AppPageResultObj<T> serverError(String msg) {
        return new AppPageResultObj<T>(AppResultObj.CODE_SERVER_ERR, msg, null, null);
    }
}
