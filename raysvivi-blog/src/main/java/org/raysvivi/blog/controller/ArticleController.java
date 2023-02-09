package org.raysvivi.blog.controller;

import org.raysvivi.blog.model.AritcleInfo;
import org.raysvivi.blog.service.ArticleService;
import org.spider.common.util.ResponseData;
import org.spider.common.util.ResponseUtil;
import org.spider.model.page.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;
    @PostMapping("/getArtclePageList")
    public ResponseData<?> getArtclePageList(@RequestBody PageParam pageParam){
        return ResponseUtil.ok(articleService.getBlogsWithPage(pageParam));
    }

    @PostMapping("/saveArtcle")
    public ResponseData<?> saveArtcle(@RequestBody AritcleInfo aritcleInfo) {
        articleService.save(aritcleInfo);
        return ResponseUtil.ok();
    }
}
