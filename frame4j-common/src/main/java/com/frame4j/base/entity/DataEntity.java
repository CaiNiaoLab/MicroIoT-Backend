package com.frame4j.base.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 普通类基类
 * 
 * @author MJ
 *
 */
@Data
@MappedSuperclass // 第二个坑 数据类抽取，jpa报错：No identifier specified for entity 解决方案基类加上
					// @MappedSuperclass
public class DataEntity implements Serializable {
	/**
	 * 删除标记（0：正常；1：删除；）
	 */
	public static final String DELFLAG_NORMAL = "0";
	public static final String DELFLAG_DELETE = "1";
	private static final long serialVersionUID = 1L;
	// 主键id
	@Id
	@Column(length = 64)
	private String id;

	// 创建时间
	@Column(name = "CREATE_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	// 更新时间
	@Column(name = "UPDATE_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;

	// 逻辑删除（0：正常；1：删除；）
	@Column(name = "DEL_FLAG")
	private String delFlag;

	// 排序
	private Integer sort;

	// 备注
	private String remarks;

	// 是否为新创建
	@Transient
	private String isNewCreate;

	public DataEntity() {
		this.delFlag = DELFLAG_NORMAL;
	}

}
