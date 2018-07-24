package com.ztjy.xxxxserver.exception;

/**
 * <p>Description: Demo工程自定义异常类型</p>
 *
 * <p>Copyright:  2018-2018 北京掌通未来科技有限公司.All rights reserved.</p>
 *
 * @author wangtonggui
 *
 * @date 2018/5/7
 **/
public enum ServerNameExceptionEnum {
    /**
     * 测试用，id错误
     */
    BOY_ID_ERROR(1380001, "测试用，id错误"),
    /**
     * 测试用，年龄错误
     */
    BOY_AGE_ERROR(1380002, "测试用，年龄错误");

    private int code;
    private String message;

    ServerNameExceptionEnum(int code, String msg) {
        this.code = code;
        this.message = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "MyResultEnum{" +
                "code=" + code +
                ", msg='" + message + '\'' +
                '}';
    }
}
