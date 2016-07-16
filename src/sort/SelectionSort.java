package sort;

import adt.interfaces.AdtArray;

/**
 * Created by Seven on 12.07.2016.
 */
public class SelectionSort implements Sort {
    // ##################################################
    // variables
    // ##################################################

    // ##################################################
    // methods
    // ##################################################
    public SelectionSort() {}

    public void sort(AdtArray array) {
        if (array != null) {
            for (int i = 0; i < array.length() - 1; i++) {
                int minIndex = findMinStartingAt(i, array);
                swap(array, i, minIndex);
            }
        }
    }
    // ##################################################
    // bonus
    // ##################################################

    // ##################################################
    // private helper
    // ##################################################
    private int findMinStartingAt(int startIndex, AdtArray array) {
        int minIndex = startIndex;
        for (int i = startIndex + 1; i < array.length(); i++) {
            if (array.get(i) < array.get(minIndex)) {
                minIndex = i;
            }
        }
        return minIndex;
    }

    private void swap(AdtArray array, int index1, int index2) {
        int tempIndex1 = array.get(index1);
        array.set(index1, array.get(index2));
        array.set(index2, tempIndex1);
    }
}
