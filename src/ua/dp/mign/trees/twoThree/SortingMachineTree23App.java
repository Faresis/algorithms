package ua.dp.mign.trees.twoThree;

import java.io.IOException;
import java.util.Arrays;
import java.util.function.LongConsumer;

////////////////////////////////////////////////////////////////
class SortingMachineTree23App {
    public static void main(String[] args) throws IOException {
        long[] array = {50, 40, 60, 30, 70, 20, 19, 18, 17, 16, 15, 14, 6, 7, 8, 9, 10, 11, 12, 2};
        System.out.println(Arrays.toString(array));
        sort(array);
        System.out.println(Arrays.toString(array));
    }  // end main()

    private static void sort(long[] array) {
        Tree23 tree23 = new Tree23();
        tree23.addAll(array);
        tree23.inOrder(new LongConsumer() {
            int cnt = 0;
            @Override
            public void accept(long value) {
                array[cnt++] = value;
            }
        });
    }
}  // end class Tree234App
////////////////////////////////////////////////////////////////

