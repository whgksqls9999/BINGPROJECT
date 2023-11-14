package com.bing.community.model.service;

import java.util.List;

import com.bing.community.model.dto.Location;

public interface LocationService {
	
	// 모든 장소 조회
	List<Location> getLocationList();
	
	// 장소 하나 조회
	Location getLocation(int id);
	
	// 장소 등록
	Location registLocation(Location location);
	
	// 장소 수정
	Location modifyLocation(Location location);
	
	// 장소 삭제
	Location removeLocation(int id);
}
