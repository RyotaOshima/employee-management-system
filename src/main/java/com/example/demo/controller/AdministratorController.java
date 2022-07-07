package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Administrator;
import com.example.demo.form.InsertAdministratorForm;
import com.example.demo.form.LoginForm;
import com.example.demo.service.AdministratorService;

@Controller
@RequestMapping("/")
public class AdministratorController {
	@Autowired
	private AdministratorService service;
	
	@Autowired
	private HttpSession session;
	
	/**
	 * InsertAdministratorFormをリクエストスコープに格納
	 * @return InsertAdministratorForm
	 */
	@ModelAttribute
	public InsertAdministratorForm setUpInsertAdministrator() {
		return new InsertAdministratorForm();
	}
	
	/**
	 * LoginFormをリクエストスコープに格納
	 * @return LoginForm
	 */
	@ModelAttribute
	public LoginForm setUpLoginForm() {
		return new LoginForm();
	}
	
	/**
	 * ログイン画面の表示
	 * @return login.html
	 */
	@RequestMapping("/")
	public String toLogin() {
		return "administrator/login";
	}
	
	/**
	 * ログイン
	 * @param form
	 * @param model
	 * @return login.html or /employee/showList
	 */
	@RequestMapping("/login")
	public String login(LoginForm form, Model model) {
		Administrator administrator = service.login(form.getMailAddress(), form.getPassword());
		if(administrator == null) {
			model.addAttribute("errorMessage", "メールアドレスまたはパスワードが不正です。");
			return toLogin();
		}
		session.setAttribute("administratorName", administrator.getName());
		return "forward:/employee/showList";
	}
	
	/**
	 * 管理者登録画面の表示
	 * @return insert.html
	 */
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert";
	}
	
	/**
	 * 管理者情報の登録
	 * @param form
	 * @return insert.html or /
	 */
	@RequestMapping("/insert")
	public String insert(@Validated InsertAdministratorForm form, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return toInsert();
		}
		Administrator administrator = service.findBymailAddress(form.getMailAddress());
		if(administrator != null) {
			model.addAttribute("errorMessage", "メールアドレスが不正です。");
			return toInsert();
		}
		BeanUtils.copyProperties(form, administrator);
		service.insert(administrator);
		return "redirect:/";
	}
	
	/**
	 * ログアウト
	 * @return /
	 */
	@RequestMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	}
}
