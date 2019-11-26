package com.frame4j.sys.entity;

import com.frame4j.base.entity.TreeEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * 区域实体类
 * 
 * @author MJ
 *
 */
@Data
@Table(name = "SYS_AREA")
@Entity
public class Area extends TreeEntity<Area> {
	private static final long serialVersionUID = 1L;
	// 区域名称
	private String name;
	// 区域类型（国家，省份、直辖市，地市，区县）
	private String types;
	// 区域编码
	private String code;
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
