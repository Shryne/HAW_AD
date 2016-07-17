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
                swap(array, j, j + 1);
                swapped = true;
            }
        }
        return swapped;
    }

    private static void swap(AdtArray array, int index1, int index2) {
        int temp = array.get(index1);
        array.set(index1, array.get(index2));
        array.set(index2, temp);
    }
}
