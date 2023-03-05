package org.raysvivi.blog.controller;

import org.raysvivi.blog.model.FootPrints;
import org.raysvivi.blog.service.TouristRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}

