package com.ashokit.serviceImpl;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashokit.entity.City;
import com.ashokit.entity.Country;
import com.ashokit.entity.State;
import com.ashokit.entity.User;
import com.ashokit.pojos.UserForm;
import com.ashokit.pojos.UserLogin;
import com.ashokit.pojos.UserUnLock;
import com.ashokit.repo.CityRepo;
import com.ashokit.repo.CountryRepo;
import com.ashokit.repo.StateRepo;
import com.ashokit.repo.UsersRepo;
import com.ashokit.service.UserMgmtService;
import com.ashokit.util.EmailUtils;


@Service
public class UserServiceImpl implements UserMgmtService {

	
	@Autowired
	private UsersRepo userRepo;
	
	@Autowired
	private CountryRepo countryRepo;
	
	@Autowired
	private StateRepo stateRepo;
	
	
	@Autowired
	private CityRepo cityRepo;
	
	@Autowired
	private EmailUtils emailUtils;


	@Override
	public String checkEmail(String email) {
	
		User user= userRepo.findByEmail(email);
		
		if(user == null) {
			return "Unique";
		}
		return "Duplicate";
	}


	@Override
	public Map<Integer, String> getCountries() {
	
		List<Country> countries = countryRepo.findAll();
		
		Map<Integer, String> countryMap = new HashMap<>();
		
		countries.forEach(country->{
			countryMap.put(country.getCountryId(), country.getCountryName());
		});
		
		return countryMap;
	}


	@Override
	public Map<Integer, String> getStates(Integer countryId) {
	
	 	List<State> states = stateRepo.findByCountryId(countryId);
	 	Map<Integer, String> stateMap = new HashMap<>();
	 	
	 	states.forEach(state ->{
	 		stateMap.put(state.getStateId(), state.getStateName());
	 	});
		return stateMap;
	}


	@Override
	public Map<Integer, String> getCities(Integer stateId) {
		
		List<City> cities = cityRepo.findByStateId(stateId);
		Map<Integer, String> cityMap = new HashMap<>();
		
		cities.forEach(city->{
			cityMap.put(city.getCityId(), city.getCityName());
		});
		
		return cityMap;
	}


	@Override
	public String registerUser(UserForm userform) {
	
		User entity = new User();
		BeanUtils.copyProperties(userform, entity);
		
		entity.setUserPwd(generateRandomPwd());
		
		entity.setAccStatus("Locked");
		userRepo.save(entity);
		
		String to  = userform.getEmail();
		String subject = "Registraton Email-Unlock Account";
		String body = readEmailBody("REG_EMAIL_BODY.txt", entity);
		
		emailUtils.sendEmail(to, subject, body);
		
		return "User Account Created";
	}


	@Override
	public String unlockAccount(UserUnLock accForm) {
	
		String email = accForm.getEmail();
		User user  = userRepo.findByEmail(email);
		
		if(user!=null && user.getUserPwd().equals(accForm.getTempPassword())){
			
			user.setUserPwd(accForm.getNewpassword());
			user.setAccStatus("UNLOCKED");
			userRepo.save(user);
			return "Account Unlocked";
		}
		return "Invalid Temporary Password";
	}


	@Override
	public String login(UserLogin loginForm) {
	
		User user = userRepo.findByEmailAndUserPwd(loginForm.getEmail(), loginForm.getPassword());
		
		if(user == null) {
			return "Invalid Credentials";
		}
		if(user.getAccStatus().equals("LOCKED")) {
			return "Account Locked";
		}
		return "SUCCESS";
	}


	@Override
	public String forgotPwd(String email) {
		User user = userRepo.findByEmail(email);
		
		if(user == null) {
			return "No Account Found";
		}
		
		String subject = "Recover Password";
		String body = readEmailBody("FORGOT_PWD_EMAIL_BODY.txt", user);
		
		emailUtils.sendEmail(email, subject, body);
		
		return "Pasword sent to registered email";
	}
	private String generateRandomPwd() {
		String text = "ABCDEFGHIJKLMNOPQRSTUVVWXYZ1234567890";
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		int pwdLength = 6;
		for(int i = 1; i<=pwdLength; i++) {
			int index  = random.nextInt(text.length());
			sb.append(text.charAt(index));
			
		}
		return sb.toString();
	}
	private String readEmailBody(String filename, User user) {
		
		StringBuffer sb = new StringBuffer();
		
		try (Stream<String> lines = (Files.lines(Paths.get(filename)))) {
			
			lines.forEach(line->{
				line = line.replace("${FirstName}", user.getFirstName());
				line = line.replace("${LastName}", user.getLastName());
				line = line.replace("${TempPassword}", user.getUserPwd());
				line = line.replace("${Email}", user.getEmail());
				line = line.replace("${Password}", user.getUserPwd());
				
				sb.append(line);
			});
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
}
