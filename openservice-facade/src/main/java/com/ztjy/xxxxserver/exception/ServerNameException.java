package com.ztjy.xxxxserver.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>Description: Demo工程异常类（类的名称按照server名称命名） </p>
 *
 * <p>Copyright:  2018-2018 北京掌通未来科技有限公司.All rights reserved.</p>
 *
 * @author wangtonggui
 *
 * @date 2018/5/7
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class ServerNameException extends RuntimeException {
    private ServerNameExceptionEnum exceptionEnum;

    public ServerNameException(ServerNameExceptionEnum exceptionEnum) {
        this.exceptionEnum = exceptionEnum;
    }
}
