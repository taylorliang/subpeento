package com.supbio.peento.models.params.manage;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by liangqiang on 2018/10/8.
 */
public class ArticleParam {

    public static class AddArticle{

        @ApiModelProperty(value = "文章id(编辑文章时用到)")
        private String articleId;

        @ApiModelProperty(value = "文章标题", required = true)
        private String articleTitle;

        @ApiModelProperty(value = "文章分类id", required = true)
        private String articleSortId;

        @ApiModelProperty(value = "文章分类名称", required = true)
        private String sortName;

        @ApiModelProperty(value = "显示平台，例如：医生端 或 医生端、患者端", required = true)
        private String showPlatform;

        @ApiModelProperty(value = "文章排序", required = true)
        private String sort;

        @ApiModelProperty(value = "封面图片url")
        private String coverUrl;

        public String getArticleId() {
            return articleId;
        }

        public void setArticleId(String articleId) {
            this.articleId = articleId;
        }

        public String getArticleTitle() {
            return articleTitle;
        }

        public void setArticleTitle(String articleTitle) {
            this.articleTitle = articleTitle;
        }

        public String getArticleSortId() {
            return articleSortId;
        }

        public void setArticleSortId(String articleSortId) {
            this.articleSortId = articleSortId;
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

        public String getCoverUrl() {
            return coverUrl;
        }

        public void setCoverUrl(String coverUrl) {
            this.coverUrl = coverUrl;
        }

    }

    public static class ArticleId{

        @ApiModelProperty(value = "文章id", required = true)
        private String articleId;

        public String getArticleId() {
            return articleId;
        }

        public void setArticleId(String articleId) {
            this.articleId = articleId;
        }
    }

    public static class ArticleList extends PageParam{

        @ApiModelProperty(value = "文章标题", required = true)
        private String articleTitle;

        @ApiModelProperty(value = "文章分类名称", required = true)
        private String sortName;

        public String getArticleTitle() {
            return articleTitle;
        }

        public void setArticleTitle(String articleTitle) {
            this.articleTitle = articleTitle;
        }

        public String getSortName() {
            return sortName;
        }

        public void setSortName(String sortName) {
            this.sortName = sortName;
        }
    }

}
