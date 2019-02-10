package com.root.cognition.system.persistence;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * DAO支持类实现
 *
 * @param <T>
 * @author
 * @version 2014-05-16
 */
@Mapper
public interface CrudDao<T> extends BaseDao {

    /**
     * 获取单条数据
     *
     * @param id
     * @return
     */
    public T get(String id);

    /**
     * 获取单条数据
     *
     * @param entity
     * @return
     */
    public T getByEntity(T entity);

    /**
     * 根据实体中内容，查询数据列表
     *
     * @param entity
     * @return
     */
    public List<T> findList(T entity);

    /**
     * 插入数据
     *
     * @param entity
     * @return
     */
    public int insert(T entity);

    /**
     * 更新数据
     *
     * @param entity
     * @return
     */
    public int update(T entity);

    /**
     * 删除数据（一般为逻辑删除，更新del_flag字段为1）
     *
     * @param id
     * @return
     * @see public int delete(T entity)
     */
    public int delete(String id);

    /**
     * 删除数据（一般为逻辑删除，更新del_flag字段为1）
     *
     * @param entity
     * @return
     */
    public int delete(T entity);


}