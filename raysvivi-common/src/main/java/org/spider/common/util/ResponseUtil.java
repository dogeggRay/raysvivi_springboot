package org.spider.common.util;


/**
 * 请求结果组装工具类
 *
 */
public class ResponseUtil {
    /**
     * 请求成功编码
     */
    private final static String SUCCESS_CODE = "0";

    private final static String FAILED_CODE = "500";
    /**
     * 请求失败编码
     */
    private final static String ERROR_CODE = "-1";

    /**
     * 请求成功信息
     */
    private final static String SUCCESS_MSG = "请求成功";
    /**
     * 请求异常信息
     */
    private final static String ERROR_MSG = "请求发生异常，请联系管理员检查";

    public static boolean isSuccess(ResponseData<?> data) {
        return data != null && SUCCESS_CODE.equals(data.getCode());
    }

    /**
     * 成功
     *
     * @return ResponseData
     */
    public static <T> ResponseData<T> ok() {
        ResponseData<T> responseData = new ResponseData<>();
        responseData.setCode(SUCCESS_CODE);
        responseData.setMsg(SUCCESS_MSG);
        return responseData;
    }

    /**
     * 成功
     *
     * @param data T
     * @param <T>  T
     * @return ResponseData
     */
    public static <T> ResponseData<T> ok(T data) {
        ResponseData<T> responseData = ok();
        responseData.setData(data);
        return responseData;
    }

    /**
     * 失败
     *
     * @param code 错误编码
     * @return ResponseData
     */
    public static ResponseData<?> error(String code) {
        ResponseData<?> responseData = new ResponseData<>();
        responseData.setCode(code);
        responseData.setMsg(ERROR_MSG);
        return responseData;
    }

    /**
     * 失败
     *
     * @param code 错误编码
     * @param msg  错误信息
     * @return ResponseData
     */
    public static ResponseData<?> error(String code, String msg) {
        ResponseData<?> responseData = new ResponseData<>();
        responseData.setCode(code);
        responseData.setMsg(msg);
        return responseData;
    }

    /**
     * 失败
     *
     * @param ex IException
     * @return ResponseData
     */
    public static ResponseData<?> error(Exception ex) {
        ResponseData<?> responseData = new ResponseData<>();
        responseData.setCode(FAILED_CODE);
        responseData.setMsg(ex.getMessage());
        return responseData;
    }

    /**
     * 失败
     * code = -1
     *
     * @param msg 错误信息
     * @return ResponseData
     */
    public static ResponseData<?> errorMsg(String msg) {
        ResponseData<?> responseData = new ResponseData<>();
        responseData.setCode(ERROR_CODE);
        responseData.setMsg(msg);
        return responseData;
    }
}
