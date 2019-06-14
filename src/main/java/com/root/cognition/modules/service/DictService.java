package com.root.cognition.modules.service;


import com.root.cognition.modules.entity.Dict;
import com.root.cognition.system.entity.User;

import java.util.List;
import java.util.Map;

/**
 * 字典表
 *
 * @author 1024
 * @date 2017-09-29 18:28:07
 */
public interface DictService {


    List<Dict> listType();


    String getName(String type, String value);

    /**
     * 获取爱好列表
     *
     * @param user
     * @return
     */
    List<Dict> getHobbyList(User user);

    /**
     * 获取性别列表
     *
     * @return
     */
    List<Dict> getSexList();

    /**
     * 根据type获取数据
     *
     * @param type
     * @return
     */
    List<Dict> listByType(String type);
    //*****************************************

    /**
     *
     * @param id
     * @return
     */
    Dict get(Long id);

    /**
     *
     * @param map
     * @return
     */
    Dict get(Map<String, Object> map);

    /**
     *
     * @param map
     * @return
     */
    List<Dict> list(Map<String, Object> map);

    /**
     *
     *
     * @param map
     * @return
     */
    int count(Map<String, Object> map);

    /**
     *
     * @param dict
     * @return
     */
    int save(Dict dict);

    /**
     *
     * @param dict
     * @return
     */
    int update(Dict dict);

    /**
     *
     * @param id
     * @return
     */
    int remove(Long id);

    /**
     *
     * @param ids
     * @return
     */
    int batchRemove(Long[] ids);

}
