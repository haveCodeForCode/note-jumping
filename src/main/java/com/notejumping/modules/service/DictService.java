package com.notejumping.modules.service;



import com.notejumping.modules.entity.Dict;
import com.notejumping.system.persistence.Tree;


import java.util.List;
import java.util.Map;

/**
 * 字典表
 *
 * @author 1024
 * @date 2017-09-29 18:28:07
 */
public interface DictService {

    Tree<Dict> getDictTree();

    /**
     * 根据名称
     * @param type
     * @param value
     * @return
     */
    String getName(String type, String value);

    /**
     * 根据type获取数据
     *
     * @param type
     * @return
     */
    List<Dict> listByType(String type);

    //*****************************************

    /**
     * 根据id获取
     * @param id
     * @return
     */
    Dict get(Long id);

    /**
     * 根据条件获取
     * @param map
     * @return
     */
    Dict get(Map<String, Object> map);

    /**
     * 根据条件查询
     * @param map
     * @return
     */
    List<Dict> list(Map<String, Object> map);

    /**
     * 分页，统计数量
     * @param map
     * @return
     */
    int count(Map<String, Object> map);

    /**
     * 插入
     * @param dict
     * @return
     */
    int save(Dict dict);

    /**
     * 更新
     * @param dict
     * @return
     */
    int update(Dict dict);

    /**
     * 移除
     * @param id
     * @return
     */
    int remove(Long id);

    /**
     * 批量移除
     * @param ids
     * @return
     */
    int batchRemove(Long[] ids);

}
