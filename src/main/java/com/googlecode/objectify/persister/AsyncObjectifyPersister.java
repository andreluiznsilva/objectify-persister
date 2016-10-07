package com.googlecode.objectify.persister;

import java.util.Collection;

import com.googlecode.objectify.AsyncObjectify;
import com.googlecode.objectify.Result;
import com.googlecode.objectify.persister.impl.ObjectifyPersisterFactory;

public interface AsyncObjectifyPersister extends AsyncObjectify {

	ObjectifyPersister sync();

	<T> Result<Void> refresh(T object);

	<T> Result<Void> refresh(T... object);

	<T> Result<Void> refresh(Collection<T> object);

	ObjectifyPersisterFactory getFactory();

	AsyncObjectify getAsyncObjectify();

}
