package com.ashokit.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	@Temporal(TemporalType.DATE)
	private Date dob;
	private String gender;
	private String country;
	private String state;
	private String city;
	private String password;
	private boolean isUserActive;

}
