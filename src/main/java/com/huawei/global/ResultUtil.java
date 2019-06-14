package com.huawei.global;

/**
 * 统一返回工具类
 *
 * @author zhouweixin
 */
public class ResultUtil {
    /**
     * 成功
     *
     * @param data
     * @return
     */
    public static <T> Result<T> success(T data) {
        return new Result<T>(ExceptionEnum.SUCCESS.getCode(), ExceptionEnum.SUCCESS.getMessage(), data);
    }

    /**
     * 成功
     *
     * @return
     */
    public static <T> Result<T> success() {
        return success(null);
    }

    /**
     * 异常
     *
     * @param exception
     * @return
     */
    public static <T> Result<T> error(ExceptionUtil exception) {
        return new Result<T>(exception.getCode(), exception.getMessage());
    }

    /**
     * 异常
     *
     * @param message
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(String message) {
        return new Result<T>(-2, message);
    }
}
