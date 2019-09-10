package com.root.cognition.modules.dao;


import com.root.cognition.system.persistence.BaseDao;
import com.root.cognition.modules.entity.Dict;
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
