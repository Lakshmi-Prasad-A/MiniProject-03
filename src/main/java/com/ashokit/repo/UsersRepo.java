package com.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.ashokit.entity.User;


public interface UsersRepo extends JpaRepository<User, Integer> {

	@Query("select * from user where email=:email")
	public User findByEmail(String email);
	
	//select * from user where email=? and userpassword=?;
	public User findByEmailAndUserPwd(String email, String pwd);

	@Query("select email from user u where u.email=:email and u.password=:password")
	public String login(@Param("email") String email, @Param ("password") String password);

	@Query("select password from user u where u.email=:email")
	public String getPassword(String email);

}
