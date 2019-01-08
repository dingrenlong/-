package com.drl.controller;

import javax.servlet.http.HttpSession;

/**
 * 用于获取用户身份有效性
 * @author Administrator
 *
 */
public abstract class BaseController {
	public Long getUidBySession(HttpSession session){
		return Long.valueOf(session.getAttribute("uid").toString());
	}
}
