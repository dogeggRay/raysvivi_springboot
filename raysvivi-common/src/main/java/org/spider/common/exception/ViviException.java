package org.spider.common.exception;

import org.spider.common.constant.ExceptionConstant;

public class ViviException extends RuntimeException {
    /**
     * 异常编号
     */
    private final String code;
    /**
     * 异常信息
     */
    private final String msg;

    public ViviException(IException exception) {
        super(exception.getMsg());
        this.code = exception.getCode();
        this.msg = exception.getMsg();
    }

    /**
     * 错误码：-1
     *
     * @param msg 错误信息
     */
    public ViviException(String msg) {
        super(msg);
        this.code = ExceptionConstant.Error.code;
        this.msg = msg;
    }

    public String getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }
}
