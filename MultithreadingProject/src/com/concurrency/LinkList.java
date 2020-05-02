package com.concurrency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LinkList<E> {
	E value;
	LinkList<E> rest;
	Lock lock;
	Condition valueChanged;
	Condition linkChanged;

	public LinkList(E value) {
		this.value = value;
		rest = null;
		lock = new ReentrantLock();
		valueChanged = lock.newCondition();
		linkChanged = lock.newCondition();
	}

	public void setValue(E value) {
		lock.lock();
		try {
			this.value = value;
			valueChanged.signalAll();
		} finally {
			lock.unlock();
		}

	}

	public void executeOnValue(E desiredValue, Runnable task)
			throws InterruptedException {
		lock.lock();
		try {
			while (!value.equals(desiredValue)) {
				valueChanged.await();
			}
			task.run();
		} finally {
			lock.unlock();
		}

	}

	public void append(E value) {
		LinkList<E> node = this;
		node.lock.lock();
		while (node.rest != null) {
			LinkList<E> next = node.rest;
			try {
				next.lock.lock();
			} finally {
				node.lock.unlock();
			}
			node = next;
		}

		try {
			node.rest = new LinkList<E>(value);
		} finally {
			node.lock.unlock();
		}
	}

	public void printUntilInterrupted(String prefix) {
		LinkList<E> node = this;
		node.lock.lock();

		while (true) {
			LinkList<E> next;
			try {
				System.out.println(prefix + ":" + node.value);
				while (node.rest != null) {
					node.linkChanged.await();
				}
				next = node.rest;
				next.lock.lock();
				node = next;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Thread.currentThread().interrupt();
			} finally {
				node.lock.unlock();
			}

		}

	}
}
