package com.bing.community.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bing.community.model.dao.FollowDao;
import com.bing.community.model.dao.UserDao;
import com.bing.community.model.dto.Follow;
import com.bing.community.model.dto.User;

@Service
public class FollowServiceImpl implements FollowService {

	@Autowired
	FollowDao followDao;

	@Override
	public int registFollow(Follow follow) {
		return followDao.insertFollow(follow);
	}

	@Override
	public int removeFollow(int follow_id) {
		return followDao.deleteFollow(follow_id);
	}

	@Override
	public List<Follow> getFollowingList(String user_email) {
		return followDao.selectFollowing(user_email);
	}

	@Override
	public List<Follow> getFollowerList(String user_email) {
		return followDao.selectFollower(user_email);
	}
}
