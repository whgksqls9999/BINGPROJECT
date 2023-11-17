package com.bing.community.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.bing.community.util.JwtUtil;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;


    @GetMapping("/")
    public List<User> userList() {
        return userService.getUserList();
    }

    @GetMapping("/{nickname}")
    public ResponseEntity<?> getUser(@PathVariable String nickname) {
        User get = userService.getUser(nickname);
        if (get == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<User>(get, HttpStatus.OK);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> signUp(@RequestBody User user) {
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

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody User user) {
        Map<String, Object> result = new HashMap<>();
        HttpStatus status = null;

        // 유효한 사용자, 비밀번호 검사
        User dbUser = userService.getUser(user.getEmail());
        if (dbUser == null || !(dbUser.getPw().equals(user.getPw()))) {
            result.put("access-token", null);
            result.put("message", FAIL);
            status = HttpStatus.NO_CONTENT;
        } else {
            result.put("access-token", jwtUtil.createToken("email", user.getEmail()));
            result.put("message", SUCCESS);
            status = HttpStatus.ACCEPTED;
        }

        return new ResponseEntity<Map<String, Object>>(result, status);
    }

    @GetMapping("/logout")
    public ResponseEntity<Void> logout() {
//        session.invalidate();

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}