package com.bing.community.model.dao;

import java.util.List;

import com.bing.community.model.dto.Favorite;

public interface FavoriteDao {

	// 특정 유저의 찜 목록 조회
	List<Favorite> selectAll(String id);
	
	// 찜 하나 조회
	Favorite selectOne(int favoriteId);
	
	// 찜 등록
	int insertFavorite(Favorite favorite);
	
	// 찜 수정
	int updateFavorite(Favorite favorite);
	
	// 찜 삭제
	int deleteFavorite(int favoriteId);
}
