/**
 * 
 */
package adt.implementations.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import adt.interfaces.AdtList;

import static adt.implementations.AdtContainerFactory.adtList;
/**
 * @author remen
 *
 */
public class AdtListTest {
	// ##################################################
	// prepared vars
	// ##################################################
	
	// normal
	private AdtList list0;
	private AdtList list5;
	private AdtList list33;
	private AdtList list1000;
	
	// after deletion / -> insertion
	private AdtList list1;
	private AdtList list4;
	private AdtList list32;
	private AdtList list999;
	
	// concatenation
	private AdtList list5_5;
	private AdtList list5_33;
	private AdtList list33_5;
	private AdtList list33_1000;
	private AdtList list1000_33;
	private AdtList list1000_1000;
	
	// find
	private AdtList list6x;
	
	@Before public void reset() {
		// creation
		list0 = adtList();
		list5 = adtList();
		list33 = adtList();
		list1000 = adtList();
		
		list1 = adtList();
		list4 = adtList();
		list32 = adtList();
		list999 = adtList();
		
		list5_5 = adtList();
		list5_33 = adtList();
		list33_5 = adtList();
		list33_1000 = adtList();
		list1000_33 = adtList();
		list1000_1000 = adtList();

		list6x = adtList();
		
		// insertion - normal
		list1.insert(1, -15);
		for (int i = 1; i <= 5; i++) list5.insert(i, i - 1);
		for (int i = 1; i <= 33; i++) list33.insert(i, i * 2);
		for (int i = 1; i <= 1000; i++) list1000.insert(i, i - 501);

		// insertion - insert / delete
		bulkInsert(list4, 0, 1, 3, 4); // 0 1 [2] 3 4
		
		// 2 4 6 ... [16] 18 20 ... 64
		for (int i = 1; i <= 7; i++) list32.insert(i, i * 2);
		for (int i = 8; i <= 32; i++) list32.insert(i, (i + 1) * 2);
		
		for (int i = 1; i <= 999; i++) list999.insert(i, i - 501);
		
		// insertion - concatination
		for (int i = 1; i <= 5; i++) list5_5.insert(i, list5.retrieve(i));
		for (int i = 6; i <= 10; i++) list5_5.insert(i, list5.retrieve(i - 5));
		
		for (int i = 1; i <= 5; i++) list5_33.insert(i, list5.retrieve(i));
		for (int i = 6; i <= 38; i++) list5_33.insert(i, list33.retrieve(i - 5));
		
		for (int i = 1; i <= 33; i++) list33_5.insert(i, list33.retrieve(i));
		for (int i = 34; i <= 38; i++) list33_5.insert(i, list5.retrieve(i - 33));
		
		for (int i = 1; i <= 33; i++) list33_1000.insert(i, list33.retrieve(i));
		for (int i = 34; i <= 1033; i++) list33_1000.insert(i, list1000.retrieve(i - 33));
		
		for (int i = 1; i <= 1000; i++) list1000_33.insert(i, list1000.retrieve(i));
		for (int i = 1001; i <= 1033; i++) list1000_33.insert(i, list33.retrieve(i - 1000));
		
		for (int i = 1; i <= 1000; i++) list1000_1000.insert(i, list1000.retrieve(i));
		for (int i = 1001; i <= 2000; i++) list1000_1000.insert(i, list1000.retrieve(i - 1000));
		
		// insertion - find
		for (int i = 1; i <= 6; i++) list6x.insert(i, 6);
	}
	
	// ##################################################
	// tests
	// ##################################################
	@Test public void isEmpty() {
		assertTrue(list0.isEmpty());
		
		assertFalse(list5.isEmpty());
		assertFalse(list33.isEmpty());
		assertFalse(list1000.isEmpty());
		
		assertFalse(list4.isEmpty());
		assertFalse(list32.isEmpty());
		assertFalse(list999.isEmpty());
		
		assertFalse(list33_5.isEmpty());
		assertFalse(list5_33.isEmpty());
		assertFalse(list33_1000.isEmpty());
		assertFalse(list1000_33.isEmpty());
		assertFalse(list1000_1000.isEmpty());
		
		assertFalse(list6x.isEmpty());
	}

	@Test public void length() {
		assertEquals(list0.length(), 0);
		assertEquals(list5.length(), 5);
		assertEquals(list33.length(), 33);
		assertEquals(list1000.length(), 1000);
		
		assertEquals(list4.length(), 4);
		assertEquals(list32.length(), 32);
		assertEquals(list999.length(), 999);
		
		assertEquals(list33_5.length(), 38);
		assertEquals(list5_33.length(), 38);
		assertEquals(list33_1000.length(), 1033);
		assertEquals(list1000_33.length(), 1033);
		assertEquals(list1000_1000.length(), 2000);
		
		assertEquals(list6x.length(), 6);
	}

	@Test public void equals() {
		// listA == listB && listB == listA -> to prove symmetry
		
		assertTrue(list0.equals(list0));
		assertTrue(list5.equals(list5));
		assertTrue(list33.equals(list33));
		assertTrue(list1000.equals(list1000));
		assertTrue(list1000.equals(list1000)); // to assure that equals doesnt change the object
		assertTrue(list6x.equals(list6x));
		
		assertEquals(list5, copyList(list5));
		assertEquals(copyList(list5), list5);
		assertEquals(list1000, copyList(list1000));
		assertEquals(copyList(list1000), list1000);
		
		// different length
		AdtList listT6 = adtList();
		forA(1, 6, i -> listT6.insert(i, i - 1));

		assertFalse(listT6.equals(list5));
		assertFalse(list5.equals(listT6));
		
		// failure at first
		AdtList listT33 = adtList();
		
		listT33.insert(1, 1);
		forA(2, 33, i -> listT33.insert(i, i * 2));
		
		assertFalse(listT33.equals(list33));
		assertFalse(list33.equals(listT33));
		
		// failure at middle
		AdtList listT1000 = adtList();
		
		forA(1, 500, i -> listT1000.insert(i, i - 501));
		listT1000.insert(10000, 501);
		forA(502, 1000, i -> listT1000.insert(i, i - 501));
		
		assertFalse(listT1000.equals(list1000));
		assertFalse(list1000.equals(listT1000));
		
		// failure at last
		AdtList listT999 = adtList();
		
		forA(1, 998, i -> listT999.insert(i, i - 501));
		listT999.insert(999, -6000);
		
		assertFalse(listT999.equals(list999));
		assertFalse(list999.equals(listT999));
		
		// failure because of other object
		assertFalse(list0.equals(true));
		assertFalse(list0.equals(null));
		assertFalse(list5.equals("Hi"));
		assertFalse(list0.equals(new int[0]));
	}
	
	@Test public void insert() { // insert <-> delete
		// first
		list0.insert(1, -15); assertEquals(list0, list1);
		
		// mid
		list4.insert(3, 2); assertEquals(list4, list5);
		list32.insert(8, 16); assertEquals(list32, list33);

		// last
		list999.insert(1000, 499); assertEquals(list999, list1000);
		
		// too low
		list0 = adtList();
		AdtList list = adtList();
		
		list.insert(0, 15); assertEquals(list, list0);
		list.insert(-1, 53); assertEquals(list, list0);
		list.insert(-23, 230); assertEquals(list, list0);
		
		// too high
		list.insert(2, 33); assertEquals(list, list0);
		list.insert(14, 0); assertEquals(list, list0);
	}
	
	@Test public void delete() { // delete <-> insert
		// first
		list1.delete(1); assertEquals(list0, list1);
		
		// mid
		list5.delete(3); assertEquals(list4, list5);
		list33.delete(8); assertEquals(list32, list33);
		
		// last
		list1000.delete(1000); assertEquals(list999, list1000);
		
		// too low
		list0 = adtList();
		AdtList list = adtList();
		
		list.delete(0); assertEquals(list, list0);
		list.delete(-1); assertEquals(list, list0);
		list.delete(-23); assertEquals(list, list0);
		
		// too high
		list.delete(1); assertEquals(list, list0);
		list.delete(2); assertEquals(list, list0);
		list.delete(14); assertEquals(list, list0);
	}
	
	@Test public void find() { // find <-> retrieve
		assertEquals(list1.find(-15), 1);
		
		// first
		assertEquals(list5.find(0), 1);
		assertEquals(list33.find(2), 1);
		assertEquals(list1000.find(-500), 1);
		
		// mid
		assertEquals(list5.find(2), 3);
		assertEquals(list33.find(16), 8);
		assertEquals(list1000.find(-1), 500);
		
		// last
		assertEquals(list5.find(4), 5);
		assertEquals(list33.find(66), 33);
		assertEquals(list1000.find(499), 1000);

		// repetitions
		assertEquals(list6x.find(6), 1);
		
		// fail
		assertEquals(list0.find(0), 0);
		assertEquals(list0.find(10), 0);
		assertEquals(list5.find(-4), 0);
		assertEquals(list1000.find(1000), 0);
	}
	
	@Test public void retrieve() { // retrieve <-> find
		assertEquals(list1.retrieve(1), -15);

		// first
		assertEquals(list5.retrieve(1), 0);
		assertEquals(list33.retrieve(1), 2);
		assertEquals(list1000.retrieve(1), -500);
		
		// mid
		assertEquals(list5.retrieve(3), 2);
		assertEquals(list33.retrieve(8), 16);
		assertEquals(list1000.retrieve(500), -1);
		
		// last
		assertEquals(list5.retrieve(5), 4);
		assertEquals(list33.retrieve(33), 66);
		assertEquals(list1000.retrieve(1000), 499);
		
		// fail
		assertEquals(list0.retrieve(0), 0);
		assertEquals(list0.retrieve(10), 0);
		assertEquals(list5.retrieve(0), 0);
		assertEquals(list33.retrieve(-1), 0);
		assertEquals(list1000.retrieve(-1000), 0);		
		assertEquals(list1000.retrieve(1001), 0);		
	}
	
	@Test public void concat() {
		// nothing to nothing
		AdtList list = adtList();
		list.concat(list0); assertEquals(list, list0);
		
		// nothing to something
		AdtList list5T = copyList(list5);
		list5T.concat(list0); assertEquals(list5T, list5);
		
		// low to low
		list5T.concat(list5);
		assertEquals(list5T, list5_5);
		
		// medium to low
		AdtList list33T = copyList(list33);
		list33T.concat(list5); 
		assertEquals(list33T, list33_5);
		
		list5T = copyList(list5);
		list5T.concat(list33); assertEquals(list5T, list5_33);
		
		// medium to high
		list33T = copyList(list33);
		list33T.concat(list1000); assertEquals(list33T, list33_1000);
		
		AdtList list1000T = copyList(list1000);
		list1000T.concat(list33); assertEquals(list1000T, list1000_33);
		
		// high to high
		list1000T = copyList(list1000);
		list1000T.concat(list1000); assertEquals(list1000T, list1000_1000);
		
		// self
		list1000.concat(list1000);
		assertEquals(list1000, list1000_1000);
	}
	
	// ##################################################
	// private helper
	// ##################################################
	private static AdtList copyList(AdtList src) {
		AdtList copy = adtList();
		
		for (int i = 1; i <= src.length(); i++)
			copy.insert(i, src.retrieve(i));
		
		return copy;
	}
	
	@FunctionalInterface private interface Function {
		public void apply(int i);
	}
	
	private static void forA(int start, int end, Function f) {
		for (int i = start; i <= end; i++) f.apply(i);
	}
	
	private static void bulkInsert(AdtList list, int... nums) {
		assert (nums.length > 0);

		for (int i = 0; i < nums.length; i++)
			list.insert(i + 1, nums[i]);
	}
}
