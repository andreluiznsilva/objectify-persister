package com.googlecode.objectify.persister.operation;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.CascadeType;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.impl.TypeUtils.FieldMetadata;
import com.googlecode.objectify.persister.impl.ObjectifyPersisterFactory;
import com.googlecode.objectify.persister.wrapper.CollectionWrapper;

public class Deleter extends AbstractOperation {

	private final Logger	logger	= Logger.getLogger(this.getClass().getName());

	public Deleter(Objectify objectify, ObjectifyPersisterFactory factory) {
		super(objectify, factory);
	}

	public void delete(Object object) {
		deleteObject(object);
	}

	private void deleteCollection(Object value) {

		Collection<?> entities = (Collection<?>) value;

		for (Object entity : entities) {
			deleteObject(entity);
		}

		if (isCollectionWrapper(entities)) {
			((CollectionWrapper<?>) entities).cleanRemoveds();
		}

	}

	private void deleteObject(Object target) {

		if (target != null) {

			Key<?> key = factory.getKey(target);

			logger.finest(buildLog("Deleting...", key, target));

			List<FieldMetadata> fields = getPesistentFields(target);

			for (FieldMetadata metadata : fields) {

				boolean hasToDelete = hasToDelete(metadata, target);
				Object value = getFieldValue(metadata, target);

				if (value != null && hasToDelete) {

					if (isEntity(value)) {
						deleteObject(value);
					} else if (isCollection(value)) {
						deleteCollection(value);
					}

				}

			}

			objectify.delete(key);

			logger.finest(buildLog("Deleted!", key, target));

		}

	}

	private boolean hasToDelete(FieldMetadata metadata, Object target) {
		return hasCascadeType(metadata, target, CascadeType.ALL, CascadeType.REMOVE);
	}

}
