package com.qaf.sys.dao;

import java.util.List;

import com.qaf.sys.model.User;

/**
 * @author 周 浩
 * @email zhou_eric90@163.com
 * @date 2018年4月12日 上午11:05:03
 * @描述
 */
public interface UserDao {

	List<User> findUsers(User searchUser);

	int updateByUserName(User updateUser);

}
