package com.bing.community.model.service;

import java.util.List;

import com.bing.community.model.dto.Reply;

public interface ReplyService {

	// 모든 리뷰 댓글
	List<Reply> getReplyList();
	
	// 댓글 하나 조회
	Reply getReply(int id);
	
	//userid로 댓글 뽑아오기
	List<Reply> pickUserReply(String writer);
	
	// 댓글 등록
	int registReply(Reply reply);
	
	// 댓글 수정
	int modifyReply(Reply reply);
	
	// 댓글 삭제
	int removeReply(int id);
}
