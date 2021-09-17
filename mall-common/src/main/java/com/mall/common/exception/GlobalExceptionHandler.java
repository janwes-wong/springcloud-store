package com.mall.common.exception;

import com.mall.common.model.Result;
import com.mall.common.model.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

/**
 * @author Janwes
 * @version 1.0
 * @package com.janwes.exception
 * @date 2021/6/4 13:39
 * @description 全局异常处理类
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 当系统出现Exception(最大异常)异常时执行
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result handlerException(Exception e) {
        log.error(">>> 服务器系统异常......", e);
        return Result.error();
    }

    @ExceptionHandler(BusinessException.class)
    public Result handlerBusinessException(BusinessException e) {
        log.error(">>> 业务异常......", e);
        return Result.errorMessage(e.getCode(), e.getMessage(), null);
    }

    @ExceptionHandler(BaseException.class)
    public Result handlerBaseException(BaseException e) {
        log.error(">>> 基础异常......", e);
        return Result.errorMessage(StatusCode.INVALID_PARAMS.code(), e.getMessage(), null);
    }

    /**
     * 一般请求参数验证异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handlerParamValidException(MethodArgumentNotValidException e) {
        String message = Objects.isNull(e.getBindingResult().getFieldError()) ? null : e.getBindingResult().getFieldError().getDefaultMessage();
        e.printStackTrace();
        return Result.errorMessage(StatusCode.INVALID_PARAMS.code(), message, null);
    }
}
