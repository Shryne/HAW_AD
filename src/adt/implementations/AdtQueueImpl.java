/**
 * 
 */
package adt.implementations;

import adt.interfaces.AdtQueue;
import adt.interfaces.AdtStack;

/**
 * An AdtQueue implementation based on two stacks.
 */
class AdtQueueImpl implements AdtQueue {
	// ##################################################
	// variables
	// ##################################################
	private final AdtStack inStack  = AdtContainerFactory.adtStack();
	private final AdtStack outStack = AdtContainerFactory.adtStack();
	
	// ##################################################
	// methods
	// ##################################################
	private AdtQueueImpl() {}

	/**
	 * A factory method used to create an AdtQueue.
     */
	public static AdtQueue valueOf() {
		return new AdtQueueImpl();
	}

	/* (non-Javadoc)
	 * @see adt.container.interfaces.AdtQueue#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return inStack.isEmpty() && outStack.isEmpty();
	}

	/* (non-Javadoc)
	 * @see adt.container.interfaces.AdtQueue#front()
	 */
	@Override
	public int front() {
		if (outStack.isEmpty()) {
			AdtStackImpl.fromStackToStack(inStack, outStack);
		}
		return outStack.top();
	}

	/* (non-Javadoc)
	 * @see adt.container.interfaces.AdtQueue#enQueue(int)
	 */
	@Override
	public void enQueue(int elem) {
		inStack.push(elem);
	}

	/* (non-Javadoc)
	 * @see adt.container.interfaces.AdtQueue#deQueue()
	 */
	@Override
	public void deQueue() {
		if (outStack.isEmpty()) {
			AdtStackImpl.fromStackToStack(inStack, outStack);
		}
		outStack.pop();
	}
	
	// ##################################################
	// bonus
	// ##################################################
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof AdtQueue)) {
			return false;
		}
		
		AdtQueue backUpThis  = valueOf();
		AdtQueue backUpOther  = valueOf();
		AdtQueue obj = (AdtQueue) o;
		
		while (!isEmpty() && !obj.isEmpty()) {
			if (front() != obj.front()) {
				restore(backUpThis, this);
				restore(backUpOther, obj);
				return false;
			}
			
			backUpThis.enQueue(front());
			backUpOther.enQueue(front());
			deQueue();
			obj.deQueue();
		}
		return true;
	}

	@Override
	public int hashCode() {
		return inStack.hashCode() * 3 + outStack.hashCode() * 7;
	}
	
	@Override
	public String toString() {
		AdtStackImpl.fromStackToStack(inStack, outStack);
		
		String stackName = outStack.getClass().getSimpleName();
		String ownName = getClass().getSimpleName();
		
		return outStack.toString().replace(stackName, ownName);
	}
	
	// ##################################################
	// private helper
	// ##################################################
	private static void restore(AdtQueue from, AdtQueue to) {
		while (!from.isEmpty()) {
			to.enQueue(from.front());
			from.deQueue();
		}
	}
}
