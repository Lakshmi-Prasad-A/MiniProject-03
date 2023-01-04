package com.ashokit.entity;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "Users")
@Entity

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
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
	private String accStatus;
	private String userPwd;
	

}
