package sort.tests;

import adt.implementations.AdtContainerFactory;
import adt.interfaces.AdtArray;
import adt.interfaces.AdtContainer;
import org.junit.Test;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import sort.InsertionSort;
import sort.Sort;

import java.util.ArrayList;
import java.util.Collection;

import static junit.framework.TestCase.assertEquals;

/**
 * This class contains all the tests for every sorting algorithm,
 * as the result of all of them should be the same.
 */
public class SortTests {

    @Test
    public void empty() {
        AdtArray toSort = array();
        createSorts().forEach(s -> s.sort(toSort));

        assertEquals(array(), toSort);
    }

    @Test
    public void oneElement() {
        AdtArray toSort = array(5);
        createSorts().forEach(s -> s.sort(toSort));

        assertEquals(array(5), toSort);
    }

    @Test
    public void twoElements() {
        AdtArray toSort = array(5, 3);
        createSorts().forEach(s -> s.sort(toSort));

        assertEquals(array(3, 5), toSort);
    }

    @Test
    public void someElementsSorted() {
        AdtArray toSort = array(1, 4, 6, 9, 10, 31);
        createSorts().forEach(s -> s.sort(toSort));

        assertEquals(array(1, 4, 6, 9, 10, 31), toSort);
    }

    @Test
    public void someElements() {
        AdtArray toSort = array(5, 2, 549, 29, 1, 59);
        createSorts().forEach(s -> s.sort(toSort));

        assertEquals(array(1, 2, 5, 29, 59, 549), toSort);
    }

    @Test
    public void someElementsDupplications() {
        AdtArray toSort = array(3, 3, 69, 120, 3, 42, 42, 3);
        createSorts().forEach(s -> s.sort(toSort));

        assertEquals(array(3, 3, 3, 3, 42, 42, 69, 120), toSort);
    }
    // ##################################################
    // private helper
    // ##################################################

    private static ArrayList<Sort> createSorts() {
        ArrayList<Sort> result = new ArrayList<>(4);
        result.add(new InsertionSort());

        return result;
    }

    private static AdtArray array(int... e) {
        AdtArray result = AdtContainerFactory.adtArray();
        for (int i = 0; i < e.length; i++) {
            result.set(i, e[i]);
        }
        return result;
    }

    private static AdtArray array(AdtArray array) {
        AdtArray result = AdtContainerFactory.adtArray();
        for (int i = 0; i < array.length(); i++) {
            result.set(i, array.get(i));
        }
        return result;
    }
}
