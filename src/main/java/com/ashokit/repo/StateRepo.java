package com.ashokit.repo;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ashokit.entity.State;

public interface StateRepo extends JpaRepository<State, Integer> {
	
	@Query("select * from State s where s.country.CountryId=:countryId")
	public Map<Integer, String> getStates(Integer countryId);
}
