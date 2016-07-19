package adt.implementations;

import java.util.stream.IntStream;

public class AdtHashMapImpl {
    public enum Strategy {
        LINEAR(0),
        QUADRATIC(1),
        DOUBLE(2);

        final int value;

        Strategy(int value) {
            this.value = value;
        }
    }

    private final Strategy strategy;
    private final double RESIZE_FACTOR = 2.0;
    private final int AMOUNT_OF_CHARS = 128;

    private int maxSize;
    private int size;

    private String container[];
    private int counters[];

    private final Probe probes[] = { // j: index - k: hashCode
            (j, k) -> j, // linear
            (j, k) -> (int) (Math.pow(j / 2, 2)) * (int) (Math.pow(-1, j)), // quadratic
            (j, k) -> j * (1 + (k % (maxSize - 2))) // double
    };

    // ####################################################
    // methods
    // ####################################################
    private AdtHashMapImpl(int capacity, Strategy strategy) {
        maxSize = findPrime(capacity, (int) (capacity * RESIZE_FACTOR));
        container = new String[maxSize];
        counters = new int[maxSize];
        this.strategy = strategy;
    }

    /**
     * Returns a new hashMap with the given strategy. The size
     * is just proposal. The HashMap will resize itself, if there
     * isn't any space left for new elements and it will always
     * have a prime number as the size.
     * <p>
     * returns null, if the capacity is 0 or smaller.
     */
    public static AdtHashMapImpl create(int capacity, Strategy strategy) {
        if (capacity <= 0) return null;
        return new AdtHashMapImpl(capacity, strategy);
    }

    /**
     * Inserts the given string into the HashMap.
     * In case of insufficient space inside the hashMap,
     * it will resize itself to a new size.
     *
     * @param word Has to be != null
     */
    public void insert(String word) {
        if (word == null) return;
        ensureSize();
        int index = calcIndex(word);

        container[index] = word;
        counters[index]++;

        // size increases only if we get a new element
        if (counters[index] == 1) size++;
    }

    /**
     * Returns how many times the given word has been
     * inserted into this hashMap.
     */
    public int find(String word) {
        if (word == null) return 0;
        return counters[calcIndex(word)];
    }

    // ####################################################
    // bonus
    // ####################################################
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof AdtHashMapImpl)) return false;

        AdtHashMapImpl other = (AdtHashMapImpl) obj;
        if (size != other.size) return false;

        for (int i = 0; i < maxSize; i++) { // same amounts?
            if (counters[i] != 0 &&
                    !contains(other.counters, counters[i])) return false;
        }

        for (int i = 0; i < maxSize; i++) { // same strings?
            if (container[i] != null &&
                    !contains(other.container, container[i])) return false;
        }

        return true;
    }

    /**
     * Returns a string representation of this object.
     * <p>
     * example:
     * HashMap(11){"a" : 1, "b" : 3, "c" : 2} -> a HashMap containing
     * one "a", three "b"s and two "c"s with a maximum capacity of
     * 11.
     */
    @Override
    public String toString() {
        StringBuilder content = new StringBuilder();

        for (int i = 0; i < maxSize; i++) {
            if (container[i] == null) continue;
            content.append("\"" + container[i] + "\"" + " : " + counters[i] + ", ");
        }

        //content.replace(content.length() - 2, content.length(), ""); // remove last ", "
        return "HashMap(" + "[" + size + "/" + IntStream.of(counters).sum() + "/" + maxSize + "]" + "|" + strategy + "){" + content.toString() + "}";
    }

    // ####################################################
    // private helper
    // ####################################################

    /**
     * Calculates and returns the index for the given word.
     * This method relies on correct input (word != null).
     */
    private int calcIndex(String word) {
        int hashCode = hashCode(word);
        int j = 0;
        int index = hashCode;

        while (container[index] != null && !container[index].equals(word)) {
            index = hashCode - probes[strategy.value].apply(j, hashCode) % maxSize;
            if (index < 0) index += maxSize;
            j++;
        }

        return index;
    }

    /**
     * A shortcut method to check if the given element
     * is contained by the array.
     */
    private static <T> boolean contains(T arr[], T elem) {
        for (T t : arr) {
            if (t != null && t.equals(elem)) return true;
        }
        return false;
    }

    /**
     * Same as {@link adt.implementations.AdtHashMapImpl#contains(Object[], Object)} but
     * for int. It's necessary because java doesn't allow generics
     * with primitives.
     */
    private static boolean contains(int arr[], int elem) {
        for (int t : arr) {
            if (t == elem) return true;
        }
        return false;
    }

    /**
     * Returns the hashCode of the given String (should be a word).
     * It uses the horner sheme to avoid an integer overflow.
     */
    private int hashCode(String word) {
        int hash = 0;

        for (int i = 0; i < word.length(); i++) {
            hash = (hash * AMOUNT_OF_CHARS + word.charAt(i)) % maxSize;
        }

        return (hash < 0) ? hash + maxSize : hash;
    }


    /**
     * Ensures that the size of this container is great enough
     * to store all values without probe problems (especially
     * for quadratic probe).
     */
    private void ensureSize() {
        if (size + 1 >= maxSize / 2) {
            maxSize = findPrime((int) (maxSize * RESIZE_FACTOR), (int) (maxSize * RESIZE_FACTOR) * 2);
            size = 0;

            String oldElems[] = container;
            int oldCounters[] = counters;
            container = new String[maxSize];
            counters = new int[maxSize];

            for (int i = 0; i < oldElems.length; i++) {
                for (int j = 1; j <= oldCounters[i]; j++) { // insert val n times
                    insert(oldElems[i]);
                }
            }
        }
    }

    /**
     * The interface for the different strategies.
     * linear: j + 1
     * quadratic: (j / 2) ^ 2 * (-1) ^ j
     * double: j * (1 + (k % (m - 2)))
     */
    private interface Probe {
        int apply(int index, int hash);
    }


    /**
     * Returns a prime between min and max. This method will
     * start its search in the middle to assure to find a number
     * far away from all powers of two.
     * This method will increment max by one, if the range between
     * min and max isn't odd. This is necessary due to nature of the
     * for loop which starts in the middle and only an odd range has
     * a middle.
     *
     * @throws IllegalArgumentException <br>1. if min > max
     *                                  <br>2. if there isn't a prime number between min and max
     */
    private static int findPrime(int min, int max) {
        if (min > max) throw new IllegalArgumentException(min + " has to be greater than " + max);
        if ((max - min) % 2 != 0) max++;

        int middle = (min + max) / 2;
        int steps = max - min;

        int badResult = Integer.MIN_VALUE;
        for (int i = 0; i <= steps; i++) {
            int num = middle + ((i % 2 == 0) ? (i / 2) : -(i / 2 + 1));

            if (isPrime(num)) {
                if (isPowerOfTwo(num)) badResult = num;
                else return num;
            }
        }

        if (badResult == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("Couldn't find a prime number between "
                    + min + " and " + max);
        }
        return badResult;
    }

    /**
     * @see <a href="http://www.java2novice.com/java-interview-programs/is-prime-number/"></a>
     */
    private static boolean isPrime(int num) {
        for (int i = 2; i <= num / 2; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    /**
     * @see <a href="http://stackoverflow.com/questions/19383248/find-if-a-number-is-a-power-of-two-without-math-function-or-log-function"></a>
     */
    private static boolean isPowerOfTwo(int num) {
        return (num & (num - 1)) == 0;
    }
}

