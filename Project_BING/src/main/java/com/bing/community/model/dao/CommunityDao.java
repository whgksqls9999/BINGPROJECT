package com.bing.community.model.dao;

import java.util.List;

import com.bing.community.model.dto.Community;

public interface CommunityDao {

	List<Community> selectAll();

	Community selectOne(int community_id);

	int insertCommunity(Community community);

	int updateCommunity(Community community);

	int deleteCommunity(int community_id);

}
