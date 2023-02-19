package com.sh.eas.base.exception;

import com.sh.eas.base.response.Result;
import com.sh.eas.base.response.ResultEnum;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@RestControllerAdvice(basePackages = "com.sh.eas")
public class EasExceptionAdvice {

    @ExceptionHandler(EasBusinessException.class)
    public Result<?> handleBusinessException(EasBusinessException ex) {
        return Result.failed(ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public Result<?> handleBusinessException(RuntimeException ex) {
        return Result.failed(ResultEnum.SYSTEM_ERROR.getCode(), ex);
    }

    @ExceptionHandler(SQLException.class)
    public Result<?> handleBusinessException(SQLException ex) {
        return Result.failed(ResultEnum.DATABASE_ERROR.getCode(), ex);
    }

}
