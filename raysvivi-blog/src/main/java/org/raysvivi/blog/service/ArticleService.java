package org.raysvivi.blog.service;

import lombok.extern.slf4j.Slf4j;
import org.raysvivi.blog.dao.ArticleMapper;
import org.raysvivi.blog.model.AritcleInfo;
import org.spider.model.page.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    public Integer save(AritcleInfo aritcleInfo){
        return articleMapper.insert(aritcleInfo);
    }

    public List<AritcleInfo> getBlogsWithPage(PageParam pageParam){
        log.info("getBlogsWithPage access");
        return articleMapper.getArticlePageList(pageParam,null);
    }

    public AritcleInfo getBlogDetail(String aritcleInfoId){
        return articleMapper.selectById(aritcleInfoId);
    }
}
