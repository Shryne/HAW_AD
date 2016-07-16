package sort;

import adt.interfaces.AdtArray;

/**
 * Insertion sort:
 * Uses a range from 0 to i (currently viewed element) and
 * sorts by inserting the element in index i into the
 * range from 0 to i - 1.
 */
public class InsertionSort implements Sort {
    // ##################################################
    // variables
    // ##################################################

    // ##################################################
    // methods
    // ##################################################
    @Override
    public void sort(AdtArray array) {
        if (array != null) {
            for (int i = 1; i <= array.length(); i++) {
                putInRange(array, i);
            }
        }
    }
    // ##################################################
    // bonus
    // ##################################################

    // ##################################################
    // private helper
    // ##################################################
    private static void putInRange(AdtArray array, int elemIndex) {
        int indexForElem = elemIndex;
        int elemToInsert = array.get(elemIndex);

        while (indexForElem > 0 && array.get(indexForElem - 1) > elemToInsert) {
            array.set(indexForElem, array.get(indexForElem - 1));
            indexForElem--;
        }
        array.set(indexForElem, elemToInsert);
    }
}
