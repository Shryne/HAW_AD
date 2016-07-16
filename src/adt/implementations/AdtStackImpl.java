/**
 * 
 */
package adt.implementations;

import adt.interfaces.AdtList;
import adt.interfaces.AdtStack;

/**
 * A stack implementation by using an AdtList.
 */
class AdtStackImpl implements AdtStack {
	// ##################################################
	// variables
	// ##################################################
	private final AdtList adtList = AdtContainerFactory.adtList();
	
	// ##################################################
	// methods
	// ##################################################
	private AdtStackImpl() {}

	/**
	 * Serves as a factory method.
     */
	static AdtStack valueOf() {
		return new AdtStackImpl();
	}

	/* (non-Javadoc)
	 * @see adt.container.interfaces.AdtStack#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return adtList.isEmpty(); 
	}

	/* (non-Javadoc)
	 * @see adt.container.interfaces.AdtStack#push(int)
	 */
	@Override
	public void push(int elem) {
		adtList.insert(adtList.length() + 1, elem);
	}

	/* (non-Javadoc)
	 * @see adt.container.interfaces.AdtStack#pop()
	 */
	@Override
	public void pop() {
		adtList.delete(adtList.length());
	}

	/* (non-Javadoc)
	 * @see adt.container.interfaces.AdtStack#top()
	 */
	@Override
	public int top() {
		return adtList.retrieve(adtList.length());
	}
	
	// ##################################################
	// bonus
	// ##################################################
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof AdtStack)) {
			return false;
		}
		
		// The following is necessary, because of the
		// imperative nature of this class. It's
		// impossible to iterate through this stack
		// implementation without changing it.
		AdtStack backUpStack = valueOf();
		AdtStack obj = (AdtStack) o;
		boolean equal = true;
		
		for (int i = adtList.length(); (i >= 1) && !obj.isEmpty(); i--) {
			if (adtList.retrieve(i) != obj.top()) {
				equal = false;
				break;
			}
			
			backUpStack.push(obj.top());
			obj.pop();
		}
		fromStackToStack(backUpStack, obj);
		return equal;
	}

	@Override
	public int hashCode() {
		return adtList.hashCode();
	}
	
	@Override
	public String toString() {
		String adtListName = adtList.getClass().getSimpleName();
		String ownName = getClass().getSimpleName();
		
		return adtList.toString().replace(adtListName, ownName);
	}
	// ##################################################
	// private helper
	// ##################################################
	static void fromStackToStack(AdtStack from, AdtStack to) {
		while (!from.isEmpty()) {
			to.push(from.top());
			from.pop();
		}
	}
}
