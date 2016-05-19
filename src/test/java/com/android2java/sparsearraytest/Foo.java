package com.android2java.sparsearraytest;

import java.lang.ref.WeakReference;

import com.android2java.SparseLongObjArray;

public class Foo {

	private static final SparseLongObjArray<WeakReference<String>> DESCRIPTION_POOL = new SparseLongObjArray<>(100000);
	
	private int id;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setDesc(String desc) {
		if (DESCRIPTION_POOL.get(this.id) == null) {
			DESCRIPTION_POOL.put(id, new WeakReference<String>(desc));
		}
	}
	
	public String getDesc() {
		return DESCRIPTION_POOL.get(this.id).get();
	}

	public static SparseLongObjArray<WeakReference<String>> getPool() {
		return DESCRIPTION_POOL;
	}
}
