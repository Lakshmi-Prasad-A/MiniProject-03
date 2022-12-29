package com.ashokit.serviceImpl;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashokit.entity.City;
import com.ashokit.entity.Country;
import com.ashokit.entity.State;
import com.ashokit.entity.User;
import com.ashokit.pojos.UserLogin;
import com.ashokit.pojos.UserUnLock;
import com.ashokit.repo.CityRepo;
import com.ashokit.repo.CountryRepo;
import com.ashokit.repo.StateRepo;
import com.ashokit.repo.UsersRepo;
import com.ashokit.service.UserMgmtService;


@Service
public class UserServiceImpl implements UserMgmtService {

	
	@Autowired
	private UsersRepo urepo;
	
	@Autowired
	private CountryRepo crepo;
	
	@Autowired
	private StateRepo srepo;
	
	
	@Autowired
	private CityRepo cirepo;
	
	@Override
	public String checkEmail(String email) {
	
		if(urepo.checkEmail(email) != null) {
			return "Email Already Exist";
		}else {
			return "";
		}
	}

	@Override
	public Map<Integer, String> getCountries() {
		return crepo.findAll().stream().collect(Collectors.toMap(Country::getCountryId, Country::getCountryName));
	}
	

	@Override
	public Map<Integer, String> getStates(Integer countryId) {

		return srepo.findAll().stream().collect(Collectors.toMap(State::getStateId, State::getStateName));
	}

	@Override
	public Map<Integer, String> getCities(Integer stateId) {
	
		return cirepo.findAll().stream().collect(Collectors.toMap(City::getCityId, City::getCityName));
	}

	@Override
	public String registerUser(User user) {
		
		urepo.save(user);
		if(user.getUserId()!=null) {
			return "ContactSaved";
		}else {
			return "Contact Already Exist";
		}
	}

	@Override
	public String unlockAccount(UserUnLock accForm) {
			return urepo.unlockAccount(accForm);
	}

	@Override
	public String login(UserLogin login) {
		
		if(login.getEmail()==null) {
			return "please enter email";
		}
		if(login.getEmail()==null) {
			return "please enter Password";
		}
		
		if(urepo.login(login.getEmail(), login.getPassword())==null) {
			return "invalid Data";
		}
		return "Your login success";
	}

	@Override
	public String forgotPwd(String email) {

		return urepo.getPassword(email);
	}

}
