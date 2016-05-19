package com.android2java.sparsearraytest;

import java.util.ArrayList;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SparseLongObjArrayTest {

	@Test
	public void stringPoolExample() {
		
		ArrayList<Foo> foos = new ArrayList<>();
		
		final int N = 1000000;
		
		//Ten possibilities of strings
		String[] strings = new String[]{"a","b","c","d","e","f","g","h","i","j"};
		
		/**
		 * We have N of Foo objects that will have one of ten strings listed above.
		 * Without the poll of strings (see in Foo class), we will have one million of strings instantiated.
		 * Note each index of array of strings is associated with an id (setId(..)),
		 * let's say, that is a simple representation of a imaginary model class, which have an 
		 * id associated to an description.
		 * 
		 * Using SparseLongObjArray (Or some of SparseArray family), we can avoid the autobox too.
		 * In that situation if the Poll was implemented with Map<Integer, String>, and we put
		 * as key of this map a int primitive type, it will generate some new Integers objects in memory.
		 * 
		 * So, that is a simplification of a problem that we can face when working with high amount of data.
		 * 
		 * ps.: That example can be implemented using SparseArray.java that use int[] to store keys,
		 * 		but I decided use SparseLongObjArray which is a modification of SparseArray made by me to
		 * 		store keys using an long[], just to test that implementation.
		 */
		for (int i = 0; i < N; i++) {
			Foo foo = new Foo();
			foo.setId(i % 10);
			foo.setDesc(new String(strings[i % 10]));
			foos.add(foo);
		}
		
		assertEquals(foos.size(), N);
		assertEquals(Foo.getPool().size(), strings.length);
	}
	
}
