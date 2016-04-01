package org.usfirst.frc3219.Robot_2016.utility;

import java.util.Iterator;

/*
 * A class that acts sort of like a ring buffer, but without
 * the "queue"ness that is unnecessary in this use case.
 * All we need is a store that overwrites the oldest value
 * in the store on add, and returns all existing elements
 * on "read" (with an iterator).  Order not important.
 */

public class RingStore<E> {
	E[] elements;
	int capacity;
	int writeIndex;
	boolean flipped;
	
	@SuppressWarnings("unchecked")
	public RingStore(int capacity) {
		elements = (E[]) new Object[capacity];
		this.capacity = capacity;
		writeIndex = -1;
		flipped = false;
	}
	
	public int size() {
		if (flipped) {
			return capacity;
		}
		
		return writeIndex + 1;
	}
	
	public int capacity() {
		return capacity;
	}
	
	private int nextIndex(int index) {
		return (index + 1) % capacity;
	}
	
	public void add(E e) {
		int next = nextIndex(writeIndex);
		if (!flipped && next < writeIndex) {
			flipped = true;
		}
		this.elements[next] = e;
		writeIndex = next;
	}
	
	public Iterator<E> iterator() {
		return new IterateRingStore(this);
	}
	
	private class IterateRingStore implements Iterator<E> {
		int startIndex;
		int lastIndex;
		RingStore<E> store;
		
		public IterateRingStore(RingStore<E> toBeIterated) {
			store = toBeIterated;
			startIndex = 0;
			lastIndex = store.flipped ? store.capacity - 1 : store.writeIndex;
		}
		
		@Override
		public boolean hasNext() {
			return startIndex <= lastIndex;
		}

		@Override
		public E next() {
			return store.elements[startIndex++];
		}
	}
}
