package com.bing.community.model.service;

import java.util.List;

import com.bing.community.model.dto.User;

public interface UserService {

	// 모든 유저 조회
	List<User> getUserList();
	
	// 닉네임으로 유저 조회
	User getUserByNickname(String nickname);
	
	// 이메일로 유저 조회
	User getUserByEmail(String email);
	
	// 유저 등록
	int registUser(User user);
	
	// 유저 수정
	int modifyUser(User user);
	
	// 유저 삭제
	int removeUser(String email);
}
