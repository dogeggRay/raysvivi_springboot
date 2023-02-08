package org.spider.controller;

import lombok.extern.slf4j.Slf4j;
import org.spider.common.util.ResponseData;
import org.spider.common.util.ResponseUtil;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/demo")
    public ResponseData<?> demo( ){
        return ResponseUtil.ok("233333333333333");
    }

    @PostMapping("/demoSubmit")
    public ResponseData<?> demoSubmit(@RequestBody String richHtml){
        log.info(richHtml);
        return ResponseUtil.ok();
    }
}
