package com.root.cognition.modules.dao;

import java.util.List;
import java.util.Map;

import com.root.cognition.modules.entity.FileRecord;
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
public interface FileRecordDao {

	FileRecord get(Long id);
	
	List<FileRecord> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(FileRecord fileRecord);
	
	int update(FileRecord fileRecord);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
