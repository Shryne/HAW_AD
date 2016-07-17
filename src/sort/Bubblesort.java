package sort;

import adt.interfaces.AdtArray;

/**
 * Bubble sort:
 *
 */
public class BubbleSort implements Sort {
    // ##################################################
    // methods
    // ##################################################
    @Override
    public void sort(AdtArray array) {
        if (array != null) {
            for (int i = 0; i <= array.length(); i++) {
                if (!pushBiggestToEnd(array, array.length() - i)) {
                    return;
                }
            }
        }

    }
    // ##################################################
    // private helper
    // ##################################################
    private static boolean pushBiggestToEnd(AdtArray array, int endIndex) {
        boolean swapped = false;

        for (int j = 0; j < endIndex; j++) {
            if (array.get(j) > array.get(j + 1)) {
                Sort.swap(array, j, j + 1);
                swapped = true;
            }
        }
        return swapped;
    }
}
