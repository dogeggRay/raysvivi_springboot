package org.spider.common.exception;

public interface IException {
    /**
     * 获取异常编码
     * @return String
     */
    String getCode();

    /**
     * 获取异常信息
     * @return String
     */
    String getMsg();
}
