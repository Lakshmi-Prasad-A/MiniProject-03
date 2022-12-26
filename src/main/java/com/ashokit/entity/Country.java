package com.ashokit.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Country")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Country {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int countryId;
	private String countryName;
	
	
}
