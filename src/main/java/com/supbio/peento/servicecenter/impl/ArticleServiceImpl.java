package com.supbio.peento.servicecenter.impl;

import com.supbio.peento.common.response.PageParameter;
import com.supbio.peento.mapper.ArticleMapper;
import com.supbio.peento.models.result.manage.ArticleJson;
import com.supbio.peento.models.result.manage.ArticleSortJson;
import com.supbio.peento.servicecenter.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by liangqiang on 2018/10/8.
 */
@Service
@Transactional
public class ArticleServiceImpl implements IArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void addArticleSort(String sortName, String sort, String showPlatform) {
        articleMapper.addArticleSort(sortName, sort, showPlatform);
    }

    @Override
    public ArticleSortJson findArticleSort(String sortId) {
        return articleMapper.findArticleSort(sortId);
    }

    @Override
    public void updateArticleSort(String sortId, String sortName, String sort, String showPlatform) {
        articleMapper.updateArticleSort(sortId, sortName, sort, showPlatform);
    }

    @Override
    public List<ArticleSortJson> findAllArticleSort() {
        return articleMapper.findAllArticleSort();
    }

    @Override
    public void deleteArticleSort(String sortId) {
        articleMapper.deleteArticleSort(sortId);
    }

    @Override
    public void addArticle(String articleTitle, String articleSortId, String sortName, String showPlatform, String sort, String coverUrl) {
        articleMapper.addArticle(articleTitle, articleSortId, sortName, showPlatform, sort, coverUrl);
    }

    @Override
    public ArticleJson findArticle(String articleId) {
        return articleMapper.findArticle(articleId);
    }

    @Override
    public void updateArticle(String articleId, String articleTitle, String articleSortId, String sortName, String showPlatform, String sort, String coverUrl) {
        articleMapper.updateArticle(articleId, articleTitle, articleSortId, sortName, showPlatform, sort, coverUrl);
    }

    @Override
    public List<ArticleJson> findAllArticle(String articleTitle, String sortName, PageParameter pageParameter) {
        return articleMapper.findAllArticle(articleTitle, sortName, pageParameter);
    }

    @Override
    public void deleteArticle(String articleId) {
        articleMapper.deleteArticle(articleId);
    }

    @Override
    public List<ArticleJson> findArticleBySortId(String sortId) {
        return articleMapper.findArticleBySortId(sortId);
    }

}
