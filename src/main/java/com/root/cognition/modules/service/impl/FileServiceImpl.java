package com.root.cognition.modules.service.impl;

import com.root.cognition.modules.dao.FileDao;
import com.root.cognition.modules.entity.File;
import com.root.cognition.modules.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;


/**
 * 文件服务类
 * @author 1122
 */
@Service
public class FileServiceImpl implements FileService {


    private FileDao fileDao;

    @Autowired
    public void setFileDao(FileDao fileDao) {
        this.fileDao = fileDao;
    }

    @Override
    public File get(Long id) {
        return fileDao.get(id);
    }

    @Override
    public List<File> list(Map<String, Object> map) {
        return fileDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return fileDao.count(map);
    }

    @Override
    public int save(File sysFile) {
        return fileDao.save(sysFile);
    }

    @Override
    public int update(File sysFile) {
        return fileDao.update(sysFile);
    }

    @Override
    public int remove(Long id) {
        return fileDao.remove(id);
    }

    @Override
    public int batchRemove(Long[] ids) {
        return fileDao.batchRemove(ids);
    }

    @Override
    public Boolean isExist(String url) {
        Boolean isExist = false;
        if (!StringUtils.isEmpty(url)) {
            String filePath = url.replace("/files/", "");
            filePath = bootdoConfig.getUploadPath() + filePath;
            File file = new File(filePath);
            if (file.exists()) {
                isExist = true;
            }
        }
        return isExist;
    }
}
