package com.mindtree.controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mindtree.exceptions.MyException;
import com.mindtree.model.Cart;
import com.mindtree.model.Product;
import com.mindtree.model.User;
import com.mindtree.services.ShoppingCartService;

@Controller
@EnableWebSecurity
public class ShoppingCartController {
	private static final Logger logger = LogManager.getLogger(ShoppingCartController.class);

	private String userMessage = "";
	@Autowired
	private ShoppingCartService shoppingCartService;

	@RequestMapping("/")
	public String getShoppingCartPage(HttpServletRequest req) {
		req.setAttribute("mode", "HOME");
		return "addToCart";
	}

	@RequestMapping("/productList")
	public String getProductList(HttpServletRequest req) {
		Set<Product> products = null;
		try {
			products = shoppingCartService.getAllProducts();
		} catch (MyException exception) {
			logger.error("Exception occured " + exception.getMessage() + " " + exception.getErrorCode());
		}
		req.setAttribute("mode", "SHOW_PRODUCTS");
		req.setAttribute("products", products);
		if (products.size() == 0) {
			userMessage = "Cart is Empty!";
			req.setAttribute("userMessage", userMessage);
		}
		return "addToCart";
	}

	@RequestMapping("/addToCart")
	public String addToCart(@RequestParam(value = "id") Integer productId, HttpServletRequest req) {
		try {
			int userId = 1;
			Product product = shoppingCartService.addProduct(productId, userId);

			if (product != null) {
				logger.info("Adding the product " + product.getProductId() + "in the cart");
				req.setAttribute("products", shoppingCartService.getAllProducts());
				req.setAttribute("mode", "SHOW_PRODUCTS");
				userMessage = "Product with name - " + product.getProductName() + " succesfully added";
			}

			else {
				logger.info("product with product id " + productId + " not present");
				userMessage = "Product with name - " + product.getProductName() + " was not added";
				req.setAttribute("mode", "HOME");
			}

			req.setAttribute("userMessage", userMessage);
		} catch (MyException exception) {
			logger.error("Exception occured " + exception.getMessage() + " " + exception.getErrorCode());
		}
		return "addToCart";

	}

	@RequestMapping("/editCart")
	public String editCart(Model model) {
		try {
			logger.info("Displaying all product already added in the cart ---");
			int userId = 1;
			User user = shoppingCartService.getUserById(userId);
			Cart cart = user.getCart();
			logger.info("products -------- " + cart.getProducts());
			cart.setUser(user);
			model.addAttribute("cart", cart);
		} catch (MyException exception) {
			logger.error("Exception occured " + exception.getMessage() + " " + exception.getErrorCode());
		}
		return "cart";

	}

	@RequestMapping(value = "/updateCart", method = RequestMethod.POST)
	public String updateCart(@ModelAttribute("cart") Cart cart, HttpServletRequest req) {
		try {
			logger.info("cart -------- " + cart.getCartId());
			logger.info("user -------- " + cart.getUser());
			logger.info("products -------- " + cart.getProducts());
			User user = cart.getUser();
			for (Product product : cart.getProducts()) {
				product.setCart(cart);
				logger.info("cart- > user -------" + product.getCart().getUser().getId());
			}
			shoppingCartService.updateProducts(cart.getProducts());
			logger.info("products -------- " + cart.getProducts());
			req.setAttribute("products", cart.getProducts());
			req.setAttribute("mode", "SHOW_PRODUCTS");
		} catch (MyException exception) {
			logger.error("Exception occured " + exception.getMessage() + " " + exception.getErrorCode());
		}
		return "addToCart";

	}

	@RequestMapping("/showDetails")
	public String showProductDetails(@RequestParam(value = "id") Integer productId, HttpServletRequest req) {
		try {
			logger.info(" Displaying Product details of the product with id - " + productId);
			Product product = shoppingCartService.getProductById(productId);
			req.setAttribute("product", product);
			req.setAttribute("mode", "PRODUCT_DETAILS");
		} catch (MyException exception) {
			logger.error("Exception occured " + exception.getMessage() + " " + exception.getErrorCode());
		}
		return "addToCart";

	}

	@RequestMapping("/updateProduct")
	public String updateProduct(@RequestParam(value = "id") Integer productId,
			@RequestParam(value = "quantity") Integer quantity, HttpServletRequest req) {
		try {
			logger.info("update product - id " + productId + " and quantity " + quantity);
			Product product = shoppingCartService.updateProductQuantity(productId, quantity);
			/*
			 * req.setAttribute("product",product); req.setAttribute("mode",
			 * "PRODUCT_DETAILS");
			 */
			req.setAttribute("products", shoppingCartService.getAllProducts());
			req.setAttribute("mode", "SHOW_PRODUCTS");
		} catch (MyException exception) {
			logger.error("Exception occured " + exception.getMessage() + " " + exception.getErrorCode());
		}
		return "addToCart";
	}

	@RequestMapping(value = "/deleteAll", method = RequestMethod.GET)
	public String removeCart(@RequestParam(value = "id") Integer cartId, Model model, HttpServletRequest req) {
		try {
			logger.info("deleting all the products form the cart with cart id " + cartId);
			Cart cart = shoppingCartService.removeCart(cartId);
			model.addAttribute("cart", cart);
			Set<Product> products = shoppingCartService.getAllProducts();
			req.setAttribute("mode", "SHOW_PRODUCTS");
			req.setAttribute("products", products);
			logger.info("Products  in cart --- " + cart.getProducts());
			if (cart.getProducts().size() == 0) {
				userMessage = "Cart is Empty!";
				req.setAttribute("userMessage", userMessage);
			}
		} catch (MyException exception) {
			logger.error("Exception occured " + exception.getMessage() + " " + exception.getErrorCode());
		}
		return "addToCart";
	}
}
