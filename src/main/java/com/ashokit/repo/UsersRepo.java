package com.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.ashokit.entity.User;
import com.ashokit.pojos.UserUnLock;

public interface UsersRepo extends JpaRepository<User, Integer> {

	@Query("select email from user u.email=:email")
	public String checkEmail(String email);

	public String unlockAccount(UserUnLock accForm);

	@Query("select email from user u where u.email=:email and u.password=:password")
	public String login(@Param("email") String email, @Param ("password") String password);

	@Query("select password from user u where u.email=:email")
	public String getPassword(String email);

}
