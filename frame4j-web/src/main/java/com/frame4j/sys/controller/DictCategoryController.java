package com.frame4j.sys.controller;

import com.frame4j.common.utils.CopyBeanUtils;
import com.frame4j.common.utils.LayuiPage;
import com.frame4j.common.utils.Frame4JResult;
import com.frame4j.sys.entity.DictCategory;
import com.frame4j.sys.service.DictCategoryService;
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

import java.util.List;

/**
 * 字典类型Controller
 * 
 * @author MJ
 *
 */
@Controller
@RequestMapping("/sys/dictCategory")
public class DictCategoryController {
	
	@Autowired
	private DictCategoryService dictCategoryService;
	
	/**
	 * 跳转列表页
	 * 
	 * @return
	 */
	@RequestMapping(value = { "list", "" }, method = RequestMethod.GET)
	public String list() {
		return "page/sys/dictCategoryList";
	}

	/**
	 * 跳转详情页
	 * 
	 * @return
	 */
	@RequestMapping(value = "form", method = RequestMethod.GET)
	public String form() {
		return "page/sys/dictCategoryForm";
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
	public LayuiPage<DictCategory> data(@RequestParam(name = "page", defaultValue = "1", required = false) int page,
										@RequestParam(name = "limit", defaultValue = "10", required = false) int limit, DictCategory dictCategory) {
		Pageable pageable = new PageRequest(page - 1, limit);
		CopyBeanUtils.Copy(dictCategory, dictCategory, false);
		Example<DictCategory> example = Example.of(dictCategory);
		Page<DictCategory> pages = dictCategoryService.findAll(example, pageable);
		return new LayuiPage<DictCategory>().data(pages.getContent(), pages.getTotalElements());
	}

	/**
	 * 保存字典类型
	 * 
	 * @param dictCategory
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public Frame4JResult save(DictCategory dictCategory) {
		Frame4JResult frame4JResult = new Frame4JResult();
		try {
			dictCategoryService.save(dictCategory);
			frame4JResult = Frame4JResult.ok();
		} catch (Exception e) {
			frame4JResult = Frame4JResult.build(500, "异常信息：" + e.getMessage());
			e.printStackTrace();
		}
		return frame4JResult;
	}

	/**
	 * 删除字典类别
	 * 
	 * @param dictCategory
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public Frame4JResult delete(DictCategory dictCategory) {
		Frame4JResult frame4JResult = new Frame4JResult();
		try {
			//注意点: 需要级联删除Dict
			dictCategory = dictCategoryService.get(dictCategory.getId());
			dictCategoryService.delete(dictCategory);
			frame4JResult = Frame4JResult.ok();
		} catch (Exception e) {
			frame4JResult = Frame4JResult.build(500, "异常信息：" + e.getMessage());
			e.printStackTrace();
		}
		return frame4JResult;
	}

	/**
	 * 批量删除字典类别
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
				//注意点: 需要级联删除Dict
				DictCategory dictCategory = dictCategoryService.get(id);
				dictCategoryService.delete(dictCategory);
			}
			frame4JResult = Frame4JResult.ok();
		} catch (Exception e) {
			frame4JResult = Frame4JResult.build(500, "异常信息：" + e.getMessage());
			e.printStackTrace();
		}
		return frame4JResult;
	}

	/**
	 * ajax获取所有字典类别信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "ajaxAllDictCategory", method = RequestMethod.GET)
	public List<DictCategory> AjaxAllDictCategory() {
		List<DictCategory> list = dictCategoryService.findAll();
		return list;
	}

}
