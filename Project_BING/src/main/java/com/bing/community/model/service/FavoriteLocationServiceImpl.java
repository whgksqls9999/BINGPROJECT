package com.bing.community.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bing.community.model.dao.FavoriteLocationDao;
import com.bing.community.model.dto.FavoriteLocation;
@Service
public class FavoriteLocationServiceImpl implements FavoriteLocationService{
	
	@Autowired
	FavoriteLocationDao favLocationDao;

	@Override
	public List<FavoriteLocation> pickUserLocation(String user_email) {
		return favLocationDao.pickUserLocation(user_email);
	}

	@Override
	public int insertFavoriteLocation(FavoriteLocation favlocation) {
		return favLocationDao.insertFavoriteLocation(favlocation);
	}

	@Override
	public int deleteFavoriteLocation(int favorite_locationId) {
		return favLocationDao.deleteFavoriteLocation(favorite_locationId);
	}

}
