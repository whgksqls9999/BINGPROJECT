package com.bing.community.model.dao;

import java.util.List;

import com.bing.community.model.dto.Location;
import com.bing.community.model.dto.SearchCondition;

public interface LocationDao {

	// 모든 장소 조회
	List<Location> selectAll();
	
	// 장소 하나 조회
	Location selectOne(int location_id);
	
	// 장소 등록
	int insertLocation(Location location);
	
	// 장소 수정
	int updateLocation(Location location);
	
	// 장소 삭제
	int deleteLocation(int location_id);
	
	//장소 검색
	List<Location> searchLocation(SearchCondition con);
}
