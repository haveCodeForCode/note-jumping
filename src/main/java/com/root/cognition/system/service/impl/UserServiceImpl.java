package com.root.cognition.system.service.impl;


import com.root.cognition.common.persistence.Tree;
import com.root.cognition.common.until.BuildTree;
import com.root.cognition.common.until.StringUtils;
import com.root.cognition.system.dao.DeptDao;
import com.root.cognition.system.dao.UserDao;
import com.root.cognition.system.dao.UserRoleDao;
import com.root.cognition.system.entity.SysDept;
import com.root.cognition.system.entity.SysUser;
import com.root.cognition.system.entity.SysUserRole;
import com.root.cognition.system.service.DeptService;
import com.root.cognition.system.service.UserService;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);


    private UserDao userDao;

    private UserRoleDao userRoleDao;

    private DeptDao deptDao;

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
    public void setDeptDao(DeptDao deptDao) {
        this.deptDao = deptDao;
    }

    @Autowired
    public void setDeptService(DeptService deptService) {
        this.deptService = deptService;
    }


    @Override
//    @Cacheable(value = "user",key = "#id")
    public SysUser get(String id) {
        List<String> roleIds = userRoleDao.listRoleId(id);
        SysUser sysUser = userDao.get(id);
        sysUser.setDept(deptDao.get(sysUser.getId()).getName());
        sysUser.setRoleIds(roleIds);
        return sysUser;
    }

    @Override
    public SysUser get(Map<String, Object> params) {
        return userDao.getByEntity(params);
    }

    @Override
    public List<SysUser> list(Map<String, Object> map) {
        String deptId = map.get("deptId").toString();
        if (StringUtils.isNotBlank(deptId)) {
            String deptIdl = String.valueOf(deptId);
            List<String> childIds = deptService.listChildrenIds(deptIdl);
            childIds.add(deptIdl);
            map.put("deptId", null);
            map.put("deptIds", childIds);
        }
        return userDao.findList(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return userDao.count(map);
    }

    @Transactional
    @Override
    public int save(SysUser sysUser) {
        int count = userDao.insert(sysUser);
        String userId = sysUser.getId();
        List<String> roles = sysUser.getRoleIds();
        userRoleDao.removeByUserId(userId);
        List<SysUserRole> list = new ArrayList<>();
        for (String roleId : roles) {
            SysUserRole ur = new SysUserRole();
            ur.setUserId(userId);
            ur.setRoleId(roleId);
            list.add(ur);
        }
        if (list.size() > 0) {
            userRoleDao.batchSave(list);
        }
        return count;
    }

    @Override
    public int update(SysUser sysUser) {
        int r = userDao.update(sysUser);
        String userId = sysUser.getId();
        List<String> roles = sysUser.getRoleIds();
        userRoleDao.removeByUserId(userId);
        List<SysUserRole> list = new ArrayList<>();
        for (String roleId : roles) {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(userId);
            sysUserRole.setRoleId(roleId);
            list.add(sysUserRole);
        }
        if (list.size() > 0) {
            userRoleDao.batchSave(list);
        }
        return r;
    }

    //    @CacheEvict(value = "user")
    @Override
    public int remove(String userId) {
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
//            if (Objects.equals(MD5Utils.encrypt(userDO.getUsername(), userVO.getPwdOld()), userDO.getPassword())) {
//                userDO.setPassword(MD5Utils.encrypt(userDO.getUsername(), userVO.getPwdNew()));
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
//        userDO.setPassword(MD5Utils.encrypt(userDO.getUsername(), userVO.getPwdNew()));
//        return userMapper.update(userDO);
//
//
//    }

    @Transactional
    @Override
    public int batchremove(String[] userIds) {
        int count = userDao.batchRemove(userIds);
        userRoleDao.batchRemoveByUserId(userIds);
        return count;
    }

    @Override
    public Tree<SysDept> getTree() {
        List<Tree<SysDept>> trees = new ArrayList<Tree<SysDept>>();
        List<SysDept> sysDepts = deptDao.findList(new HashMap<String, Object>(16));
        String[] pDepts = deptDao.listParentDept();
        String[] uDepts = userDao.listAllDept();
        String[] allDepts = (String[]) ArrayUtils.addAll(pDepts, uDepts);
        for (SysDept sysDept : sysDepts) {
            if (!ArrayUtils.contains(allDepts, sysDept.getId())) {
                continue;
            }
            Tree<SysDept> tree = new Tree<SysDept>();
            tree.setId(sysDept.getId().toString());
            tree.setParentId(sysDept.getParentId().toString());
            tree.setText(sysDept.getName());
            Map<String, Object> state = new HashMap<>(16);
            state.put("opened", true);
            state.put("mType", "sysDept");
            tree.setState(state);
            trees.add(tree);
        }
        List<SysUser> sysUsers = userDao.findList(new HashMap<String, Object>(16));
        for (SysUser sysUser : sysUsers) {
            Tree<SysDept> tree = new Tree<SysDept>();
            tree.setId(sysUser.getId().toString());
            tree.setParentId(sysUser.getDept().toString());
            tree.setText(sysUser.getUserName());
            Map<String, Object> state = new HashMap<>(16);
            state.put("opened", true);
            state.put("mType", "sysUser");
            tree.setState(state);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<SysDept> deptTree = BuildTree.build(trees);
        return deptTree;
    }

    @Override
    public int updatePersonal(SysUser sysUserDO) {
        return userDao.update(sysUserDO);
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
