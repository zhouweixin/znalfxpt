package com.huawei.global;

/**
 * 定义运行时异常
 *
 * @author huxiao
 */
public class ExceptionUtil extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private Integer code;

    private ExceptionUtil(String message, int code){
        super(message);
        this.code = code;
    }

    public static ExceptionUtil newInstance(ExceptionEnum exceptionEnum) {
        return new ExceptionUtil(exceptionEnum.getMessage(), exceptionEnum.getCode());
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
