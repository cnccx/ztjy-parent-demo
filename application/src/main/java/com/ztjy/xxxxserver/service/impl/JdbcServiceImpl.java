package com.ztjy.xxxxserver.service.impl;

import com.ztjy.common.utils.JdbcUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @program: parent-demo
 * @description: jdbc operation
 * @author: zhangjiaxing
 * @create: 2018-03-13 14:41
 **/
@Service
public class JdbcServiceImpl {

    @Autowired
    private JdbcUtils jdbcUtils;

    public <T> T queryColumn(String sql, Class<T> requiredType) throws DataAccessException {
        return jdbcUtils.queryColumn(sql, requiredType);
    }

    public <T> T queryColumn(String sql, Object[] objects, Class<T> requiredType) throws DataAccessException {
        return jdbcUtils.queryColumn(sql, objects, requiredType);
    }

    public Map<String, Object> queryOne(String sql) throws DataAccessException {
        return jdbcUtils.queryOne(sql);
    }

    public Map<String, Object> queryOne(String sql, Object[] objects) throws DataAccessException {
        return jdbcUtils.queryOne(sql, objects);
    }

    public <T> T queryObject(String sql, RowMapper<T> rowMapper) throws DataAccessException {
        return jdbcUtils.queryForObject(sql, rowMapper);
    }

    public <T> T queryObject(String sql, Object[] objects, RowMapper<T> rowMapper) throws DataAccessException {
        return jdbcUtils.queryForObject(sql, objects, rowMapper);
    }

    public <T> List<T> queryColumnList(String sql, Class<T> requiredType) throws DataAccessException {
        return jdbcUtils.queryColumnList(sql, requiredType);
    }

    public <T> List<T> queryColumnList(String sql, Object[] objects, Class<T> requiredType) throws DataAccessException {
        return jdbcUtils.queryColumnList(sql, objects, requiredType);
    }

    public List<Map<String, Object>> queryMulti(String sql) throws DataAccessException {
        return jdbcUtils.query(sql);
    }

    public List<Map<String, Object>> queryMulti(String sql, Object[] objects) throws DataAccessException {
        return jdbcUtils.query(sql, objects);
    }

    public <T> List<T> queryMultiObject(String sql, RowMapper<T> rowMapper) throws DataAccessException {
        return jdbcUtils.queryForList(sql, rowMapper);
    }

    public <T> List<T> queryMultiObject(String sql, Object[] objects, RowMapper<T> rowMapper) throws DataAccessException {
        return jdbcUtils.queryForList(sql, objects, rowMapper);
    }

    public Integer update(String sql) throws DataAccessException {
        return jdbcUtils.update(sql);
    }

    public Integer update(String sql, Object[] objects) throws DataAccessException {
        return jdbcUtils.update(sql, objects);
    }

    public int[] batchUpdate(String sql) throws DataAccessException {
        return jdbcUtils.batchUpdate(sql);
    }

    public int[] batchUpdate(String sql, List<Object[]> batchArgs) throws DataAccessException {
        return jdbcUtils.batchUpdate(sql, batchArgs);
    }

    public int[] batchUpdate(String sql, BatchPreparedStatementSetter pss) throws DataAccessException {
        return jdbcUtils.batchUpdate(sql, pss);
    }
}
