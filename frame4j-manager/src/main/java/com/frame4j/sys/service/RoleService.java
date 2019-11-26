package com.frame4j.sys.service;

import com.frame4j.base.service.BaseService;
import com.frame4j.sys.entity.Role;
import com.frame4j.sys.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService  extends BaseService<RoleRepository, Role, String> {

}
