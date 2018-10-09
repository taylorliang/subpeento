package com.supbio.peento.servicecenter;

import com.supbio.peento.common.response.PageParameter;
import com.supbio.peento.models.result.manage.ArticleJson;
import com.supbio.peento.models.result.manage.ArticleSortJson;

import java.util.List;

/**
 * Created by liangqiang on 2018/10/8.
 */
public interface IArticleService {

    void addArticleSort(String sortName, String sort, String showPlatform);

    ArticleSortJson findArticleSort(String sortId);

    void updateArticleSort(String sortId, String sortName, String sort, String showPlatform);

    List<ArticleSortJson> findAllArticleSort();

    void deleteArticleSort(String sortId);

    void addArticle(String articleTitle, String articleSortId, String sortName, String showPlatform, String sort, String coverUrl);

    ArticleJson findArticle(String articleId);

    void updateArticle(String articleId, String articleTitle, String articleSortId, String sortName, String showPlatform, String sort, String coverUrl);

    List<ArticleJson> findAllArticle(String articleTitle, String sortName, PageParameter pageParameter);

    void deleteArticle(String articleId);

    List<ArticleJson> findArticleBySortId(String sortId);

}
