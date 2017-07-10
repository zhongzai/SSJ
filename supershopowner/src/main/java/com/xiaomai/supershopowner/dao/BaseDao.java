package com.xiaomai.supershopowner.dao;


import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface BaseDao<T,KEY extends Serializable> {

    /**
     * 新增数据
     * @param statement
     * @param parameter
     * @return 影响行数
     */
    int insert(Object parameter) throws SQLException;

    /**
     * 删除数据
     * @param statement
     * @param parameter
     * @return 影响行数
     */
    int delete(Object parameter) throws SQLException;

    /**
     * 更新数据
     * @param statement
     * @param parameter
     * @return 影响行数
     */
    int update(Object parameter) throws SQLException;

    /**
     * 查询单条数据
     * @param statement
     * @param <T> 返回对象
     * @return
     */
    <T> T selectOne(String statement) throws SQLException;

    /**
     * 查询单条数据
     * @param statement
     * @param parameter
     * @param <T> 返回对象
     * @return
     */
    <T> T selectOne(String statement,Object parameter) throws SQLException;

    /**
     * 查询多条数据
     * @param statement
     * @param parameter
     * @param <T> 返回对象列表
     * @return
     */
    <T> List<T> selectList(Object parameter) throws SQLException;
    /**
     * 查询多条数据
     * @param statement
     * @param parameter
     * @param <T> 返回对象列表
     * @return
     */
    <T> List<T> selectList(String statement,Object parameter) throws SQLException;
}
