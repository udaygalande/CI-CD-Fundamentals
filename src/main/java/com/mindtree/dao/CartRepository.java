package com.mindtree.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.model.Cart;


@Repository
public interface CartRepository extends CrudRepository<Cart, Integer> {
 
}
