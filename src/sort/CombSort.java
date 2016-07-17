package sort;

import adt.interfaces.AdtArray;

/**
 * Comb sort:
 * Like bubble sort with the trick of shell sort. It uses
 * a decreasing gap between the elements, instead of comparing every
 * element all the time.
 */
public class CombSort implements Sort {
    // ##################################################
    // methods
    // ##################################################
    @Override
    public void sort(AdtArray array) {
        int i = array.length();
        boolean swapped;

        do {
            i = i > 1 ? (int) (i / 1.3) : i;
            swapped = pushBiggestWithGapsToRight(array, i);
        } while (swapped || i > 1);
    }
    // ##################################################
    // private helper
    // ##################################################
    private static boolean pushBiggestWithGapsToRight(AdtArray array, int gap) {
        boolean swapped = false;

        for (int j = 0; j <= array.length() - gap; j++) {
            if (array.get(j) > array.get(j + gap)) {
                Sort.swap(array, j, j + gap);
                swapped = true;
            }
        }
        return swapped;
    }
}
