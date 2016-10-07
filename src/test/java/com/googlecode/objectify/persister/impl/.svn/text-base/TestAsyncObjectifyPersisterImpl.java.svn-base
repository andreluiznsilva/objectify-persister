package com.googlecode.objectify.persister.impl;

import static com.googlecode.objectify.persister.util.TestUtils.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.google.appengine.api.datastore.AsyncDatastoreService;
import com.googlecode.objectify.AsyncObjectify;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.persister.AsyncObjectifyPersister;
import com.googlecode.objectify.persister.BasePersisterTest;
import com.googlecode.objectify.persister.ObjectifyPersister;
import com.googlecode.objectify.persister.test.Product;
import com.googlecode.objectify.persister.test.User;

public class TestAsyncObjectifyPersisterImpl extends BasePersisterTest {

	private AsyncObjectifyPersister asyncPersiter;

	@Before
	public void setUp() throws Exception {

		objectifyFactory.register(User.class);
		objectifyFactory.register(Product.class);

		asyncPersiter = objectifyFactory.begin().async();

	}

	@Test
	public void testDeleteClassOfTLong() {

		User user1 = create("aaa");
		User user2 = create("bbb");

		asyncPersiter.put(user1, user2).get();

		asyncPersiter.delete(User.class, user1.getId()).get();
		asyncPersiter.delete(User.class, user2.getId()).get();

	}

	@Test
	public void testDeleteClassOfTString() {

		Product prod1 = create("code1", "aaa");
		Product prod2 = create("code2", "bbb");

		asyncPersiter.put(prod1, prod2).get();

		asyncPersiter.delete(Product.class, prod1.getCode()).get();
		asyncPersiter.delete(Product.class, prod2.getCode()).get();

	}

	@Test
	public void testDeleteIterableOfQ() {

		List<User> users = new ArrayList<User>();
		users.add(create("aaa"));
		users.add(create("bbb"));

		asyncPersiter.put(users).get();

		asyncPersiter.delete(users).get();

	}

	@Test
	public void testDeleteObjectArray() {

		User user1 = create("aaa");
		User user2 = create("bbb");

		asyncPersiter.put(user1, user2).get();

		asyncPersiter.delete(user1, user2).get();

	}

	@Test
	public void testFindClassOfQextendsTLong() {

		User user1 = create("aaa");

		asyncPersiter.put(user1).get();

		User result = asyncPersiter.find(User.class, user1.getId()).get();

		assertEqualsUser(user1, result);

	}

	@Test
	public void testFindClassOfQextendsTString() {

		Product prod = create("code1", "aaa");

		asyncPersiter.put(prod).get();

		Product result = asyncPersiter.find(Product.class, prod.getCode()).get();

		assertEqualsProduct(prod, result);

	}

	@Test
	public void testFindKeyOfQextendsT() {

		User user = create("aaa");

		asyncPersiter.put(user).get();

		Key<User> key = new Key<User>(User.class, user.getId());

		User result = asyncPersiter.find(key).get();

		assertEqualsUser(user, result);

	}

	@Test
	public void testGetAsyncDatastore() {

		AsyncDatastoreService datastore = asyncPersiter.getAsyncDatastore();
		;
		assertNotNull(datastore);

	}

	@Test
	public void testGetClassOfQextendsTIterableOfS() {

		User user1 = create("aaa");
		User user2 = create("bbb");

		asyncPersiter.put(user1, user2).get();

		List<Long> ids = new ArrayList<Long>();
		ids.add(user1.getId());
		ids.add(user2.getId());

		Map<Long, User> results = asyncPersiter.get(User.class, ids).get();

		List<User> users = new ArrayList<User>();
		users.add(user1);
		users.add(user2);

		assertEqualsUsers(users, results.values());

	}

	@Test
	public void testGetClassOfQextendsTLong() {

		User user = create("aaa");

		asyncPersiter.put(user).get();

		User result = asyncPersiter.get(User.class, user.getId()).get();

		assertEqualsUser(user, result);

	}

	@Test
	public void testGetClassOfQextendsTSArray() {

		User user1 = create("aaa");
		User user2 = create("bbb");

		asyncPersiter.put(user1, user2).get();

		Map<Long, User> results = asyncPersiter.get(User.class, user1.getId(), user2.getId()).get();

		List<User> users = new ArrayList<User>();
		users.add(user1);
		users.add(user2);

		assertEqualsUsers(users, results.values());

	}

	@Test
	public void testGetClassOfQextendsTString() {

		Product prod = create("code1", "aaa");

		asyncPersiter.put(prod).get();

		Product result = asyncPersiter.get(Product.class, prod.getCode()).get();

		assertEqualsProduct(prod, result);

	}

	@Test
	public void testGetFactory() {

		ObjectifyPersisterFactory factory = asyncPersiter.getFactory();

		assertNotNull(factory);
		assertEquals(objectifyFactory, factory);

	}

	@Test
	public void testGetAsyncObjectify() {

		AsyncObjectify objectify = asyncPersiter.getAsyncObjectify();

		assertNotNull(objectify);

	}

	@Test
	public void testGetIterableOfQextendsKeyOfQextendsT() {

		User user1 = create("aaa");
		User user2 = create("bbb");

		asyncPersiter.put(user1, user2).get();

		List<Key<User>> keys = new ArrayList<Key<User>>();
		keys.add(new Key<User>(User.class, user1.getId()));
		keys.add(new Key<User>(User.class, user2.getId()));

		Map<Key<User>, User> results = asyncPersiter.get(keys).get();

		List<User> users = new ArrayList<User>();
		users.add(user1);
		users.add(user2);

		assertEqualsUsers(users, results.values());

	}

	@Test
	public void testGetKeyOfQextendsT() {

		User user = create("aaa");

		asyncPersiter.put(user).get();

		Key<User> key = new Key<User>(User.class, user.getId());

		User result = asyncPersiter.get(key).get();

		assertEqualsUser(user, result);

	}

	@Test
	public void testPutIterableOfQextendsT() {

		User user1 = create("aaa");
		User user2 = create("bbb");

		List<User> users = new ArrayList<User>();
		users.add(user1);
		users.add(user2);

		asyncPersiter.put(users).get();

	}

	@Test
	public void testPutT() {

		User user1 = create("aaa");

		asyncPersiter.put(user1).get();

	}

	@Test
	public void testPutTArray() {

		User user1 = create("aaa");
		User user2 = create("bbb");

		asyncPersiter.put(user1, user2).get();

	}

	@Test
	public void testRefreshCollectionOfT() {

		List<User> users = new ArrayList<User>();
		users.add(create("aaa"));
		users.add(create("bbb"));
		users.add(create("ccc"));

		asyncPersiter.put(users).get();

		List<User> tests = new ArrayList<User>();
		tests.add(cloneOf(users.get(0)));
		tests.add(cloneOf(users.get(1)));
		tests.add(cloneOf(users.get(2)));

		asyncPersiter.refresh(tests).get();

		assertEqualsUsers(users, tests);

	}

	@Test
	public void testRefreshT() {

		User user1 = create("aaa");

		asyncPersiter.put(user1).get();

		User user2 = cloneOf(user1);

		asyncPersiter.refresh(user2).get();

		assertEqualsUser(user1, user2);

	}

	@Test
	public void testRefreshTArray() {

		User user1 = create("aaa");
		User user2 = create("bbb");
		User user3 = create("ccc");

		asyncPersiter.put(user1, user2, user3).get();

		User test1 = cloneOf(user1);
		User test2 = cloneOf(user2);
		User test3 = cloneOf(user3);

		asyncPersiter.refresh(test1, test2, test3).get();

		assertEqualsUser(user1, test1);
		assertEqualsUser(user2, test2);
		assertEqualsUser(user3, test3);

	}

	@Test
	public void testSync() {

		ObjectifyPersister async = asyncPersiter.sync();

		assertNotNull(async);
		assertTrue(async instanceof ObjectifyPersister);

	}

}
