package com.android2java.syncronizedpooltest;

import com.android2java.Pools;

public class Foo {

	private static final Pools.SynchronizedPool<Foo> FOO_POOL = new Pools.SynchronizedPool<>(10);
	
	private String state;
	
	public static Foo obtain() {
		Foo instance = FOO_POOL.acquire();
		return (instance != null) ? instance : new Foo();
	}

	public void recycle() {
		state = null;
		FOO_POOL.release(this);
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
