package org.raysvivi.blog.controller;

import org.apache.commons.lang3.StringUtils;
import org.raysvivi.blog.model.user.CommentInfo;
import org.raysvivi.blog.service.CommentService;
import org.spider.common.util.ResponseData;
import org.spider.common.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/tourist/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/list")
    public ResponseData<?> getList(@RequestParam String moduleId,@RequestParam String relativeId){
        return ResponseUtil.ok(commentService.getList(moduleId,relativeId));
    }

    @PostMapping("/add")
    public ResponseData<?> add(HttpServletRequest request,@RequestBody CommentInfo commentInfo){
        commentService.add(request,commentInfo);
        return ResponseUtil.ok();
    }

}
