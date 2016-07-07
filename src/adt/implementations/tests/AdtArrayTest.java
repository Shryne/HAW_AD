/**
 * 
 */
package adt.implementations.tests;

import static adt.implementations.AdtContainerFactory.adtArray;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import adt.interfaces.AdtArray;

/**
 * @author remen
 *
 */
public class AdtArrayTest {
	// ##################################################
	// prepared vars
	// ##################################################
	private AdtArray array0;
	private AdtArray array20;
	private AdtArray array100;
	private AdtArray array2150;
	
	private AdtArray array_31;
	
	@Before public void reset() {
		array0 = adtArray();
		array20 = adtArray();
		array100 = adtArray();
		array2150 = adtArray();
		
		array_31 = adtArray();
		
		for (int i = 0; i <= 19; i++) array20.set(i, i);
		for (int i = -20; i <= 79; i++) array100.set(i + 20, i);
		for (int i = 100; i <= 2249; i++) array2150.set(i - 100, i);
		
		array_31.set(30, 30);
	}

	// ##################################################
	// tests
	// ##################################################
	@Test public void length() {
		assertEquals(array0.length(), -1);
		assertEquals(array20.length(), 19);
		assertEquals(array100.length(), 99);
		assertEquals(array2150.length(), 2149);
		
		assertEquals(array_31.length(), 30);
	}
	
	@Test public void equals() {
		// arrayA == arrayB && arrayB == arrayA to prove symmetry
		assertEquals(array0, array0);
		assertEquals(array100, array100);
		assertEquals(array100, array100); // to assure that equals doesnt change the object
		assertEquals(array2150, array2150);
		
		assertEquals(array0, adtArray());
		assertEquals(adtArray(), array0);

		assertEquals(array20, copyArray(array20));
		assertEquals(copyArray(array20), array20);
		
		// different size
		assertFalse(array20.equals(array100));
		assertFalse(array100.equals(array20));
		
		AdtArray array101T = copyArray(array100);
		array101T.set(array101T.length() + 50, 0);
		assertFalse(array100.equals(array101T));
		assertFalse(array101T.equals(array100));
		
		// failure at first
		AdtArray array20T = copyArray(array20);
		array20T.set(0, -539);
		assertFalse(array20T.equals(array20));
		assertFalse(array20.equals(array20T));
		
		// failure at middle
		AdtArray array2150T = copyArray(array2150);
		array2150T.set(539, 29403);
		assertFalse(array2150.equals(array2150T));
		assertFalse(array2150T.equals(array2150));
		
		// failure at last
		array20T = copyArray(array20);
		array20T.set(19, 300);
		assertFalse(array20T.equals(array20));
		assertFalse(array20.equals(array20T));
		
		// failure because of other object
		assertFalse(array0.equals(0));
		assertFalse(array20.equals(true));
		assertFalse(array2150.equals('a'));
	}
	
	@Test public void get_set() { // get <-> set
		// first
		AdtArray array1T = adtArray();
		array1T.set(0, 10);
		assertEquals(array1T.get(0), 10);
		assertEquals(array2150.get(0), 100);
		
		// middle
		assertEquals(array20.get(10), 10);
		
		// last
		AdtArray array2150T = adtArray();
		for (int i = 100; i <= 2249; i++) array2150T.set(i - 100, i);
		assertEquals(array2150, array2150T);
		
		// too low
		assertEquals(array20.get(-1), 0);
		assertEquals(array0.get(-240), 0);
		
		// too high_set
		AdtArray array_31T = adtArray();
		array_31T.set(30, 30);
		assertEquals(array_31T, array_31);
		
		assertEquals(array_31T.get(30), 30);
		
		// too high_get
		assertEquals(array_31T.get(31), 0);
		assertEquals(array0.get(1), 0);
		assertEquals(array2150T.get(-1), 0);   
	}
	// ##################################################
	// private helper
	// ##################################################
	private static AdtArray copyArray(AdtArray src) {
		AdtArray result = adtArray();
		
		for (int i = 0; i < src.length() + 1; i++)
			result.set(i, src.get(i));
		
		return result;
	}
}
