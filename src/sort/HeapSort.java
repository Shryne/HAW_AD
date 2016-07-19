package sort;

import adt.interfaces.AdtArray;

import java.util.Arrays;

/**
 *
 */
public class HeapSort implements Sort {
    // ##################################################
    // methods
    // ##################################################
    @Override
    public void sort(AdtArray array) {

    }
    // ##################################################
    // private helper
    // ##################################################
    private class Heap {
        private final int container[];

        public Heap(AdtArray array) {
            container = new int[array.length() + 1];
            for (int i = 0; i <= array.length(); i++) {
                container[i] = array.get(i);
            }
        }

        private void reHeapUp() {
            for (int i = container.length; i > 0; i--) {
                heapSeep(i);
            }
        }

        private void heapSeep(int i) {
            while (2 * i <= container.length) {
                int j = 2 * i;
                int kl = container[2 * i];
                int kr = kl - 1;

                if ((2 * i + 1) <= container.length) {
                    kr = container[2 * i + 1];
                }
                if (kl < kr) {
                    j++;
                }

                if (container[i] < container[j]) {

                }
            }
        }
    }
}