package org.spider.common.exception;

public enum RightExceptionEnum implements IException {
    /**
     * 权限异常
     */
    ERROR("400000", "权限异常"),
    /**
     * 未登录
     */
    TIMEOUT("400001", "未登录"),
    /**
     * 权限不足
     */
    DENY("400002", "权限不足"),
    ;

    private final String code;
    private final String msg;

    RightExceptionEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }
}
