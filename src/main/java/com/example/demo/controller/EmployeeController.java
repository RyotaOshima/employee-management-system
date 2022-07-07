package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Employee;
import com.example.demo.form.UpdateEmployeeForm;
import com.example.demo.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private EmployeeService service;
	
	/**
	 * UpdateEmployeeFormをリクエストスコープに格納
	 * @return UpdateEmployeeForm
	 */
	@ModelAttribute
	public UpdateEmployeeForm setUpdateEmployeeForm() {
		return new UpdateEmployeeForm();
	}
	
	/**
	 * 従業員情報の全件取得
	 * @param model
	 * @return list.html
	 */
	@RequestMapping("/showList")
	public String showList(Model model) {
		List<Employee> employeeList = service.showList();
		model.addAttribute("employeeList",employeeList);
		return "employee/list";
	}
	
	/**
	 * 従業員の詳細情報の表示
	 * @param id
	 * @param model
	 * @return detail.html
	 */
	@RequestMapping("/showDetail")
	public String showDateil(String id,Model model) {
		Employee employee = service.showDetail(Integer.parseInt(id));
		model.addAttribute("employee", employee);
		return "employee/detail";
	}
	
	/**
	 * 従業員の扶養人数の変更
	 * @param form
	 * @return /employee/showList
	 */
	@RequestMapping("/update")
	public String update(UpdateEmployeeForm form) {
		int dependentsCount = Integer.parseInt(form.getId());
		Employee employee = service.showDetail(dependentsCount);
		employee.setDependentsCount(Integer.parseInt(form.getDependentsCount()));
		service.update(employee);
		return "redirect:/employee/showList";
	}
}
