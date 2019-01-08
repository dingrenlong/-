package com.drl.service;

import com.drl.entity.User;

public interface IUserService {
	/**
	 * 用户注册
	 * @param user 注册的用户信息
	 * @return 返回用户信息，包括用户id。。
	 * 如果用户名已存在，则抛出异常
	 */
	User reg(User user);
	/**
	 * 根据用户名查询用户信息
	 * @param username 用户名
	 * @return 用户信息，没有找到则返回null
	 */
	User findUserByUsername(String username);
	/**
	 * 根据id查询用户信息
	 * @param id
	 * @return 用户信息
	 */
	User findUserById(Long id);
	/**
	 * 获取加密后的密码
	 * @param password 明文密码
	 * @param salt 盐
	 * @return 密文密码
	 */
	String getEncrpytedPassword(String password ,String salt);
	/**
	 * 用户登录
	 * @param username 用户名
	 * @param password 密码
	 * @return 成功登陆，返回用户信息
	 * @throws UserNotExitException  PasswordNotMatchException
	 */
	User login(String username,String password);
	/**
	 * 修改密码
	 * @param id 用户id
	 * @param username 用户名
	 * @param oldPassword 旧密码
	 * @param newPassword 新密码
	 * @return 成功受影响的行数，
	 * @throws UserNotExitException
	 * @throws PasswordNotMatchException
	 */
	Integer chagePassword(Long id,String oldPassword,String newPassword);
	/**
	 * 修改用户基本信息
	 * @param id
	 * @param username
	 * @param gender
	 * @param phone
	 * @param email
	 * @return 返回受影响的行数
	 * @throws UserNotExitException
	 */
	Integer chageInfo(Long id,String username,String realname,Integer gender
			,String phone,String email);
}
