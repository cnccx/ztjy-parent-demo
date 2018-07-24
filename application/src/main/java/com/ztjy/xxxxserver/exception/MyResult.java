package com.ztjy.xxxxserver.exception;

import com.ztjy.common.response.IResult;

/**
 * description: 业务返回结果类
 * Copyright: 厦门神州鹰软件科技有限公司
 * @author wangtonggui
 * @date 2018/3/9
 **/
public class MyResult implements IResult {
    private ServerNameExceptionEnum exceptionEnum;

    public MyResult(ServerNameExceptionEnum exceptionEnum) {
        this.exceptionEnum = exceptionEnum;
    }

    @Override
    public Integer getCode() {
        return exceptionEnum.getCode();
    }

    @Override
    public String getMessage() {
        return exceptionEnum.getMessage();
    }
}
