package com.huawei.global;

/**
 * 所有异常类型
 *
 * @author huxiao
 */
public enum ExceptionEnum {
    UNKOWN_ERROR(-1, "未知错误"),
    SUCCESS(0, "操作成功"),
    ADD_FAIL_EXISTS(1, "新增失败, 已存在"),

    ;

    /**
     * 编码
     */
    private Integer code;
    /**
     * 信息
     */
    private String message;

    /**
     * 构造函数
     *
     * @param code
     * @param message
     */
    private ExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

