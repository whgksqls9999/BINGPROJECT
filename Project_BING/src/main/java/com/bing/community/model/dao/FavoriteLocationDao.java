package com.bing.community.model.dao;

import java.util.List;

import com.bing.community.model.dto.FavoriteLocation;

public interface FavoriteLocationDao {

	//사용자 장소 찜 목록 가져오기
	List<FavoriteLocation> pickUserLocation(String user_email);
	
	//장소 찜 등록하기
	int insertFavoriteLocation(FavoriteLocation favlocation);
	
	//장소 찜 삭제하기
	int deleteFavoriteLocation(int favorite_locationId);
}
