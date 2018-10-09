package com.supbio.peento.mapper;

import com.supbio.peento.common.response.PageParameter;
import com.supbio.peento.models.result.manage.ArticleJson;
import com.supbio.peento.models.result.manage.ArticleSortJson;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by liangqiang on 2018/10/8.
 */
@Repository
public interface ArticleMapper {

    void addArticleSort(@Param("sortName") String sortName, @Param("sort") String sort, @Param("showPlatform") String showPlatform);

    ArticleSortJson findArticleSort(@Param("id") String sortId);

    void updateArticleSort(@Param("id") String sortId, @Param("sortName") String sortName, @Param("sort") String sort,
                           @Param("showPlatform") String showPlatform);

    List<ArticleSortJson> findAllArticleSort();

    void deleteArticleSort(@Param("id") String sortId);

    void addArticle(@Param("articleTitle") String articleTitle, @Param("articleSortId") String articleSortId, @Param("sortName") String sortName,
                    @Param("showPlatform") String showPlatform, @Param("sort") String sort, @Param("coverUrl") String coverUrl);

    ArticleJson findArticle(@Param("id") String articleId);

    void updateArticle(@Param("id") String articleId, @Param("articleTitle") String articleTitle,
                          @Param("articleSortId") String articleSortId, @Param("sortName") String sortName,
                          @Param("showPlatform") String showPlatform, @Param("sort") String sort, @Param("coverUrl") String coverUrl);

    List<ArticleJson> findAllArticle(@Param("articleTitle") String articleTitle, @Param("sortName") String sortName, PageParameter pageParameter);

    void deleteArticle(@Param("id") String articleId);

    List<ArticleJson> findArticleBySortId(@Param("articleSortId") String sortId);
}
