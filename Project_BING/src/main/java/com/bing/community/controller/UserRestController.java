package com.bing.community.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

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

import com.bing.community.model.dto.User;
import com.bing.community.model.service.UserService;

@RestController
@RequestMapping("/user")
public class UserRestController {
  @Autowired
	UserService userService;

	@GetMapping("/")
	public List<User> userList() {
		return userService.getUserList();
	}

	@GetMapping("/{user_email}")
	public ResponseEntity<?> getUser(@PathVariable String user_email) {
		User get = userService.getUser(user_email);
		if (get == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<User>(get, HttpStatus.OK);
		}
	}

	@PostMapping("/")
	public ResponseEntity<?> signUp(User user) {
		int result = userService.registUser(user);
		if (result > 0) {
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/{user_email}")
	public ResponseEntity<?> modifyUser(@RequestBody User user, @PathVariable String user_email) {
		user.setEmail(user_email);
		int result = userService.modifyUser(user);
		if (result > 0) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/{user_email}")
	public ResponseEntity<?> deleteUser(@PathVariable String user_email) {
		int result = userService.removeUser(user_email);
		if (result > 0) {
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody User user, HttpSession session) {
		User dbUser = userService.getUser(user.getEmail());

		if (dbUser == null || !(dbUser.getPw().equals(user.getPw()))) {
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		} else {
			session.setAttribute("loginUser", user);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
	}

	@GetMapping("/logout")
	public ResponseEntity<Void> logout(HttpSession session) {
		session.invalidate();

		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
