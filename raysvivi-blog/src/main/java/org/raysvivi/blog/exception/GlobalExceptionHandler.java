package org.raysvivi.blog.exception;

import lombok.extern.slf4j.Slf4j;
import org.spider.common.exception.ViviException;
import org.spider.common.util.ResponseData;
import org.spider.common.util.ResponseUtil;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 自定义异常
     *
     * @param req HttpServletRequest
     * @param e   NlaException
     * @return ResponseData
     */
    @ExceptionHandler(value = ViviException.class)
    public ResponseData<?> nlaException(HttpServletRequest req, ViviException e) {
        log.error("GbExceptionHandler access");
        return ResponseUtil.error(e.getCode(), e.getMsg());
    }
}
