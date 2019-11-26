package com.frame4j.sys.service;

import com.frame4j.base.service.BaseService;
import com.frame4j.sys.entity.Dict;
import com.frame4j.sys.repository.DictRepository;
import org.springframework.stereotype.Service;

/**
 * 字典service
 * 
 * @author MJ
 *
 */
@Service
public class DictService extends BaseService<DictRepository, Dict, String> {

}
