/**
 * 
 */
package adt.interfaces;

/**
 * This list starts with the index 1.
 * If you create a new list, the length will be 0 
 * and the first element to put in will have the
 * index 1.
 * 
 * @author remen
 *
 */
public interface AdtList extends AdtContainer {
	/**
	 * @return True if the list has no elements, otherwise false.
	 */
	boolean isEmpty();

	/**
	 * @return The length of the list.
	 * Examples: An empty list has a length of 0.
	 * A list of one element has a length of 1.
     */
	int length();
	
	/**
	 * @param pos The position of the insertion. 
	 * Have to be equal or smaller than length to succeed,
	 * otherwise nothing will happen.
	 * @param elem The element to be inserted
	 */
	void insert(int pos, int elem);
	
	/**
	 * @param pos The position of the element to be deleted. It
	 * has to be smaller or equal to length to succeed,
	 * otherwise nothing will happen.
	 * 
	 * The list's length will be reduced by one, if this operation
	 * is successful.
	 */
	void delete(int pos);
	
	/**
	 * @param elem The element to be searched for.
	 * @return The position of the first value, equal to elem
	 */
	int find(int elem);
	
	/**
	 *
	 * @param pos The position of the element.
	 * @return The element at the given position.
	 */
	int retrieve(int pos);
	
	/**
	 * @param list The list to be added to this list.
	 */
	void concat(AdtList list);
}
