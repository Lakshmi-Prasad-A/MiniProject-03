package com.ashokit.pojos;

import java.util.Date;

import lombok.Data;

@Data
public class UserSignUp {

	private Integer userId;
	private String firstName;
	private String lastName;
	private String email;
	private Long phoneNumber;
	private Date dob;
	private String gender;
	private String country;
	private String state;
	private String city;
	private String password;
	private String tempPassword;
	private boolean isUserActive;
	
}
