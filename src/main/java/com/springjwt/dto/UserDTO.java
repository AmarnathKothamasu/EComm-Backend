package com.springjwt.dto;

import lombok.Data;

@Data
public class UserDTO {

    private Long userId;

	private String name;

    private String email;

    public Long getuserId() {
		return userId;
	}

	public void setuserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
