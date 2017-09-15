package cc.hisong.clrsnote.chapter09;

import cc.hisong.clrsnote.chapter07.QuickSort;

import java.util.Arrays;
import java.util.Random;

public class OrderStatistic {
    /**
     * find the minimum element
     * @param arr
     * @return the minimum element in the array
     */
    public static int minimum(int[] arr) {
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        return min;
    }

    /**
     * find the minimum and maximum element
     * @param arr
     * @return an array as [min, max]
     */
    public static int[] minAndMax(int[] arr) {
        int min, max, start;
        if (arr.length % 2 == 0) {
            start = 2;
            if (arr[0] < arr[1]) {
                min = arr[0];
                max = arr[1];
            } else {
                min = arr[1];
                max = arr[0];
            }
        } else {
            start = 1;
            min = max = arr[0];
        }

        for (int i = start; i < arr.length; i += 2) {
            if (arr[i] < arr[i + 1]) {
                min = arr[i] < min ? arr[i] : min;
                max = arr[i + 1] > max ? arr[i + 1] : max;
            } else {
                min = arr[i + 1] < min ? arr[i + 1] : min;
                max = arr[i] > max ? arr[i] : max;
            }
        }

        return new int[]{min, max};
    }

    /**
     * select the kth small element of arr
     * can be seen as O(N), but actually is O(N^2) if got a worst case to select;
     * a little different from the one on chapter 9 of clrs
     * as the method on clrs version will return the kth small element counting from p and
     * this method will return kth small element counting from 0
     * @param arr
     * @param p the start index
     * @param r the end index
     * @param k the rank
     * @return the kth small element
     */
    public static int randomSelect(int[] arr, int p, int r, int k) {
        if (p == r) {
            return arr[p];
        }
        int q = QuickSort.randomizedPartition(arr, p, r);
        if (q == k) {
            return arr[q];
        }
        if (k < q) {
            return randomSelect(arr, p, q - 1, k);
        } else {
            return randomSelect(arr, q + 1, r, k);
        }
    }

    /**
     * exercise 9.2-3
     * random select method non-recursion version
     * can be seen as O(N), but actually is O(N^2) if got a worst case to select;
     * @param @param p the start index
     * @param r the end index
     * @param k the rank
     * @return the kth small element
     * @return
     */
    public static int randomSelectNonRecursion(int[] arr, int p, int r, int k) {
        while (p != r) {
            int q = QuickSort.randomizedPartition(arr, p, r);
            if (q == k) {
                return arr[q];
            }
            if (k < q) {
                r = q - 1;
            } else {
                p = q + 1;
            }
        }
        return arr[p];
    }

    public static void testOrderStatistic(int arraySize) {
        int[] arr = (new Random()).ints(arraySize).toArray();
        int[] copyArr;
        long start, end;

        int[] demoCopy = Arrays.copyOf(arr, arraySize);
        start = System.nanoTime();
        Arrays.sort(demoCopy);
        end = System.nanoTime();
        System.out.println("test on built-in sort pass in " + (end - start));

        copyArr = Arrays.copyOf(arr, arraySize);
        start = System.nanoTime();
        int minimumMin = minimum(copyArr);
        end = System.nanoTime();
        if (minimumMin != demoCopy[0]) {
            throw new AssertionError("minimum method error");
        }
        System.out.println("test on minimum method pass in " + (end - start));

        copyArr = Arrays.copyOf(arr, arraySize);
        start = System.nanoTime();
        int[] minAndMaxResult = minAndMax(copyArr);
        end = System.nanoTime();
        if (minAndMaxResult[0] != demoCopy[0] || minAndMaxResult[1] != demoCopy[arraySize-1]) {
            throw new AssertionError("minAndMax method error");
        }
        System.out.println("test on minAndMax method pass in " + (end - start));

        int cursor = (new Random()).nextInt(arraySize);

        copyArr = Arrays.copyOf(arr, arraySize);
        start = System.nanoTime();
        int cursorThElement = randomSelect(arr, 0, arraySize - 1, cursor);
        end = System.nanoTime();
        if (cursorThElement != demoCopy[cursor]) {
            throw new AssertionError("kth element method error");
        }
        System.out.println("test on randomSelect method sort pass in " + (end - start));

        copyArr = Arrays.copyOf(arr, arraySize);
        start = System.nanoTime();
        int randomCursorThElement = randomSelectNonRecursion(arr, 0, arraySize - 1, cursor);
        end = System.nanoTime();
        if (randomCursorThElement != demoCopy[cursor]) {
            throw new AssertionError("kth element method error");
        }
        System.out.println("test on randomSelectNonRecursion method sort pass in " + (end - start));

    }
}
