package com.notejumping.modules.dao;

import com.notejumping.modules.entity.Dict;
import com.notejumping.system.persistence.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 字典表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 15:45:42
 */
@Mapper
@Repository("DictDao")
public interface DictDao extends BaseDao<Dict> {

}
