package com.googlecode.objectify.persister.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.Future;

import com.google.appengine.api.datastore.AsyncDatastoreService;
import com.googlecode.objectify.AsyncObjectify;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.Result;
import com.googlecode.objectify.impl.ResultAdapter;
import com.googlecode.objectify.persister.AsyncObjectifyPersister;
import com.googlecode.objectify.persister.ObjectifyPersister;
import com.googlecode.objectify.persister.operation.Deleter;
import com.googlecode.objectify.persister.operation.Loader;
import com.googlecode.objectify.util.SimpleFutureWrapper;

public class AsyncObjectifyPersisterImpl implements AsyncObjectifyPersister {

	private final ObjectifyPersisterFactory	factory;
	private final AsyncObjectify			base;

	public AsyncObjectifyPersisterImpl(AsyncObjectify base, ObjectifyPersisterFactory factory) {
		this.base = base;
		this.factory = factory;
	}

	public <T> Result<Void> delete(final Class<T> clazz, final long id) {

		return new ResultHelper<Void>() {

			public Void get() {
				Objectify sync = base.sync();
				T object = sync.get(clazz, id);
				new Deleter(sync, factory).delete(object);
				return null;
			}

		};

	}

	public <T> Result<Void> delete(final Class<T> clazz, final String name) {

		return new ResultHelper<Void>() {

			public Void get() {
				Objectify sync = base.sync();
				T object = sync.get(clazz, name);
				new Deleter(sync, factory).delete(object);
				return null;
			}

		};

	}

	public Result<Void> delete(Iterable<?> keysOrEntities) {
		return base.delete(keysOrEntities);

	}

	public Result<Void> delete(final Object... keysOrEntities) {

		return new ResultHelper<Void>() {

			public Void get() {

				Objectify sync = base.sync();
				Deleter deleter = new Deleter(sync, factory);

				for (Object object : keysOrEntities) {

					if (isKey(object)) {
						sync.delete(object);
					} else {
						deleter.delete(object);
					}

				}

				return null;

			}

		};

	}

	public <T> Result<T> find(Class<? extends T> clazz, long id) {
		return this.find(new Key<T>(clazz, id));
	}

	public <T> Result<T> find(Class<? extends T> clazz, String name) {
		return this.find(new Key<T>(clazz, name));
	}

	public <T> Result<T> find(Key<? extends T> key) {

		Result<T> found = base.find(key);

		Future<T> wrapped = new SimpleFutureWrapper<T, T>(found.getFuture()) {

			@Override
			protected T wrap(T t) throws Exception {
				refreshIfNotNull(t);
				return t;
			}

		};

		return new ResultAdapter<T>(wrapped);

	}

	public <S, T> Result<Map<S, T>> get(Class<? extends T> clazz, Iterable<S> idsOrNames) {

		Result<Map<S, T>> fetched = base.get(clazz, idsOrNames);

		Future<Map<S, T>> wrapped = new SimpleFutureWrapper<Map<S, T>, Map<S, T>>(fetched.getFuture()) {

			@Override
			protected Map<S, T> wrap(Map<S, T> maps) throws Exception {
				refreshAll(maps.values());
				return maps;
			}

		};

		return new ResultAdapter<Map<S, T>>(wrapped);

	}

	public <T> Result<T> get(Class<? extends T> clazz, long id) {
		return (Result<T>) this.get(new Key<T>(clazz, id));
	}

	public <S, T> Result<Map<S, T>> get(Class<? extends T> clazz, S... idsOrNames) {
		return this.get(clazz, Arrays.asList(idsOrNames));
	}

	public <T> Result<T> get(Class<? extends T> clazz, String name) {
		return (Result<T>) this.get(new Key<T>(clazz, name));
	}

	public <T> Result<Map<Key<T>, T>> get(Iterable<? extends Key<? extends T>> keys) {

		Result<Map<Key<T>, T>> fetched = base.get(keys);

		Future<Map<Key<T>, T>> wrapped = new SimpleFutureWrapper<Map<Key<T>, T>, Map<Key<T>, T>>(fetched.getFuture()) {

			@Override
			protected Map<Key<T>, T> wrap(Map<Key<T>, T> maps) throws Exception {
				refreshAll(maps.values());
				return maps;
			}

		};

		return new ResultAdapter<Map<Key<T>, T>>(wrapped);

	}

	public <T> Result<T> get(final Key<? extends T> key) {

		Result<T> found = base.get(key);

		Future<T> wrapped = new SimpleFutureWrapper<T, T>(found.getFuture()) {

			@Override
			protected T wrap(T t) throws Exception {
				refreshIfNotNull(t);
				return t;
			}

		};

		return new ResultAdapter<T>(wrapped);

	}

	public AsyncDatastoreService getAsyncDatastore() {
		return base.getAsyncDatastore();
	}

	@Override
	public AsyncObjectify getAsyncObjectify() {
		return base;
	}

	public ObjectifyPersisterFactory getFactory() {
		return factory;
	}

	public <T> Result<Map<Key<T>, T>> put(Iterable<? extends T> objs) {

		Result<Map<Key<T>, T>> fetched = base.put(objs);

		Future<Map<Key<T>, T>> wrapped = new SimpleFutureWrapper<Map<Key<T>, T>, Map<Key<T>, T>>(fetched.getFuture()) {

			@Override
			protected Map<Key<T>, T> wrap(Map<Key<T>, T> maps) throws Exception {
				return maps;
			}

		};

		return new ResultAdapter<Map<Key<T>, T>>(wrapped);

	}

	public <T> Result<Map<Key<T>, T>> put(T... objs) {
		return this.put(Arrays.asList(objs));
	}

	public <T> Result<Key<T>> put(T obj) {

		Result<Map<Key<T>, T>> result = this.put(Collections.singleton(obj));

		Future<Key<T>> future = new SimpleFutureWrapper<Map<Key<T>, T>, Key<T>>(result.getFuture()) {

			@Override
			protected Key<T> wrap(Map<Key<T>, T> putted) throws Exception {
				Key<T> result = putted.keySet().iterator().next();
				refreshIfNotNull(result);
				return result;
			}

		};

		return new ResultAdapter<Key<T>>(future);

	}

	public <T> Result<Void> refresh(final Collection<T> object) {

		return new ResultHelper<Void>() {

			public Void get() {
				sync().refresh(object);
				return null;
			}

		};

	}

	public <T> Result<Void> refresh(T object) {
		return refresh(Collections.singletonList(object));
	}

	public <T> Result<Void> refresh(T... object) {
		return refresh(Arrays.asList(object));
	}

	public ObjectifyPersister sync() {
		return new ObjectifyPersisterImpl(base.sync(), factory);
	}

	private boolean isKey(Object obj) {
		return obj.getClass().isAssignableFrom(Key.class)
				&& obj.getClass().isAssignableFrom(com.google.appengine.api.datastore.Key.class);
	}

	private <T> void refreshAll(Iterable<T> iterable) {

		Loader loader = new Loader(base.sync(), factory);

		for (T t : iterable) {
			loader.refresh(t);
		}

	}

	private <T> void refreshIfNotNull(T result) {

		if (result != null) {
			refresh(result);
		}

	}

}
