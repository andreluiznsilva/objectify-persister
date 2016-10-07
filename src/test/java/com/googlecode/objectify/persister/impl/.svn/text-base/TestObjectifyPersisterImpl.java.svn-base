package com.googlecode.objectify.persister.impl;

import static com.googlecode.objectify.persister.util.TestUtils.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Transaction;
import com.googlecode.objectify.AsyncObjectify;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.Query;
import com.googlecode.objectify.persister.AsyncObjectifyPersister;
import com.googlecode.objectify.persister.BasePersisterTest;
import com.googlecode.objectify.persister.test.Product;
import com.googlecode.objectify.persister.test.User;

public class TestObjectifyPersisterImpl extends BasePersisterTest {

	@Before
	public void setUp() throws Exception {

		objectifyFactory.register(User.class);
		objectifyFactory.register(Product.class);

	}

	@Test
	public void testAsync() {

		AsyncObjectify async = persiter.async();

		assertNotNull(async);
		assertTrue(async instanceof AsyncObjectifyPersister);

	}

	@Test
	public void testGetcObjectify() {

		Objectify objectify = persiter.getObjectify();

		assertNotNull(objectify);

	}

	@Test
	public void testDeleteClassOfTLong() {

		User user1 = create("aaa");
		User user2 = create("bbb");

		persiter.put(user1, user2);

		persiter.delete(User.class, user1.getId());
		persiter.delete(User.class, user2.getId());

	}

	@Test
	public void testDeleteClassOfTString() {

		Product prod1 = create("code1", "aaa");
		Product prod2 = create("code2", "bbb");

		persiter.put(prod1, prod2);

		persiter.delete(Product.class, prod1.getCode());
		persiter.delete(Product.class, prod2.getCode());

	}

	@Test
	public void testDeleteIterableOfQ() {

		List<User> users = new ArrayList<User>();
		users.add(create("aaa"));
		users.add(create("bbb"));

		persiter.put(users);

		persiter.delete(users);

	}

	@Test
	public void testDeleteObjectArray() {

		User user1 = create("aaa");
		User user2 = create("bbb");

		persiter.put(user1, user2);

		persiter.delete(user1, user2);

	}

	@Test
	public void testFindClassOfQextendsTLong() {

		User user1 = create("aaa");

		persiter.put(user1);

		User result = persiter.find(User.class, user1.getId());

		assertEqualsUser(user1, result);

	}

	@Test
	public void testFindClassOfQextendsTString() {

		Product prod = create("code1", "aaa");

		persiter.put(prod);

		Product result = persiter.find(Product.class, prod.getCode());

		assertEqualsProduct(prod, result);

	}

	@Test
	public void testFindKeyOfQextendsT() {

		User user = create("aaa");

		persiter.put(user);

		Key<User> key = new Key<User>(User.class, user.getId());

		User result = persiter.find(key);

		assertEqualsUser(user, result);

	}

	@Test
	public void testGetClassOfQextendsTIterableOfS() {

		User user1 = create("aaa");
		User user2 = create("bbb");

		persiter.put(user1, user2);

		List<Long> ids = new ArrayList<Long>();
		ids.add(user1.getId());
		ids.add(user2.getId());

		Map<Long, User> results = persiter.get(User.class, ids);

		List<User> users = new ArrayList<User>();
		users.add(user1);
		users.add(user2);

		assertEqualsUsers(users, results.values());

	}

	@Test
	public void testGetClassOfQextendsTLong() {

		User user = create("aaa");

		persiter.put(user);

		User result = persiter.get(User.class, user.getId());

		assertEqualsUser(user, result);

	}

	@Test
	public void testGetClassOfQextendsTSArray() {

		User user1 = create("aaa");
		User user2 = create("bbb");

		persiter.put(user1, user2);

		Map<Long, User> results = persiter.get(User.class, user1.getId(), user2.getId());

		List<User> users = new ArrayList<User>();
		users.add(user1);
		users.add(user2);

		assertEqualsUsers(users, results.values());

	}

	@Test
	public void testGetClassOfQextendsTString() {

		Product prod = create("code1", "aaa");

		persiter.put(prod);

		Product result = persiter.get(Product.class, prod.getCode());

		assertEqualsProduct(prod, result);

	}

	@Test
	public void testGetDatastore() {

		DatastoreService datastore = persiter.getDatastore();
		assertNotNull(datastore);

	}

	@Test
	public void testGetFactory() {

		ObjectifyFactory factory = persiter.getFactory();

		assertNotNull(factory);
		assertEquals(objectifyFactory, factory);

	}

	@Test
	public void testGetIterableOfQextendsKeyOfQextendsT() {

		User user1 = create("aaa");
		User user2 = create("bbb");

		persiter.put(user1, user2);

		List<Key<User>> keys = new ArrayList<Key<User>>();
		keys.add(new Key<User>(User.class, user1.getId()));
		keys.add(new Key<User>(User.class, user2.getId()));

		Map<Key<User>, User> results = persiter.get(keys);

		List<User> users = new ArrayList<User>();
		users.add(user1);
		users.add(user2);

		assertEqualsUsers(users, results.values());

	}

	@Test
	public void testGetKeyOfQextendsT() {

		User user = create("aaa");

		persiter.put(user);

		Key<User> key = new Key<User>(User.class, user.getId());

		User result = persiter.get(key);

		assertEqualsUser(user, result);

	}

	@Test
	public void testGetTxn() {

		persiter = objectifyFactory.beginTransaction();

		Transaction txn = persiter.getTxn();

		assertNotNull(txn);

	}

	@Test
	public void testPutIterableOfQextendsT() {

		User user1 = create("aaa");
		User user2 = create("bbb");

		List<User> users = new ArrayList<User>();
		users.add(user1);
		users.add(user2);

		persiter.put(users);

	}

	@Test
	public void testPutT() {

		User user1 = create("aaa");

		persiter.put(user1);

	}

	@Test
	public void testPutTArray() {

		User user1 = create("aaa");
		User user2 = create("bbb");

		persiter.put(user1, user2);

	}

	@Test
	public void testQuery() {

		Query<Object> query = persiter.query();

		assertNotNull(query);
		assertTrue(query instanceof QueryPersisterImpl);

	}

	@Test
	public void testQueryClassOfT() {

		Query<User> query = persiter.query(User.class);

		assertNotNull(query);
		assertTrue(query instanceof QueryPersisterImpl);

	}

	@Test
	public void testRefreshCollectionOfT() {

		List<User> users = new ArrayList<User>();
		users.add(create("aaa"));
		users.add(create("bbb"));
		users.add(create("ccc"));

		persiter.put(users);

		List<User> tests = new ArrayList<User>();
		tests.add(cloneOf(users.get(0)));
		tests.add(cloneOf(users.get(1)));
		tests.add(cloneOf(users.get(2)));

		persiter.refresh(tests);

		assertEqualsUsers(users, tests);

	}

	@Test
	public void testRefreshT() {

		User user1 = create("aaa");

		persiter.put(user1);

		User user2 = cloneOf(user1);

		persiter.refresh(user2);

		assertEqualsUser(user1, user2);

	}

	@Test
	public void testRefreshTArray() {

		User user1 = create("aaa");
		User user2 = create("bbb");
		User user3 = create("ccc");

		persiter.put(user1, user2, user3);

		User test1 = cloneOf(user1);
		User test2 = cloneOf(user2);
		User test3 = cloneOf(user3);

		persiter.refresh(test1, test2, test3);

		assertEqualsUser(user1, test1);
		assertEqualsUser(user2, test2);
		assertEqualsUser(user3, test3);

	}

}
