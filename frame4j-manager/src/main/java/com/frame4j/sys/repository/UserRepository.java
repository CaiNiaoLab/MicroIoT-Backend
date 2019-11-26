package com.frame4j.sys.repository;


import com.frame4j.base.repository.BaseRepository;
import com.frame4j.sys.entity.User;

import java.util.Set;

public interface UserRepository extends BaseRepository<User, String> {
	User findByLoginNameAndPassword(String loginName, String password);
	User findByLoginName(String loginName);
}
