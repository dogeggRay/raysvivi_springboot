package org.raysvivi.blog.service;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.raysvivi.blog.model.Constant;
import org.raysvivi.blog.model.FootPrints;
import org.raysvivi.blog.utils.JedisUtil;
import org.spider.common.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Service
public class TouristRecordService {
    @Autowired
    private JedisUtil jedisUtil;

    public void footstepRecord(FootPrints footPrints, HttpServletRequest request){
        //没有登录功能，使用ip作为判断节点
        String clientIp = HttpUtil.getClientIp(request);
        String redisKey = Constant.RedisKey.FOOT_STEP_RECORD+ "_" + footPrints.getModuleName()+ "_" + footPrints.getRelativeId();
        jedisUtil.sadd(redisKey, clientIp);
    }
}
