package com.frame4j.base.service;

import com.frame4j.base.entity.DataEntity;
import com.frame4j.base.repository.BaseRepository;
import com.frame4j.common.utils.IdGen;
import com.frame4j.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 通用Service
 * 
 * @author MJ
 *
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public abstract class BaseService<D extends BaseRepository<T, ID>, T extends DataEntity, ID> {

	@Autowired
	private D d;

	/**
	 * 通过id获取对象
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public T get(ID id) {
		return d.getOne(id);
	}

	/**
	 * 保存对象
	 * 
	 * @param t
	 */
	@Transactional(readOnly = false)
	public void save(T t) {
		String isNewCreate = t.getIsNewCreate();
		// 第一次创建为isNewCreate 为true  或者   获取到的id是null   说明都是第一次创建
		if ("true".equals(isNewCreate) || StringUtils.isBlank(t.getId())) {
			IdGen idGen = new IdGen();
			long id = idGen.nextId();
			t.setId(String.valueOf(id));
			t.setCreateDate(new Date());
			t.setIsNewCreate("false");
		}
		d.save(t);
	}

	/**
	 * 获取所有对象
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<T> findAll() {
		return d.findAll();
	}

	/**
	 * 分页方法
	 * 
	 * @param pageable
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page<T> findAll(Pageable pageable) {
		return d.findAll(pageable);
	}

	/**
	 * 分页方法
	 * 
	 * @param example
	 * @param pageable
	 * @return
	 */

	@Transactional(readOnly = true)
	@CacheEvict(value = "findAll")
	public Page<T> findAll(Example example, Pageable pageable) {
		return d.findAll(example, pageable);
	}

	/**
	 * 通用sort
	 * 
	 * @param example
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<T> findAll(Example example, Sort sort) {
		return d.findAll(example, sort);
	}

	/**
	 * 通用删除方法
	 * 
	 * @param t
	 */
	@Transactional(readOnly = false)
	public void delete(T t) {
		d.delete(t);
	}

	/**
	 * 通用删除方法
	 * 
	 * @param id
	 */
	@Transactional(readOnly = false)
	public void deleteById(ID id) {
		d.deleteById(id);
	}

}
