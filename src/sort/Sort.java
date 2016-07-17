package sort;

import adt.interfaces.AdtArray;

/**
 * Created by Seven on 12.07.2016.
 */
public interface Sort {
    /**
     * Sorts the array from small to big (1, 2, 3, ...)
     * @param array to be sorted.
     */
    void sort(AdtArray array);

    static void swap(AdtArray array, int index1, int index2) {
        int temp = array.get(index1);
        array.set(index1, array.get(index2));
        array.set(index2, temp);
    }
}
