package com.googlecode.objectify.persister;

import com.googlecode.objectify.ObjectifyOpts;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.persister.impl.ObjectifyPersisterFactory;

public class ObjectifyPersisterService extends ObjectifyService {

	static {
		factory = new ObjectifyPersisterFactory();
	}

	public static ObjectifyPersister begin() {
		return factory().begin();
	}

	public static ObjectifyPersister begin(ObjectifyOpts opts) {
		return factory().begin(opts);
	}

	public static ObjectifyPersister beginTransaction() {
		return factory().beginTransaction();
	}

	public static ObjectifyPersisterFactory factory() {
		return (ObjectifyPersisterFactory) factory;
	}

	public static void register(Class<?> clazz) {
		factory().register(clazz);
	}

	public static void register(Class<?>... clazz) {
		for (Class<?> c : clazz) {
			factory().register(c);
		}
	}

}