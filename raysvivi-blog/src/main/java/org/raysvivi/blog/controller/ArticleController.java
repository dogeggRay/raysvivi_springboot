package org.raysvivi.blog.controller;

import org.raysvivi.blog.model.AritcleInfo;
import org.raysvivi.blog.service.ArticleService;
import org.spider.common.util.ResponseData;
import org.spider.common.util.ResponseUtil;
import org.spider.model.page.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/article")
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

    @PostMapping("/blogDetail")
    public ResponseData<?> blogDetail(@RequestBody AritcleInfo blogInfo) {
        return ResponseUtil.ok(articleService.getBlogDetail(blogInfo));
    }
}
