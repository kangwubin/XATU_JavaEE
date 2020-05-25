/**
 * Copyright 2018-2020 stylefeng & fengshuonan (https://gitee.com/stylefeng)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.projects.modular.system.controller;

import java.util.List;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.stylefeng.roses.core.base.controller.BaseController;

import com.projects.core.common.node.MenuNode;
import com.projects.core.shiro.ShiroKit;
import com.projects.core.shiro.ShiroUser;
import com.projects.modular.system.service.UserService;

/**
 * 登录控制器
 *
 * @author fengshuonan
 * @Date 2017年1月10日 下午8:25:24
 */
@Controller
public class LoginController extends BaseController {

	@Autowired
	private UserService userService;

	/**
	 * 跳转到主页
	 *
	 * @author fengshuonan
	 * @Date 2018/12/23 5:41 PM
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {

		// 获取当前用户角色列表
		ShiroUser user = ShiroKit.getUserNotNull();
		List<Long> roleList = user.getRoleList();

		if (roleList == null || roleList.size() == 0) {
			ShiroKit.getSubject().logout();
			model.addAttribute("tips", "该用户没有角色，无法登陆");
			return "/login.html";
		}

		List<MenuNode> menus = userService.getUserMenuNodes(roleList);
		model.addAttribute("menus", menus);

		return "/index.html";
	}

	/**
	 * 跳转到登录页面
	 *
	 * @author fengshuonan
	 * @Date 2018/12/23 5:41 PM
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		if (ShiroKit.isAuthenticated() || ShiroKit.getUser() != null) {
			return REDIRECT + "/";
		} else {
			return "/login.html";
		}
	}

	@Autowired
	private SessionDAO sessionDAO;

	/**
	 * 点击登录执行的动作
	 *
	 * @author fengshuonan
	 * @Date 2018/12/23 5:42 PM
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginVali() {

		String username = super.getPara("username").trim();
		String password = super.getPara("password").trim();
		String remember = super.getPara("remember");

		UsernamePasswordToken token = new UsernamePasswordToken(username, password.toCharArray());

		// 如果开启了记住我功能
		if ("on".equals(remember)) {
			token.setRememberMe(true);
		} else {
			token.setRememberMe(false);
		}

		// 执行shiro登录操作
		Subject currentUser = ShiroKit.getSubject();
		currentUser.login(token);
		// 登录成功，记录登录日志
		ShiroUser shiroUser = ShiroKit.getUserNotNull();
		

		ShiroKit.getSession().setAttribute("sessionFlag", true);

		return REDIRECT + "/";
	}

	/**
	 * 退出登录
	 *
	 * @author fengshuonan
	 * @Date 2018/12/23 5:42 PM
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logOut() {
	
		ShiroKit.getSubject().getSession().removeAttribute("loginedUser");
		ShiroKit.getSubject().logout();
		deleteAllCookie();
		return REDIRECT + "/login";
	}
}