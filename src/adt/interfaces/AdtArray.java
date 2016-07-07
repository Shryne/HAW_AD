/**
 * 
 */
package adt.interfaces;

/**
 * @author remen
 *
 */
public interface AdtArray extends AdtContainer {
	public int length();
	
	/**
	 * 
	 * @param pos
	 * @param elem
	 */
	public void set(int pos, int elem);
	
	/**
	 * 
	 * @param pos
	 * @return
	 */
	public int get(int pos);
}
