package com.ztjy.xxxxserver.controller;

import com.ztjy.xxxxserver.common.MyBatchPreparedStatementSetter;
import com.ztjy.common.utils.BaseRowMapper;
import com.ztjy.xxxxserver.model.BoyPo;
import com.ztjy.xxxxserver.service.impl.JdbcServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: parent-demo
 * @description: jdbc controller
 * @author: zhangjiaxing
 * @create: 2018-03-13 14:58
 **/
@RestController
@RequestMapping("/jdbc")
public class JdbcController {

    protected static Logger logger = LogManager.getLogger(JdbcController.class);

    @Autowired
    private JdbcServiceImpl jdbcService;

    @RequestMapping("/insert")
    public int insert(@RequestParam(name = "name") String name,
                          @RequestParam(name = "id") int id,
                          @RequestParam(name = "age") int age) {
        String sql = "insert into boy values(100, 100, 'zjx100')";
        int retNum = jdbcService.update(sql);
        logger.info("insert without args: " + sql + " " + retNum);

        sql = "insert into boy values (?, ?, ?)";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(id);
        params.add(age);
        params.add(name);
        retNum += jdbcService.update(sql, params.toArray());
        logger.info("insert with args: " + sql + " " + retNum);
        return retNum;
    }

    @RequestMapping("/update")
    public int update(@RequestParam(name = "name") String name, @RequestParam(name = "id") int id) {
        String sql = "update boy set name = 'buzhidao' where id = 1";
        int retNum = jdbcService.update(sql);
        logger.info("update without args: " + sql + " " + retNum);

        sql = "update boy set name = ? where id = ?";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(name);
        params.add(id);
        retNum += jdbcService.update(sql, params.toArray());
        logger.info("update with args: " + sql + " " + retNum);
        return retNum;
    }

    @RequestMapping("/delete")
    public int delete(@RequestParam(name = "name") String name, @RequestParam(name = "id") int id) {
        String sql = "delete from boy where name = 'zjx'";
        int retNum = jdbcService.update(sql);
        logger.info("delete without args: " + sql + " " + retNum);

        sql = "delete from boy where name = ? and id = ?";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(name);
        params.add(id);
        retNum += jdbcService.update(sql, params.toArray());
        logger.info("delete with args: " + sql + " " + retNum);
        return retNum;
    }

    @RequestMapping("queryone")
    public BoyPo queryOne(@RequestParam(name = "name") String name, @RequestParam(name = "id") int id) {
        // query without args
        String sql = "select * from boy where id = 1";
        Map<String, Object> result = jdbcService.queryOne(sql);
        logger.info("query one: " + result.toString());

        BoyPo boyPoRowMapper = jdbcService.queryObject(sql, new BaseRowMapper<>(BoyPo.class));
        logger.info("query one by row mapper: " + boyPoRowMapper.toString());

        sql = "select name from boy where id = 1";
        String retName = jdbcService.queryColumn(sql, String.class);
        logger.info("query one column: " + retName);

        sql = "select count(*)  from boy";
        Integer count = jdbcService.queryColumn(sql, Integer.class);
        logger.info("query count without args: " + count);

        // query with args
        sql = "select * from boy where id = ? and name = ?";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(id);
        params.add(name);
        Map<String, Object> resultArgs = jdbcService.queryOne(sql, params.toArray());
        logger.info("query one with args: " + resultArgs.toString());

        BoyPo boyPoArgsRowMapper = jdbcService.queryObject(sql, params.toArray(), new BaseRowMapper<>(BoyPo.class));
        logger.info("query one object with args by row mapper: " + boyPoArgsRowMapper.toString());

        sql = "select name from boy where id = ? and name = ?";
        String retNameArgs = jdbcService.queryColumn(sql, params.toArray(), String.class);
        logger.info("query one column with args: " + retNameArgs);

        sql = "select count(*)  from boy where id > ?";
        params.clear();
        params = new ArrayList<Object>();
        params.add(id);
        count = jdbcService.queryColumn(sql, params.toArray(), Integer.class);
        logger.info("query count with args: " + count);

        return boyPoArgsRowMapper;
    }

    @RequestMapping("querymulti")
    public List<BoyPo> queryMulti(@RequestParam(name = "id") int id) {
        // query without args
        String sql = "select * from boy where id > 1";

        List<Map<String, Object>> result = jdbcService.queryMulti(sql);
        logger.info("query multi: " + result.toString());

        List<BoyPo> boyPosRowMapper = jdbcService.queryMultiObject(sql, new BaseRowMapper<>(BoyPo.class));
        logger.info("query multi by row mapper: " + boyPosRowMapper.toString());

        sql = "select name from boy where id > 1";
        List<String> list = jdbcService.queryColumnList(sql, String.class);
        logger.info("query multi column: " + list.toString());

        // query with args
        sql = "select * from boy where id > ?";
        ArrayList<Object> params = new ArrayList<Object>();
        params.add(id);

        List<Map<String, Object>> resultArgs = jdbcService.queryMulti(sql, params.toArray());
        ArrayList<BoyPo> boyPosResult = new ArrayList<>();
        for (Map<String, Object> resMap:resultArgs) {
            BoyPo boyPo = new BoyPo();
            boyPo.setId(Integer.parseInt(resMap.get("id").toString()));
            boyPo.setName(resMap.get("name").toString());
            boyPo.setAge(Integer.parseInt(resMap.get("age").toString()));
            boyPosResult.add(boyPo);
        }
        logger.info("query multi with args: " + boyPosResult.toString());

        List<BoyPo> boyPosArgsRowMapper = jdbcService.queryMultiObject(sql, params.toArray(), new RowMapper<BoyPo>() {
            @Override
            public BoyPo mapRow(ResultSet rs, int rowNum) throws SQLException {
                BoyPo boyPo = new BoyPo();
                boyPo.setId(rs.getInt("id"));
                boyPo.setName(rs.getString("name"));
                boyPo.setAge(rs.getInt(2));
                return boyPo;
            }
        });
        logger.info("query multi by row mapper with args: " + boyPosArgsRowMapper.toString());

        sql = "select name from boy where id > ?";
        List<String> listArgs = jdbcService.queryColumnList(sql, params.toArray(), String.class);
        logger.info("query multi object with args: " + listArgs.toString());

        return boyPosArgsRowMapper;
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @RequestMapping("/procedure")
    public BoyPo procedure(@RequestParam(name = "id") int id) {
        /*  delimiter $$
        *   create or replace procedure sp_insert_table (in param1 Integer, in param2 varchar(256))
        *   begin
        *       insert into
        *           boy (age, name)
        *       values
        *           (param1, param2);
        *   end $$;
        *   delimiter ;
        *   call sp_insert_table(123,'test102')
        * */

        Object[] params = new Object[] {126, "test126"};
        jdbcService.update("{call sp_insert_table(?, ?)}", params);

       /* delimiter $$
        * CREATE PROCEDURE sp_select_table(in param1 Integer,out id_out Integer, out age_out Integer,out name_out varchar(256))
        * BEGIN
        *       select
        *           id,age,name into id_out,age_out,name_out
        *       from
        *           boy
        *       where
        *           id>param1;
        * END$$
        * delimiter ;
        * call sp_select_table(2,@id_out,@age_out,@name_out);
        * select @id_out,@age_out,@name_out;
        * */

       final int insertId = id;
       BoyPo boyPo = jdbcTemplate.execute(new CallableStatementCreator() {
           @Override
           public CallableStatement createCallableStatement(Connection con) throws SQLException {
               String storedProc = "{call sp_select_table(?,?,?,?)}";
               CallableStatement cs = con.prepareCall(storedProc);
               // 设置入参的值
               cs.setInt(1, insertId);
               // 注册输出值类型
               cs.registerOutParameter(2, Types.INTEGER);
               cs.registerOutParameter(3, Types.INTEGER);
               cs.registerOutParameter(4, Types.VARCHAR);
               return cs;
           }
       }, new CallableStatementCallback<BoyPo>() {
           @Override
           public BoyPo doInCallableStatement(CallableStatement callableStatement) throws SQLException, DataAccessException {
               BoyPo boyPo = new BoyPo();
               callableStatement.execute();//执行
               // 根据注册的位置获取到返回值
               boyPo.setId(callableStatement.getInt(2));
               boyPo.setAge(callableStatement.getInt(3));
               boyPo.setName(callableStatement.getString(4));
               return boyPo;
           }
       });
        logger.info("procedure one row 1: " + boyPo.toString());

        final String callProcedureSql = "{call sp_select_table(?,?,?,?)}";
        // 描述自定义函数占位符参数或命名参数类型
        List<SqlParameter> sqlParameters = new ArrayList<SqlParameter>();
        sqlParameters.add(new SqlParameter("param1", Types.INTEGER));
        sqlParameters.add(new SqlOutParameter("id_out", Types.INTEGER));
        sqlParameters.add(new SqlOutParameter("age_out", Types.INTEGER));
        sqlParameters.add(new SqlOutParameter("name_out", Types.VARCHAR));
        // outValues：通过SqlInOutParameter及SqlOutParameter参数定义的name来获取存储过程结果
        Map<String, Object> outValues = jdbcTemplate.call(new CallableStatementCreator() {
            @Override
            public CallableStatement createCallableStatement(Connection con) throws SQLException {
                CallableStatement cstmt = con.prepareCall(callProcedureSql);
                // 注册输出值类型
                cstmt.registerOutParameter(2, Types.INTEGER);
                cstmt.registerOutParameter(3, Types.INTEGER);
                cstmt.registerOutParameter(4, Types.VARCHAR);
                // 设置入参的值
                cstmt.setInt(1, insertId);
                return cstmt;
            }
        }, sqlParameters);
        logger.info("procedure one row 2: " + outValues.toString());

        /* delimiter $$
         * create procedure sp_select_all_table(in id_in Integer)
         * begin
         *      select
         *          *
         *      from
         *          boy
         *      where
         *          id>id_in;
         * end$$
         */

        List<Map<String, Object>> list = jdbcService.queryMulti("{call sp_select_all_table(?)}", new Object[]{id});
        logger.info("procedure all: " + list.toString());

//        List<Map<String, Object>> list1 = jdbcService.queryMulti("{call sp_select_multi_table(?)}", new Object[]{id});
//        logger.info("procedure all: " + list1.toString());

//        List<BoyPo> boyPos = jdbcTemplate.execute(new CallableStatementCreator() {
//            @Override
//            public CallableStatement createCallableStatement(Connection con) throws SQLException {
//                String storedProc = "{call sp_select_multi_table(?)}";
//                CallableStatement cs = con.prepareCall(storedProc);
//                cs.setInt(1, 20);
//                // 注册输出参数的类型
////                cs.registerOutParameter(2, Types.REF_CURSOR);
//                return cs;
//            }
//        }, new CallableStatementCallback<List<BoyPo>>() {
//            @Override
//            public List<BoyPo> doInCallableStatement(CallableStatement callableStatement) throws SQLException, DataAccessException {
//                List<BoyPo> boyPos1 = new ArrayList<>();
//                callableStatement.execute();
//                // 获取游标一行的值
////                ResultSet rs = (ResultSet) callableStatement.getObject(2);
//                ResultSet rs = (ResultSet) callableStatement.executeQuery();
//                while (rs.next()) {
//                    BoyPo boyPo = new BoyPo();
//                    boyPo.setId(rs.getInt("id_out"));
//                    boyPo.setAge(rs.getInt("age_out"));
//                    boyPo.setName(rs.getString("name_out"));
//                    boyPos1.add(boyPo);
//                }
//                rs.close();
//                return boyPos1;
//            }
//        });
//        logger.info("cursor res: " + boyPos.toString());

        return boyPo;
    }

    @RequestMapping("/batch")
    public int[] batch(@RequestParam(name = "id") int id) {
        // batch by sql
        String sql = "update boy set name = case id when 100 then 'test100' when 1 then'test1' end where id in(100, 1)";
        int[] ints = jdbcService.batchUpdate(sql);
        logger.info("batch num: " + Arrays.toString(ints));

        // batch by pss
        List<BoyPo> boyPos = new ArrayList<>();
        boyPos.add(new BoyPo(101, 29, "test101"));
        boyPos.add(new BoyPo(102, 30, "test102"));
        boyPos.add(new BoyPo(103, 31, "test103"));

        sql = "insert into boy " + "(age, name) VALUES (?, ?)";
        int[] ints1 = jdbcService.batchUpdate(sql, new MyBatchPreparedStatementSetter(boyPos));
        logger.info("batch pss num: " + Arrays.toString(ints1));

        // batch with args
        List<Object[]> deleteParams = new ArrayList<Object[]>();
        List<Object[]> insertParams = new ArrayList<Object[]>();
        for (BoyPo boyPo : boyPos) {
            String name = boyPo.getName();
            Integer age = boyPo.getAge();
            deleteParams.add(new Object[] { age, name });
            insertParams.add(new Object[] { age, name,});
        }
        sql = "delete from boy where age=? and name=?";
        int[] ints2 = jdbcService.batchUpdate(sql, deleteParams);
        logger.info("batch delete: " + Arrays.toString(ints2));
        sql = "insert into boy " + "(age, name) VALUES (?, ?)";
        int[] ints3 = jdbcService.batchUpdate(sql, insertParams);
        logger.info("batch insert: " + Arrays.toString(ints3));

        return ints1;
    }
}
