package com.bing.community.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bing.community.model.dao.LocationDao;
import com.bing.community.model.dto.Location;

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
	public Location registLocation(Location location) {
		return locationDao.insertLocation(location);
	}

	@Override
	public Location modifyLocation(Location location) {
		return locationDao.updateLocation(location);
	}

	@Override
	public Location removeLocation(int id) {
		return locationDao.deleteLocation(id);
	}

}
