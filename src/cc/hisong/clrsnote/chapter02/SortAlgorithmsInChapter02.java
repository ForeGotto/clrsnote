package cc.hisong.clrsnote.chapter02;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Random;

public class SortAlgorithmsInChapter02 {

    /**
     * insertion sort algorithm in chapter 2 of clrs
     *
     * @param arr an array of integer which is to be sorted
     */
    public static void insertionSort(int[] arr) {
        int length = arr.length;
        for (int i = 1, key; i < length; i++) {
            key = arr[i];
            int j = i - 1;
            while (j >= 0 && key < arr[j]) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    /**
     * exercise 2.1-2 in chapter of clrs
     *
     * @param arr an array of integer which is to be sorted
     */
    public static void insertionSortDescending(int[] arr) {
        int length = arr.length;
        for (int i = 1, key; i < length; i++) {
            key = arr[i];
            int j = i - 1;
            while (j >= 0 && key > arr[j]) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    /**
     * exercise 2.1-3 in chapter of clrs
     *
     * @param arr   the array to search in
     * @param value the value to search
     * @return if the key value is in the array specified to search, return it's index;
     * @return if not, return null
     */
    @Nullable
    @Contract(pure = true)
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
        System.out.println("test on linear search pass in " + (end - start));
    }

    /**
     * exercise 2.2-2 in chapter 2 of clrs
     *
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
     * use sentinel in the procedure of merge
     *
     * @param arr     the array to be sorted
     * @param left    the start index of left array
     * @param leftEnd the end index of left array
     * @param right   the end index of right array
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
     * with recursion
     * use sentinel in the procedure of merge
     *
     * @param arr   the array to be sorted
     * @param left  the start of sort area
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

    /**
     * sub procedure for bottom-up merge sort
     *
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

    /**
     * bottom-up merge sort
     *
     * @param arr
     */
    public static void mergeWithoutRecursion(int[] arr) {
        int length = arr.length;
        for (int dis = 1; dis < length; dis = (dis << 1)) {
            mergePass(arr, dis);
        }
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

    /**
     * exercise 2.3-2 in chapter 2 of clrs
     *
     * @param arr
     * @param left
     * @param right
     */
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

    /**
     * sub procedure of exercise 2.3-4
     *
     * @param arr
     * @param index
     */
    private static void insert(int[] arr, int index) {
        int key = arr[index];
        int i = index - 1;
        while (i >= 0 && key < arr[i]) {
            arr[i + 1] = arr[i];
            i--;
        }
        arr[i + 1] = key;
    }

    /**
     * exercise 2.3-4 in chapter 2 of clrs
     *
     * @param arr
     * @param index
     */
    private static void insertionSortWithRecursion(int[] arr, int index) {
        if (index > 0) {
            insertionSortWithRecursion(arr, index - 1);
            insert(arr, index);
        }
    }

    /**
     * exercise 2.3-5 in chapter 2 of clrs,
     * also used as a sub procedure in exercise 2.3-6;
     * if there are more than one position in the array with value key,
     * we can't promise which one to return,
     * yet we can modify the statements in the first branch of if-else clauses to
     * determine which one to return
     * @param arr the array to search
     * @param from the start index of the range to search
     * @param to the end index of the range to search
     * @param key the value to search
     * @return if key is in the range specified by the args, the return it's index;
     * @return if not, return the index the key value is supposed to be placed
     *
     */
    public static int binarySearch(int[] arr, int from, int to, int key) {
        if (arr[from] > key) {
            return from;
        } else if (arr[to] < key) {
            return to + 1;
        } else {
            int left = from, right = to;
            while (left <= right) {

                int middle = (left + right) / 2;
                //System.out.println(left+" "+middle+" "+right);
                if (arr[middle] == key) {
//                    while (arr[middle] == key) {
//                        middle++;
//                    }
//                    return middle - 1;
                    return middle;
                } else if (arr[middle] < key) {
                    left = middle + 1;
                } else {
                    right = middle - 1;
                }
            }

            return left;
        }
    }

    public static void testBinarySearch(int arraySize) {
        int[] arr = (new Random()).ints(arraySize).toArray();
        Arrays.sort(arr);
        long start, end;
        start = System.nanoTime();


        for (int i = 0; i < arraySize; i++) {
            //System.out.println(i);
            int index = binarySearch(arr, 0, arraySize - 1, arr[i]);
            if (index != i) {
                throw new AssertionError();
            }
        }

        end = System.nanoTime();
        System.out.println("test on binary search pass in " + (end - start));
    }

    /**
     * exercise 2.3-6 in chapter 2 of clrs
     * use binary search to find where to insert the key instead of traversal
     * yet it's still an O(N^2) method because you can't insert a key with only one copy
     * @param arr the array to be sorted
     */
    public static void insertionSortWithBinarySearch(int[] arr) {

        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int cursor = binarySearch(arr, 0, i - 1, key);
//            System.arraycopy(arr, cursor, arr, cursor + 1, i - cursor);
            for (int j = i; j > cursor; j--) {
                arr[j] = arr[j - 1];
            }
            arr[cursor] = key;
        }
    }

    /**
     * exercise 2.3-7 in chapter 2 of clrs
     * find if there exists two elements in arr whose sum is x
     * it's an O(N*lg(N)) method because merge sort's time is in O(N*lg(N)),
     * and binary is in O(lg(N)), and we sort the array one time and search it
     * no more than N times
     * @param arr the array to search
     * @param x the sum to search
     * @return if there exists two elements in arr whose sum is x, return true;
     * @return if not, return false
     */
    public static boolean isSumOfTwoElements(int[] arr, int x) {
        Arrays.sort(arr);
        for (int i = arr.length - 1; i >= 0; i--) {
            int cursor = Arrays.binarySearch(arr, 0, i, x-arr[i]);
            if (cursor > -1) {
                System.out.printf("%d+%d=%d", arr[i], arr[cursor], x);
                return true;
            }
        }
        return false;
    }

    /**
     * sub procedure of problem 2.4 in clrs
     * modified from merge method of merge sort
     * uses the following feature:
     * if inversions exist between left part and right part of the array,
     * then the amount of inversions related to the element(denoted by "key")in the left part
     * is the amount of elements from right part which are already in the final array
     * when "key" is put into the final array
     *
     * @param arr the array to search
     * @param from the start index of the left part
     * @param middle the end index of the left part and the start(exclusive) index of right
     * @param to the end index of
     * @return amount of inversions between left part and right part
     */
    public static int mergeInversion(int[] arr, int from, int middle, int to) {
        int lefLength = middle - from + 1;
        int rightLength = to - middle;
        int[] leftArr = Arrays.copyOfRange(arr, from, middle + 2);
        leftArr[lefLength] = Integer.MAX_VALUE;
        int[] rightArr = Arrays.copyOfRange(arr, middle + 1, to + 2);
        rightArr[rightLength] = Integer.MAX_VALUE;
        int inversions = 0;
        for (int k = from, i = 0, j = 0; k <= to; k++) {
            if (leftArr[i] > rightArr[j]) {
                arr[k] = rightArr[j];
                j++;
            } else {
                arr[k] = leftArr[i];
                i++;
                inversions += j;
            }
        }

        return inversions;
    }

    /**
     * problem 2.4 in chapter 2 of clrs
     * calculate the amount of inversions in the array
     * it's modified from recursive merge sort, which means it's an O(N*lg(N)) method
     * and it will sort the array while searching
     * can be used in eight-digit puzzles to determine whether the case is solvable
     *
     * @param arr the array to search in
     * @param from the start index to search
     * @param to the end index to search
     * @return amount of inversions in arr
     */
    public static int inversions(int[] arr, int from, int to) {
        if (from == to) {
            return 0;
        }
        int middle = (from + to) / 2;
        int leftInversions = inversions(arr, from, middle);
        int rightInversions = inversions(arr, middle + 1, to);
        int middleInversions = mergeInversion(arr, from, middle, to);
        return leftInversions + rightInversions + middleInversions;
    }

    /**
     * calculate the inversions of the given array
     * it's an O(N^2) method
     *
     * @param arr the array to search
     * @return the amount of inversions in arr
     */
    public static int inversionAnswer(int[] arr) {
        int inversions = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    inversions++;
                }
            }
        }
        return inversions;
    }

    public static void testInversionMethod(int arraySize) {
        int[] arr = (new Random()).ints(arraySize).toArray();
        long start, end;
        int[] copyArr = Arrays.copyOf(arr, arraySize);

        start = System.nanoTime();
        int inversionWithMerge = inversions(copyArr, 0, arraySize - 1);
        end = System.nanoTime();
        StringBuilder stringBuilder =
                new StringBuilder("test on inversions pass in " + (end - start));

        start = System.nanoTime();
        int inversionWithoutMerge = inversionAnswer(arr);
        end = System.nanoTime();
        stringBuilder.append("\nwith O(N^2) method pass in "+ (end - start));
        if (inversionWithMerge != inversionWithoutMerge) throw new AssertionError();
        else System.out.println(stringBuilder);
    }

    public static void testAllSortMethods(int arraySize) {
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
        mergeSort(copyArr, 0, arraySize - 1);
        end = System.nanoTime();
        checkIfSortedAscending(copyArr);
        System.out.println("test on merge sort pass in " + (end - start));

        copyArr = Arrays.copyOf(arr, arraySize);
        start = System.nanoTime();
        mergeWithoutRecursion(copyArr);
        end = System.nanoTime();
        checkIfSortedAscending(copyArr);
        System.out.println("test on merge sort without recursion pass in " + (end - start));

        copyArr = Arrays.copyOf(arr, arraySize);
        start = System.nanoTime();
        mergeSortWithNonSentinelMerge(copyArr, 0, arraySize - 1);
        end = System.nanoTime();
        checkIfSortedAscending(copyArr);
        System.out.println("test on merge sort without sentinel pass in " + (end - start));

        copyArr = Arrays.copyOf(arr, arraySize);
        start = System.nanoTime();
        selectionSort(copyArr);
        end = System.nanoTime();
        checkIfSortedAscending(copyArr);
        System.out.println("test on selection sort pass in " + (end - start));

        copyArr = Arrays.copyOf(arr, arraySize);
        start = System.nanoTime();
        insertionSort(copyArr);
        end = System.nanoTime();
        checkIfSortedAscending(copyArr);
        System.out.println("test on insertion sort pass in " + (end - start));

        copyArr = Arrays.copyOf(arr, arraySize);
        start = System.nanoTime();
        insertionSortDescending(copyArr);
        checkIfSortedDescending(copyArr);
        end = System.nanoTime();
        System.out.println("test on insertion descending sort pass in " + (end - start));

        copyArr = Arrays.copyOf(arr, arraySize);
        start = System.nanoTime();
        insertionSortWithRecursion(copyArr, arraySize - 1);
        end = System.nanoTime();
        checkIfSortedAscending(copyArr);
        System.out.println("test on insertion sort with recursion pass in " + (end - start));

        copyArr = Arrays.copyOf(arr, arraySize);
        start = System.nanoTime();
        insertionSortWithBinarySearch(copyArr);
        end = System.nanoTime();
        checkIfSortedAscending(copyArr);
        System.out.println("test on insertion sort with binary search pass in " + (end - start));

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
