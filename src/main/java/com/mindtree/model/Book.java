package com.mindtree.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="Book")
@PrimaryKeyJoinColumn(name="book_id")  
public class Book extends Product {
//	private int productId;
	/*@Column(name = "book_id", insertable = false, updatable = false)
    private int bookId;*/
	private String genere;
	private String publications;
	
	/*public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}*/
	public String getGenere() {
		return genere;
	}
	public void setGenere(String genere) {
		this.genere = genere;
	}
	public String getPublications() {
		return publications;
	}
	public void setPublications(String publications) {
		this.publications = publications;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((genere == null) ? 0 : genere.hashCode());
		result = prime * result + ((publications == null) ? 0 : publications.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (genere == null) {
			if (other.genere != null)
				return false;
		} else if (!genere.equals(other.genere))
			return false;
		
		if (publications == null) {
			if (other.publications != null)
				return false;
		} else if (!publications.equals(other.publications))
			return false;
		return true;
	}
	
	
}
