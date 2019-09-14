package com.root.cognition.modules.controller;

import com.root.cognition.common.config.ProjectConfig;
import com.root.cognition.common.until.FileUtil;
import com.root.cognition.common.until.PageUtils;
import com.root.cognition.common.until.Query;
import com.root.cognition.common.until.ResultMap;
import com.root.cognition.modules.entity.FileRecord;
import com.root.cognition.modules.service.FileService;
import com.root.cognition.system.persistence.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件上传
 *
 * @author taoya
 */
@Controller
@RequestMapping("/modules/sysFile")
public class FileController extends BaseController {

	private FileService fileService;

	private ProjectConfig projectConfig;

	@Autowired
	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}

	@Autowired
	public void setProjectConfig(ProjectConfig projectConfig) {
		this.projectConfig = projectConfig;
	}

	@GetMapping()
	@RequiresPermissions("common:sysFile:sysFile")
	String sysFile(Model model) {
		Map<String, Object> params = new HashMap<>(16);
		return "common/file/file";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("common:sysFile:sysFile")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Map<String, Object> query = new Query(params);
		List<FileRecord> sysFileRecordList = fileService.list(query);
		int total = fileService.count(query);
		PageUtils pageUtils = new PageUtils(sysFileRecordList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	// @RequiresPermissions("common:bComments")
	String add() {
		return "common/sysFile/add";
	}

	@GetMapping("/edit")
	// @RequiresPermissions("common:bComments")
	String edit(Long id, Model model) {
		FileRecord sysFileRecord = fileService.get(id);
		model.addAttribute("sysFile", sysFileRecord);
		return "common/sysFile/edit";
	}

	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("common:info")
	public ResultMap info(@PathVariable("id") Long id) {
		FileRecord sysFileRecord = fileService.get(id);
		return ResultMap.success().put("sysFile", sysFileRecord);
	}

//	/**
//	 * 保存
//	 */
//	@ResponseBody
//	@PostMapping("/save")
//	@RequiresPermissions("common:save")
//	public ResultMap save(FileRecord sysFileRecord) {
//		if (fileService.save(sysFileRecord) > 0) {
//			return ResultMap.success();
//		}
//		return ResultMap.error();
//	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("common:update")
	public ResultMap update(@RequestBody FileRecord sysFileRecord) {
		fileService.update(sysFileRecord);

		return ResultMap.success();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	// @RequiresPermissions("common:remove")
	public ResultMap remove(Long id, HttpServletRequest request) {
		if ("test".equals(getUsername())) {
			return ResultMap.error("演示系统不允许修改,完整体验请部署程序");
		}
		String fileName = projectConfig.getUploadPath() + fileService.get(id).getUrl().replace("/files/", "");
		if (fileService.remove(id) > 0) {
			boolean b = FileUtil.deleteFile(fileName);
			if (!b) {
				return ResultMap.error("数据库记录删除成功，文件删除失败");
			}
			return ResultMap.success();
		} else {
			return ResultMap.error();
		}
	}

	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("common:remove")
	public ResultMap remove(@RequestParam("ids[]") Long[] ids) {
		if ("test".equals(getUsername())) {
			return ResultMap.error("演示系统不允许修改,完整体验请部署程序");
		}
		fileService.batchRemove(ids);
		return ResultMap.success();
	}


	@ResponseBody
	@PostMapping("/upload")
	ResultMap upload(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request) {

		//文件名
		String fileName = multipartFile.getOriginalFilename();
		String fileUrl = "";
		//文件名不为空
		if (StringUtils.isNotEmpty(fileName)) {
			fileName = FileUtil.renameToUUID(fileName);
			fileUrl = projectConfig.getUploadPath() + fileName;
		}
		//文件上传地址与名字
		File file = new File(fileUrl, multipartFile.getOriginalFilename());
		FileRecord sysFileRecord = new FileRecord(FileUtil.fileType(fileName), fileUrl, new Date());
		try {
			multipartFile.transferTo(file);
			if (fileService.save(sysFileRecord) > 0) {
				return ResultMap.success().put("fileName", sysFileRecord.getUrl());
			}
			return ResultMap.error();
		} catch (Exception e) {
			return ResultMap.error();
		}


	}




}
