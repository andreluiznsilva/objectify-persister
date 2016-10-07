package com.googlecode.objectify.persister;

import java.io.InputStream;
import java.util.logging.LogManager;

import org.junit.After;
import org.junit.Before;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.googlecode.objectify.persister.impl.ObjectifyPersisterFactory;
import com.googlecode.objectify.persister.test.User;

public class BasePersisterTest {

	public BasePersisterTest() {

		InputStream resource = this.getClass().getClassLoader().getResourceAsStream("logging.properties");

		if (resource != null) {
			try {
				LogManager.getLogManager().readConfiguration(resource);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

	}

	protected LocalServiceTestHelper helper;

	protected ObjectifyPersister persiter;
	protected ObjectifyPersisterFactory objectifyFactory;

	@Before
	public void setUpBase() throws Exception {

		helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
		helper.setUp();

		objectifyFactory = new ObjectifyPersisterFactory();
		objectifyFactory.register(User.class);

		persiter = objectifyFactory.begin();

	}

	@After
	public void tearDownBase() throws Exception {
		helper.tearDown();
	}

}