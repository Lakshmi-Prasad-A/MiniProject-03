package com.ashokit.repo;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ashokit.entity.City;

public interface CityRepo extends JpaRepository<City, Integer> {
	@Query("select * from City  where stateId=:stateId")
	public List<City> findByStateId(Integer stateId);
}
