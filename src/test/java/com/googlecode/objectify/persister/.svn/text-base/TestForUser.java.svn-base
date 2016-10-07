package com.googlecode.objectify.persister;

import static com.googlecode.objectify.persister.util.TestUtils.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.googlecode.objectify.Query;
import com.googlecode.objectify.persister.test.User;

public class TestForUser extends BasePersisterTest {

	@Before
	public void setUp() throws Exception {
		objectifyFactory.register(User.class);
	}

	@Test
	public void testDelete() {

		User expected = create("aaa");

		persiter.put(expected);

		persiter.delete(expected);

	}

	@Test
	public void testFind() {

		User expected = create("aaa");

		persiter.put(expected);

		User result = persiter.find(User.class, expected.getId());

		assertEqualsUser(expected, result);

	}

	@Test
	public void testGet() {

		User expected = create("aaa");

		persiter.put(expected);

		User result = persiter.get(User.class, expected.getId());

		assertEqualsUser(expected, result);

	}

	@Test
	public void testPutT() {

		User expected = create("aaa");

		persiter.put(expected);

	}

	@Test
	public void testQueryList() {

		User user1 = create("aaa");
		User user2 = create("bbb");
		User user3 = create("ccc");

		persiter.put(user1, user2, user3);

		Query<User> query = persiter.query(User.class);

		List<User> actuals = query.list();

		List<User> expecteds = new ArrayList<User>();
		expecteds.add(user1);
		expecteds.add(user2);
		expecteds.add(user3);

		assertEqualsUsers(expecteds, actuals);

	}

	@Test
	public void testQuerySingle() {

		User user1 = create("aaa");
		User user2 = create("bbb");
		User user3 = create("ccc");

		persiter.put(user1, user2, user3);

		Query<User> query = persiter.query(User.class);

		User actual = query.filter("name", user2.getName()).get();

		assertEqualsUser(user2, actual);

	}

	@Test
	public void testRefresh() {

		User expected = create("aaa");

		persiter.put(expected);

		User result = new User();
		result.setId(expected.getId());

		persiter.refresh(result);

		assertEqualsUser(expected, result);

	}

}
