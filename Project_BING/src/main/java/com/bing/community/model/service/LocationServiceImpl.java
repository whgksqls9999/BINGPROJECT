package com.bing.community.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bing.community.model.dao.LocationDao;
import com.bing.community.model.dto.Location;
import com.bing.community.model.dto.SearchCondition;

@Service
public class LocationServiceImpl implements LocationService{

	@Autowired
	LocationDao locationDao;
	
	@Override
	public List<Location> getLocationList() {
		return locationDao.selectAll();
	}

	@Override
	public Location getLocation(int id) {
		return locationDao.selectOne(id);
	}

	@Override
	public int registLocation(Location location) {
		return locationDao.insertLocation(location);
	}

	@Override
	public int modifyLocation(Location location) {
		return locationDao.updateLocation(location);
	}

	@Override
	public int removeLocation(int location_id) {
		return locationDao.deleteLocation(location_id);
	}

	@Override
	public List<Location> searchLocation(SearchCondition con) {
		return locationDao.searchLocation(con);
	}

}
