package com.sh.eas.base.response;

public enum ResultEnum implements IResult {

    SUCCESS("0000", "接口调用成功"),
    FAILED("1000", "接口调用失败"),
    VALIDATE_FAILED("2000", "参数校验失败"),
    DATABASE_ERROR("9998", "数据库异常"),
    SYSTEM_ERROR("9999", "系统异常");

    ResultEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private String code;

    private String message;

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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
