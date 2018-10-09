package com.supbio.peento.models.params.manage;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by liangqiang on 2018/10/8.
 */
public class ArticleSortParam {

    public static class AddArticleSort{

        @ApiModelProperty(value = "分类id(编辑分类时用到)")
        private String sortId;

        @ApiModelProperty(value = "分类名称", required = true)
        private String sortName;

        @ApiModelProperty(value = "排序", required = true)
        private String sort;

        @ApiModelProperty(value = "显示平台，例如：医生端 或 医生端、患者端", required = true)
        private String showPlatform;

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

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getShowPlatform() {
            return showPlatform;
        }

        public void setShowPlatform(String showPlatform) {
            this.showPlatform = showPlatform;
        }

    }

    public static class ArticleSortId{

        @ApiModelProperty(value = "分类id", required = true)
        private String sortId;

        public String getSortId() {
            return sortId;
        }

        public void setSortId(String sortId) {
            this.sortId = sortId;
        }
    }


}
