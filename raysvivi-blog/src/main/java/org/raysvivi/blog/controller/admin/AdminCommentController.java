package org.raysvivi.blog.controller.admin;

import org.raysvivi.blog.service.CommentService;
import org.spider.common.util.ResponseData;
import org.spider.common.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/comment")
public class AdminCommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/delete")
    public ResponseData<?> delete(@RequestParam String commentId,@RequestParam String relativeId,@RequestParam String moduleId){
        commentService.delete(commentId,relativeId,moduleId);
        return ResponseUtil.ok();
    }
}
