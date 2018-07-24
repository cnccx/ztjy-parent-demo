/**
 * All rights Reserved, Designed By www.szy.cn
 *
 * @Title RedisUtilsTest.java
 * @Package com.ztjy.xxxx.parent.test.common.utils
 * @author 掌通家园
 * @date 2018年5月25日 下午4:17:57
 * @Copyright 2014-现在  All rights reserved. 厦门神州鹰软件科技有限公司
 */
package com.ztjy.xxxxserver.parent.test.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import lombok.extern.log4j.Log4j2;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import com.ztjy.MyApplication;
import com.ztjy.common.utils.RedisUtils;

/**
 * @Title TODO 类描述
 * @Description TODO 详细描述
 * @ClassName RedisUtilsTest
 * @Copyright 2014-现在 厦门神州鹰掌通家园项目组
 * @author 胡耀忠 huyaozhong
 * @date 2018年5月25日 下午4:17:57 
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.JVM)
@Log4j2
public class RedisUtilsTest {

    @Autowired
    private RedisUtils redisUtils;

    @Test
    public void set() {
        redisUtils.set("mystring", 1);
    }


    @Test
    public void expire() {
        redisUtils.expire("mystring", 1000L);
    }

    @Test
    public void setWithExpireTime() {
        redisUtils.set("mystring2", 1, 1000L);
    }

    @Test
    public void remove() {
        redisUtils.remove("aaa");
    }

    @Test
    public void exists() {
        redisUtils.exists("as");
    }

    @Test
    public void get() {
        Integer i = (Integer) redisUtils.get("mystring");
        System.out.println(i);
    }

    @Test
    public void mGet() {
        final List<Object> keys = new ArrayList<Object>();
        keys.add("myhash");
        keys.add("mystring");
        List<Object> list = redisUtils.mGet(keys);
        System.out.println(list);
    }

    @Test
    public void pipeline() {
        final List<String> keys = new ArrayList<>();
        keys.add("myhash");
        keys.add("mystring");
        List<Object> list = redisUtils.pipeline(keys);
        System.out.println(list);
    }

    @Test
    public void hmSet() {
        redisUtils.hmSet("myhash", "state", 1);
    }


    @Test
    public void hmInc() {
        redisUtils.hmInc("myhash", "state", 1L);
    }

    @Test
    public void hmGet() {
        Integer i = (Integer) redisUtils.hmGet("myhash", "state");
        System.out.println(i);
    }

    @Test
    public void lPush() {
        redisUtils.lPush("mylist", "1111");
        redisUtils.lPush("mylist", 22222);
    }

    @Test
    public void lPushAll() {
        List<Object> values = new ArrayList<Object>();
        values.add("myhash");
        values.add(123456);
        values.add(123456);
        redisUtils.lPushAll("mylist2", values);
    }

    @Test
    public void lRange() {
        List<Object> values = redisUtils.lRange("mylist2", 0, 1);
        for (Object o : values) {
            System.out.println(o.toString());
        }
    }

    @Test
    public void lrem() {
        redisUtils.lrem("mylist2", 1, 123456);
    }

    @Test
    public void add() {
        redisUtils.add("myset", 123456);
    }

    @Test
    public void setMembers() {
        Set<Object> objects = redisUtils.setMembers("myset");
        System.out.println(objects);
    }

    @Test
    public void zAdd() {
        redisUtils.zAdd("myzset", 123456, 1.0);
        redisUtils.zAdd("myzset", 123457, 2.0);
    }

    @Test
    public void rangeByScore() {
        Set<Object> obj = redisUtils.rangeByScore("myzset", 1.0, 2.0);
        System.out.println(obj);
    }

    @Test
    public void reverseRangeByScoreWithScores() {
        Set<ZSetOperations.TypedTuple<Object>> obj = redisUtils.reverseRangeByScoreWithScores("myzset", 1.0, 2.0, 0, 1);
        System.out.println(obj);
    }

    @Test
    public void zRem() {
        Long i = redisUtils.zRem("myzset", 123456);
        System.out.println(i);
    }

    @Test
    public void increment() {
        redisUtils.increment("mynum");
    }

    @Test
    public void decrement() {
        redisUtils.decrement("mynum");
    }
}
