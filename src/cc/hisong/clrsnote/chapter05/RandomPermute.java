package cc.hisong.clrsnote.chapter05;

import java.util.Arrays;
import java.util.Random;

public class RandomPermute {
    public static void randomPermute(int[] arr) {
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < arr.length; i++) {
            int cursor = random.nextInt(arr.length - i) + i;
            int tmp = arr[i];
            arr[i] = arr[cursor];
            arr[cursor] = tmp;
        }
    }

    public static void testRandomPermute(int arraySize) {
        int arr[] = (new Random(System.currentTimeMillis())).ints(arraySize).toArray();
        System.out.println(Arrays.toString(arr));
        randomPermute(arr);
        System.out.println(Arrays.toString(arr));
    }
}
