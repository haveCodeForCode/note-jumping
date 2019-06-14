package com.root.cognition.modules.service.impl;


import com.root.cognition.common.until.Query;
import com.root.cognition.modules.dao.DictDao;
import com.root.cognition.modules.entity.Dict;
import com.root.cognition.modules.service.DictService;
import com.root.cognition.system.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author 1024
 */
@Service
public class DictServiceImpl implements DictService {


    private DictDao dictDao;

    @Autowired
    public void setDictDao(DictDao dictDao) {
        this.dictDao = dictDao;
    }

    @Override
    public Dict get(Long id) {
        return dictDao.get(id);
    }

    @Override
    public Dict get(Map<String, Object> map) {
        return null;
    }

    @Override
    public List<Dict> list(Map<String, Object> map) {
        return dictDao.findList(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return dictDao.count(map);
    }

    @Override
    public int save(Dict dict) {
        return dictDao.insert(dict);
    }

    @Override
    public int update(Dict dict) {
        return dictDao.update(dict);
    }

    @Override
    public int remove(Long id) {
        return dictDao.remove(id);
    }

    @Override
    public int batchRemove(Long[] ids) {
        return dictDao.batchRemove(ids);
    }

    @Override

    public List<Dict> listType() {
        return dictDao.listType();
    }

    @Override
    public String getName(String type, String value) {
        Map<String, Object> param = new HashMap<String, Object>(16);
        param.put("type", type);
        param.put("value", value);
        String rString = dictDao.findList(param).get(0).getName();
        return rString;
    }

    @Override
    public List<Dict> getHobbyList(User user) {
        Map<String, Object> param = Query.withDelFlag();
        param.put("type", "hobby");
        List<Dict> hobbyList = dictDao.findList(param);

//        if (StringUtils.isNotEmpty(user.getHobby())) {
//            String userHobbys[] = user.getHobby().split(";");
//            for (String userHobby : userHobbys) {
//                for (Dict hobby : hobbyList) {
//                    if (!Objects.equals(userHobby, hobby.getId().toString())) {
//                        continue;
//                    }
//                    hobby.setRemarks("true");
//                    break;
//                }
//            }
//        }

        return hobbyList;
    }

    @Override
    public List<Dict> getSexList() {
        Map<String, Object> param = new HashMap<>(16);
        param.put("type", "sex");
        return dictDao.findList(param);
    }

    @Override
    public List<Dict> listByType(String type) {
        Map<String, Object> param = new HashMap<>(16);
        param.put("type", type);
        return dictDao.findList(param);
    }

}
