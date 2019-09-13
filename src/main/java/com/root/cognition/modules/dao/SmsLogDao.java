package com.root.cognition.modules.dao;

import com.root.cognition.modules.entity.SmsLog;
import com.root.cognition.system.persistence.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 短息日志表
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-06-15 15:54:43
 */
@Mapper
@Repository("SmsLogDao")
public interface SmsLogDao extends BaseDao<SmsLog> {

}
