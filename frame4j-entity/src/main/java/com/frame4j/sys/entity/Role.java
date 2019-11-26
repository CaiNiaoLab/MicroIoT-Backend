package com.frame4j.sys.entity;

import com.frame4j.base.entity.DataEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 角色实体类
 * 
 * @author MJ
 *
 */
@Data
@Table(name = "SYS_ROLE")
@Entity
public class Role extends DataEntity {
	private static final long serialVersionUID = 1L;
	// 角色名称
	private String name;
	// 角色编码
	private String enname;
	// 数据范围对象
	@JoinColumn(name = "DATAPERMISSION_ID")
	@ManyToOne(fetch = FetchType.LAZY)
	private DataPermission dataPermission;
	// 用户列表
	@ManyToMany(mappedBy="roles")
	private Set<User> users = new HashSet<User>();
	// 角色所拥有的菜单
	// 使用 @ManyToMany 注解来映射多对多关联关系
	// 使用 @JoinTable 来映射中间表
	// 1. name 指向中间表的名字
	// 2. joinColumns 映射当前类所在的表在中间表中的外键
	// 2.1 name 指定外键列的列名
	// 2.2 referencedColumnName 指定外键列关联当前表的哪一列
	// 3. inverseJoinColumns 映射关联的类所在中间表的外键
	@JoinTable(name = "SYS_ROLE_MENU", joinColumns = {
			@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID") }, inverseJoinColumns = {
					@JoinColumn(name = "MENU_ID", referencedColumnName = "ID") })
	@ManyToMany
	private Set<Menu> menus = new HashSet<Menu>();
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
