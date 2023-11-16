package com.bing.community.model.service;

import java.util.List;

import com.bing.community.model.dto.Favorite;

public interface FavoriteService {
	
	List<Favorite> getFavoriteList(String userId);
	
	Favorite getFavorite(int favoriteId);
	
	int registFavorite(Favorite favorite);
	
	int modifyFavorite(Favorite favorite);
	
	int removeFavorite(int favoriteId);
}
