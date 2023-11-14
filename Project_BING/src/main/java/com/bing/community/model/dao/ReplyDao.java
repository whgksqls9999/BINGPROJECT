package com.bing.community.model.dao;

import java.util.List;

import com.bing.community.model.dto.Reply;

public interface ReplyDao {
	
	// 모든 리뷰 댓글
	List<Reply> selectAll();
	
	// 댓글 하나 조회
	Reply selectOne(int id);
	
	// 댓글 등록
	int insertReply(Reply reply);
	
	// 댓글 수정
	int updateReply(Reply reply);
	
	// 댓글 삭제
	int deleteReply(int id);
}
