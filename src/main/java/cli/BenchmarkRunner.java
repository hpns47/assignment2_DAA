package cli;

import algorithms.SelectionSort;
import metrics.PerformanceTracker;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Arrays;

public class BenchmarkRunner {

    public static void main(String[] args) throws IOException {
        // --- CLI-параметры ---
        // Пример запуска:
        //   mvn exec:java -Dexec.mainClass="cli.BenchmarkRunner" -Dexec.args="100 1000 5000"
        // или
        //   java -cp target/classes cli.BenchmarkRunner 100 1000 10000

        int[] sizes;
        if (args.length > 0) {
            sizes = Arrays.stream(args).mapToInt(Integer::parseInt).toArray();
        } else {
            // если аргументы не заданы, используем значения по умолчанию
            sizes = new int[]{100, 1000, 5000, 10000, 50000};
        }

        // --- CSV writer ---
        try (FileWriter out = new FileWriter("results_selection_sort.csv")) {
            out.write("n;runtime_ns;comparisons;swaps;earlyTerminations;allocations\n");

            for (int n : sizes) {
                int[] arr = new Random(42).ints(n, -10000, 10000).toArray();
                PerformanceTracker t = new PerformanceTracker();

                long start = System.nanoTime();
                SelectionSort.sort(arr, t);
                long end = System.nanoTime();
                t.runtimeNanos = end - start;

                out.write(String.format("%d;%d;%d;%d;%d;%d%n",
                        n, t.runtimeNanos, t.comparisons, t.swaps, t.earlyTerminations, t.allocations));

                System.out.printf(
                        "✅ n=%d | time=%.3f ms | cmp=%d | swaps=%d | earlyStop=%d%n",
                        n, t.runtimeNanos / 1_000_000.0, t.comparisons, t.swaps, t.earlyTerminations
                );
            }
        }

        System.out.println("\nResults saved → results_selection_sort.csv");
    }
}
