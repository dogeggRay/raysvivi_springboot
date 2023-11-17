package org.spider.common.util;

import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.HashMap;
import java.util.Map;

public class AopHelper {
    /**
     *  获取请求方法的参数
     * @param joinPoint
     */
    public  static HashMap<String,Object> getMethodParams(JoinPoint joinPoint){
        String[] names = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        HashMap<String,Object> params =new HashMap();
        if (ArrayUtils.isEmpty(names)) return params;
        Object[] values = joinPoint.getArgs();
        for (int i = 0; i < names.length; i++) {
            params.put(names[i],values[i]);
        }
        return params;
    }
}