package com.drl.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;

import com.drl.entity.User;
import com.drl.entity.UserFile;
import com.drl.service.IUserFileService;
import com.drl.service.IUserService;

@WebServlet(name="UserInfoServlet",urlPatterns="/userInfo")
public class UserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private IUserService userService;
	@Autowired private IUserFileService userFileService;
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		//根据session得到用户信息
		HttpSession session=request.getSession();
		Long userId=(Long)session.getAttribute("uid");
		User user=userService.findUserById(userId);
		if(user==null) {
			response.sendRedirect("user/login.do");
			return ;
		}
		
		String sucess="修改成功";
		String path=null;
		try {
			ImageUpload upload=new ImageUpload(request, response, getServletContext().getRealPath("/user_images"),System.currentTimeMillis() );
			path=upload.getFilepath();
			
			UserFile userFile=new UserFile();
			if(path!=null||!"".equals(path)){
				userFile.setFilePath(path);
				userFile.setFileName(upload.getFilename());
				userFile.setFilePurposes(1);//设为头像类型文件
				userFile.setUserId(userId);
				userFile.setFileStatus(0);
				userFile.setCreatedUser(user.getUsername());
				userFile.setCreatedTime(new Date());
				//得到默认头像路径
				UserFile u=userFileService.getUserFile(userId, 1, 1);
				if(u!=null) {
					path=u.getFilePath();//默认头像
				}else {
					//沒有默认头像
					userFile.setFileStatus(1);
				}
				userFileService.upload(userFile);//上传头像
			}
			
			Map<String ,Object> m=upload.getImg();
			
			//得到网页数据，放入user，后续写入数据库
		  	String email=(String)m.get("email");
			String realname=(String)m.get("realname");
			String phone=(String)m.get("phone");
			Integer gender=(Integer)m.get("gender");
			//String address=(String)m.get("address");
			userService.chageInfo(userId, user.getUsername(), realname, gender, phone, email);
			
		} catch (FileUploadException e1) {
			e1.printStackTrace();
		} 
//		catch (SQLException e) {
//			e.printStackTrace();
//			request.setAttribute("error", "系统繁忙,稍后重试");
//			request.getRequestDispatcher("error.jsp").forward(request, response);
//		}
		request.setAttribute("path",path);
		request.setAttribute("sucess",sucess);
		//获得转发器,并转发
		request.getRequestDispatcher("user/userInfo.do").forward(request, response);
	}
}
