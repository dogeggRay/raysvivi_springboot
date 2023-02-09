package org.raysvivi.blog.controller;

import org.spider.common.aliyun.OssService;
import org.spider.common.util.ResponseData;
import org.spider.common.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private OssService ossService;

    @PostMapping("/uploadFile")
    public ResponseData<?> uploadFile(@RequestBody MultipartFile file) throws Exception {
        //无法autowired
        String path = ossService.uploadImage(file);
        return ResponseUtil.ok(path);
        //return ResponseUtil.ok("https://persional-images.oss-cn-hangzhou.aliyuncs.com/headPortrait/2023-02-08601b9905b9f149f79b97938b49a628a8.webp");
    }
}
