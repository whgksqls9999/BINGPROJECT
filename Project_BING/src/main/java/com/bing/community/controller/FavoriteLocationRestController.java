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

import com.bing.community.model.dto.FavoriteLocation;
import com.bing.community.model.service.FavoriteLocationService;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/favlocation")
@Api(tags = "찜_장소 컨트롤러")
public class FavoriteLocationRestController {

	@Autowired
	private FavoriteLocationService favLocationService;

	// 사용자 장소 찜 목록 가져오기
	@GetMapping("/{user_email}")
	public ResponseEntity<?> pickUserFavLocation(@PathVariable String user_email) {
		List<FavoriteLocation> list = favLocationService.pickUserLocation(user_email);
		if (list != null || list.size() != 0) {
			return new ResponseEntity<List<FavoriteLocation>>(list, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	// 장소 찜 등록하기
	@PostMapping("/addfavlocation")
	public ResponseEntity<?> addFavoriteLocation(@RequestBody FavoriteLocation favLocation) {
		int result = favLocationService.insertFavoriteLocation(favLocation);
		if (result > 0) {
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}

	// 장소 찜 삭제하기
	@DeleteMapping("deletefavlocation/{favorite_locationId}")
	public ResponseEntity<?> removeFavoriteLocation(@PathVariable int favorite_locationId) {
		int result = favLocationService.deleteFavoriteLocation(favorite_locationId);

		if (result > 0) {
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}

}
