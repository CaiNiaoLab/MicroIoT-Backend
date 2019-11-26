package com.frame4j.plugin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/plugin")
public class PluginController {
	/**
	 * 测试树结构选择插件
	 */
	@RequestMapping(value = "tree")
	public String tree(HttpServletRequest request, Model model) {
		return "page/plugin/tree";
	}
	
}
