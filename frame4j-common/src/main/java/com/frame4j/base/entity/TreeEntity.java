package com.frame4j.base.entity;


import lombok.*;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

/**
 * 树基类
 * 
 * @author MJ
 *
 * @param <T>
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@MappedSuperclass
public class TreeEntity<T> extends DataEntity {
	private static final long serialVersionUID = 1L;
	// 父级编号
	private T parent;
	// 所有父级编号
	private String parentIds;
	// 名称
	private String text;
	// 是否有子节点
	@Transient
	private boolean hasChildren;

}
