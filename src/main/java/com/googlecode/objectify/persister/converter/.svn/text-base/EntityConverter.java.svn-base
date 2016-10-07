package com.googlecode.objectify.persister.converter;

import com.google.appengine.api.datastore.Key;
import com.googlecode.objectify.impl.EntityMetadata;
import com.googlecode.objectify.impl.TypeUtils;
import com.googlecode.objectify.impl.conv.Converter;
import com.googlecode.objectify.impl.conv.ConverterLoadContext;
import com.googlecode.objectify.impl.conv.ConverterSaveContext;
import com.googlecode.objectify.persister.impl.ObjectifyPersisterFactory;

public class EntityConverter implements Converter {

	private final ObjectifyPersisterFactory	objectifyFactory;

	public EntityConverter(ObjectifyPersisterFactory objectifyFactory) {
		this.objectifyFactory = objectifyFactory;
	}

	public Object forDatastore(Object value, ConverterSaveContext ctx) {

		Object result = null;

		if (value != null && objectifyFactory.isRegistered(value.getClass())) {

			EntityMetadata<?> metadata = objectifyFactory.getMetadata(value.getClass());

			try {
				result = metadata.getRawKey(value);
			} catch (Exception e) {
				throw new IllegalArgumentException("Cold not convert field '"
						+ ctx.getField().getName() + "' of kind '"
						+ metadata.getKind() + "' in key. @Id field is Null!");
			}

		}

		return result;

	}

	public Object forPojo(Object value, Class<?> fieldType, ConverterLoadContext ctx, Object onPojo) {

		if (value != null && isKey(value)) {

			Key key = (Key) value;

			Class<Object> entityClass = objectifyFactory.getEntityClass(key.getKind());
			Object instance = TypeUtils.newInstance(entityClass);

			EntityMetadata<Object> metadata = objectifyFactory.getMetadata(key.getKind());
			metadata.setKey(instance, key);

			return instance;

		}

		return null;

	}

	private boolean isKey(Object value) {
		return value instanceof Key;
	}

}
