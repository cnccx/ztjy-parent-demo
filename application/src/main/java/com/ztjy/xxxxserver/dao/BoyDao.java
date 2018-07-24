package com.ztjy.xxxxserver.dao;



import com.ztjy.xxxxserver.model.BoyPo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 3.使用Dao接口的方法操作数据库

 findAll()  //查找所有对象,返回List<model>.

 findOne(ID id)  //根据主键查找对象,返回Model.

 save(T model)  //录入或修改对象,返回刚录入或修改的Model.

 delete(ID id)  //删除对象
 */
/**
 * /**
 * 
        *@author
 *
 */
public interface BoyDao extends JpaRepository<BoyPo,Integer> {

    /**
     * 通过年龄查询
     * @param age
     * @return
     */
    public List<BoyPo> findByAge(int age);
}
