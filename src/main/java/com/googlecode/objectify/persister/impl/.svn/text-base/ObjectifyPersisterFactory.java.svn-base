package com.googlecode.objectify.persister.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.google.appengine.api.datastore.AsyncDatastoreService;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyOpts;
import com.googlecode.objectify.persister.ObjectifyPersister;
import com.googlecode.objectify.persister.converter.EntityConverter;

public class ObjectifyPersisterFactory extends ObjectifyFactory {

	private final Map<String, Class<?>>	kindToClass	= new HashMap<String, Class<?>>();
	private final Map<Class<?>, String>	classToKind	= new HashMap<Class<?>, String>();

	public ObjectifyPersisterFactory() {
		this.getConversions().add(new EntityConverter(this));
	}

	@Override
	public ObjectifyPersister begin() {
		return this.begin(this.createDefaultOpts());
	}

	@Override
	public ObjectifyPersister begin(ObjectifyOpts opts) {
		AsyncDatastoreService async = this.getAsyncDatastoreService(opts);
		return this.createObjectify(async, opts);
	}

	public ObjectifyPersister beginTransaction() {
		return this.begin(this.createDefaultOpts().setBeginTransaction(true));
	}

	@SuppressWarnings("unchecked")
	public <T> Class<T> getEntityClass(String kind) {
		return (Class<T>) kindToClass.get(kind);
	}

	public String getEntityKind(Class<?> clazz) {
		return classToKind.get(clazz);
	}

	public Collection<Class<?>> getRegistereds() {
		return classToKind.keySet();
	}

	public boolean isRegistered(Class<?> clazz) {
		return getEntityKind(clazz) != null;
	}

	public boolean isValidEntity(Class<?> clazz) {

		boolean result = false;

		if (clazz.isAnnotationPresent(javax.persistence.Entity.class)) {
			result = true;
		} else if (clazz.isAnnotationPresent(com.googlecode.objectify.annotation.Entity.class)) {
			result = true;
		}

		return result;

	}

	public <T> void register(Class<?>... clazz) {

		for (Class<?> c : clazz) {
			register(c);
		}

	}

	@Override
	public <T> void register(Class<T> clazz) {

		if (!isRegistered(clazz)) {

			if (isValidEntity(clazz)) {

				super.register(clazz);

				String kind = Key.getKind(clazz);
				kindToClass.put(kind, clazz);
				classToKind.put(clazz, kind);

			} else {
				throw new IllegalArgumentException("Class " + clazz + " is not a valid entity.");
			}

		}

	}

	@Override
	protected ObjectifyOpts createDefaultOpts() {
		ObjectifyOpts opts = super.createDefaultOpts();
//		opts.setSessionCache(true);
		return opts;
	}

	@Override
	protected ObjectifyPersister createObjectify(AsyncDatastoreService ds, ObjectifyOpts opts) {
		Objectify objectify = super.createObjectify(ds, opts);
		return new ObjectifyPersisterImpl(objectify, this);
	}

}
