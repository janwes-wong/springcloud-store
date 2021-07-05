package com.mall.common.exception;

/**
 * @author Janwes
 * @version 1.0
 * @package com.janwes.common.exception
 * @date 2021/7/2 10:16
 * @description 基础异常
 */
public class BaseException extends RuntimeException {

    private Integer code;

    private String message;

    public BaseException(String message) {
        super(message);
        this.message = message;
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BaseException(Throwable cause, Integer code, String message) {
        super(cause);
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
