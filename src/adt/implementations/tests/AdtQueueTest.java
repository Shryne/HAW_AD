/**
 * 
 */
package adt.implementations.tests;

import static adt.implementations.AdtContainerFactory.adtQueue;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import adt.interfaces.AdtQueue;;

/**
 * @author remen
 *
 */
public class AdtQueueTest {
	// ##################################################
	// prepared vars
	// ##################################################
	private AdtQueue queue0;
	private AdtQueue queue1;
	private AdtQueue queue14;
	private AdtQueue queue15;
	private AdtQueue queue149;
	private AdtQueue queue150;
	private AdtQueue queue799;
	private AdtQueue queue800;
	
	@Before public void reset() {
		queue0 = adtQueue();
		queue1 = adtQueue();		
		queue14 = adtQueue();
		queue15 = adtQueue();
		queue149= adtQueue();
		queue150 = adtQueue();
		queue800 = adtQueue();
		queue799 = adtQueue();
		
		enQueue();
	}
	
	// ##################################################
	// tests
	// ##################################################
	@Test public void isEmpty() {
		assertTrue(queue0.isEmpty());
		assertFalse(queue1.isEmpty());
		assertFalse(queue14.isEmpty());
		assertFalse(queue15.isEmpty());
		assertFalse(queue149.isEmpty());
		assertFalse(queue150.isEmpty());
		assertFalse(queue799.isEmpty());
		assertFalse(queue800.isEmpty());
	}

	@Test public void equals() {
		// queueA == queueB && queueB == queueA to prove symmetry
		assertEquals(queue0, queue0);
		assertEquals(queue1, queue1);
		assertEquals(queue14, queue14);
		assertEquals(queue15, queue15);
		assertEquals(queue149, queue149);
		assertEquals(queue150, queue150);
		assertEquals(queue799, queue799);
		assertEquals(queue800, queue800);
		assertEquals(queue800, queue800); // to assure that equals doesnt change the object
		
		// different size
		assertNotEquals(queue14, queue15);
		assertNotEquals(queue15, queue14);
		assertNotEquals(queue15, queue150);
		assertNotEquals(queue150, queue15);
		
		// failure at first
		AdtQueue queue1T = adtQueue();
		queue1T.enQueue(-32);
		assertNotEquals(queue1T, queue1);
		assertNotEquals(queue1, queue1T);
		
		// failure at middle
		AdtQueue queue15T = adtQueue();
		
		for (int i = 1; i <= 5; i++) queue15T.enQueue(i);
		queue15T.enQueue(705);
		for (int i = 7; i <= 15; i++) queue15T.enQueue(i);

		assertNotEquals(queue15, queue15T);
		assertNotEquals(queue15T, queue15);
		
		// failure because of other object
		assertNotEquals(queue0, false);
		assertNotEquals(queue15, 150);
		assertNotEquals(queue150, "HEY");
	}
	
	@Test public void front() {
		assertEquals(queue0.front(), 0);
		assertEquals(queue1.front(), 0);
		assertEquals(queue14.front(), 2);
		assertEquals(queue15.front(), 1);
		assertEquals(queue149.front(), 20);
		assertEquals(queue150.front(), 10);
		assertEquals(queue799.front(), -4);
		assertEquals(queue800.front(), -2);
	}
	
	public void enQueue() { // enQueue <-> deQueue
		queue1.enQueue(0);
		
		for (int i = 2; i <= 15; i++) queue14.enQueue(i);
		for (int i = 1; i <= 15; i++) queue15.enQueue(i);
		for (int i = 2; i <= 150; i++) queue149.enQueue(i * 10);
		for (int i = 1; i <= 150; i++) queue150.enQueue(i * 10);
		for (int i = -2; i >= -800; i--) queue799.enQueue(i * 2);
		for (int i = -1; i >= -800; i--) queue800.enQueue(i * 2);
	}
	
	@Test public void deQueue() { // deQueue <-> enQueue
		queue1.deQueue(); assertEquals(queue1, queue0);
		queue15.deQueue(); assertEquals(queue15, queue14);
		queue150.deQueue(); assertEquals(queue149, queue150);
		queue800.deQueue(); assertEquals(queue800, queue799);
	}
	// ##################################################
	// private helper
	// ##################################################
}
