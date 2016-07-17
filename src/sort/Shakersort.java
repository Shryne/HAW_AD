package sort;

import adt.interfaces.AdtArray;

public class Shakersort implements Sort {
    // ##################################################
    // variables
    // ##################################################

    // ##################################################
    // methods
    // ##################################################
    @Override
    public void sort(AdtArray array) {
        //for (int i = 0; i <= array.length(); i++) {
            //pushBiggestToEnd(array, array.length() - i);
        //}

        for (int i = 0; i <= array.length(); i++) {
            boolean swapped = false;

            for (int r = i; r < array.length() - i; r++) {
                if (array.get(r) > array.get(r + 1)) {
                    swap(array, r, r + 1);
                    swapped = true;
                }
            }

            for (int l = array.length() - i - 2; l > i; l--) {
                if (array.get(l - 1) > array.get(l)) {
                    swap(array, l - 1, l);
                    swapped = true;
                }
            }
            if (!swapped) break;
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

    private static void swapStartEnd(Integer newStart, Integer newEnd) {
        Integer temp = newEnd;
        newEnd = newStart;
        newStart = newEnd;
    }
}
