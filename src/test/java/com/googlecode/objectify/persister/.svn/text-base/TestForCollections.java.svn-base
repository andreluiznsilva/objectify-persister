package com.googlecode.objectify.persister;

import static com.googlecode.objectify.persister.util.TestUtils.assertEqualsOrder;
import static com.googlecode.objectify.persister.util.TestUtils.create;

import org.junit.Before;
import org.junit.Test;

import com.googlecode.objectify.persister.test.Address;
import com.googlecode.objectify.persister.test.BasicAccount;
import com.googlecode.objectify.persister.test.Info;
import com.googlecode.objectify.persister.test.Item;
import com.googlecode.objectify.persister.test.Order;
import com.googlecode.objectify.persister.test.PremiunAccount;
import com.googlecode.objectify.persister.test.Product;
import com.googlecode.objectify.persister.test.User;

public class TestForCollections extends BasePersisterTest {

	@Before
	public void setUp() throws Exception {

		Class<?>[] classes = new Class<?>[] { Order.class, User.class, Product.class, Item.class, Info.class,
				Address.class, BasicAccount.class, PremiunAccount.class };

		for (Class<?> clazz : classes) {
			objectifyFactory.register(clazz);
		}

	}

	@Test
	public void testDelete() {

		Order expected = create("aaa", 1, true);

		persiter.put(expected);

		expected.setUser(null);
		expected.setInfo(null);
		expected.setAccount(null);
		expected.getNotes().clear();
		expected.getItens().clear();

		persiter.put(expected);

		Order result = persiter.find(Order.class, expected.getId());

		assertEqualsOrder(expected, result);

	}

	@Test
	public void testUpdate() {

		Order expected = create("aaa", 1, true);

		persiter.put(expected);

		expected.getUser().setName("xxx");
		expected.getNotes().clear();
		expected.getNotes().add("a");
		expected.getNotes().add("b");
		expected.getNotes().add("c");
		expected.getItens().get(0).getProduct().setName("z");

		persiter.put(expected);

		Order result = persiter.find(Order.class, expected.getId());

		assertEqualsOrder(expected, result);

	}

	@Test
	public void testChange() {

		Order expected = create("aaa", 2, true);

		persiter.put(expected);

		expected.setUser(create("z"));
		Info info = new Info();
		Address address = new Address();
		info.getAddress().add(address);
		expected.setInfo(info);
		expected.getNotes().clear();
		expected.getNotes().add("z");
		expected.getItens().get(0).setProduct(create("z", "z"));
		expected.getItens().remove(1);

		persiter.put(expected);

		Order result = persiter.find(Order.class, expected.getId());

		assertEqualsOrder(expected, result);

	}

}
