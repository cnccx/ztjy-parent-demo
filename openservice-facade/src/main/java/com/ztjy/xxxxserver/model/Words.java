package com.ztjy.xxxxserver.model;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>Description: 测试实体类</p>
 *
 * <p>Copyright:  2018-2018 北京掌通未来科技有限公司.All rights reserved.</p>
 *
 * @author wangtonggui
 *
 * @date 2018/5/31
 **/
@Data
public class Words implements Serializable {
    private String first;

    private String second;
}
