package com.drl.service;

import com.drl.entity.User;
import com.drl.entity.UserFile;

public interface IUserFileService {
	
	/**
	 * 上传头像
	 * @param userFile
	 * @return
	 */
	Integer upload(UserFile userFile);
	
	/**
	 * 设置新的用户默认头像
	 * @param userId
	 * @param userFile
	 * @return
	 */
	Integer update(Long userId,UserFile userFile);
	
	/**
	 * 返回用户当前的头像
	 * @param User user 用户
	 * @param fileStatus 头像状态，0为往期头像，1为当前头像
	 * @return
	 */
	UserFile getUserFile(Long id ,Integer filePurposes,Integer fileStatus);
	
	
}
