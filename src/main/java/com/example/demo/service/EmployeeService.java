package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Employee;
import com.example.demo.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeService {
	@Autowired
	private EmployeeRepository repository;
	
	/**
	 * 従業員一覧の全件取得
	 * @return employeeList
	 */
	public List<Employee> showList() {
		List<Employee> employeeList = repository.findAll();
		return employeeList;
	}
}
