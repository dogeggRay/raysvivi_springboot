package org.raysvivi.blog.service;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.raysvivi.blog.dao.PVinfoMapper;
import org.raysvivi.blog.model.Constant;
import org.raysvivi.blog.model.FootPrints;
import org.raysvivi.blog.utils.JedisUtil;
import org.spider.common.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Slf4j
@Service
public class TouristRecordService {
    @Autowired
    private JedisUtil jedisUtil;

    @Autowired
    private PVinfoMapper pVinfoMapper;
    public void footstepRecord(FootPrints footPrints, HttpServletRequest request){
        //没有登录功能，使用ip作为判断节点
        String clientIp = HttpUtil.getClientIp(request);
        String redisKey = Constant.RedisConstant.FOOT_STEP_RECORD+ "_" + footPrints.getModuleName()+ "_" + footPrints.getRelativeId();
        //判断redis是否有记录
        if(jedisUtil.sismember(redisKey,clientIp)){
            return;
        }
        //
        jedisUtil.sadd(redisKey, clientIp);
        jedisUtil.expire(redisKey,Constant.RedisConstant.FOOTSTEP_DURATION);

        pVinfoMapper.insert(footPrints);
    }
}
