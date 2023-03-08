package org.raysvivi.blog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.raysvivi.blog.dao.TagMapper;
import org.raysvivi.blog.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommonService {
    @Autowired
    private TagMapper tagMapper;

    public List<Tag> getTagEnums(){
        return tagMapper.selectList(new QueryWrapper<Tag>().eq("f_related",0));
    }
}
