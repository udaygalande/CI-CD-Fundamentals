package com.mindtree.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="cart")
public class Cart {
	
	
	@Id	
	@Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cartId;
	
	@OneToMany(cascade = CascadeType.ALL/*,
            fetch = FetchType.LAZY*/)
	@JoinColumn(name="cart_id")
	Set<Product> products;
	
	@OneToOne(fetch = FetchType.LAZY,
			cascade = CascadeType.ALL)
	@JoinColumn(name="user_id")
	private User user;
	/*private Map<Product, Integer>  products;
	private Map<Product, int>*/
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cartId;
		result = prime * result + ((products == null) ? 0 : products.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cart other = (Cart) obj;
		if (cartId != other.cartId)
			return false;
		if (products == null) {
			if (other.products != null)
				return false;
		} else if (!products.equals(other.products))
			return false;
		return true;
	}
}
