/**  
 * All rights Reserved, Designed By www.szy.cn
 * @Title  BoyOpenServiceTest.java   
 * @Package com.ztjy.dubbo
 * @author 掌通家园
 * @date   2018年5月3日 下午4:52:47   
 * @Copyright 2014-现在  All rights reserved. 厦门神州鹰软件科技有限公司
 */
package com.ztjy.xxxxserver.test.dubbo;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.ztjy.MyApplication;
import com.ztjy.xxxxserver.facade.BoyOpenService;

/**
 * @Title BoyOpenService的测试用例
 * @Description TODO 详细描述
 * @ClassName BoyOpenServiceTest
 * @Copyright 2014-现在 厦门神州鹰掌通家园项目组
 * @author 胡耀忠 huyaozhong
 * @date 2018年5月3日 下午4:52:47 
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyApplication.class)
@FixMethodOrder(MethodSorters.JVM)
public class BoyOpenServiceTest {

	@Autowired
	private BoyOpenService boyOpenService;
	
	/**
     * 测试用rpc接口
     * @return Words，测试用model
     */
	@Test
    public void sayHello(){
		System.out.println(this.boyOpenService.sayHello().getFirst());
	}
	
	
}
