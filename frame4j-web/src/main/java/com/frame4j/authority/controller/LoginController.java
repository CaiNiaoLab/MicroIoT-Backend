package com.frame4j.authority.controller;

import com.frame4j.common.utils.Frame4JResult;
import com.frame4j.common.utils.PasswordUtil;
import com.frame4j.sys.entity.User;
import com.frame4j.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 登陆页面
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    /**
     * 登陆方法
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Frame4JResult login(User user) {
        User reUser = userService.findByLoginNameAndPassword(user);
        if (reUser == null) {
            return Frame4JResult.build(233, "用户名或密码错误！");
        }
        return Frame4JResult.ok(reUser);
    }

    /**
     * 注册页面
     * @return
     */
    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String registerPage() {
        return "register";
    }

    /**
     * 注册方法
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public Frame4JResult register(User user) {
        String salt = UUID.randomUUID().toString();
        String newPassWord = PasswordUtil.md5(user.getPassword(), salt);
        user.setPassword(newPassWord);
        user.setSalt(salt);
        userService.save(user);
        return Frame4JResult.ok(user);
    }

}
