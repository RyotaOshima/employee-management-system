package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Administrator;
import com.example.demo.repository.AdministratorRepository;

@Service
@Transactional
public class AdministratorService {
	@Autowired
	private AdministratorRepository repository;
	
	/**
	 * 管理者情報の登録
	 * @param administrator
	 */
	public void insert(Administrator administrator) {
		repository.insert(administrator);
	}
	
	/**
	 * ログイン
	 * @param mailAddress
	 * @param password
	 * @return repository.findByMailAdressAndPassword
	 */
	public Administrator login(String mailAddress, String password) {
		return repository.findByMailAdressAndPassword(mailAddress, password);
	}
	
	/**
	 * メールアドレス重複チェック
	 * @param mailAddress
	 * @return repository.findByMailAddress
	 */
	public Administrator findBymailAddress(String mailAddress) {
		return repository.findByMailAddress(mailAddress);
	}
}
