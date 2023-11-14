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

import com.bing.community.model.dto.Favorite;
import com.bing.community.model.service.FavoriteService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/favorite")
@Api(tags = "찜 컨트롤러")
public class FavoriteRestController {

	@Autowired
	private FavoriteService favoriteService;

	/**
	 * 해당 유저가 찜한 목록을 불러온다.
	 * 
	 * @param userId
	 * @return
	 */
	@GetMapping("/user/{userEmail}")
	public ResponseEntity<?> getUserFavorites(@PathVariable String userEmail) {
		List<Favorite> list = favoriteService.getFavoriteList(userEmail);
		
		if (list == null || list.size() == 0) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Favorite>>(list, HttpStatus.OK);
	}

	/**
	 * 모든 유저의 찜 정보 중, 특정 찜 정보를 불러온다.
	 * 
	 * @param favoriteId
	 * @return
	 */
	@GetMapping("/favorite/{favoriteId}")
	public ResponseEntity<?> getFavorite(@PathVariable int favoriteId) {
		Favorite favor = favoriteService.getFavorite(favoriteId);
		
		if (favor == null) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Favorite>(favor, HttpStatus.OK);
	}

	/**
	 * 찜 정보를 등록한다.
	 * 
	 * @param favorite
	 * @return
	 */
	@PostMapping("/")
	public ResponseEntity<?> registFavorite(@RequestBody Favorite favorite) {
		int result = favoriteService.registFavorite(favorite);

		if (result > 0) {
			return new ResponseEntity<Favorite>(favorite, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}

	/**
	 * 찜 정보를 수정한다.
	 * 
	 * @param favorite
	 * @return
	 */
	@PutMapping("/")
	public ResponseEntity<?> modifyFavorite(@RequestBody Favorite favorite) {
		int result = favoriteService.modifyFavorite(favorite);

		if (result > 0) {
			return new ResponseEntity<Favorite>(favorite, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}

	/**
	 * 찜 정보를 삭제한다.
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{favoriteId}")
	public ResponseEntity<?> removeFavorite(@PathVariable int favoriteId) {
		int result = favoriteService.removeFavorite(favoriteId);

		if (result > 0) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}
}