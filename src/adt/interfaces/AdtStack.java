/**
 * 
 */
package adt.interfaces;

/**
 * @author remen
 *
 */
public interface AdtStack extends AdtContainer {
	public boolean isEmpty();
	
	/**
	 * @param elem The element to be added on top of
	 * this stack.
	 */
	public void push(int elem);

	/**
	 * Deletes the top element of this stack, if there
	 * are any elements to delete.
	 */
	public void pop();
	
	/**
	 * @return The top element, if there're any. The stacks 
	 * size will be reduced by one, if this operation succeeds. 
	 */
	public int top();
}
