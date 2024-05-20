package com.terry.security.demo.springsecurity6demo.user.repository;

import com.terry.security.demo.springsecurity6demo.user.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	User findByUsernameAndPasswordAndMailAddress(String username, String password, String mailAddress);
	Collection<User> findAll();
}
