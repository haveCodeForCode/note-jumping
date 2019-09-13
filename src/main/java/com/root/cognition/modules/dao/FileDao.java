package com.root.cognition.modules.dao;

import java.util.List;
import java.util.Map;

import com.root.cognition.modules.entity.File;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 文件上传
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 15:45:42
 */
@Mapper
@Repository("FileDao")
public interface FileDao {

	File get(Long id);
	
	List<File> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(File file);
	
	int update(File file);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
