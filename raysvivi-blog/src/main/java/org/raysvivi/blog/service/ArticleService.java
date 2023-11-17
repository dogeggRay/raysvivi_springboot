package org.raysvivi.blog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.raysvivi.blog.dao.ArticleMapper;
import org.raysvivi.blog.dao.TagMapper;
import org.raysvivi.blog.model.AritcleInfo;
import org.raysvivi.blog.model.AritcleQuery;
import org.raysvivi.blog.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private TagMapper tagMapper;

    public Integer save(AritcleInfo aritcleInfo){
        if(StringUtils.isNotEmpty(aritcleInfo.getId())){
            return update(aritcleInfo);
        }

        return add(aritcleInfo);
    }

    @Transactional
    public Integer update(AritcleInfo aritcleInfo){
        Integer result = articleMapper.updateById(aritcleInfo);

        List<Tag> tags = new ArrayList<>();
        List<String> tagsIds = new ArrayList<>();
        for(String tagNode : aritcleInfo.getTags()){
            tags.add(new Tag(aritcleInfo.getModule(),aritcleInfo.getId(),tagNode,true));
            tagsIds.add(tagNode);
        }
        tagMapper.delete(new QueryWrapper<Tag>().eq("f_relative_id",aritcleInfo.getId()));
        tagMapper.batchInsertTags(tags);

        return result;
    }

    @Transactional
    public Integer add(AritcleInfo aritcleInfo){
        Integer result = articleMapper.insert(aritcleInfo);

        List<Tag> tags = new ArrayList<>();
        for(String tagNode : aritcleInfo.getTags()){
            tags.add(new Tag(aritcleInfo.getModule(),aritcleInfo.getId(),tagNode,true));
        }
        tagMapper.batchInsertTags(tags);

        return result;
    }

    public List<AritcleInfo> getBlogsWithPage(AritcleQuery pageParam){
        return articleMapper.getArticlePageList(pageParam,null,pageParam.getKeyword());
    }

    public AritcleInfo getBlogDetail(String aritcleInfoId){
        return articleMapper.selectById(aritcleInfoId);
    }

    public List<AritcleInfo> getBlogsSimpleList(String tag){
        return articleMapper.getBlogsSimpleList(tag);
    }
}
