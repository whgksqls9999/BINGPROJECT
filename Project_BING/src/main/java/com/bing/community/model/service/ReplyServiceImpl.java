 package com.bing.community.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bing.community.model.dao.ReplyDao;
import com.bing.community.model.dto.Reply;

@Service
public class ReplyServiceImpl implements ReplyService{

	@Autowired
	ReplyDao replyDao; 
	
	//댓글 하나 가져오기
	@Override
	public List<Reply> getReplyList() {
		return replyDao.selectAll();
	}

	//댓글 디테일 보기
	@Override
	public Reply getReply(int id) {
		return replyDao.selectOne(id);
	}
	
	//userId로 댓글 뽑아오기
	@Override
	public List<Reply> pickUserReply(String writer) {
		return replyDao.pickUserReply(writer);
	}

	//댓글 등록하기
	@Override
	public int registReply(Reply reply) {
		return replyDao.insertReply(reply);
	}

	//댓글 수정하기
	@Override
	public int modifyReply(Reply reply) {
		return replyDao.updateReply(reply);
	}

	//댓글 삭제하기
	@Override
	public int removeReply(int id) {
		return replyDao.deleteReply(id);
	}

	// 특정 게시글에 달린 댓글 목록 조회
	@Override
	public List<Reply> getBoardReply(int board_id) {
		return replyDao.selectBoardReply(board_id);
	}


}
