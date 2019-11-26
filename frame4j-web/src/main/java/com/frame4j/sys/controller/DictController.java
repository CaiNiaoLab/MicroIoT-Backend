package com.frame4j.sys.controller;

import com.frame4j.common.utils.CopyBeanUtils;
import com.frame4j.common.utils.LayuiPage;
import com.frame4j.common.utils.Frame4JResult;
import com.frame4j.sys.entity.Dict;
import com.frame4j.sys.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 字典Controller
 * 
 * @author MJ
 *
 */
@Controller
@RequestMapping("/sys/dict")
public class DictController {

	@Autowired
	private DictService dictService;

	/**
	 * 跳转列表页
	 * 
	 * @return
	 */
	@RequestMapping(value = { "list", "" }, method = RequestMethod.GET)
	public String list(String dictCategoryId, Map<String, Object> map) {
		map.put("dictCategoryId", dictCategoryId);
		return "page/sys/dictList";
	}

	/**
	 * 跳转详情页
	 * 
	 * @return
	 */
	@RequestMapping(value = "form", method = RequestMethod.GET)
	public String form() {
		return "page/sys/dictForm";
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
	public LayuiPage<Dict> data(@RequestParam(name = "page", defaultValue = "1", required = false) int page,
								@RequestParam(name = "limit", defaultValue = "10", required = false) int limit, Dict dict) {
		Pageable pageable = new PageRequest(page - 1, limit);
		CopyBeanUtils.Copy(dict, dict, false);
		ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("remarks", GenericPropertyMatchers.contains());
		Example<Dict> example = Example.of(dict, matcher);
		Page<Dict> pages = dictService.findAll(example, pageable);
		return new LayuiPage<Dict>().data(pages.getContent(), pages.getTotalElements());
	}

	/**
	 * 保存字典数据
	 * 
	 * @param dict
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public Frame4JResult save(Dict dict) {
		Frame4JResult frame4JResult = new Frame4JResult();
		try {
			dictService.save(dict);
			frame4JResult = Frame4JResult.ok();
		} catch (Exception e) {
			frame4JResult = Frame4JResult.build(500, "异常信息：" + e.getMessage());
			e.printStackTrace();
		}
		return frame4JResult;
	}

	/**
	 * 删除字典数据
	 * 
	 * @param dict
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public Frame4JResult delete(Dict dict) {
		Frame4JResult frame4JResult = new Frame4JResult();
		try {
			// 需要级联删除Dict
			dictService.delete(dict);
			frame4JResult = Frame4JResult.ok();
		} catch (Exception e) {
			frame4JResult = Frame4JResult.build(500, "异常信息：" + e.getMessage());
			e.printStackTrace();
		}
		return frame4JResult;
	}

	/**
	 * 批量删除字典数据
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
				dictService.deleteById(id);
			}
			frame4JResult = Frame4JResult.ok();
		} catch (Exception e) {
			frame4JResult = Frame4JResult.build(500, "异常信息：" + e.getMessage());
			e.printStackTrace();
		}
		return frame4JResult;
	}
}
