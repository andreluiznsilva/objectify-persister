package com.googlecode.objectify.persister;

import static com.googlecode.objectify.persister.util.TestUtils.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.googlecode.objectify.Query;
import com.googlecode.objectify.persister.test.Address;
import com.googlecode.objectify.persister.test.BasicAccount;
import com.googlecode.objectify.persister.test.Info;
import com.googlecode.objectify.persister.test.Item;
import com.googlecode.objectify.persister.test.Order;
import com.googlecode.objectify.persister.test.PremiunAccount;
import com.googlecode.objectify.persister.test.Product;
import com.googlecode.objectify.persister.test.User;

public class TestForOrder extends BasePersisterTest {

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

		persiter.delete(expected);

	}

	@Test
	public void testFind() {

		Order expected = create("aaa", 1, true);

		persiter.put(expected);

		Order result = persiter.find(Order.class, expected.getId());

		assertEqualsOrder(expected, result);

	}

	@Test
	public void testGet() {

		Order expected = create("aaa", 1, true);

		persiter.put(expected);

		Order result = persiter.get(Order.class, expected.getId());

		assertEqualsOrder(expected, result);

	}

	@Test
	public void testPutT() {

		Order expected = create("aaa", 1, true);

		persiter.put(expected);

	}

	@Test
	public void testQueryList() {

		Order order1 = create("aaa", 1, true);
		Order order2 = create("bbb", 2, false);
		Order order3 = create("ccc", 3, true);

		persiter.put(order1, order2, order3);

		Query<Order> query = persiter.query(Order.class);

		List<Order> actuals = query.list();

		List<Order> expecteds = new ArrayList<Order>();
		expecteds.add(order1);
		expecteds.add(order2);
		expecteds.add(order3);

		assertEqualsOrders(expecteds, actuals);

	}

	@Test
	public void testQuerySingle() {

		Order order1 = create("aaa", 1, true);
		Order order2 = create("bbb", 2, false);
		Order order3 = create("ccc", 3, true);

		persiter.put(order1, order2, order3);

		Query<Order> query = persiter.query(Order.class);

		Order actual = query.filter("user", order2.getUser()).get();

		assertEqualsOrder(order2, actual);

	}

	@Test
	public void testRefresh() {

		Order expected = create("aaa", 1, false);

		persiter.put(expected);

		Order result = new Order();
		result.setId(expected.getId());

		persiter.refresh(result);

		assertEqualsOrder(expected, result);

	}

}
