package com.googlecode.objectify.persister.wrapper;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SetWrapper<E> implements Set<E>, CollectionWrapper<E> {

	private Set<E>	delegate	= new HashSet<E>();
	private Set<E>	addeds		= new HashSet<E>();
	private Set<E>	removeds	= new HashSet<E>();

	public SetWrapper() {
		this(new HashSet<E>());
	}

	public SetWrapper(Set<E> delegate) {
		this.delegate = delegate;
	}

	public boolean add(E e) {

		addeds.add(e);

		return delegate.add(e);
	}

	public boolean addAll(Collection<? extends E> c) {

		addeds.addAll(c);

		return delegate.addAll(c);

	}

	public void cleanAddeds() {
		addeds = new HashSet<E>();
	}

	public void cleanRemoveds() {
		removeds = new HashSet<E>();
	}

	public void clear() {

		removeds.addAll(delegate);

		delegate.clear();

	}

	public boolean contains(Object o) {
		return delegate.contains(o);
	}

	public boolean containsAll(Collection<?> c) {
		return delegate.containsAll(c);
	}

	public Collection<E> getAddeds() {
		return addeds;
	}

	public Collection<E> getRemoveds() {
		return removeds;
	}

	public Collection<E> getUpdateds() {

		Collection<E> result = new HashSet<E>();

		for (E update : delegate) {
			if (!addeds.contains(update)) {
				result.add(update);
			}
		}

		return result;

	}

	public boolean isEmpty() {
		return delegate.isEmpty();
	}

	public Iterator<E> iterator() {
		return delegate.iterator();
	}

	@SuppressWarnings("unchecked")
	public boolean remove(Object o) {

		boolean result = delegate.remove(o);

		if (result) {
			removeds.add((E) o);
		}

		return result;

	}

	@SuppressWarnings("unchecked")
	public boolean removeAll(Collection<?> c) {

		removeds.addAll((Collection<? extends E>) c);

		return delegate.removeAll(c);

	}

	public boolean retainAll(Collection<?> c) {
		return delegate.retainAll(c);
	}

	public int size() {
		return delegate.size();
	}

	public Object[] toArray() {
		return delegate.toArray();
	}

	public <T> T[] toArray(T[] a) {
		return delegate.toArray(a);
	}

	public String toString() {
		return delegate.toString();
	}

}
