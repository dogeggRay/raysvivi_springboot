package org.raysvivi.blog.controller;

import org.raysvivi.blog.model.AritcleQuery;
import org.raysvivi.blog.service.ArticleService;
import org.spider.common.util.ResponseData;
import org.spider.common.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tourist/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping("/getArtclePageList")
    public ResponseData<?> getArtclePageList(@RequestBody AritcleQuery pageParam){
        return ResponseUtil.ok(articleService.getBlogsWithPage(pageParam));
    }

    @GetMapping("/blogDetail")
    public ResponseData<?> blogDetail(@RequestParam("aritcleInfoId") String aritcleInfoId) {
        return ResponseUtil.ok(articleService.getBlogDetail(aritcleInfoId));
    }

    @GetMapping("/blogDetail")
    public ResponseData<?> blogDetail(@RequestParam("aritcleInfoId") String aritcleInfoId) {
        return ResponseUtil.ok(articleService.getBlogDetail(aritcleInfoId));
    }

}
