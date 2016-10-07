package com.googlecode.objectify.persister.util;

import com.googlecode.objectify.persister.ObjectifyPersister;

public class ThreadLocalObjectifyPersisterHolder extends ThreadLocal<ObjectifyPersister> implements
		ObjectifyPersisterHolder {

}
