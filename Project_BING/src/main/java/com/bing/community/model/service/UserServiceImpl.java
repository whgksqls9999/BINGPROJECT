package com.bing.community.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bing.community.model.dao.UserDao;
import com.bing.community.model.dto.User;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDao userDao;

	@Override
	public List<User> getUserList() {
		return userDao.selectAll();
	}

	@Override
	public User getUser(String nickname) {
		return userDao.selectOne(nickname);
	}

	@Override
	public int registUser(User user) {
		return userDao.insertUser(user);
	}

	@Override
	public int modifyUser(User user) {
		return userDao.updateUser(user);
	}

	@Override
	public int removeUser(String email) {
		return userDao.deleteUser(email);
	}
	
}
