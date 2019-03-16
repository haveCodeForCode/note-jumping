package com.root.cognition.common.persistence;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * DAO支持类实现
 * @author worry
 */
@Mapper
public interface BaseDao<T> {
    /**
     * 获取单条数据（根据id）
     *
     * @param id 表主键
     * @return 实体类
     */
    T get(String id);

    /**
     * 获取单条数据（根据实体）
     *
     * @param entity 对应实体类
     * @return entity   实体类
     */
    T getByEntity(T entity);

    /**
     * 根据实体中内容，查询数据列表
     *
     * @param entity 对应实体类
     * @return list<t> 实体类列
     */
    List<T> findList(T entity);

    /**
     * 数量统计
     * @param entity 实体类
     * @return int   数量
     */
    int count(T entity);

    /**
     * 插入数据
     *
     * @param entity 实体类
     * @return 数量
     */
    int insert(T entity);

    /**
     * 更新数据
     *
     * @param entity
     * @return
     */
    int update(T entity);

    /**
     * 删除数据（一般为逻辑删除，更新del_flag字段为1）
     *
     * @param id
     * @return
     * @see public int delete(T entity)
     */
    int delete(String id);

    /**
     * 删除一系列数据
     * @param Ids
     * @return
     */
    int batchDelete(String[] Ids);

}