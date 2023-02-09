package org.raysvivi.blog.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.raysvivi.blog.model.AritcleInfo;
import org.spider.model.page.PageParam;

import java.util.List;

@DS(value = "readDB")
@Mapper
public interface ArticleMapper extends BaseMapper<AritcleInfo> {
    List<AritcleInfo> getArticlePageList(@Param("pageParam") PageParam pageParam, @Param("condition") AritcleInfo condition);
}
