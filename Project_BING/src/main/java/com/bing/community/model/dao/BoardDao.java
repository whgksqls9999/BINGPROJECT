package com.bing.community.model.dao;

import java.util.List;

import com.bing.community.model.dto.Board;
import com.bing.community.model.dto.SearchCondition;

public interface BoardDao {

	// 모든 게시글 조회
	List<Board> selectAll();

	// 게시글 하나 조회
	Board selectOne(int id);

	// userid로 게시글 목록 뽑아오기
	List<Board> pickUserBoard(String writer);
	
	// 커뮤니티에 등록된 게시글 목록 가져오기
	List<Board> selectCommBoardList(int community_id);

	// 게시글 등록
	int insertBoard(Board board);

	// 게시글 수정
	int updateBoard(Board board);

	// 게시글 조회수 count
	int updateViewCnt(int id);

	// 게시글 삭제
	int deleteBoard(int id);

	// 게시글 검색
	List<Board> searchBoard(SearchCondition con);
}
