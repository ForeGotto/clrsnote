package cc.hisong.clrsnote;

import cc.hisong.clrsnote.chapter02.SortAlgorithmsInChapter02;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
	// write your code here
//        SortAlgorithmsInChapter02.testInsertionSort(10000);
//        SortAlgorithmsInChapter02.testInsertionSortDescending(10000);
//        SortAlgorithmsInChapter02.testLinearSearch(10000);
//        SortAlgorithmsInChapter02.testMergeSort(10000);
//        SortAlgorithmsInChapter02.testMergeWithoutRecursion(10000);
        int arraySize = 10002;
        SortAlgorithmsInChapter02.testAllSortMethods(arraySize);
        SortAlgorithmsInChapter02.testBinarySearch(arraySize);
        SortAlgorithmsInChapter02.testLinearSearch(arraySize);
    }
}
