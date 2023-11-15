<<<<<<< HEAD
//package com.bing.community.controller;
//
//import java.util.List;
//
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.ssafy.board.model.dto.User;
//import com.ssafy.board.model.service.UserService;
//
//@RestController
//@RequestMapping("/api-user")
//public class UserRestController {
//	
//	//UserService 라고 하는 친구를 주입
//	@Autowired
//	private UserService userService;
//	
//	//전체유저 가져와
//	@GetMapping("users")
//	public List<User> userList() {
//		return userService.getUserList();
//	}
//	
//	//회원가입을 해보자
//	@PostMapping("signup")
//	public ResponseEntity<Integer> signup(User user) {
//		int result = userService.signup(user);
//		
//		//result가 0이면 등록 안됨
//		//result가 1이면 등록됨
//		return new ResponseEntity<Integer>(result, HttpStatus.CREATED);
//	}
//	
//	
//	@PostMapping("login")
//	public ResponseEntity<?> login(User user, HttpSession session) {
//		User tmp = userService.login(user);
//		//로그인 실패 (잘못했어)
//		if(tmp == null)
//			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED); //로그인 페이지로 다시 튕기게 만듬
//		
//		session.setAttribute("loginUser", tmp.getName());
//		return new ResponseEntity<String>(tmp.getName(), HttpStatus.OK);
//	}
//	
//	@GetMapping("logout")
//	public ResponseEntity<Void> logout(HttpSession session) {
////		session.removeAttribute("loginUser");
//		session.invalidate();
//		
//		return new ResponseEntity<Void>(HttpStatus.OK);
//	}
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//}
=======
package com.bing.community.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserRestController {

}
>>>>>>> 121f4bf (locationDao,service,serviceImpl수정, location,communityRestController작성, communityMapper작성)
