package com.supbio.peento.models.params.manage;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by liangqiang on 2018/10/9.
 */
public class PageParam {

    @ApiModelProperty(value = "页码(从第0页开始)", required = true, dataType = "Integer")
    protected Integer page;

    @ApiModelProperty(value = "每页显示记录数", required = true, dataType = "Integer")
    protected Integer count;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
