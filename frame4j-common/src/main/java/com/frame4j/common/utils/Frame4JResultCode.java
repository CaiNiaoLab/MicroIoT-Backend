package com.frame4j.common.utils;

/**
 * 自定义状态码
 */
public class Frame4JResultCode {

    // 成功状态码
    public static final int SUCCESS = 200;
    public static final String SUCCESS_TEXT = "操作成功!";

    // -------------------失败状态码----------------------
    // 参数错误
    public static final int PARAMS_IS_NULL = 601;// 参数为空
    public static final int PARAMS_NOT_COMPLETE = 602; // 参数不全
    public static final int PARAMS_TYPE_ERROR = 603; // 参数类型错误
    public static final int PARAMS_IS_INVALID = 604; // 参数无效

    // 用户错误
    public static final int USER_NOT_EXIST = 701; // 用户不存在
    public static final String USER_NOT_EXIST_TEXT = "用户不存在!"; // 用户不存在
    public static final int USER_NOT_LOGGED_IN = 702; // 用户未登陆
    public static final String USER_NOT_LOGGED_IN_TEXT = "用户未登陆!"; // 用户未登陆
    public static final int USER_ACCOUNT_ERROR = 703; // 用户名或密码错误
    public static final String USER_ACCOUNT_ERROR_TEXT = "用户名或密码错误!"; // 用户名或密码错误
    public static final int USER_ACCOUNT_FORBIDDEN = 704; // 用户账户已被禁用
    public static final int USER_HAS_EXIST = 705;// 用户已存在
    public static final int USER_NOT_ADMIN = 706; // 用户非管理员
    public static final String USER_NOT_ADMIN_TEXT = "用户非管理员!"; // 用户非管理员

    // 业务错误
    public static final int BUSINESS_ERROR = 801;// 系统业务出现问题

    // 系统错误
    public static final int SYSTEM_INNER_ERROR = 1001; // 系统内部错误
    public static final String SYSTEM_INNER_ERROR_TEXT = "系统内部错误!"; // 系统内部错误

    // 数据错误
    public static final int DATA_NOT_FOUND = 1101; // 数据未找到
    public static final int DATA_IS_WRONG = 1102;// 数据有误
    public static final int DATA_ALREADY_EXISTED = 1103;// 数据已存在

    // 接口错误
    public static final int INTERFACE_INNER_INVOKE_ERROR = 1201; // 系统内部接口调用异常
    public static final int INTERFACE_OUTER_INVOKE_ERROR = 1202;// 系统外部接口调用异常
    public static final int INTERFACE_FORBIDDEN = 1203;// 接口禁止访问
    public static final int INTERFACE_ADDRESS_INVALID = 1204;// 接口地址无效
    public static final int INTERFACE_REQUEST_TIMEOUT = 1205;// 接口请求超时
    public static final int INTERFACE_EXCEED_LOAD = 1206;// 接口负载过高

    // 权限错误
    public static final int PERMISSION_NO_ACCESS = 1301;// 没有访问权限

    // 订单错误
    public static final int ORDER_NOT_FOUND = 1401;// 订单不存在
    public static final String ORDER_NOT_FOUND_TEXT = "订单不存在或未支付或已超时!";// 订单不存在
}
