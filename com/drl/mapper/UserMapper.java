package com.drl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.drl.entity.User;

public interface UserMapper {
	/**
	 * 添加用户信息
	 * @param user 用户信息
	 * @return 受影响行数
	 */
	Integer insert(User user);
	/**
	 * 查找用户信息
	 * @param where where子句，不要包含where关键字
	 * @param orderBy orderBy子句，不要包含orderBy关键字
	 * @param offset 偏移量，用于limit子句 的第一个值
	 * @param countPerPage 每页查询数据量，用于limit子句的第二个值，仅当参数offset有效时，该参数生效
	 * @return 匹配的用户信息的集合
	 */
	List<User> select(@Param("where") String where,
			@Param("orderBy") String orderBy,
			@Param("offset") Integer offset,
			@Param("countPerPage") Integer countPerPage);
	/**
	 * 修改用户信息
	 * @param user
	 * @return Integer大于0表示成功
	 */
	Integer update(User user);
}
