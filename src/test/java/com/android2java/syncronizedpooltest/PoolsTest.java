package com.android2java.syncronizedpooltest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Assert;
import org.junit.Test;


public class PoolsTest {

	private static final class PoolsRunnable implements Runnable {
		
		static final int n = 15;
		
		static final String[] strings = new String[]{"a","b","c","d","e","f","g","h","i","j"};
		
		private CountDownLatch countDownLatch;
		
		private int threadId;

		public PoolsRunnable(CountDownLatch countDownLatch, int threadId) {
			super();
			this.threadId = threadId;
			this.countDownLatch = countDownLatch;
		}

		@Override
		public void run() {
			Foo foo = null;
			for (int i = 0; i < n; i++) {
				
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				foo = Foo.obtain();
				Assert.assertNotNull(foo);
				foo.setState(new String(strings[i % 10]));
				System.out.println("THREAD " + threadId + " # " + foo);
				if (i % 5 == 0) {
					System.out.println("One instance recycled!");
					foo.recycle();
				}
			}
			countDownLatch.countDown();
		}
	}

	@Test
	public void poolsExample() {
		
		int executorNumber = 3;
		int threadNumber = executorNumber * 3;
		
		ExecutorService executor = Executors.newFixedThreadPool(executorNumber);
		
		CountDownLatch countDownLatch = new CountDownLatch(threadNumber);
		
		for (int i = 0; i < threadNumber; i++)
			executor.execute(new PoolsRunnable(countDownLatch, i));
		
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
