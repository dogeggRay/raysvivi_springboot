package org.spider.controller;

import org.spider.common.util.ResponseData;
import org.spider.common.util.ResponseUtil;
import org.spider.feign.SpiderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spider")
public class SpiderController {
    @Autowired
    private SpiderClient spiderClient;

    @PostMapping("/getList")
    public ResponseData<?> getBoList(){
        return spiderClient.getList();
    }
}
