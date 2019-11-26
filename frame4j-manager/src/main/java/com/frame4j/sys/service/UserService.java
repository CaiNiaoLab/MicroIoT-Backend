package com.frame4j.sys.service;

import com.frame4j.base.service.BaseService;
import com.frame4j.sys.entity.User;
import com.frame4j.sys.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@CacheConfig(cacheNames = "userService")
public class UserService extends BaseService<UserRepository, User, String> {

    @Autowired
    private UserRepository userRepository;

    /**
     * 通过用户名密码获取用户信息
     * @param user
     * @return
     */
    @Transactional(readOnly = true)
    public User findByLoginNameAndPassword(User user) {
        return userRepository.findByLoginNameAndPassword(user.getLoginName(), user.getPassword());
    }

    /**
     * 通过用户名获取用户信息
     * @param loginName
     * @return
     */
    @Transactional(readOnly = true)
    public User findByLoginName(String loginName) {
        return userRepository.findByLoginName(loginName);
    }

}
