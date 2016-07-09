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
    private static final int START_CAPACITY = 100_000;
    private static final double GROWING_FACTOR = 1.5;

    private int capacity = START_CAPACITY;
    private int length;

    /*
    As a result of the requirement that the list starts by 1,
    the indices are all shifted. I decided against using a
    method to calculate the indices. Instead I just ignore
    the first index and use the rest as always.
     */
    private int container[] = new int[START_POS + capacity];

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
        return length;
    }

	/**
	 * @see AdtList#insert(int, int)
	 */
	@Override
	public void insert(int pos, int elem) {
		if (!insertable(pos))
			return;
		//if (!fitsIn(1)) grow();

        System.arraycopy(container, pos, container, pos + 1, START_POS + length - pos);
        container[pos] = elem;
        length++;
	}

	/**
	 * @see AdtList#delete(int)
	 */
	@Override
	public void delete(int pos) {
		if (insideList(pos)) {
			System.arraycopy(container, pos + 1, container, pos, length - pos + START_POS);
			length--;
		}
	}

	/**
	 * @see AdtList#find(int)
	 */
	@Override
	public int find(int elem) {
		for (int i = START_POS; i <= length; i++) {
            if (container[i] == elem)
				return i;
        }

		return 0;
	}

	/**
	 * @see AdtList#retrieve(int)
	 */
	@Override
	public int retrieve(int pos) {
		return (insideList(pos) ? container[pos] : 0);
	}

	/**
	 * @see AdtList#concat(AdtList)
	 */
	@Override
	public void concat(AdtList list) {
		int otherLength = list.length(); // important, if list == this! Otherwise -> overflow!

		for (int i = START_POS; i <= otherLength; i++) {
            insert(START_POS + length, list.retrieve(i));
        }
	}
	
	// ##################################################
	// bonus
	// ##################################################
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (!(o instanceof AdtList))
			return false;
		
		AdtList obj = (AdtList) o;
		if (length != obj.length())
			return false;

		for (int i = 1; i <= length(); i++) {
            if (container[i] != obj.retrieve(i))
				return false;
        }

		return true;
	}
	
	/*
	 * AdtList with the elements: 1, 2, 3, 4 ->
	 * AdtList[1, 2, 3, 4]
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if (isEmpty())
			return "[]";

		return getClass().getSimpleName() +
				Arrays.toString(
						Arrays.copyOfRange(container, START_POS, length + START_POS)
				);
	}
	
	// ##################################################
	// private helper
	// ##################################################
    private boolean fitsIn(int amount) {
        return (length + amount <= capacity);
    }

    private void grow() {
        capacity *= GROWING_FACTOR;
        container = Arrays.copyOf(container, capacity + 1); // + 1 because we ignore the first field
    }

	private boolean insideList(int pos) {
        return (START_POS <= pos && pos <= length);
	}
	
	private boolean insertable(int pos) {
		return (START_POS <= pos && pos <= length + 1);
	}

}

