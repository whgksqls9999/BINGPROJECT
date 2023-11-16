package com.bing.community.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bing.community.model.dto.Board;
import com.bing.community.model.dto.FavoriteBoard;
import com.bing.community.model.dto.FavoriteLocation;
import com.bing.community.model.dto.Reply;
import com.bing.community.model.service.BoardService;
import com.bing.community.model.service.FavoriteBoardService;
import com.bing.community.model.service.FavoriteLocationService;
import com.bing.community.model.service.ReplyService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/mypage")
@Api(tags="마이페이지 컨트롤러")
public class MyPageRestController {
	@Autowired
	private BoardService boardService;

	@Autowired
	private ReplyService replyService;

	@Autowired
	private FavoriteLocationService favlocationService;

	@Autowired
	private FavoriteBoardService favboardService;

	// 개인정보 수정은 나중에 front에서 userUseStore가져와서 처리할 것

	// 사용자가 작성한 게시글 가져오기
	@GetMapping("pickboard/{email}")
	public ResponseEntity<?> pickUserBoard(@PathVariable String email) {
		List<Board> list = boardService.pickUserBoard(email);

		if (list != null || list.size() != 0) {
			return new ResponseEntity<List<Board>>(list, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

	}

	// 사용자가 작성한 댓글 가져오기
	@GetMapping("pickreply/{writer}")
	public ResponseEntity<?> pickUserReply(@PathVariable String writer) {
		List<Reply> list = replyService.pickUserReply(writer);

		if (list != null || list.size() != 0) {
			return new ResponseEntity<List<Reply>>(list, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	// 사용자가 찜한 장소 가져오기
	@GetMapping("pickfavlocation/{user_email}")
	public ResponseEntity<?> pickUserFavLocation(@PathVariable String user_email) {
		List<FavoriteLocation> list = favlocationService.pickUserLocation(user_email);

		if (list != null || list.size() != 0) {
			return new ResponseEntity<List<FavoriteLocation>>(list, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

	}

	// 사용자가 찜한 게시글 가져오기
	@GetMapping("pickfavboard/{user_email}")
	public ResponseEntity<?> pickUserFavBoard(@PathVariable String user_email) {
		List<FavoriteBoard> list = favboardService.pickUserBoard(user_email);

		if (list != null || list.size() != 0) {
			return new ResponseEntity<List<FavoriteBoard>>(list, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
