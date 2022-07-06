package com.example.demo.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class InsertAdministratorForm {
	/** 名前 */
	@NotBlank
	private String name;
	/** メールアドレス */
	@NotBlank
	@Email
	private String mailAddress;
	/** パスワード */
	@NotBlank
	private String password;
	
	public InsertAdministratorForm() {
	}
	public InsertAdministratorForm(@NotBlank String name, @NotBlank @Email String mailAddress,
			@NotBlank String password) {
		super();
		this.name = name;
		this.mailAddress = mailAddress;
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "InsertAdministratorForm [name=" + name + ", mailAddress=" + mailAddress + ", password=" + password
				+ "]";
	}
}
