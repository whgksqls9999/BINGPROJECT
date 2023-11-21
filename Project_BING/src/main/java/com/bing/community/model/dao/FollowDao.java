package com.bing.community.model.dao;

import java.util.List;

import com.bing.community.model.dto.Follow;

public interface FollowDao {

	// 팔로우 등록
	int insertFollow(Follow follow);
	
	// 팔로우 삭제
	int deleteFollow(int follow_id);
	
	// 유저 팔로잉 목록 조회
	List<Follow> selectFollowing(String user_email);

	// 유저 팔로워 목록 조회
	List<Follow> selectFollower(String user_email);
}
