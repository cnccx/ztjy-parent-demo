package com.ztjy.xxxxserver.service;

import com.ztjy.xxxxserver.model.BoyPo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * @author Administrator
 */
public interface BoyService {
    /**
     * 
     * 方法描述: 新增
     * @param boyPo
     * @return  
     * @author 胡耀忠 huyaozhong 2018年5月3日下午2:53:15
     * @since V2.0.0
     */
    BoyPo add(BoyPo boyPo);

    /**
     *
     * 方法描述: 新增redis锁测试
     * @param boyPo
     * @return
     * @author zhangjiaxing
     * @since V2.0.0
     */
    BoyPo addRedis(BoyPo boyPo);
    /**
     * 
     * 方法描述:删除 
     * @param id  
     * @author 胡耀忠 huyaozhong 2018年5月3日下午2:53:28
     * @since V2.0.0
     */
    void delete(Integer id);
    /**
     * 
     * 方法描述: 通过年纪查询
     * @param id
     * @return  
     * @author 胡耀忠 huyaozhong 2018年5月3日下午2:53:41
     * @since V2.0.0
     */
    List<BoyPo> findByAge(int id);
    /**
     * 
     * 方法描述:保存 
     * @param boyPo
     * @return  
     * @author 胡耀忠 huyaozhong 2018年5月3日下午2:54:23
     * @since V2.0.0
     */
    BoyPo save(BoyPo boyPo);
    /**
     * 
     * 方法描述: 通过ID获取单个实体
     * @param id
     * @return  
     * @author 胡耀忠 huyaozhong 2018年5月3日下午2:54:34
     * @since V2.0.0
     */
    BoyPo findOne(Integer id);
    /**
     * 
     * 方法描述: 查询所有
     * @return  
     * @author 胡耀忠 huyaozhong 2018年5月3日下午2:55:12
     * @since V2.0.0
     */
    List<BoyPo> findAll();
    /**
     * 
     * 方法描述: 分页查询
     * @param pageable
     * @return  
     * @author 胡耀忠 huyaozhong 2018年5月3日下午2:55:21
     * @since V2.0.0
     */
    Iterable<BoyPo> getBoyPageList(Pageable pageable);
    /**
     * 
     * 方法描述: 排序查询
     * @param sort
     * @return  
     * @author 胡耀忠 huyaozhong 2018年5月3日下午2:55:39
     * @since V2.0.0
     */
    Iterable<BoyPo> getBoySortList(Sort sort);
}
