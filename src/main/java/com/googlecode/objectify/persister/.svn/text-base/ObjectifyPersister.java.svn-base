package com.googlecode.objectify.persister;

import java.util.Collection;

import com.googlecode.objectify.Objectify;

public interface ObjectifyPersister extends Objectify {

	AsyncObjectifyPersister async();

	<T> void refresh(T object);

	<T> void refresh(T... object);

	<T> void refresh(Collection<T> object);

	Objectify getObjectify();

}
