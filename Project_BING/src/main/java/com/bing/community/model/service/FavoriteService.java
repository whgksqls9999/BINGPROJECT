package com.bing.community.model.service;

import java.util.List;

import com.bing.community.model.dto.Favorite;

public interface FavoriteService {
	// 해당 유저의 찜 목록 조회
	List<Favorite> getLikeList(int userId);
	
	// 해당 유저의 찜 하나 조회
	Favorite getLike(int userId);
	
	// 찜 등록
	int registLike(int userId, Favorite like);
	
	// 찜 수정
	int modifyLike(int userId, Favorite like);
	
	// 찜 삭제
	int removeLike(int userId, int likeId);
}
