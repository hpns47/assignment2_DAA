package algorithms;

import metrics.PerformanceTracker;

public class SelectionSort {

    public static void sort(int[] arr, PerformanceTracker t) {
        if (arr == null || arr.length < 2) return;


        boolean nonDecreasing = true;
        for (int i = 1; i < arr.length; i++) {
            t.comparisons++;
            if (arr[i] < arr[i - 1]) {
                nonDecreasing = false;
                break;
            }
        }
        if (nonDecreasing) {
            t.earlyTerminations++;
            return;
        }


        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                t.comparisons++;
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                swap(arr, i, minIndex);
                t.swaps++;
            }
        }
    }

    private static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
