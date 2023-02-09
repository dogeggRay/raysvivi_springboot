package org.raysvivi.blog.service;

import lombok.extern.slf4j.Slf4j;
import org.raysvivi.blog.dao.ArticleMapper;
import org.raysvivi.blog.model.AritcleInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    public Integer save(AritcleInfo aritcleInfo){
        return articleMapper.insert(aritcleInfo);
    }
}
