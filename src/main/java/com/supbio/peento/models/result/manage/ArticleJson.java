package com.supbio.peento.models.result.manage;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by liangqiang on 2018/10/8.
 */
public class ArticleJson {

    @ApiModelProperty(value = "文章id")
    private String articleId;

    @ApiModelProperty(value = "文章标题")
    private String articleTitle;

    @ApiModelProperty(value = "文章分类id")
    private String articleSortId;

    @ApiModelProperty(value = "分类名称")
    private String sortName;

    @ApiModelProperty(value = "显示平台")
    private String showPlatform;

    @ApiModelProperty(value = "收藏数")
    private int collect;

    @ApiModelProperty(value = "点赞数")
    private int like;

    @ApiModelProperty(value = "排序")
    private String sort;

    @ApiModelProperty(value = "封面图片url")
    private String coverUrl;

    @ApiModelProperty(value = "最后修改日期")
    private String updateTime;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

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

    public int getCollect() {
        return collect;
    }

    public void setCollect(int collect) {
        this.collect = collect;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
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

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
