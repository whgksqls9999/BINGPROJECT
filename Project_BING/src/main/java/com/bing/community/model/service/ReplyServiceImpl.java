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
	
	@Override
	public List<Reply> getReplyList() {
		return replyDao.selectAll();
	}

	@Override
	public Reply getReply(int id) {
		return replyDao.selectOne(id);
	}

	@Override
	public int registReply(Reply reply) {
		return replyDao.insertReply(reply);
	}

	@Override
	public int modifyReply(Reply reply) {
		return replyDao.updateReply(reply);
	}

	@Override
	public int removeReply(int id) {
		return replyDao.deleteReply(id);
	}

}
