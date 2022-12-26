package com.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashokit.entity.Users;

public interface UsersRepo extends JpaRepository<Users, Integer> {

}
