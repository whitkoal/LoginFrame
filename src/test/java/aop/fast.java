package aop;

import org.yaml.snakeyaml.util.ArrayUtils;

import java.lang.reflect.Array;
import java.util.Arrays;


public class fast {

    public static void main(String[] args) {
        int[] arr = {5, 9, 3, 7, 4, 1, 6, 0, 8, 2};
        print("开始：", arr);
        sort1(arr);
        print("结束：", arr);
    }

    public static void sort1(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0 && arr[j] < arr[j - 1]; j--) {
                swap(arr, j - 1, j);

            }
        }
    }

    private static void print(String msg, int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }


    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void sort(int[] arr) {
        int p = 0;
        int t = arr.length - 1;
        int mid = arr.length / 2;
        while (p + 1 != t) {
            if (arr[p] > arr[mid] && arr[t] < arr[mid]) {
                int temp = arr[p];
                arr[p] = arr[t];
                arr[t] = temp;
            } else if (arr[p] <= arr[mid]) {
                p++;
            } else if (arr[t] >= arr[mid]) {
                t--;

            } else {
                p++;
                t--;
            }

        }

    }

}
