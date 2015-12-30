package com.android2java;

import java.lang.ref.WeakReference;

public class Foo {

	private static final SparseLongObjArray<WeakReference<String>> POOL = 
			new SparseLongObjArray<WeakReference<String>>(100000);
	
	private int id;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setDesc(String desc) {
		if (POOL.get(this.id) == null) {
			POOL.put(id, new WeakReference<String>(desc));
		}
	}
	
	public String getDesc() {
		return POOL.get(this.id).get();
	}

	public static SparseLongObjArray<WeakReference<String>> getPool() {
		return POOL;
	}
}
