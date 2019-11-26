package com.frame4j.sys.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 在线用户基本数据
 */
@Data
public class LineUser implements Serializable {
    // id
    private String id;
    // 登录名
    private String loginName;
    // 昵称
    private String nick;
    // 邮件
    private String email;
    // 手机号
    private String phone;
    // jwt-token
    private String token;
}
