package com.bing.community.model.dao;

import java.util.List;

import com.bing.community.model.dto.FavoriteBoard;

public interface FavoriteBoardDao {

	//사용자 찜 목록 다 가져오기
	List<FavoriteBoard> pickUserBoard(String user_email);
	
	//게시글 찜하기
	int insertFavoriteBoard(FavoriteBoard favBoard);
	
	//게시글 찜 삭제하기
	int deleteFavoriteBoard(int favorite_boardId);
}
