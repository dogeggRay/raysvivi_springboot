package org.raysvivi.blog.controller.admin;

import org.raysvivi.blog.model.structure.StructureInfo;
import org.raysvivi.blog.service.StructureService;
import org.spider.common.util.ResponseData;
import org.spider.common.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("api/admin/structure")
public class AdminStructureController {

    @Autowired
    private StructureService structureService;

    @PostMapping("/update")
    public ResponseData<?> updateOne(@RequestBody StructureInfo structureInfo) {
        structureService.updateOne(structureInfo);
        return ResponseUtil.ok();
    }

    @PostMapping("/add")
    public ResponseData<?> addOne(@RequestBody StructureInfo structureInfo) {
        structureService.addOne(structureInfo);
        return ResponseUtil.ok();
    }

}
