package com.authentication.dto;

public class AuthRequest {
    private String username;
    private String password;
    private String email;
    private Integer userId;
	public AuthRequest(String username, String password,String email,Integer userId) {
		super();
		this.username = username;
		this.password = password;
		this.email=email;
		this.userId=userId;
	}
	public AuthRequest() {
		super();
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
    
}
