package ua.dp.mign.recursion;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class KnapsackProblem {

    public static List<Integer> chooseItems(int targetSize, int[] items) {
        return choose(targetSize, items, 0);
    }

    private static List<Integer> choose(int targetSize, int[] items, int i) {
        if (i >= items.length) {
            System.out.println("No more items.");
            return Collections.emptyList();
        }

        int current = items[i];
        if (targetSize == current) {
            System.out.printf("Target %d, %d is just right. Success!\n", targetSize, current);

            return Stream.of(current).collect(Collectors.toList());
        }

        if (targetSize > current) {
            System.out.printf("Target %d, %d is too small.\n", targetSize, current);
            List<Integer> chosen = choose(targetSize - current, items, i + 1);
            if (!chosen.isEmpty()) {
                chosen.add(current);
                return chosen;
            }
        } else {
            System.out.printf("Target %d, %d is too big.\n", targetSize, current);
        }

        return choose(targetSize, items, i + 1);
    }

    public static void main(String[] args) {
        int targetSize = 20;
        int[] items = { 11, 8, 7, 6, 5 };

        List<Integer> ints = chooseItems(targetSize, items);

        if (!ints.isEmpty()) {
            System.out.println("Items selected: " + ints);
        } else {
            System.out.println("No combinations found.");
        }
    }
}
