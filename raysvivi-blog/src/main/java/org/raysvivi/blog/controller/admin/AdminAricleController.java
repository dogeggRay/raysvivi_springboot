package org.raysvivi.blog.controller.admin;

import org.raysvivi.blog.model.AritcleInfo;
import org.raysvivi.blog.service.ArticleService;
import org.spider.common.util.ResponseData;
import org.spider.common.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/article")
public class AdminAricleController {

    @Autowired
    private ArticleService articleService;


    @PostMapping("/saveArtcle")
    public ResponseData<?> saveArtcle(@RequestBody AritcleInfo aritcleInfo) {
        articleService.save(aritcleInfo);
        return ResponseUtil.ok();
    }

    @GetMapping("/blogsSimpleList")
    public ResponseData<?> blogsSimpleList() {
        return ResponseUtil.ok(articleService.getBlogsSimpleList());
    }
}
