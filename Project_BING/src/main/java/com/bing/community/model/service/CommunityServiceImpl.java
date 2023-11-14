package com.bing.community.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bing.community.model.dao.CommunityDao;
import com.bing.community.model.dto.Community;

@Service
public class CommunityServiceImpl implements CommunityService {
	
	@Autowired
	CommunityDao communityDao;

	@Override
	public List<Community> getCommunityList() {
		return communityDao.selectAll();
	}

	@Override
	public Community getCommunity(int community_id) {
		return communityDao.selectOne(community_id);
	}

	@Override
	public int registCommunity(Community community) {
		return communityDao.insertCommunity(community);
	}

	@Override
	public int modifyCommunity(Community community) {
		return communityDao.updateCommunity(community);
	}

	@Override
	public int removeCommunity(int community_id) {
		return communityDao.deleteCommunity(community_id);
	}
}
