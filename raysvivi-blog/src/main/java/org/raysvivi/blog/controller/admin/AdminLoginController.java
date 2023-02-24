package org.raysvivi.blog.controller.admin;

import org.raysvivi.blog.model.log.LoginParam;
import org.raysvivi.blog.service.AdminService;
import org.spider.common.util.ResponseData;
import org.spider.common.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/admin")
public class AdminLoginController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public ResponseData<?> login(@RequestBody LoginParam param, HttpServletRequest request){
        return adminService.checkLogin(param);
    }

    @PostMapping("/demo")
    public ResponseData<?> demo(HttpServletRequest request){
        return ResponseUtil.ok("demo");
    }
}
