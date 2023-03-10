package com.ashokit.repo;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ashokit.entity.State;

public interface StateRepo extends JpaRepository<State, Integer> {
	
	@Query("select * from State where countryId=:countryId")
	public List<State> findByCountryId(Integer countryId);
}
