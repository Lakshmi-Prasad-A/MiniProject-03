package com.ashokit.pojos;

import java.time.LocalDate;


import lombok.Data;


@Data
public class UserForm {

	
	private Integer userId;
	private String firstName;
	private String lastName;
	private String email;
	private Long phoneNumber;
	private LocalDate dob;
	private String gender;
	private String countryId;
	private String stateId;
	private String cityId;

}
