package com.bing.community.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bing.community.model.dto.Community;
import com.bing.community.model.service.CommunityService;

@RestController
@RequestMapping("/community")
public class CommunityRestController {

	@Autowired
	private CommunityService cService;

	// 커뮤니티 다 가져와
	@GetMapping("/list")
	public List<Community> getAllCommunity() {
		return cService.getCommunityList();
	}

	@GetMapping("/{community_id}")
	public ResponseEntity<?> getCommunity(@PathVariable int community_id) {
		Community get = cService.getCommunity(community_id);
		if (get == null) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Community>(get, HttpStatus.OK);
		}
	}

	@PostMapping("/regist")
	public ResponseEntity<?> registCommunity(Community community) {
		int result = cService.registCommunity(community);

		if (result > 0) {
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/modify/{community_id}")
	public ResponseEntity<?> modifyCommunity(@PathVariable int community_id, Community community) {
		community.setCommunity_id(community_id);
		int result = cService.modifyCommunity(community);
		if (result > 0) {
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}

	// 커뮤니티 삭제할 일은 없지만, 삭제하려면 커뮤니티에 속한 모든 게시글을 삭제한 후 해당 커뮤니티를 삭제해야 한다.
	// board 테이블의 community_id가 community 테이블의 외래키와 연결되어 있기 때문.
	@DeleteMapping("/delete/{community_id}")
	public ResponseEntity<?> removeCommunity(@PathVariable int community_id) {
		int result = cService.removeCommunity(community_id);
		if (result > 0) {
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}
}
