package com.drl.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


/**
 * 
 * @author Administrator
 *
 */
@WebServlet("/upload")
public class ImageUpload extends HttpServlet{
	private static final long serialVersionUID = 1L;
	/**
	 * 文件路径地址
	 */
	private String filepath =null;
	/**
	 * 是否上传成功
	 */
	private boolean uploadFlage = false;
	/**
	 * 表单的普通文本信息
	 */
	private Map<String,Object> img = new HashMap<String, Object>();
	/**
	 * 图片上传出现错误提示
	 */
	String fileErro = null;
	/**
	 * 图片格式
	 */
	String sqlFilePath = null;
	/**
	 * 文件名
	 */
	String filename = null;


	public ImageUpload() {
		// TODO Auto-generated constructor stub
	}
	
	public ImageUpload(HttpServletRequest request,HttpServletResponse response,String savePath,Long time) throws ServletException, IOException, FileUploadException {
		this.upload(request, response, savePath, time);
	}
	
	public void upload(HttpServletRequest request,HttpServletResponse response,String savePath,Long time) throws ServletException, IOException, FileUploadException {
		System.out.println(savePath);
		//设置上传的图片保存路径
		File file = new File(savePath);
		if(!file.exists() && !file.isDirectory()) {
			System.out.println(savePath+"目录不存在，需要创建！");
			boolean flage = file.mkdir();
			System.out.println(flage);
		}
		//上传文件的API
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//2.创建一个文件上传解析器
		ServletFileUpload upload = new ServletFileUpload(factory);
		//设置头部的编码为utf-8
		upload.setHeaderEncoding("utf-8");
		//3.判断提交上来的数据是否是上传表单的数据
		if(!ServletFileUpload.isMultipartContent(request)) {
			//判断发送过来的表单是否是普通表单
			System.out.println("这是一个普通的表单");
			return;
		}
		//如果是带有文件的表单继续下面的操作
		try {
			List<FileItem> list = upload.parseRequest(request);
			for (FileItem fileItem : list) {
				if(fileItem.isFormField()) {
					filename = fileItem.getFieldName();
					String value = fileItem.getString("utf-8");
					System.out.println(filename+"="+value);
					img.put(filename, value);
				}else {
					filename = fileItem.getName();
					// 文件的路径 以及保存的路径，在内存中或者临时文件的路径
					System.out.println(list.toString());
					//FileItem item = list.get(0);//规定只能上传1个文件，无论上传多少就读取第一个文件
					//filename = item.getName();// 获得一个项的文件名称
					System.out.println("filename:"+filename);
					// 如果为空就跳过
					if (filename == null || filename.trim().equals("")) {
						fileErro = "请上传图片";
						System.out.println("无文件上传");
						continue;
					}
					//检查图片的后缀
					if (filename.substring(filename.lastIndexOf(".") + 1).equals("png")
							|| filename.substring(filename.lastIndexOf(".") + 1).equals("jpg")
							|| filename.substring(filename.lastIndexOf(".") + 1).equals("jpeg")) {
						// 获得上传的输入流
						InputStream in = fileItem.getInputStream();
						// 保存在指定的目录下
						FileOutputStream out = new FileOutputStream(savePath + "/" +time+"image."+filename.substring(filename.lastIndexOf(".") + 1));
						

						int len = 0;
						byte buffer[] = new byte[1024];
						// 每次读取
						while ((len = in.read(buffer)) > 0)
						{
							out.write(buffer, 0, len);
						}
						in.close();
						out.close();
						fileItem.delete();
						filepath = savePath + "/" +time+"image."+filename.substring(filename.lastIndexOf(".") + 1);
						sqlFilePath = "'"+savePath + "/" +time+"image."+filename.substring(filename.lastIndexOf(".") + 1)+"'";
						if(filepath==null) {
							fileErro = "图片格式错误";
						}
						System.out.println("文件上传成功！");
						System.out.println("文件路径："+filepath);
					} else {  //必须是图片才能上传否则失败
						fileErro = "文件格式不正确！";
						System.out.println("文件上传失败！");
						continue ;
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public String getFilepath() {
		return filepath;
	}

	public boolean isUploadFlage() {
		return uploadFlage;
	}

	public Map<String, Object> getImg() {
		return img;
	}

	public String getFileErro() {
		return fileErro;
	}

	public String getSQLfilepath() {
		return sqlFilePath;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	
}
