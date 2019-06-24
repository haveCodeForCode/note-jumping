package com.root.cognition.modules.dao;


import com.root.cognition.common.persistence.BaseDao;
import com.root.cognition.modules.entity.Task;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author taoya
 * @date 2017-10-03 15:45:42
 */
@Mapper
@Repository("TaskDao")
public interface TaskDao extends BaseDao<Task> {

}
