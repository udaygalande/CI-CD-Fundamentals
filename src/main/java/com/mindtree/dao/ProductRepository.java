package com.mindtree.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.model.Product;


@Repository
public interface ProductRepository extends ProductBaseRepository<Product>{
	
	/*public List<Product> getAllProducts();
	public List<Product> getCartProducts();
	public boolean addProduct(Product product);
	public boolean updateCart(Product product);*/

}
