package com.ztjy.xxxxserver.common;

import com.ztjy.xxxxserver.model.BoyPo;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * @program: parent-demo
 * @description:
 * @author: zhangjiaxing
 * @create: 2018-03-14 16:20
 **/
public class MyBatchPreparedStatementSetter implements BatchPreparedStatementSetter{
    private List list;

    public MyBatchPreparedStatementSetter(List list){
        this.list = list;
    }

    @Override
    public void setValues(PreparedStatement ps, int i) throws SQLException {
        BoyPo boyPo = (BoyPo)list.get(i);
        ps.setInt(1, boyPo.getAge());
        ps.setString(2, boyPo.getName());
    }

    @Override
    public int getBatchSize() {
        return list.size();
    }
}
