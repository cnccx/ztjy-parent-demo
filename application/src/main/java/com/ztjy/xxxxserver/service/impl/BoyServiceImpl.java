package com.ztjy.xxxxserver.service.impl;

import com.ztjy.common.utils.RedisUtils;
import com.ztjy.redis.DistributedLock;
import com.ztjy.xxxxserver.dao.BoyDao;
import com.ztjy.xxxxserver.service.BoyService;
import com.ztjy.xxxxserver.exception.ServerNameException;
import com.ztjy.xxxxserver.exception.ServerNameExceptionEnum;
import com.ztjy.xxxxserver.model.BoyPo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
@Service
@Log4j2
public class BoyServiceImpl implements BoyService, Serializable {

    @Autowired(required = false)
    private BoyDao boyDao;

    @Autowired(required = false)
    private RedisUtils redisUtils;

    @Override
    public BoyPo add(BoyPo boyPo) {
        return boyDao.save(boyPo);
    }

    @Override
    public void delete(Integer id) {
        boyDao.delete(id);
    }

    @Override
    public List<BoyPo> findByAge(int id) {
        return boyDao.findByAge(id);
    }

    @Override
    public BoyPo save(BoyPo boyPo) {
        return boyDao.save(boyPo);
    }

    @Override
    @Cacheable(value = "usercache", key = "'selectUserById:id_'+#id")
    public BoyPo findOne(Integer id) {
        // 简单校验id
        if (id < 0) {
            throw new ServerNameException(ServerNameExceptionEnum.BOY_AGE_ERROR);
        }
        return boyDao.findOne(id);
    }

    @Override
    public List<BoyPo> findAll() {
        return boyDao.findAll();
    }

    @Override
    public Iterable<BoyPo> getBoyPageList(Pageable pageable){ return boyDao.findAll(pageable);}

    @Override
    public Iterable<BoyPo> getBoySortList(Sort sort) {
        return boyDao.findAll(sort);
    }

    @Override
    @DistributedLock(value = "#boyPo.name", expireTime = 6)
    public BoyPo addRedis(BoyPo boyPo) {
        this.redisUtils.add("boyPo:1",boyPo);
        this.redisUtils.set("boySet:1",boyPo);
        this.redisUtils.zAdd("boyZset:1",boyPo,100);
        List<BoyPo> list = new ArrayList<>();
        list.add(boyPo);
        this.redisUtils.lPush("boyList:1",list);
        return boyPo;
    }
}
