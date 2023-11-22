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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bing.community.model.dto.Reply;
import com.bing.community.model.service.ReplyService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/reply")
@Api(tags = "댓글 컨트롤러")
public class ReplyRestController {
	
	@Autowired
	private ReplyService replyService;
	
	// 댓글 전체 목록 가져오기
	@GetMapping("/")
	public List<Reply> replyList() {
		return replyService.getReplyList();
	}

	// 특정 게시글에 달린 댓글 목록 가져오기
	@GetMapping("/board/{board_id}")
	public ResponseEntity<?> getBoardReply(@PathVariable int board_id) {
		List<Reply> list = replyService.getBoardReply(board_id);
		if (list == null || list.size() == 0) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<Reply>>(list, HttpStatus.OK);
		}
	}
	
	// 댓글 하나 가져오기
	@GetMapping("/{reply_id}")
	public ResponseEntity<?> getReply(@PathVariable int reply_id) {
		Reply get = replyService.getReply(reply_id);
		if (get == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Reply>(get, HttpStatus.OK);
		}
	}

	@PostMapping("/")
	public ResponseEntity<?> signUp(@RequestBody Reply reply) {
		int result = replyService.registReply(reply);
		if (result > 0) {
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/{reply_id}")
	public ResponseEntity<?> modifyReply(@RequestBody Reply reply, @PathVariable int reply_id) {
		reply.setReply_id(reply_id);
		int result = replyService.modifyReply(reply);
		if (result > 0) {
			return new ResponseEntity<Reply>(reply, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/{reply_id}")
	public ResponseEntity<?> deleteReply(@PathVariable int reply_id) {
		int result = replyService.removeReply(reply_id);
		if (result > 0) {
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}

}
