package sort.generator;

import adt.interfaces.AdtArray;

import adt.implementations.AdtContainerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Utility class for sortnum and everything around it.
 * Created by remen on 05.11.15.
 */
public class Generator {
    private static final String FILENAME = "zahlen.dat";
    private static final String DELIMETER = " ";

    private Generator() {
    }

    /**
     * Generates the specified amount of random
     * numbers and exports them to a file.
     * The range goes from 0 to amount. If the file already
     * exists, it will be overwritten without any exceptions.
     * <p>
     * The default filename is "zahlen.dat"
     *
     * @param amount of random numbers and the greatest
     *               possible number. The amount has to be >= 0,
     *               otherwise this operation will be ignored.
     */
    public static void sortnum(int amount) {
        if (amount <= 0) return;

        int arr[] = new int[amount];
        for (int i = 0; i < amount; i++)
            arr[i] = (int) (Math.random() * amount + 1);

        String output = createExportString(arr);
        writeFile(FILENAME, output);
    }

    /**
     * Same as {@link Generator#sortnum(int)}, but it's generated numbers
     * are sorted from left to right.
     */
    public static void sortnumLeft(int amount) {
        sortnum(amount);

        int arr[] = importArray("zahlen.dat", amount);
        Arrays.sort(arr);

        String output = createExportString(arr);
        writeFile(FILENAME, output);
    }

    /**
     * Same as {@link Generator#sortnum(int)}, but its generated numbers
     * are sorted from right to left.
     */
    public static void sortnumRight(int amount) {
        sortnumLeft(amount);
        int arr[] = reverse(importArray("zahlen.dat", amount));

        String output = createExportString(arr);
        writeFile(FILENAME, output);
    }

    // ####################################################
    // bonus
    // ####################################################
    public static AdtArray importNums(String filename) {
        try {
            AdtArray arr = AdtContainerFactory.adtArray();
            Scanner input = new Scanner(new File(filename));

            for (int i = 0; input.hasNext(); i++)
                arr.set(i, input.nextInt());

            return arr;
        } catch (FileNotFoundException e) {
        }
        return null;
    }

    // ####################################################
    // private helper
    // ####################################################
    private static String createExportString(int arr[]) {
        StringBuilder output = new StringBuilder(arr.length * 3);
        for (int e : arr) output.append(e).append(DELIMETER);

        return output.toString();
    }

    private static void writeFile(String filename, String output) {
        try {
            Files.write(Paths.get(filename), output.getBytes());
        } catch (IOException e) {
        }
    }

    private static int[] importArray(String filename, int amount) {
        try {
            int arr[] = new int[amount];
            Scanner input = new Scanner(new File(filename));

            for (int i = 0; input.hasNext(); i++)
                arr[i] = input.nextInt();

            return arr;
        } catch (FileNotFoundException e) {
        }

        return null;
    }

    private static int[] reverse(int[] arr) {
        for (int i = 0; i < arr.length / 2; i++)
            swap(arr, arr.length - i - 1, i);

        return arr;
    }

    private static void swap(int array[], int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}

