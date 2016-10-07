package com.googlecode.objectify.persister.test;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.persister.annotation.Cascade;

@Entity
public class Order extends IdEntity {

	@Cascade
	private List<Item>		itens	= new ArrayList<Item>();

	private List<String>	notes	= new ArrayList<String>();

	@Cascade
	private User			user;

	@Cascade
	private Account			account;

	@Cascade
	private Info			info	= new Info();

	public Account getAccount() {
		return account;
	}

	public Info getInfo() {
		return info;
	}

	public List<Item> getItens() {
		return itens;
	}

	public List<String> getNotes() {
		return notes;
	}

	public User getUser() {
		return user;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
