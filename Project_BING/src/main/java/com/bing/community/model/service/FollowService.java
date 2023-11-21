package com.bing.community.model.service;

import java.util.List;

import com.bing.community.model.dto.Follow;

public interface FollowService {

	// 팔로우 등록
	int registFollow(Follow follow);
	
	// 팔로우 취소
	int removeFollow(int follow_id);
	
	// 유저 팔로잉 목록 조회
	List<Follow> getFollowingList(String user_email);
	
	// 유저 팔로워 목록 조회
	List<Follow> getFollowerList(String user_email);
}
