package com.googlecode.objectify.persister.test;

import com.googlecode.objectify.annotation.Entity;

@Entity
public class BasicAccount extends Account {

	private Integer credits;

	public int getAvaiableCredits() {
		return credits * 1;
	}

	public void addCredits(Integer credits) {
		this.credits = credits;
	}

}
