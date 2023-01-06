package org.spider.controller;

import org.spider.common.util.ResponseData;
import org.spider.common.util.ResponseUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("demo")
    public ResponseData<?> demo( ){
        return ResponseUtil.ok("233333333333333");
    }
}
