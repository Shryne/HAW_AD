/**
 * 
 */
package adt.interfaces;

/**
 * @author remen
 *
 */
public interface AdtQueue extends AdtContainer {
	public boolean isEmpty();
	
	/**
	 * 
	 * @return
	 */
	public int front();

	/**
	 * 
	 * @param elem
	 */
	public void enQueue(int elem);
	
	/**
	 * 
	 */
	public void deQueue();
}
