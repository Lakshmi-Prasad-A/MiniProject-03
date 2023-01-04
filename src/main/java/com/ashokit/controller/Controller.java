package com.ashokit.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ashokit.entity.User;
import com.ashokit.pojos.UserForm;
import com.ashokit.pojos.UserLogin;
import com.ashokit.pojos.UserUnLock;
import com.ashokit.service.UserMgmtService;

@RestController
public class Controller {

	@Autowired
	private UserMgmtService service;
	
	@GetMapping("/checkEmail/{email}")
	public String checkEmail(@PathVariable String email) {
		return service.checkEmail(email);
	}
	
	@GetMapping("/country")
	public Map<Integer, String> getCountries(){
		return service.getCountries();
	}
	
	@GetMapping("/state/{countryId}")
	public Map<Integer, String> getStates(@PathVariable Integer countryId){
		return service.getStates(countryId);
	}
	
	@GetMapping("/city/{state}")
	public Map<Integer, String> getCities(@PathVariable Integer stateId){
		return service.getCities(stateId);
	}
	
	@PostMapping(value = "/signup")
	public String registerUser(UserForm userform) {
		return service.registerUser(userform);
	}
	
	@PostMapping("/unlockuser")
	public String unlockAccount(@RequestBody UserUnLock accForm) {
		return service.unlockAccount(accForm);
	}
	
	@PostMapping("/userlogin")
	public String login (@RequestBody UserLogin userlogin){
		return service.login(userlogin);
	}
	
	// forgot-Password
	@GetMapping("/forgotpwd/{email}")
	public String forgotPwd(@PathVariable String email) {
		return service.forgotPwd(email);
	}
	
}
