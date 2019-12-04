package com.notejumping.modules.dao;

import com.notejumping.modules.entity.Task;
import com.notejumping.system.persistence.BaseDao;
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
