package com.example.jant.canvas;

/**
 * Created by Jant on 2017/2/15.
 * 这个是一些排序的算法
 */

public class JavaClass {


    public static void main(String arg[]) {
        System.out.print("hello java ");

        int[] arr = {2, 5, 3, 4, 1};
        insertion_sort2(arr);

        for (int i = 0; i < arr.length;i++) {

            System.out.print(arr[i]);
        }
    }


    public static void insertion_sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i ; j >= 0; j--) {
                if (arr[j] <= arr[j+1])
                    break;
                int temp = arr[j+1];
                arr[j+1] = arr[j];
                arr[j] = temp;
            }
        }
    }

    public static void insertion_sort1(int[] arr)
    {
        for (int i = 1; i < arr.length; i++ ) {
            int temp = arr[i];
            for (int j = i ; j > 0 && arr[j-1] > temp; j-- ) {
                arr[j] = arr[j-1];
                arr[j-1] = temp;
            }
        }
    }
    public static void insertion_sort2(int[] arr)
    {
        for (int i = 0; i < arr.length; i++ ) {
            for (int j = 0 ; j < arr.length - i-1; j++ ) {

                if (arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;

                }
            }
        }
    }

    public static void insertion_sort3(int[] arr)
    {
        for (int i = 0; i < arr.length; i++ ) {
            for (int j = 0 ; j < arr.length - i-1; j++ ) {

                if (arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;

                }
            }
        }
    }

}
