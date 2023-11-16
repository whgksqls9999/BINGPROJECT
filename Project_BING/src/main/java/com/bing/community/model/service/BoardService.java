package com.bing.community.model.service;

import java.util.List;

import com.bing.community.model.dto.Board;
import com.bing.community.model.dto.SearchCondition;

public interface BoardService {
	// 모든 게시글 조회
	List<Board> getBoardList();
	
	// 게시글 하나 조회
	Board getBoard(int id);
	
	//userid로 게시글 다 뽑아오기
	List<Board> pickUserBoard(String email);
	
	// 커뮤니티에 등록된 게시글 가져오기
	List<Board> getCommBoard(int community_id);

	// 게시글 등록
	int registBoard(Board board);
	
	// 게시글 수정
	int modifyBoard(Board board);
	
	// 게시글 삭제
	int removeBoard(int id);
	
	//게시글 검색
	List<Board> searchBoard(SearchCondition con);
}
