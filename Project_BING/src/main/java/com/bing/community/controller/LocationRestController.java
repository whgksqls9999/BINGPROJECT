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

import com.bing.community.model.dto.Location;
import com.bing.community.model.service.LocationService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/location")
@Api(tags = "장소 컨트롤러")
public class LocationRestController {

	@Autowired
	private LocationService locationService;

	// 모든 장소 조회
	@GetMapping("/list")
	public List<Location> getAllLocation() {
		return locationService.getLocationList();
	}

	@GetMapping("/{location_id}")
	public ResponseEntity<?> getLocation(@PathVariable int location_id) {
		Location get = locationService.getLocation(location_id);
		if (get != null) {
			return new ResponseEntity<Location>(get, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping("/regist")
	public ResponseEntity<?> registLocation(Location location) {
		int result = locationService.registLocation(location);
		if (result > 0) {
			return new ResponseEntity<Location>(location, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/modify/{location_id}")
	public ResponseEntity<?> modifyLocation(Location location) {
		int result = locationService.modifyLocation(location);
		if (result > 0) {
			return new ResponseEntity<Location>(location, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/delete/{location_id}")
	public ResponseEntity<?> deleteLocation(@PathVariable int location_id) {
		int result = locationService.removeLocation(location_id);
		if (result > 0) {
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}

}
