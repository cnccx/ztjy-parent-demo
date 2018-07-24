package com.ztjy.xxxxserver.exception;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ztjy.common.response.ResultBody;
import com.ztjy.common.response.ResultEnum;

/**
 * <p>Description: 业务相关统一异常处理类</p>
 *
 * <p>Copyright:  2018-2018 北京掌通未来科技有限公司.All rights reserved.</p>
 *
 * @author wangtonggui
 *
 * @date 2018/5/7
 **/
@ControllerAdvice
public class MyExceptionHandler {
    /**
     * 捕获自定义的异常实体
     * @param e 异常实体
     * @return response的统一实体
     */
    @ExceptionHandler(ServerNameException.class)
    @ResponseBody
    public ResultBody myException(ServerNameException e) {
        switch (e.getExceptionEnum()) {
            case BOY_ID_ERROR:
            // 业务处理
            default:
        }
        return ResultBody.errorBody(new MyResult(e.getExceptionEnum()));
    }
    
    /**
     * 
     * 方法描述: API参数校验异常统一处理
     * @param e
     * @return  
     * @author 胡耀忠 huyaozhong 2018年5月24日上午10:51:38
     * @since V6.0
     */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public ResultBody notValidException(MethodArgumentNotValidException e) {

		// 默认赋值 参数异常
		ResultBody body = ResultBody.errorBody(ResultEnum.OPERATION_FAILD_WITHOUT_NOTE);

		// 获取所有校验出错的属性的默认提示语
		List<String> messages = Lists.newArrayList();
		for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
			messages.add(fieldError.getDefaultMessage());
		}

		// 重置提示语
		body.setMessage(StringUtils.join(messages, ","));

		return body;
	}
    
    
}
