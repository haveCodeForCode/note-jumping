package com.root.cognition.modules.service;

import com.root.cognition.modules.entity.SmsLog;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 短息日志表
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-06-15 15:54:43
 */
public interface SmsLogService {

    SmsLog get(Long id);

    List<SmsLog> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(SmsLog smsLog);

    int update(SmsLog smsLog);

    int remove(Long id);

    int batchRemove(Long[] ids);
}
