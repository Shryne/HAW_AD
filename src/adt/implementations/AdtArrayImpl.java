/**
 * 
 */
package adt.implementations;

import adt.interfaces.AdtArray;
import adt.interfaces.AdtList;

/**
 * @author remen
 *
 */
class AdtArrayImpl implements AdtArray {
	// ##################################################
	// variables
	// ##################################################
	private final static int FILLER = 0;
	private final static int START_POS = 0;
	
	private final AdtList adtList = AdtContainerFactory.adtList();
	// ##################################################
	// methods
	// ##################################################
	private AdtArrayImpl() {}
	
	public static AdtArray valueOf() { return new AdtArrayImpl(); }

	/* (non-Javadoc)
	 * @see adt.container.interfaces.AdtArray#length()
	 */
	@Override public int length() { 
		return adtList.length() - 1;
	}

	/* (non-Javadoc)
	 * @see adt.container.interfaces.AdtArray#set(int, int)
	 */
	@Override
	public void set(int pos, int elem) {
		if (pos > adtList.length()) fillTo(pos);
		
		adtList.insert(pos + 1, elem);
		adtList.delete(pos + 2);
	}

	/* (non-Javadoc)
	 * @see adt.container.interfaces.AdtArray#get(int)
	 */
	@Override
	public int get(int pos) {
		return adtList.retrieve(pos + 1);
	}
	// ##################################################
	// bonus
	// ##################################################
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof AdtArray)) return false;
		
		AdtArray obj = (AdtArray) o;
		if (length() != obj.length()) return false;
		
		for (int i = START_POS; i <= length(); i++)
			if (get(i) != obj.get(i)) return false;
		
		return true;
	}
	
	@Override public String toString() {
		String adtListName = adtList.getClass().getSimpleName();
		String ownName = getClass().getSimpleName();
		
		return adtList.toString().replace(adtListName, ownName);
	}
	// ##################################################
	// private helper
	// ##################################################
	private void fillTo(int pos) {
		int diff = pos - length();
		for (int i = 0; i < diff - 1; i++)
			adtList.insert(adtList.length() + 1, FILLER);
	}
}
