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
@Api(tags="찜 컨트롤러")
public class FavoriteRestController {
	
	@Autowired
	private FavoriteService favoriteService;
	
	/**	
	 * 해당 유저가 찜한 목록을 불러온다.
	 * @param userId
	 * @return
	 */
	@GetMapping("/users/{id}")
	public ResponseEntity<?> getUserFavorites(@PathVariable("id") String id){
		System.out.println(id);
		List<Favorite> list = favoriteService.getFavoriteList(id);
		if (list == null || list.size() == 0) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		
		System.out.println(list);
		return new ResponseEntity<List<Favorite>>(list, HttpStatus.OK);
	}
	
	/**
	 * 모든 유저의 찜 정보 중, 특정 찜 정보를 불러온다.
	 * @param favoriteId
	 * @return
	 */
	@GetMapping("/favorites/{id}")
	public ResponseEntity<?> getFavorite(@PathVariable int id){
		Favorite favor = favoriteService.getFavorite(id);
		
		if (favor == null) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Favorite>(favor,HttpStatus.OK);
	}
	/**
	 * 찜 정보를 등록한다.
	 * @param favorite
	 * @return
	 */
	@PostMapping("/")
	public ResponseEntity<?> registFavorite(@RequestBody Favorite favorite){
		int result = favoriteService.registFavorite(favorite);
		
		if (result > 0) {
			return new ResponseEntity<Favorite>(favorite, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/")
	public ResponseEntity<?> modeifyFavorite(@RequestBody Favorite favorite){
		int result = favoriteService.modifyFavorite(favorite);
		
		if (result > 0) {
			return new ResponseEntity<Favorite>(favorite, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> removeFavorite(@PathVariable int id){
		int result = favoriteService.removeFavorite(id);
		
		if (result > 0) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}
	
}
