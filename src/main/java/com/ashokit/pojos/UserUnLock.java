package com.ashokit.pojos;

import lombok.Data;

@Data
public class UserUnLock {

	private String email;
	private String tempPassword;
	private String password;
	private String confirmPassword;
}
