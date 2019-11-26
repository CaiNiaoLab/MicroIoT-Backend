package com.frame4j.common.utils;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

public class JWTUtil {

	private static final String JWT_SIGNATURE = "123456";

	public static String getTokenWithTemp(String id)
			throws IllegalArgumentException, JWTCreationException, UnsupportedEncodingException {
		String token = "";
		token = JWT.create().withAudience(id).// 将 user id 保存到 token 里面
				withExpiresAt(new Date(System.currentTimeMillis() + 15 * 60 * 1000)).// 过期时间 15 分钟
				withIssuedAt(new Date())// 请求JWT的时间，可用来判断JWT是否过期
				.sign(Algorithm.HMAC256(JWT_SIGNATURE));// 以 password 作为 token 的密钥 默认xxxxxx
		return token;
	}

	public static String getTokenWithLong(String id)
			throws IllegalArgumentException, JWTCreationException, UnsupportedEncodingException {
		String token = "";
		token = JWT.create().withAudience(id).// 将 user id 保存到 token 里面
				withExpiresAt(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000)).// 过期时间 15 分钟
				withIssuedAt(new Date())// 请求JWT的时间，可用来判断JWT是否过期
				.sign(Algorithm.HMAC256(JWT_SIGNATURE));// 以 password 作为 token 的密钥 默认xxxxxx
		return token;
	}

	public static String getUserIdByToken(String token){
		String userId = JWT.decode(token).getAudience().get(0);
		return userId;
	}
}
