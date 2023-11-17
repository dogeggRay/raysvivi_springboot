package org.raysvivi.blog.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.raysvivi.blog.aspect.annotation.BlogDetailAnno;
import org.spider.common.util.AopHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.HashMap;

@Aspect
@Component
public class BlogDetailAspect {

//    @Pointcut("execution(public * org.raysvivi.blog.controller.*.*(..))")
    @Pointcut("execution(public * org.raysvivi.blog.controller.ArticleController.*(..))")
    public void controllerAccess() {
    }

    @Around("controllerAccess()")
    public Object myLogger(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        HashMap<String,Object> params =  AopHelper.getMethodParams(joinPoint);
        for(String param : params.keySet()){
            System.out.println(param+"=============="+params.get(param));
        }

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取执行方法
        Method method = signature.getMethod();

        // 获取注解信息
        BlogDetailAnno blogDetailAnno = method.getAnnotation(BlogDetailAnno.class);

        if(blogDetailAnno==null){
            System.out.println("方法前调用：noAnno");
        }else{
            System.out.println("方法前调用："+blogDetailAnno.relativeId());
        }

        //调用整个目标函数执行
        Object obj = joinPoint.proceed();

        System.out.println("方法后调用");
        //执行函数后存储操作日志

        return obj;
    }
}
