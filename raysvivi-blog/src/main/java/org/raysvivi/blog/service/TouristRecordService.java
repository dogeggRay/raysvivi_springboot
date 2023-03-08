package org.raysvivi.blog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.raysvivi.blog.dao.PVinfoMapper;
import org.raysvivi.blog.model.CharacteristicData;
import org.raysvivi.blog.constant.Constant;
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

        pVinfoMapper.numberIncrease(footPrints.getModuleName(),footPrints.getRelativeId());
    }

    public CharacteristicData pageExtendInfo(String moduleName, String relativeId){
        String resultNum = jedisUtil.hget(Constant.RedisConstant.PAGE_VIEW_NUMBER,moduleName+ "--" + relativeId);
        if(StringUtils.isNotEmpty(resultNum)){
            return new CharacteristicData(Integer.valueOf(resultNum));
        }

        FootPrints footPrints = pVinfoMapper.selectOne(new QueryWrapper<FootPrints>().eq("f_module_name",moduleName).eq("f_relative_id",relativeId));
        if(footPrints==null){
            return new CharacteristicData(0);
        }
        jedisUtil.hset(Constant.RedisConstant.PAGE_VIEW_NUMBER,moduleName+ "--" + relativeId,footPrints.getNumber()+"");
        jedisUtil.expire(Constant.RedisConstant.PAGE_VIEW_NUMBER,Constant.RedisConstant.PAGE_EXTENTION_DURATION);

        return new CharacteristicData(footPrints.getNumber());
    }
}
