package com.bing.community.model.service;

import java.util.List;

import com.bing.community.model.dto.Community;

public interface CommunityService {
	
	List<Community> getCommunityList();
	
	Community getCommunity(int community_id);
	
	int registCommunity(Community community);
	
	int modifyCommunity(Community community);
	
	int removeCommunity(int community_id);
}
