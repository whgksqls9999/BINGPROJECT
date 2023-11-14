package com.bing.community.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bing.community.model.dao.BoardDao;
import com.bing.community.model.dto.Board;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	BoardDao boardDao;
	
	// 모든 게시글 조회
	public List<Board> getBoardList(){
		return boardDao.selectAll();
	}
	
	// 게시글 하나 조회
	public Board getBoard(int id) {
		return boardDao.selectOne(id);
	}
	
	// 게시글 등록
	public int registBoard(Board board) {
		return boardDao.insertBoard(board);
	}
	
	// 게시글 수정
	public int modifyBoard(Board board) {
		return boardDao.updateBoard(board);
	}
	
	// 게시글 삭제
	@Override
	public int removeBoard(int id) {
		return boardDao.deleteBoard(id);
	}
}
