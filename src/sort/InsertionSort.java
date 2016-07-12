package sort;

import adt.interfaces.AdtArray;

/**
 * Created by Seven on 12.07.2016.
 */
public class InsertionSort implements Sort {
    // ##################################################
    // variables
    // ##################################################

    // ##################################################
    // methods
    // ##################################################
    public InsertionSort() {}

    public void sort(AdtArray array) {
        if (array != null) {
            for (int i = 1; i <= array.length(); i++) {
                int j = i;
                int k = array.get(i);

                while (j > 0 && array.get(j - 1) > k) {
                    array.set(j, array.get(j - 1));
                    j--;
                }
                array.set(j, k);
            }
        }
    }
    // ##################################################
    // bonus
    // ##################################################

    // ##################################################
    // private helper
    // ##################################################
}
