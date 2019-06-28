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
    UPDATE_FAIL_NOT_EXISTS(2, "更新失败, id为空或不存在"),
    UPLOAD_FAILED_FILE_EMPTY(3, "上传失败, 文件为空"),
    ADD_FAILED_DEPARTMENT_NOT_EXISTS(4, "新增失败, 部门不存在"),
    ADD_FAILED_NAME_DUP(5, "新增失败, 名称重复"),
    UPDATE_FAILED_NAME_DUP(6, "更新失败, 名称重复"),

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

