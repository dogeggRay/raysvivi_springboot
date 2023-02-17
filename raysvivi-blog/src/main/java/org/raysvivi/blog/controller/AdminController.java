package org.raysvivi.blog.controller;

import org.raysvivi.blog.model.log.LoginParam;
import org.raysvivi.blog.service.AdminService;
import org.spider.common.util.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @PostMapping("/login")
    public ResponseData<?> login(@RequestBody LoginParam param, HttpServletRequest request){
        return adminService.checkLogin(param);
    }
}
