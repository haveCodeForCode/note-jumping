package com.root.cognition.system.service.impl;


import com.root.cognition.common.persistence.Tree;
import com.root.cognition.common.until.BuildTree;
import com.root.cognition.common.until.Query;
import com.root.cognition.common.until.StringUtils;
import com.root.cognition.system.dao.RoleDao;
import com.root.cognition.system.dao.UserDao;
import com.root.cognition.system.dao.UserInfoDao;
import com.root.cognition.system.dao.UserRoleDao;
import com.root.cognition.system.entity.*;
import com.root.cognition.system.service.DeptService;
import com.root.cognition.system.service.UserService;
import com.root.cognition.system.vo.UserVo;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


/**
 * @author taoya
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);


    private UserDao userDao;

    private RoleDao roleDao;

    private UserRoleDao userRoleDao;

    private UserInfoDao userInfoDao;

    private DeptService deptService;


//    @Autowired
//    private FileService sysFileService;
//    @Autowired
//    private BootdoConfig bootdoConfig;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setUserRoleDao(UserRoleDao userRoleDao) {
        this.userRoleDao = userRoleDao;
    }

    @Autowired
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Autowired
    public void setDeptService(DeptService deptService) {
        this.deptService = deptService;
    }

    @Autowired
    public void setUserInfoDao(UserInfoDao userInfoDao) {
        this.userInfoDao = userInfoDao;
    }


    /***
     * 声明用户相关所有对象
     * @param userId
     * @return
     */
    @Cacheable(value = "zero", key = "targetClass + methodName +#p0")
    @Override
    public UserVo getbyUserId(Long userId) {
        UserVo userVo = new UserVo();
        Map<String, Object> query = Query.withDelFlag();
        query.put("userId", userId);
        User user = userDao.getByEntity(query);
        userVo.setUser(user);
        //用户信息
        UserInfo userInfo = userInfoDao.getByEntity(query);
        userVo.setUserInfo(userInfo);
        //用户角色
        List<Role> roles = roleDao.findWithUserId(userId);
        userVo.setRoles(roles);
        //部门ID
        userVo.setDept(deptService.get(user.getId()));
        return userVo;
    }

    @Override
    public User get(Long id) {
        return userDao.get(id);
    }

    @Override
    public User get(Map<String, Object> params) {
        return userDao.getByEntity(params);
    }

    @Override
    public List<User> list(Map<String, Object> map) {
        String deptId = map.get("deptId").toString();
        if (StringUtils.isNotBlank(deptId)) {
            long deptIdL = Long.parseLong(deptId);
            List<Long> childIds = deptService.listChildrenIds(deptIdL);
            childIds.add(deptIdL);
            map.put("deptId", null);
            map.put("deptIds", childIds);
        }
        return userDao.findList(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return userDao.count(map);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int save(User user) {
        user.preInsert();
        int insert = userDao.insert(user);
        batchModifyRoles(user);
        return insert;
    }

    @Override
    public int update(User user) {
        int update = userDao.update(user);
        batchModifyRoles(user);
        return update;
    }

    /**
     * 批量修改用户角色信息
     *
     * @param user 用户对象
     */
    private void batchModifyRoles(User user) {
        try {
            Long userId = user.getId();
            UserVo userVo = getbyUserId(userId);
            List<Role> roles = userVo.getRoles();
            userRoleDao.removeByUserId(userId);
            List<UserRole> list = new ArrayList<>();
            for (Role role : roles) {
                UserRole userRole = new UserRole();
                userRole.setUserId(userId);
                userRole.setRoleId(role.getId());
                list.add(userRole);
            }
            if (list.size() > 0) {
                userRoleDao.batchSave(list);
            }
        } catch (Exception e) {
            e.fillInStackTrace();
            throw e;
        }
    }

    //    @CacheEvict(value = "user")
    @Override
    public int delete(Long userId) {
        userRoleDao.removeByUserId(userId);
        return userDao.remove(userId);
    }

    @Override
    public boolean exit(Map<String, Object> params) {
        boolean exit;
        exit = userDao.findList(params).size() > 0;
        return exit;
    }

    @Override
    public Set<String> listRoles(Long userId) {
        return null;
    }

//    @Override
//    public int resetPwd(UserVO userVO, UserDO userDO) throws Exception {
//        if (Objects.equals(userVO.getUserDO().getUserId(), userDO.getUserId())) {
//            if (Objects.equals(Md5Utils.encrypt(userDO.getUsername(), userVO.getPwdOld()), userDO.getPassword())) {
//                userDO.setPassword(Md5Utils.encrypt(userDO.getUsername(), userVO.getPwdNew()));
//                return userMapper.update(userDO);
//            } else {
//                throw new Exception("输入的旧密码有误！");
//            }
//        } else {
//            throw new Exception("你修改的不是你登录的账号！");
//        }
//    }

//    @Override
//    public int adminResetPwd(UserVO userVO) throws Exception {
//        UserDO userDO = get(userVO.getUserDO().getUserId());
//        if ("admin".equals(userDO.getUsername())) {
//            throw new Exception("超级管理员的账号不允许直接重置！");
//        }
//        userDO.setPassword(Md5Utils.encrypt(userDO.getUsername(), userVO.getPwdNew()));
//        return userMapper.update(userDO);
//
//
//    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int batchDelete(Long[] userIds) {
        int count = userDao.batchRemove(userIds);
        userRoleDao.batchRemoveByUserId(userIds);
        return count;
    }


    @Override
    public Tree<Dept> getTree() {
        List<Tree<Dept>> trees = new ArrayList<Tree<Dept>>();
        List<Dept> depts = deptService.findList(new HashMap<String, Object>(16));
        String[] pDepts = deptService.listParentDept();
        String[] uDepts = deptService.listAllDept();
        String[] allDepts = (String[]) ArrayUtils.addAll(pDepts, uDepts);
        for (Dept dept : depts) {
            if (!ArrayUtils.contains(allDepts, dept.getId())) {
                continue;
            }
            Tree<Dept> tree = new Tree<Dept>();
            tree.setId(dept.getId().toString());
            tree.setParentId(dept.getParentId().toString());
            tree.setText(dept.getName());
            Map<String, Object> state = new HashMap<>(16);
            state.put("opened", true);
            state.put("mType", "dept");
            tree.setState(state);
            trees.add(tree);
        }
        List<User> users = userDao.findList(new HashMap<String, Object>(16));
        for (User user : users) {
            Tree<Dept> tree = new Tree<Dept>();
            tree.setId(user.getId().toString());
            tree.setParentId(user.getDeptId().toString());
            tree.setText(user.getLoginName());
            Map<String, Object> state = new HashMap<>(16);
            state.put("opened", true);
            state.put("mType", "user");
            tree.setState(state);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<Dept> deptTree = BuildTree.build(trees);
        return deptTree;
    }

    @Override
    public int updatePersonal(User userDO) {
        return userDao.update(userDO);
    }

//    @Override
//    public Map<String, Object> updatePersonalImg(MultipartFile file, String avatar_data, Long userId) throws Exception {
//        return null;
//    }

//    @Override
//    public Map<String, Object> updatePersonalImg(MultipartFile file, String avatar_data, Long userId) throws Exception {
//        String fileName = file.getOriginalFilename();
//        fileName = FileUtil.renameToUUID(fileName);
//        FileDO sysFile = new FileDO(FileType.fileType(fileName), "/files/" + fileName, new Date());
//        //获取图片后缀
//        String prefix = fileName.substring((fileName.lastIndexOf(".") + 1));
//        String[] str = avatar_data.split(",");
//        //获取截取的x坐标
//        int x = (int) Math.floor(Double.parseDouble(str[0].split(":")[1]));
//        //获取截取的y坐标
//        int y = (int) Math.floor(Double.parseDouble(str[1].split(":")[1]));
//        //获取截取的高度
//        int h = (int) Math.floor(Double.parseDouble(str[2].split(":")[1]));
//        //获取截取的宽度
//        int w = (int) Math.floor(Double.parseDouble(str[3].split(":")[1]));
//        //获取旋转的角度
//        int r = Integer.parseInt(str[4].split(":")[1].replaceAll("}", ""));
//        try {
//            BufferedImage cutImage = ImageUtils.cutImage(file, x, y, w, h, prefix);
//            BufferedImage rotateImage = ImageUtils.rotateImage(cutImage, r);
//            ByteArrayOutputStream out = new ByteArrayOutputStream();
//            boolean flag = ImageIO.write(rotateImage, prefix, out);
//            //转换后存入数据库
//            byte[] b = out.toByteArray();
//            FileUtil.uploadFile(b, bootdoConfig.getUploadPath(), fileName);
//        } catch (Exception e) {
//            throw new Exception("图片裁剪错误！！");
//        }
//        Map<String, Object> result = new HashMap<>();
//        if (sysFileService.save(sysFile) > 0) {
//            UserDO userDO = new UserDO();
//            userDO.setUserId(userId);
//            userDO.setPicId(sysFile.getId());
//            if (userMapper.update(userDO) > 0) {
//                result.put("url", sysFile.getUrl());
//            }
//        }
//        return result;
//    }

}
