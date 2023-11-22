package com.bing.community.model.service;

import java.util.List;

import com.bing.community.model.dto.FavoriteLocation;

public interface FavoriteLocationService {
	
	//사용자 찜한 장소 가져오기
	List<FavoriteLocation> pickUserLocation(String writername);
	
	//장소 찜하기
	int insertFavoriteLocation(FavoriteLocation favLocation);
	
	//장소 찜 삭제하기
	int deleteFavoriteLocation(int favorite_locationId);
}
