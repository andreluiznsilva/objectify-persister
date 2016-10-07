package com.googlecode.objectify.persister.test;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.googlecode.objectify.persister.annotation.Cascade;

@Entity
public class Info {

	@Id
	private Long			id;

	@Cascade
	private List<Address>	address	= new ArrayList<Address>();

	public List<Address> getAddress() {
		return address;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
