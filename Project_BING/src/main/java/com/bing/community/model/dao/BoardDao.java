package com.bing.community.model.dao;

import java.util.List;

import com.bing.community.model.dto.Board;

public interface BoardDao {
	
	// 모든 게시글 조회
	List<Board> selectAll();
	
	// 게시글 하나 조회
	Board selectOne(int id);
	
	// 게시글 등록
	int insertBoard(Board board);
	
	// 게시글 수정
	int updateBoard(Board board);
	
	// 게시글 조회수 count
	int updateViewCnt(int id);
	
	// 게시글 삭제
	int deleteBoard(int id);
}
