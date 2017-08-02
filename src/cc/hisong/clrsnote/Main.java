package cc.hisong.clrsnote;

import cc.hisong.clrsnote.chapter02.SortAlgorithmsInChapter02;

public class Main {

    public static void main(String[] args) {
	// write your code here
//        SortAlgorithmsInChapter02.testInsertionSort(10000);
//        SortAlgorithmsInChapter02.testInsertionSortDescending(10000);
//        SortAlgorithmsInChapter02.testLinearSearch(10000);
//        SortAlgorithmsInChapter02.testMergeSort(10000);
//        SortAlgorithmsInChapter02.testMergeWithoutRecursion(10000);
        SortAlgorithmsInChapter02.testAllSortMethods(1 << 16);
        System.out.println(2 << 29);
    }
}
