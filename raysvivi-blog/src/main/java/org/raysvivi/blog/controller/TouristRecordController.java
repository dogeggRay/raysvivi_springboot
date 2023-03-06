package org.raysvivi.blog.controller;

import org.raysvivi.blog.model.FootPrints;
import org.raysvivi.blog.service.TouristRecordService;
import org.spider.common.util.ResponseData;
import org.spider.common.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/tourist/record")
public class TouristRecordController {

    @Autowired
    private TouristRecordService touristRecordService;
    @PostMapping("/footstepRecord")
    public void footstepRecord(@RequestBody FootPrints footPrints, HttpServletRequest request){
        touristRecordService.footstepRecord(footPrints,request);
    }

    @GetMapping("/pageExtendInfo")
    public ResponseData<?> pageExtendInfo(@RequestParam("moduleName") String moduleName, @RequestParam("relativeId") String relativeId){
        return ResponseUtil.ok(touristRecordService.pageExtendInfo(moduleName,relativeId));
    }

}

