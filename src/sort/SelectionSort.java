package sort;

import adt.interfaces.AdtArray;

/**
 * Selection sort:
 * Swaps the currently view element on index i with
 * the smallest element in the range from i + 1 to
 * length.
 */
public class SelectionSort implements Sort {
    // ##################################################
    // methods
    // ##################################################
    @Override
    public void sort(AdtArray array) {
        if (array != null) {
            for (int i = 0; i <= array.length() - 1; i++) {
                int minIndex = findMinStartingAt(i, array);
                swap(array, i, minIndex);
            }
        }
    }
    // ##################################################
    // private helper
    // ##################################################
    private static int findMinStartingAt(int startIndex, AdtArray array) {
        int minIndex = startIndex;
        for (int i = startIndex + 1; i <= array.length(); i++) {
            if (array.get(i) < array.get(minIndex)) {
                minIndex = i;
            }
        }
        return minIndex;
    }

    private static void swap(AdtArray array, int index1, int index2) {
        int temp = array.get(index1);
        array.set(index1, array.get(index2));
        array.set(index2, temp);
    }
}
