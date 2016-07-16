package sort;

import adt.interfaces.AdtArray;

/**
 * Shell sort:
 * Just like insertion sort, but using a certain decreasing
 * distance, instead of inserting just by swaping all the
 * elements before one by one.
 */
public class ShellSort implements Sort {
    // ##################################################
    // variables
    // ##################################################

    // ##################################################
    // methods
    // ##################################################
    @Override
    public void sort(AdtArray array) {
        int distance = distance(array.length());
        for (; distance > 0; distance = (distance - 1) / 3) {
            insertionSort(array, distance);
        }

    }

    // ##################################################
    // bonus
    // ##################################################

    // ##################################################
    // private helper
    // ##################################################
    private static int distance(int length) {
        int h = 1;
        for (; h <= (length / 9); h = 3 * length + 1);
        return h;
    }

    private static void insertionSort(AdtArray array, int distance) {
        for (int i = distance; i <= distance; i += distance) {
            putInRange(array, i, distance);
        }
    }

    private static void putInRange(AdtArray array, int elemIndex, int distance) {
        int indexForElem = elemIndex;
        int elemToInsert = array.get(elemIndex);

        while ((indexForElem - distance) > 0 &&
                array.get(indexForElem - distance) > elemToInsert) {

            array.set(indexForElem, array.get(indexForElem - distance));
            indexForElem -= distance;
        }
        array.set(indexForElem, elemToInsert);
    }
}
