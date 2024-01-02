package com.bing.community.model.service;

import java.util.List;

import com.bing.community.model.dto.Location;
import com.bing.community.model.dto.SearchCondition;

public interface LocationService {

	// 모든 장소 조회
	List<Location> getLocationList();

	// 장소 하나 조회
	Location getLocation(int location_id);

	// 장소 등록
	int registLocation(Location location);

	// 장소 수정
	int modifyLocation(Location location);

	// 장소 삭제
	int removeLocation(int location_id);

	// 장소 검색
	List<Location> searchLocation(SearchCondition con);
}
