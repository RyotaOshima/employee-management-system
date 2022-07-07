package com.example.demo.form;

import javax.validation.constraints.NotBlank;

public class InsertAdministratorForm {
	/** 名前 */
	@NotBlank(message = "名前を入力してください。")
	private String name;
	/** メールアドレス */
	@NotBlank(message = "メールアドレスを入力してください。")
	private String mailAddress;
	/** パスワード */
	@NotBlank(message = "パスワードを入力してください。")
	private String password;
	
	public InsertAdministratorForm() {
	}
	public InsertAdministratorForm(String name, String mailAddress, String password) {
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
