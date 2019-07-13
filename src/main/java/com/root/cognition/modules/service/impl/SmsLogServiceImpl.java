package com.root.cognition.modules.service.impl;

import com.root.cognition.modules.dao.SmsLogDao;
import com.root.cognition.modules.entity.SmsLog;
import com.root.cognition.modules.service.SmsLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



/**
 *
 * @author taoya
 */
@Service
public class SmsLogServiceImpl implements SmsLogService {
	

	private SmsLogDao smsLogDao;

	@Autowired
	public void setSmsLogDao (SmsLogDao smsLogDao){
		this.smsLogDao=smsLogDao;
	}

	@Override
	public SmsLog get(Long id){
		return smsLogDao.get(id);
	}
	
	@Override
	public List<SmsLog> list(Map<String, Object> map){
		return smsLogDao.findList(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return smsLogDao.count(map);
	}
	
	@Override
	public int save(SmsLog smsLog){
		smsLog.preInsert();
		return smsLogDao.insert(smsLog);
	}
	
	@Override
	public int update(SmsLog smsLog){
		return smsLogDao.update(smsLog);
	}
	
	@Override
	public int remove(Long id){
		return smsLogDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return smsLogDao.batchRemove(ids);
	}
	
}
