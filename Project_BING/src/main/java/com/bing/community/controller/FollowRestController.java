package com.bing.community.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bing.community.model.dto.Follow;
import com.bing.community.model.service.FollowService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/follow")
@Api(tags = "팔로우 컨트롤러")
public class FollowRestController {

	@Autowired
	private FollowService followService;

	@GetMapping("/{email}/following")
	public ResponseEntity<?> getUserFollowingList(@PathVariable String email) {
		List<Follow> list = followService.getFollowingList(email);
		if(list == null || list.size() == 0) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Follow>>(list, HttpStatus.OK);
	}
	@GetMapping("/{email}/follower")
	public ResponseEntity<?> getUserFollowerList(@PathVariable String email) {
		List<Follow> list = followService.getFollowerList(email);
		if(list == null || list.size() == 0) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Follow>>(list, HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<?> registFollow(@RequestBody Follow follow) {
		int result = followService.registFollow(follow);
		if (result > 0) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/{follow_id}")
	public ResponseEntity<?> removeFollow(@PathVariable int follow_id) {
		int result = followService.removeFollow(follow_id);
		if (result > 0) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}

}