package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Administrator;

@Repository
public class AdministratorRepository {
	/**
	 * AdministratorRowMapperの生成
	 */
	public static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER = (rs, i) -> {
		Administrator administrator = new Administrator();
		administrator.setId(rs.getInt("id"));
		administrator.setName(rs.getString("name"));
		administrator.setMailAddress(rs.getString("mail_address"));
		administrator.setPassword(rs.getString("password"));
		return administrator;
	};
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * 管理者情報の登録
	 * @param administrator
	 */
	public void insert(Administrator administrator) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);
		String sql = "insert into administrators(name,mail_address,password)values(:name,:mailAddress,:password);";
		template.update(sql, param);
	}
	
	/**
	 * メールアドレスとパスワードから管理者情報の取得
	 * @param mailAdress
	 * @param password
	 * @return template.queryForObject or null
	 */
	public Administrator findByMailAdressAndPassword(String mailAdress,String password) {
		String sql = "select id,name,mail_address,password from administrators"
				+ " where mail_address=:mailAddress and password=:password;";
		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("mailAddress", mailAdress)
				.addValue("password", password);
		try {
			return template.queryForObject(sql, param, ADMINISTRATOR_ROW_MAPPER);
		}catch(Exception e) {
			return null;
		}
	}
	
	/**
	 * メールアドレスから管理者情報の取得
	 * @param mailAddress
	 * @return administratorList.get(0) or null
	 */
	public Administrator findByMailAddress(String mailAddress) {
		String sql = "select id,name,mail_address,password from administrators"
				+ " where mail_address=:mailAddress;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", mailAddress);
		List<Administrator> administratorList = template.query(sql, param, ADMINISTRATOR_ROW_MAPPER);
		if(administratorList.size() == 0) {
			return null;
		}
		return administratorList.get(0);
	}
}
