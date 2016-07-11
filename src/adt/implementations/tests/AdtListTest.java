/**
 *
 */
package adt.implementations.tests;

import static adt.implementations.AdtContainerFactory.adtSingleConcatList;
import static org.junit.Assert.*;
import org.junit.Test;

import adt.interfaces.AdtList;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import static adt.implementations.AdtContainerFactory.adtList;
/**
 * @author remen
 *
 */
@RunWith(Suite.class)
@SuiteClasses({
		AdtListTest.EqualsTests.class, AdtListTest.RetrieveTests.class,
		AdtListTest.ConcatTests.class, AdtListTest.FindTests.class,
		AdtListTest.InsertTests.class, AdtListTest.DeleteTests.class,
		AdtListTest.LengthTests.class, AdtListTest.IsEmptyTests.class
})
public class AdtListTest {
	public static class EqualsTests {
		@Test
		public void same() {
			AdtList toTest = list(0, 1, 2, 3, 4, 55);
			assertEquals(toTest, toTest);
		}

		@Test
		public void empty() {
			assertEquals(list(), list());
		}

		@Test
		public void oneElement() {
			assertEquals(list(5), list(5));
		}

		@Test
		public void someElements() {
			assertEquals(list(10, 23, 12, 18, 66), list(10, 23, 12, 18, 66));
		}

		@Test
		public void manyElements() {
			assertEquals(
					list(33_000, (i) -> i * 12 - 4),
					list(33_000, (i) -> i * 12 - 4)
			);
		}

		@Test
		public void oneElementAndDifferent() {
			assertNotEquals(list(4), list(-4));
		}

		@Test
		public void someElementsDifferentLength() {
			assertNotEquals(list(1, 2, 3, 4), list(1, 2, 3));
			assertNotEquals(list(1, 2, 3), list(1, 2, 3, 4));
		}

		@Test
		public void someElementsAllDifferent() {
			assertNotEquals(
					list(5, 4, 2, -7, -632, 0),
					list(4, 239, 2, 39, 20, 13)
			);
		}

		@Test
		public void someElementsOneDifferent() {
			assertNotEquals(
					list(29, 409, -2, 43, -32, 1),
					list(29, 409, -2, 12, -32, 1)
			);
		}

		@Test
		public void doesNotHarm() {
			AdtList self = list(29, 430, 23, 4);
			AdtList copyOfSelf = list(self);

			self.equals(list(29, 430, 23, 4));
			assertEquals(copyOfSelf, self);
		}

		@Test
		public void doesNotHarmParameter() {
			AdtList parameter = list(5, 20, 132, 394);
			AdtList copyOfParameter = list(parameter);

			list(5, 20, 132, 394).equals(parameter);
			assertEquals(copyOfParameter, parameter);
		}

		@Test
		public void doesNotHarmDifferent() {
			AdtList self = list(29, 430, 23, 4);
			AdtList copyOfSelf = list(self);

			self.equals(list(29, 430, 230, -4));
			assertEquals(copyOfSelf, self);
		}

		@Test
		public void doesNotHarmParameterDifferent() {
			AdtList parameter = list(23, 439, 214, 2, 0);
			AdtList copyOfParameter = list(parameter);

			list(23, 439, 214, 15, 0).equals(parameter);
			assertEquals(copyOfParameter, parameter);
		}
	}

	public static class IsEmptyTests {
		@Test
		public void empty() {
			assertTrue(list().isEmpty());
		}

		@Test
		public void oneElement() {
			assertFalse(list(0).isEmpty());
		}

		@Test
		public void someElements() {
			assertFalse(list(32, 439, 430, 2_310, -32).isEmpty());
		}

		@Test
		public void manyElements() {
			assertFalse(list(4_320, (i) -> i).isEmpty());
		}

		@Test
		public void doesNotHarm() {
			AdtList isEmptyAt = list(5, 6, 10, 12);
			isEmptyAt.isEmpty();

			assertEquals(isEmptyAt, list(5, 6, 10, 12));
		}
	}

	public static class LengthTests {
		@Test
		public void empty() {
			assertEquals(0, list().length());
		}

		@Test
		public void oneElement() {
			assertEquals(1, list(3).length());
		}

		@Test
		public void someElements() {
			assertEquals(4, list(0, 1, 23, 449).length());
		}

		@Test
		public void manyElements() {
			assertEquals(23_430, list(23_430, (i) -> i * i).length());
		}

		@Test
		public void doesNotHarm() {
			AdtList lengthAt = list(3, 7, 230, 231, 549, 230);
			lengthAt.length();

			assertEquals(list(3, 7, 230, 231, 549, 230), lengthAt);
		}
	}

	public static class InsertTests {
		@Test
		public void emptyToOne() {
			AdtList result = list();
			result.insert(1, 10);

			assertEquals(list(10), result);
		}

		@Test
		public void oneToTwoFrontInsert() {
			AdtList result = list(5);
			result.insert(1, -1);

			assertEquals(list(-1, 5), result);
		}

		@Test
		public void oneToTwoBackInsert() {
			AdtList result = list(66);
			result.insert(2, 300);

			assertEquals(list(66, 300), result);
		}

		@Test
		public void insertInSomeMiddle() {
			AdtList result = list(5, 230, 439, -43, 423);
			result.insert(3, -43);

			assertEquals(list(5, 230, -43, 439, -43, 423), result);
		}

		@Test
		public void insertInManyFront() {
			AdtList result = list(7_900, i -> 1 + i);
			result.insert(1, 1);

			assertEquals(list(7901, (i) -> i), result);
		}

		@Test
		public void insertInManyBack() {
			AdtList result = list(11_999, i -> i * 5);
			result.insert(12_000, 60_000);

			assertEquals(list(12_000, i -> i * 5), result);
		}

		@Test
		public void insertAtZero() {
			AdtList result = list(4, 23, 544);
			result.insert(0, 22);

			assertEquals(list(4, 23, 544), result);
		}

		@Test
		public void insertAtNegative() {
			AdtList result = list(4, 30, 239, 230);
			result.insert(-1, 2_000);

			assertEquals(list(4, 30, 239, 230), result);
		}

		@Test
		public void insertAtOverLengthByTwo() {
			AdtList result = list(22, 222, 4, -32, -230);
			result.insert(7, 99);

			assertEquals(list(22, 222, 4, -32, -230), result);
		}
	}

	public static class DeleteTests {
		@Test
		public void deleteEmpty() {
			AdtList result = list();
			result.delete(1);

			assertEquals(list(), result);
		}

		@Test
		public void deleteWithOne() {
			AdtList result = list(-323);
			result.delete(1);

			assertEquals(list(), result);
		}

		@Test
		public void deleteWithSomeFront() {
			AdtList result = list(59, 230, 230, -4, 0, -23, -430);
			result.delete(5);

			assertEquals(list(59, 230, 230, -4, -23, -430), result);
		}

		@Test
		public void deleteWithSomeBack() {
			AdtList result = list(50, 430, -2, 213, 4, 9, 6);
			result.delete(7);

			assertEquals(list(50, 430, -2, 213, 4, 9), result);
		}

		@Test
		public void	deleteWithSomeMiddle() {
			AdtList result = list(95, 40, -3, -54, 23, 549);
			result.delete(4);

			assertEquals(list(95, 40, -3, 23, 549), result);
		}

		@Test
		public void deleteManyFront() {
			AdtList result = list(60_000, i -> i * 23);
			result.delete(1);

			assertEquals(list(59_999, i -> (i + 1) * 23), result);
		}

		@Test
		public void deleteAtZero() {
			AdtList result = list(59, 21, -2, -4, -2);
			result.delete(0);

			assertEquals(list(59, 21, -2, -4, -2), result);
		}

		@Test
		public void deleteAtNegative() {
			AdtList result = list(0, -2, 0, 16, 32);
			result.delete(-1);

			assertEquals(list(0, -2, 0, 16, 32), result);
		}

		@Test
		public void deleteAtOverLengthByOne() {
			AdtList result = list(9, 9, 59, 2, 10);
			result.delete(6);

			assertEquals(list(9, 9, 59, 2, 10), result);
		}
	}

	public static class FindTests {
		@Test
		public void findAtFirstIndex() {
			assertEquals(1, list(10, 23, 40).find(10));
		}

		@Test
		public void findAtLastIndex() {
			assertEquals(6, list(0, 1, 2, 3, 4, 5).find(5));
		}

		@Test
		public void findAtMiddleIndex() {
			assertEquals(4, list(4, 20, 12, 60, 23, 549).find(60));
		}

		@Test
		public void findInBig() {
			assertEquals(
					8500,
					list(9_000, (i) -> i * 10).find(85_000)
			);
		}

		@Test
		public void findWithDuplicationsGaped() {
			assertEquals(3, list(43, 203, 123, 549, 203, 123, 4).find(123));
		}

		@Test
		public void findWithDuplications() {
			assertEquals(4, list(0, 1, 2, 3, 3, 3).find(3));
		}

		@Test
		public void findNotInEmpty() {
			assertEquals(0, list().find(12));
		}

		@Test
		public void findNot() {
			assertEquals(0, list(12, 32, -43, 43, -34).find(34));
		}
	}

	public static class RetrieveTests {
		@Test
		public void retrieveFirst() {
			assertEquals(522, list(522).retrieve(1));
		}

		@Test
		public void retrieveLast() {
			assertEquals(-25, list(15, 0, 230, 25, -25).retrieve(5));
		}

		@Test
		public void retrieveMiddle() {
			assertEquals(10, list(22, 24, 54, 10, 249, -32, 0).retrieve(4));
		}

		@Test
		public void retrieveFromBig() {
			assertEquals(192_510, list(40_000, (i) -> i * 5).retrieve(38_502));
		}

		@Test
		public void retrieveNegativeIndex() {
			assertEquals(0, list(20, 18, 0, 23).retrieve(-1));
		}

		@Test
		public void retrieveZeroIndex() {
			assertEquals(0, list(88, 12).retrieve(0));
		}

		@Test
		public void retrieveGreaterThanLength() {
			assertEquals(0, list(0, 1, 2, 3).retrieve(5));
		}

		@Test
		public void doesNotHarm() {
			AdtList retrieveFrom = list(5, 6, 7, 8, 9, 10);
			AdtList preRetrieve = list(retrieveFrom);

			retrieveFrom.retrieve(3);
			assertEquals(retrieveFrom, preRetrieve);
		}
	}

	public static class ConcatTests {
		@Test
		public void nothingToNothing() {
			AdtList result = list();
			result.concat(list());

			assertEquals(list(), result);
		}

		@Test
		public void nothingToSomething() {
			AdtList result = list(0, 50, 20, 22, -10);
			result.concat(list());

			assertEquals(list(0, 50, 20, 22, -10), result);
		}

		@Test
		public void somethingToNothing() {
			AdtList result = list();
			result.concat(list(-14, 24, -300));

			assertEquals(list(-14, 24, -300), result);
		}

		@Test
		public void somethingToSomething() {
			AdtList result = list(12, 54, 22);
			result.concat(list(-16, 0));

			assertEquals(list(12, 54, 22, -16, 0), result);
		}

		@Test
		public void bigToBig() {
			AdtList result = list(10_000, (i) -> i * 2);
			result.concat(list(20_000, (i) -> (10_000 + i) * 2));

			assertEquals(list(30_000, (i) -> i * 2), result);
		}

		@Test
		public void selfToSelf() {
			AdtList result = list(5, 5, 38);
			result.concat(result);

			assertEquals(list(5, 5, 38, 5, 5, 38), result);
		}

		@Test
		public void doesNotHarmParameter() {
			AdtList result = list(100, 50, 0);
			AdtList parameter = list(0, 13, -42);

			result.concat(parameter);
			assertEquals(list(0, 13, -42), parameter);
		}
	}

	// ##################################################
	// private helper
	// ##################################################

	/**
	 * @param src List to be copied.
	 * @return A list with the same elements, as src.
	 */
	private static AdtList list(AdtList src) {
		return list(src.length(), src::retrieve);
	}

	/**
	 * A shortcut to create a list and put the given elements
	 * in.
	 *
	 * @param elems: 0, 5, 10, 23
	 * @return adtList(0, 5, 10, 23)
	 */
	private static AdtList list(int... elems) {
		return list(elems.length, (i) -> elems[i - 1]);
	}

	/**
	 * Returns a list with the given length and elements calculated
	 * with the given lambda. This method serves as a shortcut to create
	 * large lists, as I can't write a list with 1000 and more elements
	 * by hand.
	 *
	 * @param length The length of the list.
	 * @param function The function to calculate the elements for
	 *                 the list.
	 * @return The constructed list.
	 */
	private static AdtList list(int length, CalcFunction function) {
		AdtList adtList = adtSingleConcatList();
		for (int i = 1; i <= length; i++) {
			adtList.insert(i, function.apply(i));
		}
		return adtList;
	}

	/**
	 * An interface to create a lambda for list(int length, CalcFunction
	 * function). It provides a method to calculate a value with the given
	 * index.
	 */
	@FunctionalInterface
	private interface CalcFunction {
		/**
		 * @param i The index given from outside.
		 * @return The calculated value.
		 */
		int apply(int i);
	}
}
