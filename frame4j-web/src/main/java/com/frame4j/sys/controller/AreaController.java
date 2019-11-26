package com.frame4j.sys.controller;

import com.frame4j.common.utils.Frame4JResult;
import com.frame4j.sys.entity.Area;
import com.frame4j.sys.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 区域管理Controller
 * 
 * @author MJ
 *
 */
@Controller
@RequestMapping("/sys/area")
public class AreaController {

	@Autowired
	private AreaService areaService;

	@ModelAttribute
	public void getArea(@RequestParam(value = "id", required = false) String id, Map<String, Object> map) {
		if (id != null) {
			Area area = areaService.get(id);
			map.put("area", area);
		}
	}

	/**
	 * 跳转列表页
	 * 
	 * @return
	 */
	@RequestMapping(value = { "list", "" }, method = RequestMethod.GET)
	public String list() {
		return "page/sys/areaList";
	}

	/**
	 * 跳转详情页
	 * 
	 * @return
	 */
	@RequestMapping(value = "form", method = RequestMethod.GET)
	public String form(Model model) {
		model.addAttribute("area", new Area());
		return "page/sys/areaForm";
	}

	/**
	 * 保存区域信息
	 * 
	 * @param area
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public Frame4JResult save(Area area) {
		Frame4JResult frame4JResult = new Frame4JResult();
		try {
			areaService.save(area);
			frame4JResult = Frame4JResult.ok();
		} catch (Exception e) {
			frame4JResult = Frame4JResult.build(500, "异常信息：" + e.getMessage());
			e.printStackTrace();
		}
		return frame4JResult;
	}

	@ResponseBody
	@RequestMapping(value = "treeData")
	public String treeData(@RequestParam(required = false) String extId, HttpServletResponse response) {
		/*
		 * List<Map<String, Object>> mapList = Lists.newArrayList(); List<Area> list =
		 * areaService.findList(new Area()); for (int i = 0; i < list.size(); i++) {
		 * Area e = list.get(i); if (StringUtils.isBlank(extId) || (extId != null &&
		 * !extId.equals(e.getId()) && e.getParentIds().indexOf("," + extId + ",") ==
		 * -1)) { Map<String, Object> map = Maps.newHashMap(); map.put("id", e.getId());
		 * map.put("pId", e.getParentId()); map.put("name", e.getName());
		 * mapList.add(map); } }
		 */
		return "{name: \"中国\", pId: \"0\", id: \"1\"}" + "{name: \"北京市\", pId: \"1\", id: \"110000\"}"
				+ "{name: \"东城区\", pId: \"110100\", id: \"110101\"}"
				+ "{name: \"市辖区\", pId: \"110000\", id: \"110100\"}" + "{name: \"天津市\", pId: \"1\", id: \"120000\"}"
				+ "{name: \"西城区\", pId: \"110100\", id: \"110102\"}";
	}

}
