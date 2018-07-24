package com.ztjy;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.ztjy.xxxxserver.test.dubbo.BoyOpenServiceTest;
import com.ztjy.xxxxserver.test.http.BoyControllerTest;
/**
 *
        * @Title 触发所有单元测试类
        * @Description 在@SuiteClasses中填写需要测试的用例
		* @ClassName AllTest
		* @Copyright 2014-现在 厦门神州鹰掌通家园项目组
		* @author 胡耀忠 huyaozhong
		* @date 2018年5月3日 下午5:25:18
 *
 */
@RunWith(Suite.class)
@SuiteClasses({BoyOpenServiceTest.class,BoyControllerTest.class  })
public class AllTest {

}
