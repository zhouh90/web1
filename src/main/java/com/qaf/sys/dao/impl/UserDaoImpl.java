package com.qaf.sys.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qaf.sys.dao.UserDao;
import com.qaf.sys.model.User;

/**
 * @author 周 浩
 * @email zhou_eric90@163.com
 * @date 2018年4月12日 上午11:05:22
 * @描述
 */
@Service
public class UserDaoImpl implements UserDao {

	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<User> findUsers(User searchUser) {
		return sqlSession.selectList("findUsers", searchUser);
	}

	@Override
	public int updateByUserName(User updateUser) {
		return sqlSession.update("updateByUserName", updateUser);
	}

}
