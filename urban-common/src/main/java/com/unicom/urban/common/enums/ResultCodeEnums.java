package com.unicom.urban.common.enums;

public enum ResultCodeEnums {

    SUCCESS(0, "成功"),
    // 1开头的为用户端错误
    AUTHENTICATION_FAIL(10001, "账号或密码错误");

    private final Integer code;

    private final String message;

    ResultCodeEnums(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }


}
