/**
 * 
 */
package adt.implementations.tests;

import static adt.implementations.AdtContainerFactory.adtStack;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import adt.interfaces.AdtStack;

/**
 * @author remen
 *
 */
public class AdtStackTest {
	// ##################################################
	// prepared vars
	// ##################################################
	 private AdtStack stack0;
	 private AdtStack stack1;
	 private AdtStack stack9;
	 private AdtStack stack10;
	 private AdtStack stack49;
	 private AdtStack stack50;
	 private AdtStack stack499;
	 private AdtStack stack500;
	
	@Before public void reset() {
		// creation
		stack0 = adtStack();
		stack1 = adtStack();
		stack9 = adtStack();
		stack10 = adtStack();
		stack49 = adtStack();
		stack50 = adtStack();
		stack499 = adtStack();
		stack500 = adtStack();
		
		// insertion
		stack1.push(-43);
		
		for (int i = 1; i <= 9; i++) stack9.push(i);
		for (int i = 1; i <= 10; i++) stack10.push(i);
		
		for (int i = 50; i <= 99; i++) stack49.push(i);
		for (int i = 50; i <= 100; i++) stack50.push(i);
		
		for (int i = -100; i >= -599; i--) stack499.push(i);
		for (int i = -100; i >= -600; i--) stack500.push(i);
	}
	
	// ##################################################
	// tests
	// ##################################################
	@Test public void isEmpty() {
		assertTrue(stack0.isEmpty());
		assertFalse(stack1.isEmpty());
		assertFalse(stack9.isEmpty());
		assertFalse(stack10.isEmpty());
		assertFalse(stack49.isEmpty());
		assertFalse(stack50.isEmpty());
		assertFalse(stack499.isEmpty());
		assertFalse(stack500.isEmpty());
	}
	
	@Test public void equals() {
		// stackA == stackB && stackB == stackA to prove symmetry
		assertEquals(stack0, stack0);
		assertEquals(stack50, stack50);
		assertEquals(stack499, stack499);
		assertEquals(stack499, stack499); // to assure that equals doesnt change the object

		assertEquals(stack0, copyStack(stack0));
		assertEquals(copyStack(stack0), stack0);
		
		assertEquals(stack50, copyStack(stack50));
		assertEquals(copyStack(stack50), stack50);
		
		// different size
		AdtStack stackT51 = copyStack(stack50);
		stackT51.push(540);

		assertFalse(stackT51.equals(stack50));
		assertFalse(stack50.equals(stackT51));
		
		assertEquals(stack0, stack10);
		assertEquals(stack10, stack0);
		
		// failure at first
		AdtStack stackT10 = adtStack();
		stackT10.push(-1);
		for (int i = 2; i <= 10; i++) stackT10.push(i);
		
		assertFalse(stackT10.equals(stack10));
		assertFalse(stack10.equals(stackT10));
		
		// failure at middle
		AdtStack stackT50 = adtStack();
		for (int i = 50; i <= 68; i++) stackT50.push(i);
		
		stack50.push(0);
		for (int i = 69; i <= 100; i++) stackT50.push(i);
		
		assertFalse(stackT50.equals(stack50));
		assertFalse(stack50.equals(stackT50));
		
		// failure at last
		AdtStack stackT500 = adtStack();
		for (int i = -100; i >= -599; i--) stackT500.push(i);
		stackT500.push(540);
		
		assertFalse(stackT500.equals(stack500));
		assertFalse(stack500.equals(stackT500));
		
		// failure because of other object
		assertFalse(stack0.equals(0));
		assertFalse(stack0.equals("Hey"));
		assertFalse(stack1.equals(-43));
		assertFalse(stack10.equals(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
	}
	
	@Test public void push() { // push <-> pop
		// small
		stack0.push(-43); assertEquals(stack0, stack1);
		stack9.push(10); assertEquals(stack9, stack10);
		
		// medium
		stack49.push(100); assertEquals(stack49, stack50);
		
		// big
		stack499.push(-600); assertEquals(stack499, stack500);
	}
	
	@Test public void pop() { // pop <-> push
		// small
		stack1.pop(); assertEquals(stack0, stack1);
		stack10.pop(); assertEquals(stack9, stack10);
		
		// medium
		stack50.pop(); assertEquals(stack49, stack50);
		
		// big
		stack500.pop(); assertEquals(stack499, stack500);
	}
	
	@Test public void top() {
		// small
		assertEquals(stack0.top(), 0);
		assertEquals(stack1.top(), -43);
		
		// middle
		assertEquals(stack9.top(), 9);
		assertEquals(stack50.top(), 100);
		
		// big
		assertEquals(stack500.top(), -600);
	}
	// ##################################################
	// private helper
	// ##################################################
	private AdtStack copyStack(AdtStack src) {
		AdtStack result = adtStack();
		AdtStack backUp = adtStack();
		
		/*
		 * 2 Steps are necessary, because otherwise the result stack
		 * would be inverted. Additionally a backUp stack is needed,
		 * because iterating through the stack is imperative.
		 */
		while (!src.isEmpty()) {
			backUp.push(src.top());
			src.pop();
		}
		
		while (!backUp.isEmpty()) {
			src.push(backUp.top());
			result.push(backUp.top());
			backUp.pop();
		}
		
		return result;
	}
}
