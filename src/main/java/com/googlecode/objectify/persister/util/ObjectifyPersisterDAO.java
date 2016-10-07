package com.googlecode.objectify.persister.util;

import java.util.List;

import com.googlecode.objectify.ObjectifyOpts;
import com.googlecode.objectify.persister.ObjectifyPersister;
import com.googlecode.objectify.persister.impl.ObjectifyPersisterFactory;

public class ObjectifyPersisterDAO {

	private final ObjectifyOpts				opts;
	private final ObjectifyPersisterHolder	holder;
	private final ObjectifyPersisterFactory	factory;

	public ObjectifyPersisterDAO(ObjectifyPersisterFactory factory) {
		this(factory, new ObjectifyOpts(), new ThreadLocalObjectifyPersisterHolder());
	}

	public ObjectifyPersisterDAO(ObjectifyPersisterFactory factory, ObjectifyOpts opts) {
		this(factory, opts, new ThreadLocalObjectifyPersisterHolder());
	}

	public ObjectifyPersisterDAO(ObjectifyPersisterFactory factory, ObjectifyOpts opts, ObjectifyPersisterHolder holder) {
		this.factory = factory;
		this.opts = opts;
		this.holder = holder;
	}

	public ObjectifyPersisterFactory fact() {
		return factory;
	}

	public ObjectifyPersister ofy() {

		ObjectifyPersister result = holder.get();

		if (result == null) {
			result = fact().begin(opts);
			holder.set(result);
		}

		return result;

	}

	public void delete(Object... object) {
		ofy().delete(object);
	}

	public <T> T findByField(Class<T> clazz, String condition, Object value) {
		return ofy().query(clazz).filter(condition, value).get();
	}

	public <T> T findById(Class<T> clazz, Long id) {
		return ofy().find(clazz, id);
	}

	public <T> List<T> list(Class<T> clazz) {
		return ofy().query(clazz).list();
	}

	public void put(Object... object) {
		ofy().put(object);
	}

	public <T> List<T> queryByField(Class<T> clazz, String condition, Object value) {
		return ofy().query(clazz).filter(condition, value).list();
	}

	public void refresh(Object... object) {
		ofy().refresh(object);
	}

}