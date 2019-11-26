package com.frame4j.sys.service;

import com.frame4j.base.service.BaseService;
import com.frame4j.sys.entity.DataPermission;
import com.frame4j.sys.repository.DataPermissionRepository;
import org.springframework.stereotype.Service;

/**
 * 数据权限表service层
 * 
 * @author MJ
 *
 */
@Service
public class DataPermissionService extends BaseService<DataPermissionRepository, DataPermission, String> {

}
