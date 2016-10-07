package com.googlecode.objectify.persister.test;


public abstract class Account extends IdEntity {

	private String number;

	public abstract void addCredits(Integer credits);

	public abstract int getAvaiableCredits();

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
