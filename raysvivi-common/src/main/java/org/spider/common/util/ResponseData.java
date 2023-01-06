package org.spider.common.util;

import lombok.Data;

import java.io.Serializable;

/**
 * @author TJW
 */
@Data
public class ResponseData<T> implements Serializable {
    /**
     * 状态码
     */
    private String code;
    /**
     * 消息
     */
    private String msg;
    /**
     * 返回的数据
     */
    private T data;
}
