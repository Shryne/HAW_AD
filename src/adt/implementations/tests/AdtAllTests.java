/**
 * 
 */
package adt.implementations.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * @author remen
 *
 * This class serves as a point to run all tests at once.
 */
@RunWith(Suite.class)
@SuiteClasses({ AdtArrayTest.class, AdtListTest.class, AdtQueueTest.class, AdtStackTest.class })
public class AdtAllTests {
}