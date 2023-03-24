package org.raysvivi.blog.controller;

import org.raysvivi.blog.service.StructureService;
import org.spider.common.util.ResponseData;
import org.spider.common.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/tourist/structure")
public class StructureController {

    @Autowired
    private StructureService structureService;

    @GetMapping("/getNameList")
    public ResponseData<?> getNameList() {
        return ResponseUtil.ok(structureService.queryNameList());
    }

    @GetMapping("/queryOne")
    public ResponseData<?> queryOne(@RequestParam("name") String name) {
        return ResponseUtil.ok(structureService.queryOne(name));
    }
}
