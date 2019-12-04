package com.notejumping.modules.controller;

import com.notejumping.common.config.ProjectConfig;
import com.notejumping.common.until.FileUtil;
import com.notejumping.common.until.PageUtils;
import com.notejumping.common.until.Query;
import com.notejumping.common.until.ResultMap;
import com.notejumping.system.persistence.BaseController;
import com.notejumping.modules.entity.FileRecord;
import com.notejumping.modules.service.FileService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
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

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

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
		return "modules/file/file";
	}

	@GetMapping("/index")
	String sysFileIndex(Model model) {
		Map<String, Object> params = new HashMap<>(16);
		return "clienthtml/userFile";
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
	 @RequiresPermissions("common:bComments")
	String add() {
		return "common/sysFile/add";
	}

	@GetMapping("/edit")
	 @RequiresPermissions("common:bComments")
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
	 @RequiresPermissions("common:remove")
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
		fileService.batchRemove(ids);
		return ResultMap.success();
	}


	@ResponseBody
	@PostMapping("/upload")
    @Async("taskExecutor")
	ResultMap upload(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request) {
		//文件名
		String fileName = multipartFile.getOriginalFilename();
		String publicUrl = "";

		//文件名不为空
		if (fileName != null) {

			publicUrl = projectConfig.getUploadPath();
			//文件归属文件夹与标识
			Map<String, String> fileType = FileUtil.fileType(fileName);
			//文件随机生成uuid
			fileName = FileUtil.renameToUUID(fileName);
			if (!fileType.get("state").equals("500")) {
				String path = "/uploaded_files/"+fileType.get("Suffix")+"/";
				//文件上传地址与名字 multipartFile.getOriginalFilename()
				FileRecord sysFileRecord = new FileRecord(multipartFile.getOriginalFilename(), Integer.valueOf(fileType.get("state")), path + fileName);
				sysFileRecord.preInsert();
				try {
					File file = new File(publicUrl+path, fileName);
					if (!file.exists() && !file.isDirectory()) {
						logger.info("method[uploadPic]文件不存在，正在创建文件。。。。。");
						file.mkdirs();
						logger.info("method[uploadPic]文件创建完成 success。。。。。。。");
					}
					multipartFile.transferTo(file);
					if (fileService.save(sysFileRecord) > 0) {
						return ResultMap.success().put("fileName", sysFileRecord.getUrl());
					}
					System.err.println("保存失败！！");
					logger.info("保存失败！！");
					return ResultMap.error();
				} catch (Exception e) {
					System.err.println(e.toString());
					logger.info(e.toString());
					return ResultMap.error();
				}
			}
			return ResultMap.error();
		} else {
			logger.info("选择的文件为空");
			return ResultMap.error("您选择的文件为空");
		}
	}




}
