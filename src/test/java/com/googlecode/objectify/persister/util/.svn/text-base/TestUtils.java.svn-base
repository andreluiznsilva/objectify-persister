package com.googlecode.objectify.persister.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.googlecode.objectify.persister.test.Address;
import com.googlecode.objectify.persister.test.BasicAccount;
import com.googlecode.objectify.persister.test.Info;
import com.googlecode.objectify.persister.test.Item;
import com.googlecode.objectify.persister.test.Order;
import com.googlecode.objectify.persister.test.PremiunAccount;
import com.googlecode.objectify.persister.test.Product;
import com.googlecode.objectify.persister.test.User;

public class TestUtils {

	public interface Comparation<T> {

		void compare(T expected, T actual);

	}

	public static void assertEqualsOrder(Order expected, Order actual) {

		assertEqualsUser(expected.getUser(), actual.getUser());
		assertEqualsInfo(expected.getInfo(), actual.getInfo());

		compareCollections(expected.getNotes(), actual.getNotes(), new Comparation<String>() {

			public void compare(String expected, String actual) {
				assertEquals(expected, actual);
			}

		});

		compareCollections(expected.getItens(), actual.getItens(), new Comparation<Item>() {

			public void compare(Item expected, Item actual) {
				assertEquals(expected.getId(), actual.getId());
				assertEqualsProduct(expected.getProduct(), actual.getProduct());
			}

		});

	}

	public static void assertEqualsOrders(List<Order> expecteds, List<Order> actuals) {

		compareCollections(expecteds, actuals, new Comparation<Order>() {

			public void compare(Order expected, Order actual) {
				assertEqualsOrder(expected, expected);
			}

		});

	}

	public static void assertEqualsProduct(Product expected, Product actual) {

		if (notNulls(expected, actual)) {
			assertEquals(expected.getName(), actual.getName());
			assertEquals(expected.getCode(), actual.getCode());
		} else if (!allNulls(expected, actual)) {
			fail("On object is null");
		}

	}

	public static void assertEqualsProducts(List<Product> expecteds, List<Product> actuals) {

		compareCollections(expecteds, actuals, new Comparation<Product>() {

			public void compare(Product expected, Product actual) {
				assertEqualsProduct(expected, actual);
			}

		});

	}

	public static void assertEqualsUser(User expected, User actual) {

		if (notNulls(expected, actual)) {
			assertEquals(expected.getName(), actual.getName());
			assertEquals(expected.getId(), actual.getId());
		} else if (!allNulls(expected, actual)) {
			fail("On object is null");
		}

	}

	public static void assertEqualsUsers(Collection<User> expecteds, Collection<User> actuals) {

		compareCollections(expecteds, actuals, new Comparation<User>() {

			public void compare(User expected, User actual) {
				assertEqualsUser(expected, actual);
			}

		});

	}

	public static User cloneOf(User user) {
		User result = new User();
		result.setId(user.getId());
		return result;
	}

	public static User create(String name) {
		User result = new User();
		result.setName(name);
		return result;
	}

	public static Order create(String name, int itens, boolean basic) {

		Order result = new Order();
		result.setUser(create(name));

		if (basic) {
			result.setAccount(createBasicAccount());
		} else {
			result.setAccount(createPremiunAccount());
		}

		for (int i = 0; i < itens; i++) {
			result.getNotes().add("Note" + i);
			result.getItens().add(createIten(i));
			result.getInfo().getAddress().add(createAdress(i));
		}

		return result;

	}

	public static Product create(String code, String name) {
		Product result = new Product();
		result.setCode(code);
		result.setName(name);
		return result;
	}

	private static boolean allNulls(Object expected, Object actual) {
		return expected == null && actual == null;
	}

	private static void assertEqualsAddress(Address expected, Address actual) {

		if (notNulls(expected, actual)) {
			assertEquals(expected.getId(), actual.getId());
			assertEquals(expected.getText(), actual.getText());
		} else if (!allNulls(expected, actual)) {
			fail("On object is null");
		}

	}

	private static void assertEqualsInfo(Info expected, Info actual) {

		if (notNulls(expected, actual)) {

			assertEquals(expected.getId(), actual.getId());

			compareCollections(expected.getAddress(), actual.getAddress(), new Comparation<Address>() {

				public void compare(Address expected, Address actual) {
					assertEqualsAddress(expected, actual);
				}

			});

		} else if (!allNulls(expected, actual)) {
			fail("On object is null");
		}

	}

	private static <T> void compareCollections(Collection<T> expecteds, Collection<T> actuals, Comparation<T> asert) {

		assertEquals(expecteds.size(), actuals.size());

		Iterator<T> itExpected = expecteds.iterator();
		Iterator<T> itActual = actuals.iterator();

		while (itExpected.hasNext() && itActual.hasNext()) {
			T expected = itExpected.next();
			T actual = itActual.next();
			asert.compare(expected, actual);
		}

	}

	private static Address createAdress(int i) {
		Address result = new Address();
		result.setText("adress" + i);
		return result;
	}

	private static BasicAccount createBasicAccount() {
		BasicAccount account = new BasicAccount();
		account.setNumber("1234");
		account.addCredits(1);
		return account;
	}

	private static Item createIten(int i) {
		Item result = new Item();
		result.setProduct(create("code" + i, "product " + i));
		return result;
	}

	private static PremiunAccount createPremiunAccount() {
		PremiunAccount account = new PremiunAccount();
		account.setNumber("5678");
		account.addCredits(2);
		account.addSpecialCredits(5);
		return account;
	}

	private static boolean notNulls(Object expected, Object actual) {
		return expected != null && actual != null;
	}

}
