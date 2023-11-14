package com.bing.community.model.dao;

import java.util.List;

import com.bing.community.model.dto.Favorite;

public interface FavoriteDao {

	// 해당 유저의 찜 목록 조회
	List<Favorite> selectAll(int userId);
	
	// 해당 유저의 찜 하나 조회
	Favorite selectOne(int userId);
	
	// 찜 등록
	int insertFavorite(int userId, Favorite favorite);
	
	// 찜 수정
	int updateFavorite(int userId, Favorite favorite);
	
	// 찜 삭제
	int deleteFavorite(int userId, int favoriteId);
}
