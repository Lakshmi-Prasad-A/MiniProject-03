package com.ashokit.service;


import java.util.Map;

import com.ashokit.entity.User;
import com.ashokit.pojos.UserForm;
import com.ashokit.pojos.UserLogin;
import com.ashokit.pojos.UserUnLock;

public interface UserMgmtService {
	
	public String checkEmail(String email);
	
	public Map<Integer, String> getCountries();
	
	public Map<Integer, String> getStates(Integer countryId);
	
	public Map<Integer, String> getCities(Integer stateId);
	
	public String registerUser(UserForm userform);
	
	public String unlockAccount(UserUnLock accForm);
	
	public String login (UserLogin loginForm);
	
	public String forgotPwd(String email);
	
	
}
