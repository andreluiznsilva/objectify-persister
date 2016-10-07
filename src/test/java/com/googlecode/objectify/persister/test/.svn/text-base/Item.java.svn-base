package com.googlecode.objectify.persister.test;

import javax.persistence.Entity;

import com.googlecode.objectify.persister.annotation.Cascade;

@Entity
public class Item extends IdEntity {

	@Cascade
	private Product product;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
