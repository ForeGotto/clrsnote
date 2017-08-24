package cc.hisong.clrsnote.chapter06;

import java.util.Arrays;
import java.util.Random;

public class PriorityQueue {
    int[] data;
    int heapSize;

    public PriorityQueue(int size) {
        data = new int[size];
        heapSize = 0;
    }

    public void insert(int value) {
        checkHeapSize();
        data[heapSize] = Integer.MIN_VALUE;
        heapSize++;
        increaseToKey(heapSize - 1, value);
    }

    public int maximum() {
        checkIfEmpty();
        return data[0];
    }

    public int extractMax() {
        checkIfEmpty();
        int max = data[0];
        heapSize--;
        data[0] = data[heapSize];
        HeapSort.maxHeapIfY(data, heapSize, 0);
        return max;
    }

    public void increaseToKey(int cursor, int key) {
        if (cursor < 0 || cursor >= heapSize || key < data[cursor]) {
            if (cursor < 0 || cursor >= heapSize) {
                throw new AssertionError("cursor is " + cursor);
            } else {
                throw new AssertionError("key is too small");
            }

        }

        data[cursor] = key;
        while (cursor > 0 && data[(cursor - 1) / 2] < data[cursor]) {
            int parent = (cursor - 1) / 2;
            int temp = data[cursor];
            data[cursor] = data[parent];
            data[parent] = temp;
            cursor = parent;
        }
    }

    private void checkHeapSize() {
        if (heapSize == data.length) {
            int[] newArr = new int[heapSize * 2];
            System.arraycopy(data, 0, newArr, 0, heapSize);
            data = newArr;
        }
    }

    private void checkIfEmpty() {
        if (data.length == 0) {
            throw new AssertionError("heap underflow");
        }
    }

    public static void testPriorityQueue(int arraySize) {
        PriorityQueue queue = new PriorityQueue(arraySize);
        int[] arr = (new Random()).ints(arraySize).toArray();
        for (int i : arr) {
            queue.insert(i);
            HeapSort.checkIfMaxHeap(Arrays.copyOfRange(queue.data, 0, queue.heapSize));
        }

        queue.extractMax();
        HeapSort.checkIfMaxHeap(Arrays.copyOfRange(queue.data, 0, queue.heapSize));

    }

}
