package org.spider.controller;

import org.apache.log4j.Logger;
import org.spider.common.util.ResponseData;
import org.spider.common.util.ResponseUtil;
import org.spider.service.BoxOfficeNAServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/boxOffice")
public class BoxOfficeController {
    Logger logger = Logger.getLogger(BoxOfficeController.class);

    @Autowired
    private BoxOfficeNAServ boxOfficeNAServ;
    @PostMapping("/getList")
    public ResponseData<?> getList(){

        return ResponseUtil.ok(boxOfficeNAServ.getAllBO());
    }
}
