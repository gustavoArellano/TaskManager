package com.codingdojo.taskmanager.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.codingdojo.taskmanager.models.UserModel;



@Repository
public interface UserRepository extends CrudRepository<UserModel, Long> {
	UserModel findByEmail(String email);
	List<UserModel> findAll();
}
