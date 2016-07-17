package sort;

import adt.interfaces.AdtArray;

/**
 * Shakers sort:
 * Like bubble sort, but going from left to right and from
 * right to left alternately.
 */
public class ShakerSort implements Sort {
    // ##################################################
    // methods
    // ##################################################
    @Override
    public void sort(AdtArray array) {
        for (int i = 0; i <= array.length(); i++) {
            boolean swapped = pushBiggestToRight(array, i, array.length() - i);
            swapped = pushSmallestToLeft(array, array.length() - i - 2, i) || swapped;

            if (!swapped) {
                return;
            }
        }
    }
    // ##################################################
    // private helper
    // ##################################################
    private static boolean pushBiggestToRight(AdtArray array, int startIndex, int endIndex) {
        boolean swapped = false;

        for (int r = startIndex; r < endIndex; r++) {
            if (array.get(r) > array.get(r + 1)) {
                Sort.swap(array, r, r + 1);
                swapped = true;
            }
        }
        return swapped;
    }

    private static boolean pushSmallestToLeft(AdtArray array, int startIndex, int endIndex) {
        boolean swapped = false;

        for (int l = startIndex; l > endIndex; l--) {
            if (array.get(l - 1) > array.get(l)) {
                Sort.swap(array, l - 1, l);
                swapped = true;
            }
        }
        return swapped;
    }
}
