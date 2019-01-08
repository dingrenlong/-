package com.drl.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.drl.entity.User;
import com.drl.mapper.UserMapper;
import com.drl.service.IUserService;
import com.drl.service.ex.PasswordNotMatchException;
import com.drl.service.ex.UsernameConflictException;
import com.drl.service.ex.UserNotExitException;

@Service("userService")
public class UserServiceImpl implements IUserService {
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 用户注册
	 */
	@Transactional//注释事务:一旦抛出RuntimeException异常，事务自动回滚
	public User reg(User user) {
		//两个用户同时注册，确保username唯一性
		synchronized("username") {
			//根据尝试注册的用户名进行查询，判断用户名是否被占用
			User u=findUserByUsername(user.getUsername());
			if(u!=null){
				//用户名被占用，则：
				throw new UsernameConflictException("用户名"+user.getUsername()+"已经被注册");
			}else{
				//用户名未被占用，则执行注册：
				//把密码加密。
				String salt=UUID.randomUUID().toString();
				String md5Password=getEncrpytedPassword(user.getPassword(), salt);
				user.setPassword(md5Password);
				//保存salt，即盐
				user.setUuid(salt);
				//保存日志信息
				Date now=new Date();
				user.setCreatedUser("System");
				user.setCreatedTime(now);
				user.setModifiedUser("System");
				user.setModifiedTime(now);
				//使用Mybatis处理insert后，数据的id 将会被封装到这个对象中。
				//在执行以下代码之前，user中并没有id，执行结束后MyBatis会将id封装到user参数中。
				//UserMapper.xml参数中需要设置属性useGeneratedKeys="true" keyProperty="id"。
				userMapper.insert(user);
				return user;
			}
		}
	}

	/**
	 * 通过用户名查找用户
	 */
	public User findUserByUsername(String username) {
		//确定where子句内容
		String where="username='"+username+"'";
		//调用持久层的对象实现查询
		List<User> list=userMapper.select(where, null, null, null);
		//判断查询结果
		if(list.size()>0){
			//找到数据，由于用户名唯一，则集合中第一个元素就是查询目标数据
			return list.get(0);
		}
		//未找到，返回null
		return null;
	}
	
	/**
	 * 通过id查找用户
	 */
	public User findUserById(Long id) {
		//确定where子句内容
		String where="id="+id;
		//调用持久层的对象实现查询
		List<User> list=userMapper.select(where, null, null, null);
		//判断查询结果
		if(list.size()>0){
			//找到数据，由于id唯一，则集合中第一个元素就是查询目标数据
			return list.get(0);
		}
		//未找到，返回null
		return null;
	}
	
	/**
	 * 密码加密
	 */
	public String getEncrpytedPassword(String password, String salt) {
		//把密文和明文拼接起来
		String str=password+salt;
		//获取拼接后的字符串的消息摘要
		String md5=DigestUtils.md5DigestAsHex(str.getBytes());
//		str=md5+salt;
//		md5=DigestUtils.md5DigestAsHex(str.getBytes());
		//返回摘要字符串
		return md5;
	}

	/**
	 * 用户登录
	 */
	public User login(String username, String password) {
		//根据用户名查询用户信息
		User user=findUserByUsername(username);
		
		//判断是否查询到匹配的用户信息
		if(user==null){
			//没有：抛出异常，用户名不存在UsernameNotExitException
			throw new UserNotExitException("用户名"+username+"不存在");
		}else{//存在：继续
			//获取该用户的盐（UUID）
			String salt=user.getUuid();
			//基于盐，对用户输入的密码进行加密
			String md5Password=getEncrpytedPassword(password, salt);
			//判断加密后的密码与获取的用户信息中的密码是否匹配
			if(md5Password.equals(user.getPassword())){
				//匹配,返回查询到的User对象
				return user;
			}else{
				//不匹配,抛出异常：密码不匹配PasswordNotMatchException
				throw new PasswordNotMatchException("密码不正确");
			}
		}
	}

	/**
	 * 修改密码
	 */
	public Integer chagePassword(Long id, String oldPassword, String newPassword) {
		//获取username对应的对象
		User data=findUserById(id);
		if(data==null){
			throw new UserNotExitException("尝试操作的用户信息不存在，或可能已经退出登录或被删除，请联系系统管理员");
		}else{
			//根据data找到salt
			String salt=data.getUuid();
			//旧密码加密
			String md5OldPassword=getEncrpytedPassword(oldPassword, salt);
			if(md5OldPassword.equals(data.getPassword())){
				//新密码加密
				String md5NewPassword=getEncrpytedPassword(newPassword, salt);
				//将id和新的加密的密码存入user对象
				User user=new User();
				user.setId(id);
				user.setPassword(md5NewPassword);
				//修改密码
				return userMapper.update(user);
			}else{
				throw new PasswordNotMatchException("原始密码不正确");
			}
		}
	}

	/**
	 * 修改用户信息
	 */
	public Integer chageInfo(Long id, String username,String realname, Integer gender, String phone, String email) {
		//获取id对应的用户信息
		User data=findUserById(id);
		//判断是否存在该用户
		if(data==null){
			throw new UserNotExitException("未找到该用户");
		}else{
			//检查是否提交用户名
			if(username!=null){
				//确保用户名唯一性
				synchronized ("username") {
					//不为null，说明用户提交了用户名
					User u=findUserByUsername(username);
					if(u!=null){
						//判断用户名是否是自己的
						if(data.getUsername().equals(username)){
							//是自己的用户名
							username=null;
						}else{
							//找到的用户已经被占用
							throw new UsernameConflictException("用户名已存在");
						}
					}
					//执行修改用户信息
					data.setUsername(username);
					data.setRealname(realname);
					data.setGender(gender);
					data.setPhone(phone);
					data.setEmail(email);
					data.setModifiedUser(username);
					data.setModifiedTime(new Date());
					return userMapper.update(data);
				}
			}else {
				//未修改用户名
				//执行修改用户信息
				data.setGender(gender);
				data.setPhone(phone);
				data.setEmail(email);
				return userMapper.update(data);
			}
		}
	}

}
