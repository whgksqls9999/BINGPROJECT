package com.bing.community.util;

import java.io.UnsupportedEncodingException;
import java.security.SignatureException;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtil {

	private static final String SALT = "BIN/G";

	// 1. 토큰 생성
	// key, value를 필요하다면 여러개 받게 하기
	public String createToken(String key, String value) {
		try {
			return Jwts.builder().setHeaderParam("alg", "HS256").setHeaderParam("typ", "JWT").claim(key, value)
					.signWith(SignatureAlgorithm.HS256, SALT.getBytes("UTF-8")).compact();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 2. 토큰 유효성 검사
	public void valid(String token) {
		try {
			Jwts.parser().setSigningKey(SALT.getBytes("UTF-8")).parseClaimsJws(token);
		} catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | IllegalArgumentException
				| UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
