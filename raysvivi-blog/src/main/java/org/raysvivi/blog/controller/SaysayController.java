package org.raysvivi.blog.controller;

import org.raysvivi.blog.model.CommonRequest;
import org.raysvivi.blog.service.SaysayService;
import org.spider.common.util.ResponseData;
import org.spider.common.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tourist/saysay")
public class SaysayController {

    @Autowired
    private SaysayService saysayService;

    @PostMapping("/getList")
    public ResponseData<?> getList(@RequestBody CommonRequest cr) {
        return ResponseUtil.ok(saysayService.getSaysayList(cr.getStartDateTime()));
    }

    @PostMapping("/getLastSaysay")
    public ResponseData<?> getLastSaysay() {
        return ResponseUtil.ok(saysayService.getLastSaysay());
    }
}
