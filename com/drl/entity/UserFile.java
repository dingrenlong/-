package com.drl.entity;

import java.io.Serializable;
import java.util.Date;

public class UserFile implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6300376034871515679L;
	/**
	 * 文件id
	 */
	private Long id;
	/**
	 * 文件名
	 */
	private String fileName;
	/**
	 * 文件路径
	 */
	private String filePath;
	/**
	 * 文件类型（图片）
	 */
	private String fileType;
	/**
	 * 文件用途（0为默认文件，1为头像）
	 */
	private Integer filePurposes;
	/**
	 * 文件状态（如头像是否是当前头像,0为往期头像，1为当前头像）
	 */
	private Integer fileStatus;
	/**
	 * 文件的用户
	 */
	private Long userId;
	/**
	 * 创建者
	 */
	private String createdUser;
	/**
	 * 创建时间
	 */
	private Date createdTime;
	/**
	 * 修改者
	 */
	private String modifiedUser;
	/**
	 * 修改时间
	 */
	private Date modifiedTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public Integer getFileStatus() {
		return fileStatus;
	}
	public void setFileStatus(Integer fileStatus) {
		this.fileStatus = fileStatus;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public String getModifiedUser() {
		return modifiedUser;
	}
	public void setModifiedUser(String modifiedUser) {
		this.modifiedUser = modifiedUser;
	}
	public Date getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public Integer getFilePurposes() {
		return filePurposes;
	}
	public void setFilePurposes(Integer filePurposes) {
		this.filePurposes = filePurposes;
	}
	
}
