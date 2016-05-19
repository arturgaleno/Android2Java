package com.android2java.simplepoolstest;

import org.junit.Assert;
import org.junit.Test;


public class PoolsTest {

	@Test
	public void poolsExample() {
		final int N = 15;
		
		//Ten possibilities of strings
		String[] strings = new String[]{"a","b","c","d","e","f","g","h","i","j"};
		
		Foo foo = null;
		for (int i = 0; i < N; i++) {
			foo = Foo.obtain();
			Assert.assertNotNull(foo);
			foo.setState(new String(strings[i % 10]));
			System.out.println(foo);
			if (i % 5 == 0) {
				System.out.println("One instance recycled!");
				foo.recycle();
			}
		}
	}
	
}
