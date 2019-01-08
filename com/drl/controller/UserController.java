package com.drl.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.drl.entity.util.ResponseResult;
import com.drl.entity.User;
import com.drl.service.IUserService;
import com.drl.service.ex.ServiceException;
import com.drl.service.ex.UsernameConflictException;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
	
	@Autowired
	private IUserService userService;
	
	/**
	 * 显示注册页面
	 * @return
	 */
	@RequestMapping("/reg.do")
	public String showReg(){
		return "register";
	}
	
	/**
	 * 显示登录页面
	 * @return
	 */
	@RequestMapping("/login.do")
	public String showLogin(){
		return "login";
	}
	
	/**
	 * 显示用户信息页面
	 * @return
	 */
	@RequestMapping("/userInfo.do")
	public String showInfo(ModelMap modelMap,HttpSession session){
		String username=(String) session.getAttribute("username");
		User user=userService.findUserByUsername(username);
		if(user!=null){
			//将数据封装到ModelMap，以转发到前端页面
			modelMap.addAttribute("user",user);
			//执行转发
			return "userInfo";
		}else{
			//用户被管理删除
			//执行转发/重定向
			//转发：地址栏不变，可以附带信息
			//重定向：地址栏改变，不可以附带信息
			return "redirect:../main/error.do";
		}
	}
	
	/**
	 * 显示修改密码页面
	 * @return
	 */
	@RequestMapping("/change_password.do")
	public String showChangePassword(){
		return "user_password";
	}
	
//	/**
//	 * 显示修改用户信息页面
//	 * @return
//	 */
//	@RequestMapping("/change_info.do")
//	public String showChangeInfo(ModelMap modelMap,
//			HttpSession session){
//		//获取id
//		Long id=getUidBySession(session);
//		//获取当前用户的信息
//		User user=userService.findUserById(id);
//		//可能在登录后，用户信息被管理员删除，判断是否获取到用户
//		if(user!=null){
//			//将数据封装到ModelMap，以转发到前端页面
//			modelMap.addAttribute("user",user);
//			//执行转发
//			return "user_info";
//		}else{
//			//用户被管理删除
//			//执行转发/重定向
//			//转发：地址栏不变，可以附带信息
//			//重定向：地址栏改变，不可以附带信息
//			return "redirect:../main/error.do";
//		}
//	}
//	
	/**
	 * 处理注册业务
	 * @param username
	 * @param password
	 * @param phone
	 * @param email
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/handle_reg.do",method=RequestMethod.POST)
	@ResponseBody//实质内容
	public ResponseResult<Void> handleReg(String username,
			String password,String phone,String email,
			HttpSession session){
		ResponseResult<Void> rr;
		User user=new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setPhone(phone);
		user.setEmail(email);
		try{
			User u=userService.reg(user);
			session.setAttribute("uid", u.getId());
			session.setAttribute("username", u.getUsername());
			rr=new ResponseResult<Void>(ResponseResult.STATE_OK);
		}catch(UsernameConflictException e){
			rr=new ResponseResult<Void>(e);
		}
		return rr;
	}
	
	@RequestMapping(value="/handle_login.do",method=RequestMethod.POST)
	@ResponseBody
	public ResponseResult<Void> handleLogin(String username,String password,HttpSession session){
		ResponseResult<Void> rr;
		try{
			User user=userService.login(username, password);
			session.setAttribute("uid", user.getId());
			session.setAttribute("username", user.getUsername());
			rr=new ResponseResult<Void>(ResponseResult.STATE_OK);
		}catch(ServiceException e){
			rr=new ResponseResult<Void>(e);
		}
		return rr;
	}
	
//	@RequestMapping(value="/handle_change_password.do",method=RequestMethod.POST)
//	@ResponseBody
//	public ResponseResult<Void> handleChangePassword(
//			String oldPassword, String newPassword,String confirmPassword,HttpSession session){
//		//声明返回值
//		ResponseResult<Void> rr;
//		//检查数据的有效性
//		if(newPassword!=null && newPassword.equals(confirmPassword) &&
//				newPassword.length()>=6 && newPassword.length()<=16){
//			try{
//				//根据session获取用户id
//				Long id=getUidBySession(session);
//				//执行修改密码
//				userService.chagePassword(id, oldPassword, newPassword);
//				rr=new ResponseResult<Void>(ResponseResult.STATE_OK);
//			}catch(ServiceException e){
//				rr=new ResponseResult<Void>(e);
//			}
//		}else{
//			//新密码等数据无效，设置返回值
//			rr=new ResponseResult<Void>();
//			rr.setState(ResponseResult.STATE_ERR);
//			rr.setMessage("新密码输入格式不正确");
//		}
//		return rr;
//	}
	
//	@RequestMapping(value="/handle_change_info.do")//,method=RequestMethod.POST)
//	@ResponseBody
//	public ResponseResult<Void> handleChangeInfo(
//			String username, Integer gender, 
//			String phone, String email,HttpSession session){
//		Long id=getUidBySession(session);
//		//声明返回值
//		ResponseResult<Void> rr;
//		//检查数据的有效性
//		if("".equals(username)){
//			username=null;
//		}
//		//执行修改方法
//		try{
//			userService.chageInfo(id, username, gender, phone, email);
//			rr=new ResponseResult<Void>(ResponseResult.STATE_OK);
//		}catch(ServiceException e){
//			rr=new ResponseResult<Void>(e);
//		}
//		return rr;
//	}
	
}
