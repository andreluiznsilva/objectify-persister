package com.googlecode.objectify.persister.operation;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.CascadeType;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.impl.TypeUtils.FieldMetadata;
import com.googlecode.objectify.persister.impl.ObjectifyPersisterFactory;
import com.googlecode.objectify.persister.wrapper.CollectionWrapper;

public class Persister extends AbstractOperation {

	private final Logger	logger	= Logger.getLogger(this.getClass().getName());

	public Persister(Objectify objectify, ObjectifyPersisterFactory factory) {
		super(objectify, factory);
	}

	public void persiste(Object object) {
		persisteObject(object);
	}

	private boolean hasToPersiste(FieldMetadata metadata, Object target) {
		return hasCascadeType(metadata, target, CascadeType.ALL, CascadeType.PERSIST);
	}

	private void persisteCollection(Object value) {

		Collection<?> entities = (Collection<?>) value;

		if (isCollectionWrapper(entities)) {

			CollectionWrapper<?> wrapper = (CollectionWrapper<?>) entities;

			for (Object removed : wrapper.getRemoveds()) {
				removeEntity(removed);
			}

			for (Object update : wrapper.getUpdateds()) {
				persisteObject(update);
			}

			for (Object added : wrapper.getAddeds()) {
				persisteObject(added);
			}

			wrapper.cleanAddeds();
			wrapper.cleanRemoveds();

		} else {

			for (Object entity : entities) {
				persisteObject(entity);
			}

		}

	}

	private void persisteObject(Object target) {

		if (target != null) {

			String kind = factory.getEntityKind(target.getClass());

			logger.finest(buildLog("Persisting...", kind, target));

			List<FieldMetadata> fields = getPesistentFields(target);

			for (FieldMetadata metadata : fields) {

				boolean hasToPersiste = hasToPersiste(metadata, target);
				Object value = getFieldValue(metadata, target);

				if (value != null && hasToPersiste) {

					if (isEntity(value)) {
						persisteObject(value);
					} else if (isCollection(value)) {
						persisteCollection(value);
					}

				}

			}

			objectify.put(target);

			logger.finest(buildLog("Persited!", kind, target));

		}

	}

	private void removeEntity(Object entity) {

		if (entity != null) {
			new Deleter(objectify, factory).delete(entity);
		}

	}

}
