package com.googlecode.objectify.persister.operation;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.persistence.CascadeType;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.impl.TypeUtils.FieldMetadata;
import com.googlecode.objectify.persister.impl.ObjectifyPersisterFactory;
import com.googlecode.objectify.persister.wrapper.CollectionWrapper;
import com.googlecode.objectify.persister.wrapper.ListWrapper;
import com.googlecode.objectify.persister.wrapper.SetWrapper;

public class Loader extends AbstractOperation {

	private final Logger		logger		= Logger.getLogger(this.getClass().getName());

	private Map<Key<?>, Object>	refresheds	= new java.util.HashMap<Key<?>, Object>();

	public Loader(Objectify objectify, ObjectifyPersisterFactory factory) {
		super(objectify, factory);
	}

	public Object load(Key<?> key) {

		logger.finest(buildLog("Loading...", key, null));

		Object object = objectify.get(key);
		loadObject(object);

		logger.finest(buildLog("Loaded!", key, object));

		return object;

	}

	public void refresh(Object object) {
		refreshObject(object);
	}

	private void copyFields(Object src, Object target) {

		List<FieldMetadata> fields = getPesistentFields(target);

		for (FieldMetadata metadata : fields) {

			Object fieldValue = getFieldValue(metadata, src);

			setFieldValue(metadata, fieldValue, target);

		}

	}

	private CollectionWrapper<Object> getWrapper(FieldMetadata metadata) {

		Class<?> type = metadata.field.getType();

		CollectionWrapper<Object> value = null;

		if (List.class.isAssignableFrom(type)) {
			value = new ListWrapper<Object>();
		} else if (Set.class.isAssignableFrom(type)) {
			value = new SetWrapper<Object>();
		}

		return value;

	}

	private boolean hasToLoad(FieldMetadata metadata, Object target) {
		return hasCascadeType(metadata, target, CascadeType.ALL, CascadeType.REFRESH);
	}

	private void loadObject(Object target) {

		List<FieldMetadata> fields = getPesistentFields(target);

		for (FieldMetadata metadata : fields) {

			boolean hasToLoad = hasToLoad(metadata, target);
			Object value = getFieldValue(metadata, target);

			if (value != null && hasToLoad) {

				if (isEntity(value)) {

					Object entity = refreshObject(value);

					setFieldValue(metadata, entity, target);

				} else if (isCollection(value)) {

					CollectionWrapper<Object> entities = getWrapper(metadata);
					Collection<?> values = (Collection<?>) value;

					for (Object object : values) {
						Object entity = refreshObject(object);
						entities.add(entity);
					}

					entities.cleanAddeds();

					setFieldValue(metadata, entities, target);

				}

			}

		}

	}

	private Object refreshObject(Object object) {

		Object result = object;

		Key<Object> key = factory.getKey(object);

		logger.finest(buildLog("Refreshing...", key, object));

		boolean alreadyRefreshed = refresheds.keySet().contains(key);

		if (!alreadyRefreshed) {

			refresheds.put(key, object);

			Object copy = load(key);
			copyFields(copy, object);

			logger.finest(buildLog("Refreshed!", key, object));

		} else {

			logger.finest(buildLog("Already Refreshed!", key, object));

			result = refresheds.get(key);

		}

		return result;

	}

}