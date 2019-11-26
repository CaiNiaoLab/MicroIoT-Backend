package com.frame4j.sys.entity;

import com.frame4j.base.entity.TreeEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 菜单实体类
 * 
 * @author MJ
 *
 */
@Data
@Table(name = "SYS_MENU")
@Entity
public class Menu extends TreeEntity<Menu> {
	private static final long serialVersionUID = 1L;
	// 菜单名称
	private String name;
	// 菜单链接
	private String href;
	// 菜单图标
	private String icon;
	// 菜单显示（0：显示；1：隐藏；）
	private String isShow;
	// 权限标识
	private String permission;
	// 菜单类型（0：菜单；1：权限；）
	private String type;
	// 菜单所关联的所有角色
	@ManyToMany(mappedBy="menus")
	private Set<Role> roles = new HashSet<Role>();
	// 预留字段1
	private String string1;
	// 预留字段2
	private String string2;
	// 预留字段3
	private String string3;
	// 预留字段4
	private String string4;
	// 预留字段5
	private String string5;
	// 预留字段6
	private Integer integer1;
	// 预留字段7
	private Integer integer2;
	// 预留字段8
	private Integer integer3;
	// 预留字段9
	private Integer integer4;
	// 预留字段10
	private Integer integer5;
	// 预留字段11
	private Date data1;
	// 预留字段12
	private Date data2;
	// 预留字段13
	private Date data3;
	// 预留字段14
	private Date data4;
	// 预留字段15
	private Date data5;

}
