package com.googlecode.objectify.persister.impl;

import java.util.List;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.impl.QueryImpl;
import com.googlecode.objectify.persister.ObjectifyPersister;
import com.googlecode.objectify.persister.operation.Loader;

public class QueryPersisterImpl<T> extends QueryImpl<T> {

	private final ObjectifyPersisterFactory	factoryPersister;
	private final Objectify					objectifyPersister;

	public QueryPersisterImpl(ObjectifyPersisterFactory fact, ObjectifyPersister objectify) {
		super(fact, objectify);
		this.factoryPersister = fact;
		this.objectifyPersister = objectify;
	}

	public QueryPersisterImpl(ObjectifyPersisterFactory fact, ObjectifyPersister objectify, Class<T> clazz) {
		super(fact, objectify, clazz);
		this.factoryPersister = fact;
		this.objectifyPersister = objectify;
	}

	@Override
	public T get() {

		T result = super.get();

		if (result != null) {
			new Loader(objectifyPersister, factoryPersister).refresh(result);
		}

		return result;

	}

	@Override
	public List<T> list() {

		List<T> list = super.list();

		if (!list.isEmpty()) {

			Loader loader = new Loader(objectifyPersister, factoryPersister);

			for (T result : list) {
				loader.refresh(result);
			}

		}

		return list;

	}

}
