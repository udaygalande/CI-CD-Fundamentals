package com.mindtree.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.model.Product;


@Repository
public interface ProductBaseRepository<T extends Product> extends CrudRepository<T, Integer  > {
	public Iterable<T> findAll();
	
}
