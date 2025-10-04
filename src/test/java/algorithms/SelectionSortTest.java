package algorithms;

import metrics.PerformanceTracker;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class SelectionSortTest {

    //   1. Пустой массив
    @Test
    void handlesEmptyArray() {
        int[] arr = {};
        PerformanceTracker t = new PerformanceTracker();

        SelectionSort.sort(arr, t);

        assertEquals(0, arr.length, "Пустой массив должен остаться пустым");
        assertEquals(0, t.comparisons);
        assertEquals(0, t.swaps);
    }

    //  2. Один элемент
    @Test
    void handlesSingleElement() {
        int[] arr = {42};
        PerformanceTracker t = new PerformanceTracker();

        SelectionSort.sort(arr, t);

        assertArrayEquals(new int[]{42}, arr, "Один элемент не должен измениться");
        assertEquals(0, t.swaps, "Swaps не должно быть при одном элементе");
    }

    //  3. Дубликаты
    @Test
    void handlesArrayWithDuplicates() {
        int[] arr = {5, 1, 5, 3, 5, 2, 1};
        int[] expected = arr.clone();
        Arrays.sort(expected);

        PerformanceTracker t = new PerformanceTracker();
        SelectionSort.sort(arr, t);

        assertArrayEquals(expected, arr, "Массив с дубликатами должен сортироваться корректно");
        assertTrue(t.comparisons > 0);
    }

    //  4. Уже отсортированный (best case)
    @Test
    void handlesAlreadySortedArray() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        PerformanceTracker t = new PerformanceTracker();

        SelectionSort.sort(arr, t);

        assertArrayEquals(new int[]{1,2,3,4,5,6,7}, arr);
        assertTrue(t.earlyTerminations >= 1, "Должен сработать ранний выход");
    }

    //  5. Обратный порядок (worst case)
    @Test
    void handlesReverseSortedArray() {
        int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] expected = arr.clone();
        Arrays.sort(expected);

        PerformanceTracker t = new PerformanceTracker();
        SelectionSort.sort(arr, t);

        assertArrayEquals(expected, arr);
        assertTrue(t.comparisons > 0);
        assertTrue(t.swaps > 0, "Должны быть перестановки в худшем случае");
    }

    //  6. Почти отсортированный (nearly sorted)
    @Test
    void handlesNearlySortedArray() {
        int[] arr = {1, 2, 3, 5, 4, 6, 7};
        int[] expected = arr.clone();
        Arrays.sort(expected);

        PerformanceTracker t = new PerformanceTracker();
        SelectionSort.sort(arr, t);

        assertArrayEquals(expected, arr);
        assertTrue(t.comparisons > 0);
    }

    //  7. Случайный массив (average case)
    @Test
    void sortsRandomArray() {
        Random rnd = new Random(42);
        int[] arr = rnd.ints(100, -100, 100).toArray();
        int[] expected = arr.clone();
        Arrays.sort(expected);

        PerformanceTracker t = new PerformanceTracker();
        SelectionSort.sort(arr, t);

        assertArrayEquals(expected, arr, "Random array должен сортироваться корректно");
        assertTrue(t.comparisons > 0);
    }

    //  8. Большой массив для стабильности и производительности
    @Test
    void sortsLargeArray() {
        Random rnd = new Random(123);
        int[] arr = rnd.ints(5000, -1000, 1000).toArray();
        int[] expected = arr.clone();
        Arrays.sort(expected);

        PerformanceTracker t = new PerformanceTracker();
        SelectionSort.sort(arr, t);

        assertArrayEquals(expected, arr, "Большой массив должен сортироваться корректно");
        assertTrue(t.runtimeNanos == 0 || t.comparisons > 0);
    }
}
