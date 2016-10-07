package com.googlecode.objectify.persister.wrapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListWrapper<E> implements List<E>, CollectionWrapper<E> {

	private List<E>	delegate	= new ArrayList<E>();
	private List<E>	addeds		= new ArrayList<E>();
	private List<E>	removeds	= new ArrayList<E>();

	public ListWrapper() {
		this(new ArrayList<E>());
	}

	public ListWrapper(List<E> delegate) {
		this.delegate = delegate;
	}

	public boolean add(E e) {

		addeds.add(e);

		return delegate.add(e);
	}

	public void add(int index, E element) {

		addeds.add(element);

		delegate.add(index, element);

	}

	public boolean addAll(Collection<? extends E> c) {

		addeds.addAll(c);

		return delegate.addAll(c);

	}

	public boolean addAll(int index, Collection<? extends E> c) {

		addeds.addAll(c);

		return delegate.addAll(index, c);

	}

	public void cleanAddeds() {
		addeds = new ArrayList<E>();
	}

	public void cleanRemoveds() {
		removeds = new ArrayList<E>();
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

	public E get(int index) {
		return delegate.get(index);
	}

	public Collection<E> getAddeds() {
		return addeds;
	}

	public Collection<E> getRemoveds() {
		return removeds;
	}

	public Collection<E> getUpdateds() {

		List<E> result = new ArrayList<E>();

		for (E update : delegate) {
			if (!addeds.contains(update)) {
				result.add(update);
			}
		}

		return result;

	}

	public int indexOf(Object o) {
		return delegate.indexOf(o);
	}

	public boolean isEmpty() {
		return delegate.isEmpty();
	}

	public Iterator<E> iterator() {
		return delegate.iterator();
	}

	public int lastIndexOf(Object o) {
		return delegate.lastIndexOf(o);
	}

	public ListIterator<E> listIterator() {
		return delegate.listIterator();
	}

	public ListIterator<E> listIterator(int index) {
		return delegate.listIterator(index);
	}

	public E remove(int index) {

		E result = delegate.remove(index);

		removeds.add(result);

		return result;

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

	public E set(int index, E element) {

		E result = delegate.set(index, element);

		addeds.add(element);
		removeds.add(result);

		return result;

	}

	public int size() {
		return delegate.size();
	}

	public List<E> subList(int fromIndex, int toIndex) {
		return delegate.subList(fromIndex, toIndex);
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
