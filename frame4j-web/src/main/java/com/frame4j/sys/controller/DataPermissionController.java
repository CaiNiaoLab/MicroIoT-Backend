package com.frame4j.sys.controller;

import com.frame4j.common.utils.CopyBeanUtils;
import com.frame4j.common.utils.LayuiPage;
import com.frame4j.common.utils.Frame4JResult;
import com.frame4j.sys.entity.DataPermission;
import com.frame4j.sys.service.DataPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 数据权限管理Controller
 * 
 * @author MJ
 *
 */
@Controller
@RequestMapping("/sys/dataPermission")
public class DataPermissionController {

	@Autowired
	private DataPermissionService dataPermissionService;

	/**
	 * 跳转列表页
	 * 
	 * @return
	 */
	@RequestMapping(value = { "list", "" }, method = RequestMethod.GET)
	public String list(Map<String, Object> map) {
		return "page/sys/dataPermissionList";
	}

	/**
	 * 跳转详情页
	 * 
	 * @return
	 */
	@RequestMapping(value = "form", method = RequestMethod.GET)
	public String form() {
		return "page/sys/dataPermissionForm";
	}

	/**
	 * 列表页获取数据
	 * 
	 * @param page
	 * @param limit
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "data", method = RequestMethod.GET)
	public LayuiPage<DataPermission> data(@RequestParam(name = "page", defaultValue = "1", required = false) int page,
										  @RequestParam(name = "limit", defaultValue = "10", required = false) int limit,
										  DataPermission dataPermission) {
		Pageable pageable = new PageRequest(page - 1, limit);
		CopyBeanUtils.Copy(dataPermission, dataPermission, false);
		Example<DataPermission> example = Example.of(dataPermission);
		Page<DataPermission> pages = dataPermissionService.findAll(example, pageable);
		return new LayuiPage<DataPermission>().data(pages.getContent(), pages.getTotalElements());
	}

	/**
	 * 保存字典数据
	 * 
	 * @param dataPermission
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public Frame4JResult save(DataPermission dataPermission) {
		Frame4JResult frame4JResult = new Frame4JResult();
		try {
			dataPermissionService.save(dataPermission);
			frame4JResult = Frame4JResult.ok();
		} catch (Exception e) {
			frame4JResult = Frame4JResult.build(500, "异常信息：" + e.getMessage());
			e.printStackTrace();
		}
		return frame4JResult;
	}

	/**
	 * 删除数据权限数据
	 * 
	 * @param dataPermission
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public Frame4JResult delete(DataPermission dataPermission) {
		Frame4JResult frame4JResult = new Frame4JResult();
		try {
			dataPermissionService.delete(dataPermission);
			frame4JResult = Frame4JResult.ok();
		} catch (Exception e) {
			frame4JResult = Frame4JResult.build(500, "异常信息：" + e.getMessage());
			e.printStackTrace();
		}
		return frame4JResult;
	}

	/**
	 * 批量删除数据权限数据
	 * 
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "deleteAll", method = RequestMethod.GET)
	public Frame4JResult deleteAll(String ids) {
		Frame4JResult frame4JResult = new Frame4JResult();
		try {
			String[] idArray = ids.split(",");
			for (String id : idArray) {
				dataPermissionService.deleteById(id);
			}
			frame4JResult = Frame4JResult.ok();
		} catch (Exception e) {
			frame4JResult = Frame4JResult.build(500, "异常信息：" + e.getMessage());
			e.printStackTrace();
		}
		return frame4JResult;
	}

}
