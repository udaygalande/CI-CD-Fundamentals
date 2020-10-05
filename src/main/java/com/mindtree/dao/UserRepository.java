package com.mindtree.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.model.User;


@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	

}
