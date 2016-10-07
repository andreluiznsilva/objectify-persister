package com.googlecode.objectify.persister;

import static com.googlecode.objectify.persister.util.TestUtils.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.googlecode.objectify.Query;
import com.googlecode.objectify.persister.test.Product;

public class TestForProduct extends BasePersisterTest {

	@Before
	public void setUp() throws Exception {
		objectifyFactory.register(Product.class);
	}

	@Test
	public void testDelete() {

		Product expected = create("code1", "aaa");

		persiter.put(expected);

		persiter.delete(expected);

	}

	@Test
	public void testFind() {

		Product expected = create("code1", "aaa");

		persiter.put(expected);

		Product result = persiter.find(Product.class, expected.getCode());

		assertEqualsProduct(expected, result);

	}

	@Test
	public void testGet() {

		Product expected = create("code1", "aaa");

		persiter.put(expected);

		Product result = persiter.get(Product.class, expected.getCode());

		assertEqualsProduct(expected, result);

	}

	@Test
	public void testPutT() {

		Product expected = create("code1", "aaa");

		persiter.put(expected);

	}

	@Test
	public void testQueryList() {

		Product product1 = create("code1", "aaa");
		Product product2 = create("code2", "bbb");
		Product product3 = create("code3", "ccc");

		persiter.put(product1, product2, product3);

		Query<Product> query = persiter.query(Product.class);

		List<Product> actuals = query.list();

		List<Product> expecteds = new ArrayList<Product>();
		expecteds.add(product1);
		expecteds.add(product2);
		expecteds.add(product3);

		assertEqualsProducts(expecteds, actuals);

	}

	@Test
	public void testQuerySingle() {

		Product product1 = create("code1", "aaa");
		Product product2 = create("code2", "bbb");
		Product product3 = create("code3", "ccc");

		persiter.put(product1, product2, product3);

		Query<Product> query = persiter.query(Product.class);

		Product actual = query.filter("name", product2.getName()).get();

		assertEqualsProduct(product2, actual);

	}

	@Test
	public void testRefresh() {

		Product expected = create("code1", "aaa");

		persiter.put(expected);

		Product result = create("code1", "");

		persiter.refresh(result);

		assertEqualsProduct(expected, result);

	}

}
