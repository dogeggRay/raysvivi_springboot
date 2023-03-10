package org.raysvivi.blog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.raysvivi.blog.dao.ArticleMapper;
import org.raysvivi.blog.dao.CommentMapper;
import org.raysvivi.blog.dao.PVinfoMapper;
import org.raysvivi.blog.dao.TagMapper;
import org.raysvivi.blog.model.Tag;
import org.raysvivi.blog.model.user.BloggerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommonService {
    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private PVinfoMapper pVinfoMapper;

    @Autowired
    private CommentMapper commentMapper;
    public List<Tag> getTagEnums(){
        return tagMapper.selectList(new QueryWrapper<Tag>().eq("f_related",0));
    }

    public BloggerInfo getBloggerInfo(){
        Long aritcleNum = articleMapper.selectCount(new QueryWrapper<>());

        Long viewNum = pVinfoMapper.selectTotalCount();

        Long commentNum = commentMapper.selectCount(new QueryWrapper<>());

        return new BloggerInfo(aritcleNum,commentNum,viewNum);
    }
}
