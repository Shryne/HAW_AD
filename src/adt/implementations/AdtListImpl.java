/**
 * 
 */
package adt.implementations;

import java.util.Arrays;
import java.util.Objects;

import adt.interfaces.AdtList;
import org.junit.Test;

/**
 * @author remen
 *
 */
class AdtListImpl implements AdtList {
	// ##################################################
	// variables
	// ##################################################
    private static final int START_POS = 1;
    private static final int START_CAPACITY = 32;
    private int container[] = new int[START_CAPACITY];

	// ##################################################
	// methods
	// ##################################################
	private AdtListImpl() {}
	
	public static AdtList valueOf() {
        return new AdtListImpl();
    }
	
	/** (non-Javadoc)
	 * @see AdtList#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
        return (length() == 0);
	}

	/**
	 * @see AdtList#length()
	 */
	@Override
	public int length() {
        return container.length;
    }

	/**
	 * @see AdtList#insert(int, int)
	 */
	@Override
	public void insert(int pos, int elem) {
		if (!insertable(pos)) return;

        container.push(pos - 1);
        container.set(pos - 1, elem);
	}

	/**
	 * @see AdtList#delete(int)
	 */
	@Override
	public void delete(int pos) {
		if (!insideList(pos - 1)) return;
        container.pop(pos - 1);
	}

	/**
	 * @see AdtList#find(int)
	 */
	@Override
	public int find(int elem) {
		for (int i = 0; i < length(); i++) 
			if (container.get(i) == elem) return i + START_POS;
		
		return (START_POS - 1); // exception
	}

	/**
	 * @see AdtList#retrieve(int)
	 */
	@Override
	public int retrieve(int pos) {
		return (insideList(pos) ? container.get(pos - START_POS) : 0); // !precondition -> 0
	}

	/**
	 * @see AdtList#concat(AdtList)
	 */
	@Override
	public void concat(AdtList list) {
		int length = list.length(); // important, if list == this! Otherwise -> overflow!
			
		for (int i = START_POS; i <= length; i++)
			insert(length() + START_POS, list.retrieve(i));
	}
	
	// ##################################################
	// bonus
	// ##################################################
	@Override public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof AdtList)) return false;
		
		AdtList obj = (AdtListImpl) o;
		if (length() != obj.length()) return false;
		
		for (int i = 0; i < length(); i++)
			if (container.get(i) != obj.retrieve(i + 1)) return false;
		
		return true;
	}
	
	/*
	 * AdtList with the elements: 1, 2, 3, 4 ->
	 * AdtList[1, 2, 3, 4]
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override public String toString() {
		return (getClass().getSimpleName() + container);
	}
	
	// ##################################################
	// private helper
	// ##################################################
	private boolean insideList(int pos) {
		return (START_POS <= pos && pos <= length());
	}
	
	private boolean insertable(int pos) {
		return (START_POS <= pos && pos <= (length() + 1));
	}


    }
}

