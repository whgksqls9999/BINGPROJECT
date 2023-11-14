package com.bing.community.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.bing.community.model.dao.FavoriteDao;
import com.bing.community.model.dto.Favorite;
@Service
public class FavoriteServiceImpl implements FavoriteService{
	
	@Autowired
	FavoriteDao favoriteDao;

	@Override
	public List<Favorite> getFavoriteList(String userId) {
		return favoriteDao.selectAll(userId);
	}

	@Override
	public Favorite getFavorite(int favoriteId) {
		return favoriteDao.selectOne(favoriteId);
	}

	@Override
	public int registFavorite(Favorite favorite) {
		return favoriteDao.insertFavorite(favorite);
	}

	@Override
	public int modifyFavorite(Favorite favorite) {
		return favoriteDao.updateFavorite(favorite);
	}

	@Override
	public int removeFavorite(int favoriteId) {
		return favoriteDao.deleteFavorite(favoriteId);
	}

}
