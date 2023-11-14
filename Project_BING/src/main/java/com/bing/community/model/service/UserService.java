package com.bing.community.model.service;

import java.util.List;

import com.bing.community.model.dto.User;

public interface UserService {

	// 모든 유저 조회
	List<User> getUserList();
	
	// 유저 하나 조회
	User getUser(String email);
	
	// 유저 등록
	int registUser(User user);
	
	// 유저 수정
	int modifyUser(User user);
	
	// 유저 삭제
	int removeUser(int id);
}
