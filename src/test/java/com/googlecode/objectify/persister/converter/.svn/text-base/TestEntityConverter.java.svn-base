package com.googlecode.objectify.persister.converter;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.googlecode.objectify.impl.Wrapper;
import com.googlecode.objectify.impl.conv.ConverterLoadContext;
import com.googlecode.objectify.impl.conv.ConverterSaveContext;
import com.googlecode.objectify.persister.impl.ObjectifyPersisterFactory;
import com.googlecode.objectify.persister.test.Item;
import com.googlecode.objectify.persister.test.Product;
import com.googlecode.objectify.persister.test.User;

public class TestEntityConverter {

	private EntityConverter				converter;
	private LocalServiceTestHelper		helper;

	private ConverterSaveContext		saveContext;
	private ConverterLoadContext		loadContext;
	private ObjectifyPersisterFactory	factory;

	@Before
	public void setUp() throws Exception {

		saveContext = new ConverterSaveContext() {

			public boolean inEmbeddedCollection() {
				return false;
			}

			public Field getField() {
				try {
					return TestEntityConverter.class.getDeclaredField("factory");
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}

		};

		loadContext = new ConverterLoadContext() {

			public Wrapper getField() {
				return null;
			}

		};

		helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
		helper.setUp();

		factory = new ObjectifyPersisterFactory();
		factory.register(User.class);
		factory.register(Product.class);
		factory.register(Item.class);
		converter = new EntityConverter(factory);

	}

	@After
	public void tearDown() throws Exception {
		helper.tearDown();
	}

	@Test
	public void testForDatastore_ValueNull() {

		User value = null;

		Object result = converter.forDatastore(value, saveContext);

		assertNull(result);

	}

	@Test
	public void testForDatastore_IdNull() {

		Long id = null;

		User value = new User();
		value.setId(id);

		Object result = null;

		try {
			result = converter.forDatastore(value, saveContext);
			fail("Expected Exception");
		} catch (IllegalArgumentException e) {
			assertNull(result);
		}

	}

	@Test
	public void testForDatastore_NameNull() {

		String name = null;

		Product value = new Product();
		value.setCode(name);

		Object result = null;

		try {
			result = converter.forDatastore(value, saveContext);
			fail("Expected Exception");
		} catch (IllegalArgumentException e) {
			assertNull(result);
		}

	}

	@Test
	public void testForDatastore_NameNotNull() {

		String name = "test";

		Product value = new Product();
		value.setCode(name);

		Object result = converter.forDatastore(value, saveContext);

		assertNotNull(result);
		assertTrue(result instanceof Key);

	}

	@Test
	public void testForDatastore_IdNotNull() {

		Long id = 23423423L;

		User value = new User();
		value.setId(id);

		Object result = converter.forDatastore(value, saveContext);

		assertNotNull(result);
		assertTrue(result instanceof Key);

	}

	@Test
	public void testForPojo_KeyNull() {

		Key value = null;
		User pojo = new User();

		Object result = converter.forPojo(value, User.class, loadContext, pojo);

		assertNull(result);

	}

	@Test
	public void testForPojo_IdKeyNotNull() {

		Long id = 23353454L;

		String kind = factory.getMetadata(User.class).getKind();
		Key value = KeyFactory.createKey(kind, id);
		User pojo = new User();

		Object result = converter.forPojo(value, User.class, loadContext, pojo);

		assertNotNull(result);
		assertTrue(result instanceof User);
		assertEquals(id, ((User) result).getId());

	}

	@Test
	public void testForPojo_NamedKeyNotNull() {

		String name = "test";

		String kind = factory.getMetadata(Product.class).getKind();
		Key value = KeyFactory.createKey(kind, name);
		Product pojo = new Product();

		Object result = converter.forPojo(value, Product.class, loadContext, pojo);

		assertNotNull(result);
		assertTrue(result instanceof Product);
		assertEquals(name, ((Product) result).getCode());

	}

	@Test
	public void testForPojo_HierarchicalId() {

		Long id = 23353454L;

		String kind = factory.getMetadata(Item.class).getKind();
		Key value = KeyFactory.createKey(kind, id);
		Item pojo = new Item();

		Object result = converter.forPojo(value, Item.class, loadContext, pojo);

		assertNotNull(result);
		assertTrue(result instanceof Item);
		assertEquals(id, ((Item) result).getId());

	}

}
