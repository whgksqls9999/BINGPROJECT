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

import com.bing.community.model.dto.Board;
import com.bing.community.model.service.BoardService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/board")
@Api(tags = "게시판 컨트롤러")
public class BoardRestController {

	@Autowired
	private BoardService boardService;

	//게시판 전체 조회
	@GetMapping("/")
	public List<Board> boardList() {
		return boardService.getBoardList();
	}

	//게시글 하나 가져오기
	@GetMapping("/{board_id}")
	public ResponseEntity<?> getBoard(@PathVariable int board_id) {
		Board get = boardService.getBoard(board_id);
		if (get == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Board>(get, HttpStatus.OK);
		}
	}

	//게시글 넣기
	@PostMapping("/insert")
	public ResponseEntity<?> signUp(Board board) {
		int result = boardService.registBoard(board);
		if (result > 0) {
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/modify/{board_id}")
	public ResponseEntity<?> modifyBoard(@RequestBody Board board, @PathVariable int board_id) {
		board.setBoard_id(board_id);
		int result = boardService.modifyBoard(board);
		if (result > 0) {
			return new ResponseEntity<Board>(board, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/delete/{board_id}")
	public ResponseEntity<?> deleteBoard(@PathVariable int board_id) {
		int result = boardService.removeBoard(board_id);
		if (result > 0) {
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}

}
// class끝
