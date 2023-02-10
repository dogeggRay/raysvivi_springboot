package org.spider.controller;

import org.spider.common.aliyun.OssService;
import org.spider.common.util.ResponseData;
import org.spider.common.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private OssService ossService;

    @PostMapping("/uploadFile")
    public ResponseData<?> uploadFile(@RequestBody MultipartFile file) throws Exception {
        String path = ossService.uploadImage(file,"spider");
        return ResponseUtil.ok(path);
    }
}
