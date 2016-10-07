package com.googlecode.objectify.persister.test;

import com.googlecode.objectify.annotation.Entity;

@Entity
public class PremiunAccount extends Account {

	private Integer specialCredits = 0;
	private Integer credits = 0;

	public int getAvaiableCredits() {
		return credits * 2 + specialCredits * 4;
	}

	public void addCredits(Integer credits) {
		this.credits = credits;
	}

	public void addSpecialCredits(Integer credits) {
		this.specialCredits += credits;
	}

}
