package com.supbio.peento.common.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PageInfo {

    /**
     * 总页数
     */
    private Integer totalPage;

    /**
     * 总记录数
     */
    @JsonProperty("total")
    private Long total;

    /**
     * 是否有下一页
     */
    @JsonProperty("has_next")
    private boolean hasNext;

    public PageInfo() {

    }
    public PageInfo(Long total, boolean hasNext) {
        this.total = total;
        this.hasNext = hasNext;
    }

    public PageInfo(PageParameter pageParameter) {
        this.setTotal(pageParameter.getTotalCount());
        this.setHasNext(pageParameter.getCurrentPage() < pageParameter.getTotalPage());
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
}
