package com.qaf.sys.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qaf.sys.dao.UserDao;
import com.qaf.sys.model.User;
import com.qaf.sys.service.UserService;

/**
 * @author 周 浩
 * @email zhou_eric90@163.com
 * @date 2018年4月12日 上午11:03:47
 * @描述
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired

	private UserDao userDao;

	@Override
	public User findUserByName(String userName) {
		User searchUser = new User();
		searchUser.setUserName(userName);
		List<User> users = userDao.findUsers(searchUser);
		if (CollectionUtils.isNotEmpty(users)) {
			return users.get(0);
		}
		return null;
	}

	@Override
	public int updatePassWordByName(String userName, String newPassWord) {
		User updateUser = new User();
		updateUser.setUserName(userName);
		updateUser.setPassWord(newPassWord);
		return userDao.updateByUserName(updateUser);
	}

	@Override
	public User findUserByUserNameAndEmail(String userName, String email) {
		User searchUser = new User();
		searchUser.setUserName(userName);
		searchUser.setEmail(email);
		List<User> users = userDao.findUsers(searchUser);
		if (CollectionUtils.isNotEmpty(users)) {
			return users.get(0);
		}
		return null;
	}

}
