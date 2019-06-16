package com.huawei.global;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandle {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result<Object> handle(Exception e) {
        if (e instanceof ExceptionUtil) {
            // 自定义异常
            return ResultUtil.error((ExceptionUtil) e);
        }  else {
            logger.error("【系统异常】 {}", e.getMessage());
            return ResultUtil.error(e.getMessage());
        }
    }
}
