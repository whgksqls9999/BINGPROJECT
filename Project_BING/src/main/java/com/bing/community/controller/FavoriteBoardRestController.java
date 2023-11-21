package com.bing.community.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bing.community.model.dto.FavoriteBoard;
import com.bing.community.model.dto.FavoriteLocation;
import com.bing.community.model.service.FavoriteBoardService;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/favboard")
@Api(tags = "찜_게시글 컨트롤러")
public class FavoriteBoardRestController {

	@Autowired
	private FavoriteBoardService favBoardService;

	// 사용자 게시글 찜 목록 가져오기
	@GetMapping("/{writername}")
	public ResponseEntity<?> pickUserFavLocation(@PathVariable String writername) {
		List<FavoriteBoard> list = favBoardService.pickUserBoard(writername);
		if (list != null || list.size() != 0) {
			return new ResponseEntity<List<FavoriteBoard>>(list, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	// 장소 찜 등록하기
	@PostMapping("/addfavboard")
	public ResponseEntity<?> addFavoriteBoard(@RequestBody FavoriteBoard favBoard) {
		System.out.println(favBoard);
		int result = favBoardService.insertFavoriteBoard(favBoard);
		if (result > 0) {
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}

	// 장소 찜 삭제하기
	@DeleteMapping("/deletefavboard/{favorite_boardId}")
	public ResponseEntity<?> removeFavoriteBoard(@PathVariable int favorite_boardId) {
		int result = favBoardService.deleteFavoriteBoard(favorite_boardId);

		if (result > 0) {
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}

}
