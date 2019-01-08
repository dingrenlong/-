package com.drl.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.drl.service.util.ftp.ContinueFTP;
import com.drl.service.util.ftp.ContinueFTP.DownloadStatus;

@Controller
@RequestMapping("/ftp")
public class FtpController extends BaseController{
	
	
	/**
	 * 显示注册页面
	 * @return
	 * @throws FTPListParseException 
	 * @throws FTPAbortedException 
	 * @throws FTPDataTransferException 
	 */
	@RequestMapping("/file.do")
	public String showfileIndex(ModelMap modelMap,HttpSession session,HttpServletRequest request) {
		ContinueFTP myFtp =ContinueFTP.getInstance();
		try {
			System.out.println("网址0:"+request.getServletPath());
			String ftpPath=session.getAttribute("uid").toString();
			modelMap.addAttribute("ftpPath","/ftp/file.do/"+ftpPath);
			//client.createDirectory(ftpPath);
			List<List> list= myFtp.getFileList(ftpPath);
			if(list !=null) {
				modelMap.addAttribute("files", list.get(0));
				modelMap.addAttribute("dirs", list.get(1));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "file";
	}
	
	/**
	 * 显示注册页面
	 * @return
	 * @throws FTPListParseException 
	 * @throws FTPAbortedException 
	 * @throws FTPDataTransferException 
	 */
	@RequestMapping("/file.do/**")
	public String showfs(ModelMap modelMap,HttpSession session,HttpServletRequest request) {
		ContinueFTP myFtp =ContinueFTP.getInstance();

		String ftpPath=request.getServletPath().replace("/ftp/file.do","");
		System.out.println("ftpPath:"+ftpPath);
		modelMap.addAttribute("ftpPath","/ftp/file.do"+ftpPath);
		//client.createDirectory(ftpPath);
		List<List> list;
		try {
			list = myFtp.getFileList(ftpPath);
			if(list !=null) {
				modelMap.addAttribute("files", list.get(0));
				modelMap.addAttribute("dirs", list.get(1));
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if("0".equals(request.getParameter("type"))) {
			String lastPath=request.getServletPath();
//			System.out.println("lastPath"+lastPath);
			try {
				DownloadStatus result=myFtp.download(lastPath.replace("/ftp/file.do", "")+"/"+request.getParameter("name"), "E:\\"+request.getParameter("name"));
				switch(result){
					case Remote_File_Noexist: // 远程文件不存在
					case Local_Bigger_Remote: // 本地文件大于远程文件
					case Download_From_Break_Success: // 断点下载文件成功
					case Download_From_Break_Failed: // 断点下载文件失败
					case Download_New_Success: // 全新下载文件成功
					case Download_New_Failed: // 全新下载文件失败
				        return "file";
					default:
						//其他默认跳过
						return "error";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("1".equals(request.getParameter("type"))){
			String lastPath=request.getServletPath();
			System.out.println("lastPath"+lastPath);
			try {
				String path=lastPath.replace("/ftp/file.do", "");
				System.out.println("****path"+path);
				myFtp.downloadAll(path, "E:\\");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "file";
	}
	
}
