package com.drl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drl.entity.User;
import com.drl.entity.UserFile;
import com.drl.mapper.UserFileMapper;
import com.drl.service.IUserFileService;

@Service("userFileService")
public class UserFileServiceImpl implements IUserFileService{

	@Autowired
	UserFileMapper userFileMapper;
	
	@Override
	public Integer upload(UserFile userFile) {
		return userFileMapper.insert(userFile);
	}
	
	@Override
	public Integer update(Long userId, UserFile userFile) {
		//先查找到活跃的用户头像,并将它设置为非活动状态（条件，用户id，文件用途，文件状态）
		String where="userId="+userId+" AND filePurposes="+userFile.getFilePurposes()+" AND fileStatus="+1;
		UserFile oldUserFile=userFileMapper.select(where, null, null, null).get(0);
		if(oldUserFile!=null) {
			oldUserFile.setFileStatus(0);
			userFileMapper.update(oldUserFile);
		}
		//设置当前头像为活跃状态
		userFile.setFileStatus(1);
		return userFileMapper.update(userFile);
	}

	@Override
	public UserFile getUserFile(Long id ,Integer filePurposes, Integer fileStatus) {
		String where="userId="+id+" AND filePurposes="+filePurposes+" AND fileStatus="+fileStatus;
		List<UserFile> list = userFileMapper.select(where, null, null, null);
		if(list!=null) {
			if(list.size()>0) {
				return list.get(0);
			}
		}
		return null;
	}

	

}
