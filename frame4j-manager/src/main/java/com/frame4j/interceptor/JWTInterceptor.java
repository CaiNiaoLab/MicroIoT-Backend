package com.frame4j.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.frame4j.common.annotation.PassToken;
import com.frame4j.common.annotation.UserLoginToken;
import com.frame4j.common.utils.JWTUtil;
import com.frame4j.common.utils.StringUtils;
import com.frame4j.sys.entity.User;
import com.frame4j.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
/**
 * @author mj
 * @date 2018-7-17 15:56:11
 */

@Component
public class JWTInterceptor implements HandlerInterceptor {

    @Autowired
    UserService userService;

    private static final String JWT_SIGNATURE = "123456";

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                             Object object) throws Exception {
        //添加跨域CORS
        //httpServletResponse.addHeader("Access-Control-Max-Age", "1800");//30 min
       // httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        //httpServletResponse.setHeader("Access-Control-Allow-Headers", "X-Requested-With,content-type,Content-Type,token");
        //httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        //httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
        String token = httpServletRequest.getHeader("Authorization");// 长久 token
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();

        // 检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }

        // 检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            String tokenId = "";
            if (userLoginToken != null) {
                if (userLoginToken.required()) {
                    // 执行认证
                    if (StringUtils.isEmpty(token)) {
                        //throw new RuntimeException("msg:无token，请重新登录");
                        httpServletResponse.setHeader("msg", "无token，请重新登录");
                        throw new RuntimeException("msg:无token，请重新登录");
                    }
                    // 获取 token 中的 user id
                    try {
                        tokenId = JWT.decode(token).getAudience().get(0);
                    } catch (JWTDecodeException j) {
                        httpServletResponse.setHeader("msg", "token解析失败");
                        throw new RuntimeException("msg:token解析失败");
                    }

                    // 获取用户信息
                    User user = userService.get(tokenId);
                    if (user == null) {
                        httpServletResponse.setHeader("msg", "用户不存在，请重新登录");
                        throw new RuntimeException("msg:用户不存在，请重新登录");
                    }

                    // 验证 token
                    JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(JWT_SIGNATURE)).build();
                    //1、验证token 若临时token不过期，则返回就得token，否则走catch验证永久token是否过期，过期则返回登录否则则返回新的一对token。
                    try {
                        jwtVerifier.verify(token);
                        httpServletResponse.setHeader("Authorization", token);
                    } catch (JWTVerificationException e) {
                        try {
                            jwtVerifier.verify(token);
                            tokenId = JWT.decode(token).getAudience().get(0);
                            String new_token = JWTUtil.getTokenWithLong(tokenId);
                            httpServletResponse.setHeader("Authorization", new_token);
                        } catch (JWTVerificationException e1) {
                            httpServletResponse.setHeader("msg", "token已过期");
                            throw new RuntimeException("msg:token已过期");
                        }

                    }
                    return true;
                }
            }
        } else {
            return true;
        }
        return true;

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }
}