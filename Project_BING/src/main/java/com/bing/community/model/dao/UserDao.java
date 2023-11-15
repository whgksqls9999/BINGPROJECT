package com.bing.community.model.dao;

import java.util.List;

import com.bing.community.model.dto.User;

public interface UserDao {
	
	// 모든 유저 조회
	List<User> selectAll();
	
	// 유저 한명 조회
	User selectOne(String email);
	
	// 유저 등록(회원가입)
	int insertUser(User user);
	
	// 유저 수정(회원 정보 수정)
	int updateUser(User user);
	
	// 유저 삭제(탈퇴)
	int deleteUser(String email);
	
}
