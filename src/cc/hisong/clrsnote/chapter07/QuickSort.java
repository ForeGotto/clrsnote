package cc.hisong.clrsnote.chapter07;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {
    public static int partition(int[] arr, int p, int r) {
        int pivot = arr[r];
        int result = p - 1;
        for (int i = p; i < r; i++) {
            if (arr[i] < pivot) {
                result++;
                int temp = arr[i];
                arr[i] = arr[result];
                arr[result] = temp;
            }
        }
        int temp = arr[result+1];
        arr[result+1] = arr[r];
        arr[r] = temp;
        return result + 1;
    }

    public static void quickSort(int[] arr, int p, int r) {
        if (p < r) {
            int q = partition(arr, p, r);
            quickSort(arr, p, q - 1);
            quickSort(arr, q, r);
        }
    }

    public static void testQuickSort(int arraySize) {
        int[] arr = (new Random()).ints(arraySize).toArray();

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
        quickSort(copyArr, 0, arraySize - 1);
        end = System.nanoTime();
        checkIfSortedAscending(copyArr);
        System.out.println("test on quick sort pass in " + (end - start));
    }

    private static void checkIfSortedAscending(int[] arr) {
        int size = arr.length;
        for (int i = 1; i < size; i++) {
            if (arr[i] < arr[i - 1])
                throw new AssertionError(String.format("%d at %d, %d at %d", arr[i], i, arr[i - 1], i - 1));
        }
    }
}
