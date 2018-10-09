package com.supbio.peento.controller.manage;

import com.supbio.peento.common.response.AppPageResultObj;
import com.supbio.peento.common.response.AppResultObj;
import com.supbio.peento.common.response.PageParameter;
import com.supbio.peento.models.params.manage.ArticleParam;
import com.supbio.peento.models.params.manage.ArticleSortParam;
import com.supbio.peento.models.result.manage.ArticleJson;
import com.supbio.peento.models.result.manage.ArticleSortJson;
import com.supbio.peento.servicecenter.IArticleService;
import com.supbio.peento.utils.DateUtil;
import com.supbio.peento.utils.JacksonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liangqiang
 * @date 2018/10/08 09:13
 */
@Api(value = "PeentoUniversityController", description = "蟠桃大学模块-相关API")
@RestController
@RequestMapping("/api/manage/article")
public class PeentoUniversityController extends BaseManageController {

    @Autowired
    private IArticleService articleService;

    @ApiOperation(value = "添加/编辑文章分类接口", notes = "添加/编辑文章分类接口")
    @RequestMapping(value = "/addOrEditArticleSort", method = RequestMethod.POST)
    public AppResultObj<String> addOrEditArticleSort(@RequestBody ArticleSortParam.AddArticleSort param){
        logger.info("/api/manage/article/addOrEditArticleSort: {}", JacksonUtil.toJSON(param));
        if (StringUtils.isNotBlank(param.getSortId())){
            ArticleSortJson articleSort = articleService.findArticleSort(param.getSortId());
            if (articleSort == null){
                return AppResultObj.parameterError("该分类不存在");
            }
            //编辑分类
            articleService.updateArticleSort(param.getSortId(), param.getSortName(),param.getSort(),param.getShowPlatform());
            return AppResultObj.success("文章分类编辑成功");
        }
        //添加分类
        articleService.addArticleSort(param.getSortName(), param.getSort(), param.getShowPlatform());
        return AppResultObj.success("文章分类添加成功");
    }

    @ApiOperation(value = "文章分类详情接口", notes = "文章分类详情接口")
    @RequestMapping(value = "/articleSortDetail", method = RequestMethod.POST)
    public AppResultObj<ArticleSortJson> articleSortDetail(@RequestBody ArticleSortParam.ArticleSortId param){
        logger.info("/api/manage/article/articleSortDetail: {}", JacksonUtil.toJSON(param));
        ArticleSortJson articleSort = articleService.findArticleSort(param.getSortId());
        if (articleSort != null){
            return AppResultObj.success(articleSort);
        }
        return AppResultObj.parameterError("入参文章分类id有误");
    }

    @ApiOperation(value = "文章分类列表接口", notes = "文章分类列表接口")
    @RequestMapping(value = "/articleSortList", method = RequestMethod.POST)
    public AppResultObj<List<ArticleSortJson>> articleSortList(){
        logger.info("/api/manage/article/articleSortList: {}");
        List<ArticleSortJson> articleSortJsonList = articleService.findAllArticleSort();
        return AppResultObj.success(articleSortJsonList);
    }

    @ApiOperation(value = "删除文章分类接口", notes = "删除文章分类接口")
    @RequestMapping(value = "/deleteArticleSort", method = RequestMethod.POST)
    public AppResultObj<String> deleteArticleSort(@RequestBody ArticleSortParam.ArticleSortId param){
        logger.info("/api/manage/article/deleteArticleSort: {}", JacksonUtil.toJSON(param));
        List<ArticleJson> articleJsonList = articleService.findArticleBySortId(param.getSortId());
        if (CollectionUtils.isEmpty(articleJsonList)){
            articleService.deleteArticleSort(param.getSortId());
            return AppResultObj.success("删除成功");
        }
        return AppResultObj.serverError("该分类下有对应的文章，不可删除");
    }

    @ApiOperation(value = "添加/编辑文章接口", notes = "添加/编辑文章接口")
    @RequestMapping(value = "/addOrEditArticle", method = RequestMethod.POST)
    public AppResultObj<String> addOrEditArticle(@RequestBody ArticleParam.AddArticle param){
        logger.info("/api/manage/article/addOrEditArticle: {}", JacksonUtil.toJSON(param));
        if (StringUtils.isNotBlank(param.getArticleId())){
            ArticleJson article = articleService.findArticle(param.getArticleId());
            if (article == null){
                return AppResultObj.parameterError("该文章不存在");
            }
            //编辑文章
            articleService.updateArticle(param.getArticleId(), param.getArticleTitle(), param.getArticleSortId(),
                    param.getSortName(), param.getShowPlatform(), param.getSort(), param.getCoverUrl());
            return AppResultObj.success("文章编辑成功");
        }
        //添加文章
        articleService.addArticle(param.getArticleTitle(), param.getArticleSortId(), param.getSortName(),
                param.getShowPlatform(), param.getSort(), param.getCoverUrl());
        return AppResultObj.success("文章添加成功");
    }

    @ApiOperation(value = "文章详情接口", notes = "文章详情接口")
    @RequestMapping(value = "/articleDetail", method = RequestMethod.POST)
    public AppResultObj<ArticleJson> articleDetail(@RequestBody ArticleParam.ArticleId param){
        logger.info("/api/manage/article/articleDetail: {}", JacksonUtil.toJSON(param));
        ArticleJson article = articleService.findArticle(param.getArticleId());
        if (article != null){
            return AppResultObj.success(article);
        }
        return AppResultObj.parameterError("入参文章id有误");
    }

    @ApiOperation(value = "文章列表接口", notes = "文章列表接口")
    @RequestMapping(value = "/articleList", method = RequestMethod.POST)
    public AppPageResultObj<List<ArticleJson>> articleList(@RequestBody ArticleParam.ArticleList param){
        logger.info("/api/manage/article/articleList: {}", JacksonUtil.toJSON(param));
        PageParameter pageParameter = new PageParameter(param.getPage(), param.getCount());
        List<ArticleJson> articleJsonList = articleService.findAllArticle(param.getArticleTitle(), param.getSortName(), pageParameter);
        if (CollectionUtils.isEmpty(articleJsonList)){
            return AppPageResultObj.success(new ArrayList<>());
        }
        String updateTime = "";
        //转换时间字符串格式
        for (ArticleJson json : articleJsonList) {
            updateTime = json.getUpdateTime();
            String time = DateUtil.date2Str(DateUtil.dateString2Date(updateTime, DateUtil.DF_yyyy_MM_dd_HH_mm_ss),
                    DateUtil.DF_yyyy_MM_dd_HH_mm_ss);
            json.setUpdateTime(time);
        }
        return AppPageResultObj.success(articleJsonList, pageParameter);
    }

    @ApiOperation(value = "删除文章接口", notes = "删除文章接口")
    @RequestMapping(value = "/deleteArticle", method = RequestMethod.POST)
    public AppResultObj<String> deleteArticle(@RequestBody ArticleParam.ArticleId param){
        logger.info("/api/manage/article/deleteArticle: {}", JacksonUtil.toJSON(param));
        articleService.deleteArticle(param.getArticleId());
        return AppResultObj.success("删除成功");
    }

}
