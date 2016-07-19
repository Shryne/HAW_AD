package adt;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by remen on 27.11.15.
 */
public class AdtAvlTree {
    private AdtAvlTree pre;
    private AdtAvlTree left;
    private AdtAvlTree right;

    private Integer value = null;
    private int high = 1;
    private int balance = 0;

    private AdtAvlTree() {
    }

    private AdtAvlTree(int value, AdtAvlTree pre) {
        this.value = value;
        this.pre = pre;
        this.high = 1;
        pre.high(high + 1);
    }

    public static AdtAvlTree create() {
        return new AdtAvlTree();
    }

    public boolean isEmpty() {
        return (high() == 0);
    }

    public int high() {
        return high;
    }

    public void insert(int elem) {
        if (value == null) value = elem;
        else if (elem < value) {
            if (left == null) {
                left = new AdtAvlTree(elem, this);
            } else {
                left.insert(elem);
            }
        } else {
            if (right == null) {
                right = new AdtAvlTree(elem, this);
            } else {
                right.insert(elem);
            }
        }

        calcBalance();
        assureBalance();
    }

    public void delete(int elem) {
        AdtAvlTree toDelete = search(elem);
        if (toDelete == null) return;

        switch (toDelete.children()) {
            case 0:
                toDelete.delete();
                break;
            case 1:
                toDelete.replace(toDelete.left != null ? toDelete.left : toDelete.right);
                break;
            case 2:
                AdtAvlTree replacement = left.findMaxNote();
                toDelete.value = replacement.value;
                replacement.delete(replacement.value);
                break;
        }

        toDelete.assureBalance();
    }

    public void print(String filename) {
        final String path = "C:\\Users\\Tom\\IdeaProjects\\Aufgabe3_AD\\Aufgabe3_AD\\";
        try {
            Path outputFile = Paths.get(filename + ".dot");
            BufferedWriter writer = Files.newBufferedWriter(outputFile, StandardCharsets.UTF_8);

            StringBuilder output = new StringBuilder();
            output.append("graph G { \n");
            addToString(output);
            output.append("}");

            writer.write(output.toString());
            writer.close();
        } catch (IOException e) {
        }


        System.out.println(Paths.get(filename));
        try {
            String command = "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe -Tpng " + path + filename + ".dot" + " > " + path + filename + ".png";
            Runtime.getRuntime().exec("doMyStuff.bat");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addToString(StringBuilder builder) {
        final String direction = "--";
        if (left != null) {
            builder.append(value + direction + left.value + ";\n");
            left.addToString(builder);
        }
        if (right != null) {
            builder.append(value + direction + right.value + ";\n");
            right.addToString(builder);
        }
    }

    // ####################################################
    // bonus
    // ####################################################
    public AdtAvlTree getRight() {
        return right;
    }

    public AdtAvlTree getLeft() {
        return left;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdtAvlTree)) return false;

        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();

        addToList(list1);
        ((AdtAvlTree) o).addToList(list2);

        Collections.sort(list1);
        Collections.sort(list2);

        return list1.equals(list2);
    }

    private void addToList(List<Integer> list) {
        list.add(value);

        if (left != null) left.addToList(list);
        if (right != null) right.addToList(list);
    }

    @Override
    public String toString() {
        if (left == null && right == null)
            return "L[" + value + "]";
        return "T[" + value + "] (" + left + ", " + right + ")";
    }

    // ####################################################
    // private
    // ####################################################
    private void calcBalance() {
        int leftHigh = (left == null) ? 0 : left.high();
        int rightHigh = (right == null) ? 0 : right.high();
        balance = rightHigh - leftHigh;

        if (pre == null) return;
        pre.calcBalance();
    }

    private void high(int high) {
        this.high = high;
        if (pre == null) return;
        if (pre.high() < high + 1) pre.high(high + 1);
    }

    private void assureBalance() {
        if (pre == null) return;

        if (pre.balance == -2 && balance == -1) pre.rotateRight();
        else if (pre.balance == 2 && balance == 1) pre.rotateLeft();
        else if (pre.balance == -2 && balance == 1) rotateDoubleRight(pre);
        else if (pre.balance == 2 && balance == -1) rotateDoubleLeft(pre);
        else throw new IllegalArgumentException(pre.balance + " & " + balance + " is an illegal combination.");

        pre.assureBalance();
    }

    private void rotateRight() {
        System.out.println("HALLO " + value);
        print(toString());

        AdtAvlTree tempRight = left.right;
        replace(pre, left);
        left.right = this;
        left = tempRight;

        print(toString());
        System.out.println();
    }

    private void rotateLeft() {
        AdtAvlTree tempLeft = right.left;
        if (pre != null) replace(right);
        right.left = this;
        right = tempLeft;
    }

    private void rotateDoubleRight(AdtAvlTree nextCenter) {
        rotateLeft();
        nextCenter.rotateRight();
    }

    private void rotateDoubleLeft(AdtAvlTree nextCenter) {
        rotateRight();
        nextCenter.rotateLeft();
    }

    private AdtAvlTree search(int elem) {
        if (elem == value) return this;
        else if (elem <= value) {
            if (left == null) return null;
            return left.search(elem);
        } else {
            if (right == null) return null;
            return right.search(elem);
        }
    }

    private AdtAvlTree findMaxNote() {
        if (right == null) return this;
        return right.findMaxNote();
    }

    private int children() {
        int children = 0;
        children += (left != null) ? 1 : 0;
        children += (right != null) ? 1 : 0;
        return children;
    }

    private void replace(AdtAvlTree container, AdtAvlTree replacement) {
        if (container == null) { // starting tree
            swapTrees(replacement);
        } else {
            if (container.left == this) left = replacement;
            else if (right == this) right = replacement;
            else throw new IllegalArgumentException(this + " + is not part of this tree.");
        }
    }

    private void swapTrees(AdtAvlTree replacement) {
        swap(right, replacement.right);
        swap(left, replacement.left);
        swap(pre, replacement.pre);
        swap(value, replacement.value);

        int swapHelper = high;
        high = replacement.high;
        replacement.high = swapHelper;

        swapHelper = balance;
        balance = replacement.balance;
        replacement.balance = swapHelper;
    }

    private <T> void swap(T a, T b) {
        T temp = a;
        a = b;
        b = a;
    }

    private void replace(AdtAvlTree replacement) {
        if (replacement == null) pre.delete(this);
        else {
            left = replacement.left;
            right = replacement.right;
            value = replacement.value;
            if (replacement.left != null) replacement.left.pre = this;
            if (replacement.right != null) replacement.right.pre = this;
        }
    }

    private void delete() {
        replace(null);
    }

    private void copyFrom(AdtAvlTree copy) {
        left = copy.left;
        right = copy.right;
        value = copy.value;
        high = copy.high;
        balance = copy.balance;
    }

    private void delete(AdtAvlTree tree) {
        if (tree == left) left = null;
        else if (tree == right) right = null;
        else throw new IllegalArgumentException("The tree " + tree + " isn't a subtree of this tree.");
    }
}

