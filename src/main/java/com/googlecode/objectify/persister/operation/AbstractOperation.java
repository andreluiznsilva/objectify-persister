package com.googlecode.objectify.persister.operation;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.impl.TypeUtils;
import com.googlecode.objectify.impl.TypeUtils.FieldMetadata;
import com.googlecode.objectify.persister.annotation.Cascade;
import com.googlecode.objectify.persister.impl.ObjectifyPersisterFactory;
import com.googlecode.objectify.persister.wrapper.CollectionWrapper;

public abstract class AbstractOperation {

	protected final ObjectifyPersisterFactory	factory;
	protected final Objectify					objectify;

	public AbstractOperation(Objectify objectify, ObjectifyPersisterFactory factory) {
		this.objectify = objectify;
		this.factory = factory;
	}

	private Cascade getCascade(FieldMetadata metadata, Object object) {

		Cascade result = metadata.field.getAnnotation(Cascade.class);

		if (result == null) {
			result = object.getClass().getAnnotation(Cascade.class);
		}

		return result;

	}

	private boolean isOneOfTypes(CascadeType cascadeType, CascadeType[] types) {

		boolean result = false;

		for (CascadeType type : types) {
			if (cascadeType.equals(type)) {
				result = true;
				break;
			}
		}

		return result;

	}

	protected String buildLog(String prefix, Key<?> key, Object object) {

		StringBuilder builder = new StringBuilder();
		builder.append(prefix).append(" ( ");
		builder.append(" Key:").append(key);

		if (object != null) {
			builder.append(" | Object:").append(object);
		}

		builder.append(" )");

		return builder.toString();

	}

	protected String buildLog(String prefix, String kind, Object object) {

		StringBuilder builder = new StringBuilder();
		builder.append(prefix).append(" ( ");
		builder.append(" Kind:").append(kind);

		if (object != null) {
			builder.append(" | Object:").append(object);
		}

		builder.append(" )");

		return builder.toString();

	}

	protected Object getFieldValue(FieldMetadata metadata, Object target) {
		return TypeUtils.field_get(metadata.field, target);
	}

	protected List<FieldMetadata> getPesistentFields(Object target) {
		return TypeUtils.getPesistentFields(target.getClass(), false);
	}

	protected boolean hasCascadeType(FieldMetadata metadata, Object target, CascadeType... types) {

		boolean result = false;

		Cascade annotation = getCascade(metadata, target);

		if (annotation != null) {

			CascadeType[] cascadeTypes = annotation.value();

			for (CascadeType cascadeType : cascadeTypes) {

				if (isOneOfTypes(cascadeType, types)) {
					result = true;
					break;
				}

			}

		}

		return result;

	}

	protected boolean isCollection(Object value) {
		return Collection.class.isAssignableFrom(value.getClass());
	}

	protected boolean isCollectionWrapper(Collection<?> entities) {
		return CollectionWrapper.class.isAssignableFrom(entities.getClass());
	}

	protected boolean isEntity(Object value) {
		return factory.isValidEntity(value.getClass());
	}

	protected void setFieldValue(FieldMetadata metadata, Object value, Object target) {
		TypeUtils.field_set(metadata.field, target, value);
	}

}