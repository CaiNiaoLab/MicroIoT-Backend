package com.frame4j.sys.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.frame4j.base.entity.DataEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * 字典类型实体类
 * 
 * @author MJ
 *
 */
@Data
@Table(name = "SYS_DICTCATEGORY")
@Entity
public class DictCategory extends DataEntity {
	private static final long serialVersionUID = 1L;
	// 字典名称
	private String name;
	// 字典类型
	private String type;
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
	// 字典数据
	@JsonIgnore
	@OneToMany(cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY, mappedBy = "dictCategory")
	private Set<Dict> dicts;

}
