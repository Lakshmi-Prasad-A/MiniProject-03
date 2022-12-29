package com.ashokit.repo;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ashokit.entity.City;

public interface CityRepo extends JpaRepository<City, Integer> {
	@Query("select * from City ci where ci.State.StateId=:stateId")
	public Map<Integer, String> getCities(Integer stateId);
}
