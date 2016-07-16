/**
 * 
 */
package adt.implementations;

import adt.AdtListSingleConc;
import adt.interfaces.AdtArray;
import adt.interfaces.AdtList;
import adt.interfaces.AdtQueue;
import adt.interfaces.AdtStack;

/**
 * A factory class to create instances of the children of
 * AdtContainer and the only one way to do this.
 * 
 * @author remen
 *
 */
public class AdtContainerFactory {
	// To prohibit instantiation and inheritance.
	private AdtContainerFactory() {}

	/**
	 * @return AdtList based on an array
     */
	public static AdtList adtList() {
		return AdtListImpl.valueOf();
	}

	/**
	 * @return AdtStack based on an AdtList.
     */
	public static AdtStack adtStack() {
		return AdtStackImpl.valueOf();
	}

	/**
	 * @return AdtQueue based on two AdtStacks.
     */
	public static AdtQueue adtQueue() {
		return AdtQueueImpl.valueOf();
	}

	/**
	 * @return An AdtArray based on an AdtList.
     */
	public static AdtArray adtArray() {
		return AdtArrayImpl.valueOf();
	}

	/**
	 * @return AdtList based on an array with concatenation between the elements.
     */
	public static AdtList adtSingleConcatList() {
		return AdtListSingleConc.valueOf();
	}
}
