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
	public AdtContainerFactory() {}
	
	public static AdtList adtList() { return AdtListImpl.valueOf(); }
	public static AdtStack adtStack() { return AdtStackImpl.valueOf(); }
	public static AdtQueue adtQueue() { return AdtQueueImpl.valueOf(); }
	public static AdtArray adtArray() { return AdtArrayImpl.valueOf(); }

	public static AdtList adtSingleConcatList() { return AdtListSingleConc.valueOf(); }
}
