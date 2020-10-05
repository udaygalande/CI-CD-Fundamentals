package com.mindtree.services;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.mindtree.exceptions.MyException;
import com.mindtree.model.Book;
import com.mindtree.model.Cart;
import com.mindtree.model.Product;
import com.mindtree.model.User;


public interface ShoppingCartService {
	
	public Set<Product> getAllProducts() throws MyException;
	public List<Product> getCartProducts() throws MyException;
	public Product addProduct(int productId,int userId) throws MyException;
	public Product updateProducts( Set<Product> products) throws MyException;
	public Cart updateCart(Cart cart) throws MyException;
	public Cart getCartByUserId(int userId) throws MyException;
	public User getUserById(int userId) throws MyException;
	public Product getProductById(int productId) throws MyException;
	public Product updateProductQuantity( int id, int quantity) throws MyException;
	public Cart removeCart(int cartId) throws MyException;

}
