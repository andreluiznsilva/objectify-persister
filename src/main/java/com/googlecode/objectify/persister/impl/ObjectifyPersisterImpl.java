package com.googlecode.objectify.persister.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Transaction;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.NotFoundException;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.Query;
import com.googlecode.objectify.persister.AsyncObjectifyPersister;
import com.googlecode.objectify.persister.ObjectifyPersister;
import com.googlecode.objectify.persister.operation.Deleter;
import com.googlecode.objectify.persister.operation.Loader;
import com.googlecode.objectify.persister.operation.Persister;

public class ObjectifyPersisterImpl implements ObjectifyPersister {

	private final ObjectifyPersisterFactory	factory;
	private final Objectify					base;

	public ObjectifyPersisterImpl(Objectify base, ObjectifyPersisterFactory factory) {
		this.base = base;
		this.factory = factory;
	}

	public AsyncObjectifyPersister async() {
		return new AsyncObjectifyPersisterImpl(base.async(), factory);
	}

	public <T> void delete(Class<T> clazz, long id) {
		T object = find(clazz, id);
		if (object != null) {
			new Deleter(base, factory).delete(object);
		}
	}

	public <T> void delete(Class<T> clazz, String name) {
		T object = find(clazz, name);
		if (object != null) {
			new Deleter(base, factory).delete(object);
		}
	}

	public void delete(Iterable<?> keysOrEntities) {

		for (Object obj : keysOrEntities) {

			if (isKey(obj)) {
				obj = this.get((Key<?>) obj);
			}

			new Deleter(base, factory).delete(obj);

		}

	}

	public void delete(Object... keysOrEntities) {
		base.delete(keysOrEntities);
	}

	public <T> T find(Class<? extends T> clazz, long id) {

		T result = base.find(clazz, id);
		refreshIfNotNull(result);
		return result;

	}

	public <T> T find(Class<? extends T> clazz, String name) {

		T result = base.find(clazz, name);
		refreshIfNotNull(result);
		return result;

	}

	public <T> T find(Key<? extends T> key) {

		T result = base.find(key);
		refreshIfNotNull(result);
		return result;

	}

	public <S, T> Map<S, T> get(Class<? extends T> clazz, Iterable<S> idsOrNames) {

		Map<S, T> results = base.get(clazz, idsOrNames);

		for (Entry<S, T> entry : results.entrySet()) {
			refreshIfNotNull(entry.getValue());
		}

		return results;

	}

	public <T> T get(Class<? extends T> clazz, long id) throws NotFoundException {

		T result = base.get(clazz, id);
		refreshIfNotNull(result);
		return result;

	}

	public <S, T> Map<S, T> get(Class<? extends T> clazz, S... idsOrNames) {

		Map<S, T> results = base.get(clazz, idsOrNames);

		for (Entry<S, T> entry : results.entrySet()) {
			refreshIfNotNull(entry.getValue());
		}

		return results;

	}

	public <T> T get(Class<? extends T> clazz, String name) throws NotFoundException {

		T result = base.get(clazz, name);
		refreshIfNotNull(result);
		return result;

	}

	public <T> Map<Key<T>, T> get(Iterable<? extends Key<? extends T>> keys) {

		Map<Key<T>, T> results = base.get(keys);

		for (Entry<Key<T>, T> entry : results.entrySet()) {
			refreshIfNotNull(entry.getValue());
		}

		return results;

	}

	public <T> Map<Key<T>, T> get(Key<? extends T>... keys) {
		return null;
	}

	public <T> T get(Key<? extends T> key) throws NotFoundException {

		T result = base.get(key);
		refreshIfNotNull(result);
		return result;

	}

	public DatastoreService getDatastore() {
		return base.getDatastore();
	}

	public ObjectifyFactory getFactory() {
		return factory;
	}

	@Override
	public Objectify getObjectify() {
		return base;
	}

	public Transaction getTxn() {
		return base.getTxn();
	}

	public <T> Map<Key<T>, T> put(Iterable<? extends T> objs) {

		Map<Key<T>, T> map = new HashMap<Key<T>, T>();

		Persister persister = new Persister(base, factory);

		for (T obj : objs) {
			persister.persiste(obj);
			Key<T> key = factory.getKey(obj);
			map.put(key, obj);
		}

		return map;

	}

	public <T extends Object> Map<Key<T>, T> put(T... objs) {
		return put(Arrays.asList(objs));
	}

	public <T extends Object> Key<T> put(T obj) {
		new Persister(base, factory).persiste(obj);
		return factory.getKey(obj);
	};

	public <T> Query<T> query() {
		return new QueryPersisterImpl<T>(factory, this);
	}

	public <T> Query<T> query(Class<T> clazz) {
		return new QueryPersisterImpl<T>(factory, this, clazz);
	}

	public <T> void refresh(Collection<T> object) {

		Loader loader = new Loader(base, factory);

		for (T t : object) {
			loader.refresh(t);
		}

	}

	public <T> void refresh(T... object) {

		Loader loader = new Loader(base, factory);

		for (T t : object) {
			loader.refresh(t);
		}

	}

	public <T> void refresh(T object) {
		new Loader(base, factory).refresh(object);
	}

	private boolean isKey(Object obj) {
		return obj.getClass().isAssignableFrom(Key.class)
				&& obj.getClass().isAssignableFrom(com.google.appengine.api.datastore.Key.class);
	}

	private <T> void refreshIfNotNull(T result) {

		if (result != null) {
			refresh(result);
		}

	}

}
