package com.root.cognition.modules.service;


import com.root.cognition.modules.entity.File;

import java.util.List;
import java.util.Map;

/**
 * 文件上传
 * 
 * @author 1122
 * @date 2017-09-19 16:02:20
 */
public interface FileService {
	
	File get(Long id);
	
	List<File> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(File sysFile);
	
	int update(File sysFile);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	/**
	 * 判断一个文件是否存在
	 * @param url File中存的路径
	 * @return
	 */
    Boolean isExist(String url);
}
