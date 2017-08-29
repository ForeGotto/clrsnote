/**
 * some algorithms from clrs, either in the text or exercises or problems
 */
package cc.hisong.clrsnote.chapter06;

import java.util.Arrays;
import java.util.Random;

public class HeapSort {

    /**
     * treat the given array as a binary tree and make element at cursor larger than it's children
     * used to build a max heap
     *
     * @param arr    the given array to build a min heap on
     * @param cursor the root of the sub-heap or the sub-tree
     */
    public static void maxHeapIfY(int[] arr, int heapSize, int cursor) {
        int max = cursor;
        int left = cursor * 2 + 1;
        int right = left + 1;

        if (left < heapSize && arr[max] < arr[left]) {
            max = left;
        }

        if (right < heapSize && arr[max] < arr[right]) {
            max = right;
        }
        if (cursor != max) {
            int temp = arr[max];
            arr[max] = arr[cursor];
            arr[cursor] = temp;
            maxHeapIfY(arr, heapSize, max);
        }
    }

    /**
     * build a max heap on the given array
     *
     * @param arr the array to build a max heap on
     */
    public static void buildMaxHeap(int[] arr) {
        for (int i = (arr.length) / 2 - 1; i >= 0; i--) {
            maxHeapIfY(arr, arr.length, i);
        }
    }

    /**
     * treat the given array as a binary tree and make element at cursor larger than it's children
     * used to build a max heap
     * non recursive
     *
     * @param arr    the given array to build a min heap on
     * @param cursor the root of the sub-heap or the sub-tree
     */
    public static void maxHeapIfYNonRecursion(int[] arr, int heapSize, int cursor) {
        int left, right, max = cursor;
        while (max <= heapSize / 2 - 1) {
            left = max * 2 + 1;
            right = left + 1;
            if (left < heapSize && arr[max] < arr[left]) {
                max = left;
            }
            if (right < heapSize && arr[max] < arr[right]) {
                max = right;
            }
            if (cursor != max) {
                int temp = arr[max];
                arr[max] = arr[cursor];
                arr[cursor] = temp;
                cursor = max;
            } else {
                return;
            }
        }
    }

    /**
     * build a max heap on the given array
     * use non recursive sub procedure
     *
     * @param arr the array to build a max heap on
     */
    public static void buildMaxHeapNonRecursion(int[] arr) {
        for (int i = (arr.length) / 2 - 1; i >= 0; i--) {
            maxHeapIfYNonRecursion(arr, arr.length, i);
        }
    }

    public static void checkIfMaxHeap(int[] arr, int heapSize) {
        for (int i = heapSize / 2 - 1; i >= 0; i--) {
            int left = i * 2 + 1;
            int right = left + 1;
            if (left < heapSize && arr[left] > arr[i]) {
                throw new AssertionError(String.format("father at %d is %d, child at %d is %d", i, arr[i], left, arr[left]));
            }
            if (right < heapSize && arr[right] > arr[i]) {
                throw new AssertionError(String.format("father at %d is %d, child at %d is %d", i, arr[i], left, arr[left]));
            }
        }
    }

    /**
     * treat the given array as a binary tree and and make element at cursor smaller than it's children
     * used to build a min heap
     *
     * @param arr    the given array to build a min heap on
     * @param cursor the root of the sub-heap or the sub-tree
     */
    public static void minHeapIfY(int[] arr, int heapSize, int cursor) {
        int min = cursor;
        int left = cursor * 2 + 1;
        int right = left + 1;
        if (left < heapSize && arr[min] > arr[left]) {
            min = left;
        }

        if (right < heapSize && arr[min] > arr[right]) {
            min = right;
        }

        if (cursor != min) {
            int temp = arr[min];
            arr[min] = arr[cursor];
            arr[cursor] = temp;
            minHeapIfY(arr, heapSize, min);
        }
    }

    /**
     * build a min-heap on the given array
     *
     * @param arr the array to build a min heap on
     */
    public static void buildMinHeap(int[] arr) {
        for (int i = (arr.length) / 2 - 1; i >= 0; i--) {
            minHeapIfY(arr, arr.length, i);
        }
    }

    public static void checkIfMinHeap(int[] arr) {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            int left = i * 2 + 1;
            int right = left + 1;
            if (left < arr.length && arr[left] < arr[i]) {
                throw new AssertionError(String.format("father at %d is %d, child at %d is %d", i, arr[i], left, arr[left]));
            }
            if (right < arr.length && arr[right] < arr[i]) {
                throw new AssertionError(String.format("father at %d is %d, child at %d is %d", i, arr[i], right, arr[right]));
            }
        }
    }


    public static void testBuildHeap(int arraySize) {
        int[] arr = (new Random()).ints(arraySize).toArray();

        int[] copyArr;

        long start, end;

        copyArr = Arrays.copyOf(arr, arraySize);
        start = System.nanoTime();
        buildMaxHeap(copyArr);
        end = System.nanoTime();
        checkIfMaxHeap(copyArr, arraySize);
        System.out.println("test on max-heap building pass in " + (end - start));

        copyArr = Arrays.copyOf(arr, arraySize);
        start = System.nanoTime();
        buildMinHeap(copyArr);
        end = System.nanoTime();
        checkIfMinHeap(copyArr);
        System.out.println("test on min-heap building pass in " + (end - start));

        copyArr = Arrays.copyOf(arr, arraySize);
        start = System.nanoTime();
        buildMaxHeapNonRecursion(copyArr);
        end = System.nanoTime();
        checkIfMaxHeap(copyArr, arraySize);
        System.out.println("test on max-heap building non recursive pass in " + (end - start));
    }

    /**
     * heap sort
     *
     * @param arr the array to be sorted
     */
    public static void heapSortAscending(int[] arr) {
        buildMaxHeap(arr);
        for (int i = arr.length - 1; i > 0; i--) {
            int temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            maxHeapIfY(arr, i, 0);
        }

    }

    public static void testHeapSort(int arraySize) {
        int[] arr = (new Random()).ints(arraySize, -10000, 10000).toArray();

        int[] copyArr;

        long start, end;

        copyArr = Arrays.copyOf(arr, arraySize);
        start = System.nanoTime();
        Arrays.sort(copyArr);
        end = System.nanoTime();
        checkIfSortedAscending(copyArr);
        System.out.println("test on built-in sort pass in " + (end - start));

        copyArr = Arrays.copyOf(arr, arraySize);
        start = System.nanoTime();
        heapSortAscending(copyArr);
        end = System.nanoTime();
        checkIfSortedAscending(copyArr);
        System.out.println("test on heap sort pass in " + (end - start));
    }

    private static void checkIfSortedAscending(int[] arr) {
        int size = arr.length;
        for (int i = 1; i < size; i++) {
            if (arr[i] < arr[i - 1])
                throw new AssertionError(String.format("%d at %d, %d at %d", arr[i], i, arr[i - 1], i - 1));
        }
    }

    private static void checkIfSortedDescending(int[] arr) {
        int size = arr.length;
        for (int i = 1; i < size; i++) {
            if (arr[i] > arr[i - 1])
                throw new AssertionError(String.format("%d at %d, %d at %d", arr[i], i, arr[i - 1], i - 1));
        }
    }


}
