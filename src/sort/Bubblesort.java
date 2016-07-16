package sort;

import adt.interfaces.AdtArray;

/**
 * Bubble sort:
 *
 */
public class Bubblesort implements Sort {
    // ##################################################
    // variables
    // ##################################################

    // ##################################################
    // methods
    // ##################################################
    @Override
    public void sort(AdtArray array) {
        for (int i = 0; i <= array.length(); i++) {
            pushBiggestToEnd(array, array.length() - i);
        }
    }
    // ##################################################
    // bonus
    // ##################################################

    // ##################################################
    // private helper
    // ##################################################
    private static void pushBiggestToEnd(AdtArray array, int endIndex) {
        for (int j = 0; j < endIndex; j++) {
            if (array.get(j) > array.get(j + 1)) {
                swap(array, j, j + 1);
            }
        }
    }

    private static void swap(AdtArray array, int index1, int index2) {
        int temp = array.get(index1);
        array.set(index1, array.get(index2));
        array.set(index2, temp);
    }
}
