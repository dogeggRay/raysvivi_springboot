package org.raysvivi.blog.feign;

import org.spider.common.util.ResponseData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("spider-douban")
public interface SpiderClient {
    @PostMapping("/boxOffice/getList")
    ResponseData<?> getList();
}