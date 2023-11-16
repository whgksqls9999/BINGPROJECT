package com.bing.community.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bing.community.model.dao.FavoriteBoardDao;
import com.bing.community.model.dto.FavoriteBoard;

@Service
public class FavoriteBoardServiceImpl implements FavoriteBoardService{
	
	@Autowired
	FavoriteBoardDao favBoardDao;

	@Override
	public List<FavoriteBoard> pickUserBoard(String writer_name) {
		return favBoardDao.pickUserBoard(writer_name);
	}

	@Override
	public int insertFavoriteBoard(FavoriteBoard favBoard) {
		return favBoardDao.insertFavoriteBoard(favBoard);
	}

	@Override
	public int deleteFavoriteBoard(int favorite_boardId) {
		return favBoardDao.deleteFavoriteBoard(favorite_boardId);
	}
	
	
	
	
	
	

}
