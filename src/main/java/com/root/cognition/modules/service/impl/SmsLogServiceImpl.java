package com.root.cognition.modules.service.impl;

import com.root.cognition.common.config.Constant;
import com.root.cognition.modules.config.AlibabaSms;
import com.root.cognition.modules.dao.SmsLogDao;
import com.root.cognition.modules.entity.SmsLog;
import com.root.cognition.modules.service.SmsLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger logger = LoggerFactory.getLogger(SmsLogService.class);

	private SmsLogDao smsLogDao;

	private AlibabaSms alibabaSms;

	@Autowired
	public void setSmsLogDao (SmsLogDao smsLogDao){
		this.smsLogDao=smsLogDao;
	}

	@Autowired
	public void setAlibabaSms(AlibabaSms alibabaSms) {
		this.alibabaSms = alibabaSms;
	}


	@Override
	public int snedSmsMessage(String moudle, String mobile, String signName, String templateCode, String[] keyword, String outId) {
		try {
			//通过接口发送短信回传短信记录
			alibabaSms.setConfigureAlibaba();
			SmsLog smsLog = alibabaSms.sendMesage(moudle, mobile, signName, templateCode, keyword, outId);
			if (smsLog != null) {
				smsLog.preInsert();
				//插入短信日志
				smsLogDao.insert(smsLog);
				if (Constant.OK.equals(smsLog.getSmsReturnCode())){
					return Constant.INT_ONE;
				}else{
					return Constant.INT_ZERO;
				}
			}
			return Constant.INT_ZERO;
		} catch (InterruptedException e) {
			//报错抛出异常
			e.printStackTrace();
			System.err.println(e.getMessage());
			//返回信息
			return Constant.INT_ZERO;
		}
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
