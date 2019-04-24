package com.root.cognition;

import com.root.cognition.common.until.codegenerate.SnowFlake;
import com.root.cognition.system.dao.MenuDao;
import com.root.cognition.system.entity.Dept;
import com.root.cognition.system.entity.Menu;
import com.root.cognition.system.service.DeptService;
import com.root.cognition.system.service.MenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

/**
 * @author Worry
 * @version 2019/4/5
 */
@RunWith(SpringRunner.class)
@SpringBootTest
//@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MapperTest {

    private static final Logger logger = LoggerFactory.getLogger(MapperTest.class);

    private MenuDao menuDao;
    private MenuService menuService;
    @Autowired
    public void setMenuDao(MenuDao menuDao) {
        this.menuDao = menuDao;
    }
    @Autowired
    public void setMenuService (MenuService menuService){this.menuService = menuService;}

    private DeptService deptService;
    @Autowired
    public void setDeptService(DeptService deptService){this.deptService = deptService;}


    @Test
    public void testFind() {
        try {

            System.out.println("测试开始");
            //简单验证结果集是否正确
            Map<String, Object> params = new HashMap<>();
            params.put("delFlag",0);
//            Menu sysMenu=menuDao.get("MU201904070001");
//            sysMenu.setIcon("hsgja");
//            menuService.update(sysMenu);
            List<Menu> oldList =menuService.findList(params);
            List<Menu> newList = new ArrayList<>();
            List<Long> sysMenusIds = new ArrayList<>();
            for (Menu dept : oldList){
                Long deptId = SnowFlake.createSFid();
                sysMenusIds.add(dept.getId());
                menuService.delete(dept.getId());
                dept.setId(deptId);
                dept.setCreateTime(new Date());
                dept.setUpdateBy("566956334545633280");
                dept.setUpdateTime(new Date());
                dept.setCreateBy("566956334545633280");
                menuService.save(dept);
                System.out.println("更新"+deptId+"成功");
//                sysMenusUpData.add(sysMenu);
//                sysMenusIds.add(sysMenu.getId());
            }
//            Long[] strings = new Long[sysMenusIds.size()];
//            sysMenusIds.toArray(strings);
//            menuService.batchDelete(strings);

//            String[] strings = new String[sysMenusIds.size()];
//            sysMenusIds.toArray(strings);
//            int delectNumber= menuService.batchremove(strings);
//            if (delectNumber<0){
//                System.out.println("删除失败");
//            }

        }catch (Exception e){
            e.getMessage();
            e.printStackTrace();
        }

//        Assert.assertEquals();
//        Menu sysMenu = new Menu();
//        sysMenu.setId();
//        Assert.assertEquals(2, menuDao.insert(sysMenu));

    }
}
