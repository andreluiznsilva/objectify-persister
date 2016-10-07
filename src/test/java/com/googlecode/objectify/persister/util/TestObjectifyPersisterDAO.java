package com.googlecode.objectify.persister.util;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.googlecode.objectify.persister.BasePersisterTest;
import com.googlecode.objectify.persister.ObjectifyPersister;
import com.googlecode.objectify.persister.ObjectifyPersisterService;
import com.googlecode.objectify.persister.impl.ObjectifyPersisterFactory;
import com.googlecode.objectify.persister.test.User;

public class TestObjectifyPersisterDAO extends BasePersisterTest {

	private ObjectifyPersisterDAO	dao;

	@Before
	public void setUp() throws Exception {
		dao = new ObjectifyPersisterDAO(ObjectifyPersisterService.factory());
		ObjectifyPersisterService.register(User.class);
	}

	@Test
	public void testFact() {

		ObjectifyPersisterFactory fact1 = dao.fact();
		ObjectifyPersisterFactory fact2 = dao.fact();

		assertNotNull(fact1);
		assertNotNull(fact2);

		assertSame(fact1, fact2);

	}

	@Test
	public void testOfy() {

		ObjectifyPersister fact1 = dao.ofy();
		ObjectifyPersister fact2 = dao.ofy();

		assertNotNull(fact1);
		assertNotNull(fact2);

		assertSame(fact1, fact2);

	}

	@Test
	public void testDelete() {

		User user = TestUtils.create("test");

		ObjectifyPersisterService.begin().put(user);

		dao.delete(user);

		User result = ObjectifyPersisterService.begin().find(User.class, user.getId());

		assertNull(result);

	}

	@Test
	public void testFindByField() {

		User user = TestUtils.create("test");

		ObjectifyPersisterService.begin().put(user);

		User result = dao.findByField(User.class, "name", user.getName());

		assertNotNull(result);

	}

	@Test
	public void testFindById() {

		User user = TestUtils.create("test");

		ObjectifyPersisterService.begin().put(user);

		User result = dao.findById(User.class, user.getId());

		assertNotNull(result);

	}

	@Test
	public void testList() {

		int expected = 3;

		ObjectifyPersisterService.begin().put(TestUtils.create("test1"));
		ObjectifyPersisterService.begin().put(TestUtils.create("test2"));
		ObjectifyPersisterService.begin().put(TestUtils.create("test3"));

		List<User> results = dao.list(User.class);

		assertEquals(expected, results.size());

	}

	@Test
	public void testListByField() {

		int expected = 3;

		String name = "test";

		ObjectifyPersisterService.begin().put(TestUtils.create(name));
		ObjectifyPersisterService.begin().put(TestUtils.create(name));
		ObjectifyPersisterService.begin().put(TestUtils.create(name));

		List<User> results = dao.queryByField(User.class, "name", name);

		assertEquals(expected, results.size());

	}

	@Test
	public void testPut() {

		User user = TestUtils.create("test");

		dao.put(user);

		User result = ObjectifyPersisterService.begin().find(User.class, user.getId());

		assertNotNull(result);

	}

	@Test
	public void testRefresh() {

		String expected = "test";

		User user = TestUtils.create("test");

		ObjectifyPersisterService.begin().put(user);

		user.setName("X");

		dao.refresh(user);

		assertEquals(expected, user.getName());

	}

}
