package com.mindtree.services.impl;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mindtree.dao.ApparalRepository;
import com.mindtree.dao.BookRepository;
import com.mindtree.dao.CartRepository;
import com.mindtree.dao.ProductRepository;
import com.mindtree.dao.UserRepository;
import com.mindtree.exceptions.MyException;
import com.mindtree.model.Cart;
import com.mindtree.model.Product;
import com.mindtree.model.User;
import com.mindtree.services.ShoppingCartService;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

	private static final Logger logger = LogManager.getLogger(ShoppingCartServiceImpl.class);
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private ApparalRepository apparalRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CartRepository cartRepository;

	@Transactional
	public Set<Product> getAllProducts() throws MyException {
		try {
			Set<Product> products = new HashSet<Product>();
			for (Product product : bookRepository.findAll()) {
				products.add(product);

			}

			for (Product product : apparalRepository.findAll()) {
				products.add(product);

			}
			return products;
		} catch (IllegalArgumentException exception) {
			throw new MyException(exception.getMessage(),
					"method has been passed an illegal or inappropriate argument");
		} catch (Exception exception) {
			throw new MyException(exception.getMessage(), "Unresolved Exception occured");
		}

	}


	@Transactional
	public Product addProduct(int productId, int userId) throws MyException {
		try {
			Optional<Product> optional = productRepository.findById(productId);
			// Product product = null;
			if (optional.isPresent()) {
				Product product = optional.get();

				Optional<User> optionalUser = userRepository.findById(userId);
				Set<Product> products;
				if (optionalUser.isPresent()) {
					User user = optionalUser.get();
					Cart cart;
					if (user.getCart() == null) {
						cart = new Cart();
						products = new HashSet<>();
						product.setQuantity(product.getQuantity() + 1);
						product.setCart(cart);
						products.add(product);
					} else {

						cart = user.getCart();
						logger.info("cart products size - " + cart.getProducts().size());
						products = cart.getProducts();
						product.setQuantity(product.getQuantity() + 1);
						product.setCart(cart);
						products.add(product);
						cart.setProducts(products);
						logger.info("cart products size - " + cart.getProducts().size());
					}

					cart.setUser(user);
					user.setCart(cart);
					user.setName("uday galande");
					userRepository.save(user);
				}

				return product;
			}
		} catch (IllegalArgumentException exception) {
			throw new MyException(exception.getMessage(),
					"method has been passed an illegal or inappropriate argument");
		} catch (NoSuchElementException exception) {
			throw new MyException(exception.getMessage(), "There is no value present with the given id");
		} catch (Exception exception) {
			throw new MyException(exception.getMessage(), "Unresolved Exception occured");
		}
		return null;
	}

	@Transactional
	public Cart updateCart(Cart cart) throws MyException {
		try {
			cart = cartRepository.save(cart);
			return cart;
		} catch (IllegalArgumentException exception) {
			throw new MyException(exception.getMessage(),
					"method has been passed an illegal or inappropriate argument");
		} catch (Exception exception) {
			throw new MyException(exception.getMessage(), "Unresolved Exception occured");
		}

	}

	@Override
	public User getUserById(int userId) {
		Optional<User> optional = userRepository.findById(userId);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	@Transactional
	public Product getProductById(int productId) throws MyException {
		try {
			Optional<Product> optional = productRepository.findById(productId);
			if (optional.isPresent()) {
				return (optional.get());
			}
		} catch (IllegalArgumentException exception) {
			throw new MyException(exception.getMessage(),
					"method has been passed an illegal or inappropriate argument");
		} catch (NoSuchElementException exception) {
			throw new MyException(exception.getMessage(), "There is no value present with the given id");
		} catch (Exception exception) {
			throw new MyException(exception.getMessage(), "Unresolved Exception occured");
		}
		return null;
	}

	@Override
	public Product updateProducts(Set<Product> products) throws MyException {
		try {
			for (Product product : products) {
				productRepository.save(product);
			}
		} catch (IllegalArgumentException exception) {
			throw new MyException(exception.getMessage(),
					"method has been passed an illegal or inappropriate argument");

		} catch (Exception exception) {
			throw new MyException(exception.getMessage(), "Unresolved Exception occured");
		}

		return null;
	}

	@Transactional
	@Override
	public Product updateProductQuantity(int productId, int quantity) throws MyException {
		try {
			Optional<Product> optional = productRepository.findById(productId);
			Product product = null;
			if (optional.isPresent()) {
				product = (optional.get());
				product.setQuantity(quantity);
				if (quantity == 0)
					product.setCart(null);

			}
			return productRepository.save(product);
		} catch (IllegalArgumentException exception) {
			throw new MyException(exception.getMessage(),
					"method has been passed an illegal or inappropriate argument");
		} catch (NoSuchElementException exception) {
			throw new MyException(exception.getMessage(), "There is no value present with the given id");
		} catch (Exception exception) {
			throw new MyException(exception.getMessage(), "Unresolved Exception occured");
		}
	}

	@Override
	@Transactional
	public Cart removeCart(int cartId) throws MyException {
		try {
			Optional<Cart> optional = cartRepository.findById(cartId);
			if (optional.isPresent()) {
				Cart cart = optional.get();
				for (Product product : cart.getProducts()) {
					product.setQuantity(0);
					product.setCart(null);
					productRepository.save(product);
					// cartRepository.deleteAll();
				}
				// cartRepository.delete(cart);
				return cart;
			}
		} catch (IllegalArgumentException exception) {
			throw new MyException(exception.getMessage(),
					"method has been passed an illegal or inappropriate argument");
		} catch (NoSuchElementException exception) {
			throw new MyException(exception.getMessage(), "There is no value present with the given id");
		} catch (Exception exception) {
			throw new MyException(exception.getMessage(), "Unresolved Exception occured");
		}
		return null;
	}

}
