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
    // methods
    // ##################################################
    @Override
    public void sort(AdtArray array) {
        int distance = distance(array.length() + 1);
        for (; distance > 0; distance = (distance - 1) / 3) {
            insertionSort(array, distance);
        }
    }
    // ##################################################
    // private helper
    // ##################################################
    private static int distance(int length) {
        int h = 1;
        for (; h <= (length / 9); h = 3 * length + 1);
        return h;
    }

    private static void insertionSort(AdtArray array, int distance) {
        for (int i = distance; i <= array.length(); i += distance) {
            putInRange(array, i, distance);
        }
    }

    private static void putInRange(AdtArray array, int elemIndex, int distance) {
        int indexForElem = elemIndex;
        int elemToInsert = array.get(elemIndex);

        while (0 <= (indexForElem - distance) &&
                array.get(indexForElem - distance) > elemToInsert) {

            array.set(indexForElem, array.get(indexForElem - distance));
            indexForElem -= distance;
        }
        array.set(indexForElem, elemToInsert);
    }
}
