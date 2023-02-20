package com.sh.eas.core.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {

    @JsonProperty("Code")
    private String code;

    @JsonProperty("Message")
    private String message;

    @JsonProperty("Data")
    private T data;

    public static <T> Result<T> success(T dto) {
        return new Result<>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), dto);
    }

    public static <T> Result<T> success(String message, T dto) {
        return new Result<>(ResultEnum.SUCCESS.getCode(), message, dto);
    }

    public static Result<?> failed() {
        return newInstance(ResultEnum.FAILED.getCode(), ResultEnum.FAILED.getMessage(), null);
    }

    public static Result<?> failed(String message) {
        return newInstance(ResultEnum.FAILED.getCode(), message, null);
    }

    public static Result<?> failed(String code, String message) {
        return newInstance(code, message, null);
    }

    public static Result<?> failed(String code, Exception ex) {
        return newInstance(code, ex.getMessage(), null);
    }

    public static Result<?> failed(IResult result) {
        return newInstance(result.getCode(), result.getMessage(), null);
    }

    public static <T> Result<T> newInstance(String code, String message, T data) {
        return new Result<>(code, message, data);
    }
}
