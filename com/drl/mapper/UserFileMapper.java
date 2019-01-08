package com.drl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.drl.entity.UserFile;

public interface UserFileMapper {
	
	/**
	 * 插入新的图片
	 * @return
	 */
	Integer insert(UserFile userFile);
	
	/**
	 * 查找用户头像信息
	 * @param where where子句，不要包含where关键字
	 * @param orderBy orderBy子句，不要包含orderBy关键字
	 * @param offset 偏移量，用于limit子句 的第一个值
	 * @param countPerPage 每页查询数据量，用于limit子句的第二个值，仅当参数offset有效时，该参数生效
	 * @return 匹配的用户头像信息的集合
	 */
	List<UserFile> select(@Param("where") String where,
			@Param("orderBy") String orderBy,
			@Param("offset") Integer offset,
			@Param("countPerPage") Integer countPerPage);
	
	/**
	 * 更新用户头像状态
	 * @return
	 */
	Integer update(UserFile userFile);
	
}
