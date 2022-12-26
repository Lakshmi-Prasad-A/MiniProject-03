package com.ashokit.service;

import java.util.List;

import com.ashokit.pojos.UserLogin;
import com.ashokit.pojos.UserSignUp;
import com.ashokit.pojos.UserUnLock;

public interface UserService {
	
	public String signIn(UserLogin login);
	
	//User Registrations- Start
	public String userRegistration(UserSignUp signUp);
	public List<String> getCounty();
	public List<String> getState(String countryName);
	public List<String> getCity(String state);
	//User Registrations- End
	
	//User Unlock
	public String userActivate();
	public String userUnLock(UserUnLock userUnLock);
	
	// forgot-Password
	public String forgotPassword(String email);
	
	
}
