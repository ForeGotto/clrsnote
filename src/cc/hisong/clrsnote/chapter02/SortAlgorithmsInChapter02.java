package cc.hisong.clrsnote.chapter02;

import java.util.Arrays;
import java.util.Random;

public class SortAlgorithmsInChapter02 {

    /**
     * insertion sort algorithm in chapter 2 of clrs
     * @param arr an array of integer which is to be sorted
     */
    public static void insertionSort(int[] arr) {
        int length = arr.length;
        for (int i =1, key; i < length; i++) {
            key = arr[i];
            int j = i - 1;
            while (j >= 0 && key < arr[j]) {
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = key;
        }
    }

    public static void testInsertionSort(int arraySize) {
        int[] arr = (new Random()).ints(arraySize).toArray();
        long start = System.nanoTime();
        insertionSort(arr);
        for (int i = 1; i < arraySize; i++) {
            if (arr[i] < arr[i - 1]) throw new AssertionError();
        }
        long end = System.nanoTime();
        System.out.println("test on insertion sort pass in "+(end - start));
    }

    /**
     * exercise 2.1-2 in chapter of clrs
     * @param arr an array of integer which is to be sorted
     */
    public static void insertionSortDescending(int[] arr) {
        int length = arr.length;
        for (int i = 1, key; i < length; i++) {
            key = arr[i];
            int j = i - 1;
            while (j >= 0 && key > arr[j]) {
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = key;
        }
    }

    public static void testInsertionSortDescending(int arraySize) {
        int[] arr = (new Random()).ints(arraySize).toArray();
        long start = System.nanoTime();
        insertionSortDescending(arr);
        for (int i = 1; i < arraySize; i++) {
            if (arr[i] > arr[i - 1]) throw new AssertionError();
        }
        long end = System.nanoTime();
        System.out.println("test on descending insertion sort pass in "+(end - start));
    }

    /**
     * exercise 2.1-3 in chapter of clrs
     * @param arr the array in which to find v
     * @param value the value to find
     * @return
     */
    public static Integer linearSearch(int[] arr, int value) {
        int length = arr.length;
        for (int i = 0; i < length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return null;
    }

    public static void testLinearSearch(int arraySize) {
        int[] arr = (new Random()).ints(arraySize).toArray();
        long start = System.nanoTime();
        for (int i = 0; i < arraySize; i++) {
            if (arr[linearSearch(arr, arr[i])] != arr[i]) throw new AssertionError();
        }
        long end = System.nanoTime();
        System.out.println("test on linear search pass in "+(end - start));
    }

    /**
     * exercise 2.2-2 in chapter of clrs
     * @param arr the array to be sorted
     */
    public static void selectionSort(int[] arr) {
        int length = arr.length;
        for (int i = 0, minCursor; i < length - 1; i++) {
            minCursor = i;
            for (int j = i + 1; j < length; j++) {
                if (arr[j] < arr[minCursor]) {
                    minCursor = j;
                }
            }
            if (minCursor != i) {
                int tmp = arr[minCursor];
                arr[minCursor] = arr[i];
                arr[i] = tmp;
            }
        }
    }

    /**
     * merge function for mergeSort
     * @param arr the array to sort
     * @param left the start index of left array
     * @param leftEnd the end index of left array
     * @param right the end index of right array
     */
    private static void merge(int[] arr, int left, int leftEnd, int right) {
        int lengthLeft = leftEnd - left + 1;
        int lengthRight = right - leftEnd;

        int[] l = Arrays.copyOfRange(arr, left, leftEnd + 2);
        int[] r = Arrays.copyOfRange(arr, leftEnd + 1, right + 2);

        l[lengthLeft] = Integer.MAX_VALUE;
        r[lengthRight] = Integer.MAX_VALUE;

        for (int i = 0, j = 0, k = left; k <= right; k++) {
            if (l[i] < r[j]) {
                arr[k] = l[i];
                i++;
            } else {
                arr[k] = r[j];
                j++;
            }
        }

    }

    /**
     * merge sort algorithm in chapter 2 of clrs
     * @param arr the array to be sorted
     * @param left the start of sort area
     * @param right the end of sort area
     */
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int leftEnd = (left + right) / 2;
            mergeSort(arr, left, leftEnd);
            mergeSort(arr, leftEnd + 1, right);

            //if it's already ordered from left to right, then we don't merge them
            if (arr[leftEnd] > arr[leftEnd + 1]) {
                merge(arr, left, leftEnd, right);
            }

        }
    }

    public static void testMergeSort(int arraySize) {
        int[] arr = (new Random()).ints(arraySize).toArray();
        long start = System.nanoTime();
        mergeSort(arr, 0, arraySize-1);
        for (int i = 1; i < arraySize; i++) {
            if (arr[i] < arr[i - 1]) throw new AssertionError();
        }
        long end = System.nanoTime();
        System.out.println("test on merge sort pass in "+(end - start));
    }

    /**
     * sub procedure for bottom-up merge sort
     * @param arr the array to be sorted
     * @param dis the length of each ordered piece
     */
    private static void mergePass(int[] arr, int dis) {
        int length = arr.length;
        int i;
        for (i = 0; i + (dis * 2) - 1 < length; i = i + (dis * 2)) {
            merge(arr, i, i + dis - 1, i + (dis * 2) - 1);
        }
        if (i + dis - 1 < length) {
            merge(arr, i, i + dis - 1, length - 1);
        }
    }

    public static void mergeWithoutRecursion(int[] arr) {
        int length = arr.length;
        for (int dis = 1; dis < length; dis = (dis << 1)) {
            mergePass(arr, dis);
        }
    }

    public static void testMergeWithoutRecursion(int arraySize) {
        int[] arr = (new Random()).ints(arraySize).toArray();
        long start = System.nanoTime();
        mergeWithoutRecursion(arr);
        for (int i = 1; i < arraySize; i++) {
            if (arr[i] < arr[i - 1]) throw new AssertionError();
        }
        long end = System.nanoTime();
        System.out.println("test on merge sort without recursion pass in "+(end - start));
    }


    private static void nonSentinelMerge(int[] arr, int left, int leftEnd, int right) {
        int lengthLeft = leftEnd - left + 1;
        int lengthRight = right - leftEnd;

        int[] l = Arrays.copyOfRange(arr, left, leftEnd + 1);
        int[] r = Arrays.copyOfRange(arr, leftEnd + 1, right + 1);

        int i, j, k;
        for (i = 0, j = 0, k = left; i < lengthLeft && j < lengthRight && k <= right; k++) {
            if (r[j] > l[i]) {
                arr[k] = l[i];
                i++;
            } else {
                arr[k] = r[j];
                j++;
            }
        }

        if (i == lengthLeft) {
            System.arraycopy(r, j, arr, k, lengthRight - j);
        }

        if (j == lengthRight) {
            System.arraycopy(l, i, arr, k, lengthLeft - i);
        }
    }

    public static void mergeSortWithNonSentinelMerge(int[] arr, int left, int right) {
        if (left < right) {
            int leftEnd = (left + right) / 2;
            mergeSortWithNonSentinelMerge(arr, left, leftEnd);
            mergeSortWithNonSentinelMerge(arr, leftEnd + 1, right);
            if (arr[leftEnd] > arr[leftEnd + 1]) {
                nonSentinelMerge(arr, left, leftEnd, right);
            }

        }
    }

    public static void testAllSortMethods(int arraySize) {
        int[] arr = (new Random()).ints(arraySize).toArray();
//        mergeWithoutRecursion(arr);
//        insertionSortDescending(arr);
        int[] copyArr = Arrays.copyOf(arr, arraySize);

        long start = System.nanoTime();
        Arrays.sort(copyArr);
        for (int i = 1; i < arraySize; i++) {
            if (copyArr[i] < copyArr[i - 1]) throw new AssertionError();
        }
        long end = System.nanoTime();
        System.out.println("test on built-in sort pass in "+(end - start));

        copyArr = Arrays.copyOf(arr, arraySize);
        start = System.nanoTime();
        insertionSort(copyArr);
        for (int i = 1; i < arraySize; i++) {
            if (copyArr[i] < copyArr[i - 1]) throw new AssertionError();
        }
        end = System.nanoTime();
        System.out.println("test on insertion sort pass in "+(end - start));

        copyArr = Arrays.copyOf(arr, arraySize);
        start = System.nanoTime();
        insertionSortDescending(copyArr);
        for (int i = 1; i < arraySize; i++) {
            if (copyArr[i] > copyArr[i - 1]) throw new AssertionError();
        }
        end = System.nanoTime();
        System.out.println("test on insertion descending sort pass in "+(end - start));

        copyArr = Arrays.copyOf(arr, arraySize);
        start = System.nanoTime();
        selectionSort(copyArr);
        for (int i = 1; i < arraySize; i++) {
            if (copyArr[i] < copyArr[i - 1]) throw new AssertionError();
        }
        end = System.nanoTime();
        System.out.println("test on selection sort descending sort pass in "+(end - start));

        copyArr = Arrays.copyOf(arr, arraySize);
        start = System.nanoTime();
        mergeSort(copyArr, 0, arraySize - 1);
        for (int i = 1; i < arraySize; i++) {
            if (copyArr[i] < copyArr[i - 1]) throw new AssertionError();
        }
        end = System.nanoTime();
        System.out.println("test on merge sort pass in "+(end - start));

        copyArr = Arrays.copyOf(arr, arraySize);
        start = System.nanoTime();
        mergeWithoutRecursion(copyArr);
        for (int i = 1; i < arraySize; i++) {
            if (copyArr[i] < copyArr[i - 1]) throw new AssertionError();
        }
        end = System.nanoTime();
        System.out.println("test on merge sort without recursion pass in "+(end - start));

        copyArr = Arrays.copyOf(arr, arraySize);
        start = System.nanoTime();
        mergeSortWithNonSentinelMerge(copyArr, 0, arraySize - 1);

        for (int i = 1; i < arraySize; i++) {
            if (copyArr[i] < copyArr[i - 1]) throw new AssertionError();
        }
        end = System.nanoTime();
        System.out.println("test on merge sort without sentinel pass in "+(end - start));

    }
}