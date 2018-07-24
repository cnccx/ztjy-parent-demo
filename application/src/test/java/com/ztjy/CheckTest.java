package com.ztjy;


import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author 胡耀忠 huyaozhong
 * @Title 校验dubbo接口都写了单元测试用例
 * @Description facadeBasePackage和testBasePackage需要正确填写
 * @ClassName CheckTest
 * @Copyright 2014-现在 厦门神州鹰掌通家园项目组
 * @date 2018年5月3日 下午5:26:40
 */
public class CheckTest {
    /**
     * 接口所在基础包名
     */
    private final String facadeBasePackage = "com.ztjy.xxxxserver";
    /**
     * 单元测试类所在基础包
     */
    private final String testBasePackage = "com.ztjy.xxxxserver";

    @Test
    public void checkTest() {
        List<String> facadeMethods = new ArrayList<>();
        List<String> testMethods = new ArrayList<>();
        ClassScanner scanner = new ClassScanner();
        Set<Class> facadeList = scanner.doScan(facadeBasePackage);
        Set<Class> testList = scanner.doScan(testBasePackage);
        for (Class c : facadeList) {
            if (c.getName().endsWith("Facade")) {
                Method[] methods = c.getDeclaredMethods();
                //Loop through the methods and print out their names
                for (Method method : methods) {
                    facadeMethods.add(method.getName());
                }
            }
        }

        for (Class c : testList) {
            if (c.getName().endsWith("Test")) {
                Method[] methods = c.getDeclaredMethods();
                //Loop through the methods and print out their names
                for (Method method : methods) {
                    testMethods.add(method.getName());
                }
            }
        }

        facadeMethods.removeAll(testMethods);

        if (!facadeMethods.isEmpty()) {
            Assert.fail("还有" + facadeMethods.size() + "个接口方法未写单元测试用例.具体如下:" + String.join(",", facadeMethods));
        } else {
            System.out.println("★★★★★★恭喜，所有接口方法均有单元测试用例!★★★★★★");
        }


    }

}
