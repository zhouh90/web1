package com.qaf.sys.service;
/**
 * @author 周 浩 
 * @email zhou_eric90@163.com 
 * @date 2018年4月12日 上午11:01:59 
 * @描述 
 */

import com.qaf.sys.model.User;

public interface UserService {

	/**
	 * 根据用户名查找用户
	 * 
	 * @param userName
	 * @return
	 */
	User findUserByName(String userName);

	/**
	 * 根据用户名修改密码
	 * 
	 * @param userName
	 * @param newPassWord
	 * @return
	 */
	int updatePassWordByName(String userName, String newPassWord);

	/**
	 * 根据用户名和注册邮箱号查找用户
	 * 
	 * @param userName
	 * @param email
	 * @return
	 */
	User findUserByUserNameAndEmail(String userName, String email);

	/**
	 * 新增一个用户
	 * 
	 * @param user
	 * @return
	 */
	int addUser(User user);

}
