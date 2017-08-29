/**
 * priority queue in chapter 6
 */
package cc.hisong.clrsnote.chapter06;

import java.util.Random;

public class CPriorityQueue {
    int[] data;
    int heapSize;

    public CPriorityQueue(int size) {
        data = new int[size];
        heapSize = 0;
    }

    /**
     * insert method
     *
     * @param value
     */
    public void insert(int value) {
        checkHeapSize();
        data[heapSize] = Integer.MIN_VALUE;
        heapSize++;
        increaseToKey(heapSize - 1, value);
    }

    /**
     * part of exercise 6.5-6, mainly used to test increase without exchange
     * @param value
     */
    public void insertWithNonExchangeIncrease(int value) {
        checkHeapSize();
        data[heapSize] = Integer.MIN_VALUE;
        heapSize++;
        increaseToKeyWithoutExchange(heapSize - 1, value);
    }

    /**
     * the maximum method
     * @return
     */
    public int maximum() {
        checkIfEmpty();
        return data[0];
    }

    /**
     * return and remove the max value
     * @return
     */
    public int extractMax() {
        checkIfEmpty();
        int max = data[0];
        heapSize--;
        data[0] = data[heapSize];
        HeapSort.maxHeapIfY(data, heapSize, 0);
        return max;
    }

    /**
     * increase the value at cursor to key
     *
     * @param cursor
     * @param key
     */
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

    /**
     * increase the value at cursor to key
     * without exchange
     *
     * @param cursor
     * @param key
     */
    public void increaseToKeyWithoutExchange(int cursor, int key) {
        if (cursor < 0 || cursor >= heapSize || key < data[cursor]) {
            if (cursor < 0 || cursor >= heapSize) {
                throw new AssertionError("cursor is " + cursor);
            } else {
                throw new AssertionError("key is too small");
            }

        }
        data[cursor] = key;
        int parent = (cursor - 1) / 2;
        while (data[cursor] > data[parent]) {
            data[cursor] = data[parent];
            cursor = parent;
            parent = (cursor - 1) / 2;
        }
        data[cursor] = key;
    }

    /**
     * delete method
     * O(log(n))
     *
     * @param cursor
     */
    public void delete(int cursor) {
        checkCursor(cursor);
        if (heapSize == 0) {
            return;
        }
        heapSize--;
        data[cursor] = data[heapSize];
        HeapSort.maxHeapIfY(data, heapSize, cursor);
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

    private void checkCursor(int cursor) {
        if (cursor < 0 || cursor >= heapSize) {
            throw new AssertionError("cursor not right");
        }
    }

    public static void testPriorityQueue(int arraySize) {
        CPriorityQueue queue = new CPriorityQueue(arraySize);
        int[] arr = (new Random()).ints(arraySize).toArray();
        for (int i : arr) {
            queue.insert(i);
            HeapSort.checkIfMaxHeap(queue.data, queue.heapSize);
        }

        queue.extractMax();
        HeapSort.checkIfMaxHeap(queue.data, queue.heapSize);

        for (int i = queue.heapSize - 1; i >= 0; i--) {
            queue.delete(i);
            HeapSort.checkIfMaxHeap(queue.data, queue.heapSize);
        }

        System.out.println(queue.heapSize);
        for (int i : arr) {
            queue.insertWithNonExchangeIncrease(i);
            HeapSort.checkIfMaxHeap(queue.data, queue.heapSize);
        }
    }

}
