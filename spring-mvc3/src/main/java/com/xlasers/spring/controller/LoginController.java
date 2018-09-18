package com.xlasers.spring.controller;

import javax.servlet.http.HttpServletRequest;

import com.xlasers.spring.service.MsgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The type Login controller.
 *
 * @package: com.xlasers.spring.controller
 * @author: Elijah.D
 * @time: CreateAt 2018/9/21 && 17:32
 * @description: 登陆注册controller
 * @copyright: Copyright © 2018 xlasers
 * @version: V1.0
 * @modified: Elijah.D
 */
@Slf4j
@Controller
public class LoginController {
	/**
	 * The Msg service.
	 */
	@Autowired
	MsgService msgService;

	/**
	 * Login string.
	 *
	 * @param req the req
	 * @return the string
	 */
	@RequestMapping("/login")
	public String login(HttpServletRequest req) {

		String msg = msgService.getMsg();
		log.info("【login】注入service,调用相关方法,getMsg: {}", msg);

		req.setAttribute("data", "页面传值: msg = " + msg);
		return "login";
	}

	/**
	 * Register string.
	 *
	 * @param req the req
	 * @return the string
	 */
	@RequestMapping("/register")
	public String register( HttpServletRequest req) {

		String pwd = req.getParameter("password");
		String name = req.getParameter("username");
		log.info("【login】页面传值, username: {}, password: {}", name, pwd);

		req.setAttribute("password", pwd);
		req.setAttribute("username", name);
		return "login";
	}
}
