/**
 * 
 */
package adt.interfaces;

/**
 * @author remen
 *
 */
public interface AdtArray extends AdtContainer {
	/**
	 * @return The length of the array. An array with one element
	 * on position = 0 has a length of 0 and an Array with zero has -1.
	 *
	 * A array with just one element on position 1.000 leads to a length
	 * of 1.000.
     */
	int length();
	
	/**
	 * @param pos The position on which the element will be inserted.
	 *            Every position greater than 0 is accepted. If there
	 *            is already an element on this position, it will be
	 *            overwritten.
	 * @param elem The element to be inserted.
	 */
	void set(int pos, int elem);
	
	/**
	 * @param pos The position of the element to be returned. Every value
	 *            is valid.
	 * @return The element on the specified position. The value will be 0
	 * in case of a position smaller than 0 or if no element was put on
	 * it.
	 */
	int get(int pos);
}
