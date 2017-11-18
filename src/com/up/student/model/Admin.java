
package com.up.student.model;

/**
 * 模块说明：对应账号表
 * 
 */
public class Admin {
	private int id;
	private String name;
	private String username;
	private String password;
	private String privi;

	public String getPrivi() {
		return privi;
	}

	public void setPrivi(String privi) {
		this.privi = privi;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
