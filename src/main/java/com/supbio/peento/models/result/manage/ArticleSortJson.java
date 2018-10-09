package com.supbio.peento.models.result.manage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by liangqiang on 2018/10/8.
 */
public class ArticleSortJson {

    @ApiModelProperty(value = "分类id")
    private String sortId;

    @ApiModelProperty(value = "分类名称")
    private String sortName;

    @ApiModelProperty(value = "显示平台")
    private String showPlatform;

    @ApiModelProperty(value = "排序")
    private String sort;

    public String getSortId() {
        return sortId;
    }

    public void setSortId(String sortId) {
        this.sortId = sortId;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getShowPlatform() {
        return showPlatform;
    }

    public void setShowPlatform(String showPlatform) {
        this.showPlatform = showPlatform;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
