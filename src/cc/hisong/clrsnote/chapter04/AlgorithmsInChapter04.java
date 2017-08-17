package cc.hisong.clrsnote.chapter04;

import java.util.Random;

public class AlgorithmsInChapter04 {

    /**
     * exercise 4.1-2 in chapter 4 of clrs
     * return sub array which has biggest sum
     * brute force method of O(N^2)
     *
     * @param arr the array to search
     * @return the sub array which has biggest sum
     */
    public static SubArray maxSubArrayBruteForce(int[] arr) {
        int from = 0, to = 0, max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                if (sum > max) {
                    from = i;
                    to = j;
                    max = sum;
                }
            }
        }
        return new SubArray(from, to, max);
    }

    /**
     * sub procedure of exercise 4.1-3
     * return the sub array which crosses two parts and has biggest sum
     *
     * @param arr    the array to search
     * @param from   left origin
     * @param middle left bound and one element before right origin
     * @param to     right bound
     * @return the sub array which crosses two parts and has biggest sum
     */
    public static SubArray maxCrossingSubArray(int[] arr, int from, int middle, int to) {

        int leftSum = Integer.MIN_VALUE, left = middle;
        for (int i = middle, sum = 0; i >= from; i--) {
            sum += arr[i];
            if (sum > leftSum) {
                left = i;
                leftSum = sum;
            }
        }

        int rightSum = Integer.MIN_VALUE, right = middle + 1;
        for (int i = middle + 1, sum = 0; i <= to; i++) {
            sum += arr[i];
            if (sum > rightSum) {
                right = i;
                rightSum = sum;
            }
        }

        return new SubArray(left, right, leftSum + rightSum);
    }

    /**
     * exercise 4.1-3 of chapter 4 of clrs
     * return sub array which has biggest sum
     * recursive method of O(N*log(N))
     *
     * @param arr  the array to search
     * @param from origin of searching area
     * @param to   bound of searching area
     * @return the sub array which has biggest sum
     */
    public static SubArray maxSubArrayRecursive(int[] arr, int from, int to) {
        if (from == to) {
            return new SubArray(from, to, arr[from]);
        }
        int middle = (from + to) / 2;
        SubArray leftArray = maxSubArrayRecursive(arr, from, middle);
        SubArray rightArray = maxSubArrayRecursive(arr, middle + 1, to);
        SubArray middleArray = maxCrossingSubArray(arr, from, middle, to);

        if (leftArray.sum > rightArray.sum && leftArray.sum > middleArray.sum) {
            return leftArray;
        } else if (middleArray.sum > leftArray.sum && middleArray.sum > rightArray.sum) {
            return middleArray;
        } else {
            return rightArray;
        }
    }

    /**
     * exercise 4.1-3 of chapter 4 in clrs
     * get the cross point of two methods\
     * the return value is usually around 30 or equals to 2 or 3
     *
     * @return the cross point of two methods
     */
    public static int getCrossPoint() {
        for (int i = 2; ; i++) {
            long start, end, bruteTime, recursiveTime;
            int[] arr = (new Random(System.nanoTime())).
                    ints(i, -10000, 10000).toArray();
            start = System.nanoTime();
            maxSubArrayBruteForce(arr);
            end = System.nanoTime();
            bruteTime = end - start;
            start = System.nanoTime();
            maxSubArrayRecursive(arr, 0, i - 1);
            end = System.nanoTime();
            recursiveTime = end - start;

            if (Math.abs(recursiveTime - bruteTime) < 1000) {
                return i;
            }
        }
    }

    /**
     * exercise 4.1-5 in chapter of clrs
     * return sub array which has biggest sum
     * linear method of O(N)
     *
     * @param arr
     * @return
     */
    public static SubArray maxSubArrayLinear(int[] arr) {

        int left = 0, right = 0, max = Integer.MIN_VALUE, curLeft = 0, sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (sum > max) {
                max = sum;
                left = curLeft;
                right = i;
            }
            if (sum < 0) {
                curLeft = i + 1;
                sum = 0;
            }
        }

        return new SubArray(left, right, max);
    }

    public static void testMaxSum(int arraySize) {
        int[] arr = (new Random(10000)).
                ints(arraySize, -10000, 10000).
                toArray();
        long start, end;

        StringBuilder stringBuilder = new StringBuilder();
        start = System.nanoTime();
        SubArray answer = maxSubArrayBruteForce(arr);
        end = System.nanoTime();
        stringBuilder.append("brute max sum method pass in " + (end - start));
        start = System.nanoTime();
        SubArray recursiveAnswer = maxSubArrayRecursive(arr, 0, arraySize - 1);
        end = System.nanoTime();
        stringBuilder.append("\nrecursive max sum method pass in " + (end - start));
        start = System.nanoTime();
        SubArray linearAnswer = maxSubArrayLinear(arr);
        end = System.nanoTime();
        stringBuilder.append("\nlinear max sum method pass in " + (end - start));

        if (!answer.equals(recursiveAnswer)) {
            throw new AssertionError("recursive max sum method fail");
        } else if (!answer.equals(linearAnswer)) {
            throw new AssertionError("linear max sum method fail");
        } else {
            System.out.println(stringBuilder);
        }
    }


}
