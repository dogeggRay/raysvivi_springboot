package org.raysvivi.blog.controller;

import org.raysvivi.blog.service.CommonService;
import org.spider.common.util.ResponseData;
import org.spider.common.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/constants")
public class MineController {

    @Autowired
    private CommonService commonService;

    @GetMapping("/getTagEnums")
    public ResponseData<?> getArtclePageList(){
        return ResponseUtil.ok(commonService.getTagEnums());
    }
}
