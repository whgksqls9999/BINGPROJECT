package com.bing.community.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bing.community.model.dao.BoardDao;
import com.bing.community.model.dto.Board;

@Service
public class BoardServiceImpl {
	
	@Autowired
	BoardDao boardDao;
	
	// 모든 게시글 조회
	List<Board> getBoardList(){
		return boardDao.selectAll();
	}

}
