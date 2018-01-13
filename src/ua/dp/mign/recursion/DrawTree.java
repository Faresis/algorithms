package ua.dp.mign.recursion;

public final class DrawTree {
    public static void makeBranches(char[][] arr) {
        makeBranchesRec(arr, 0, arr[0].length, 0);
    }

    private static void makeBranchesRec(char[][] arr, int left, int right, int row) {
        int middle = (right + left) / 2;
        arr[row][middle] = 'X';

        if (right - left <= 1) {
            return;
        }

        makeBranchesRec(arr, left, middle, row + 1);
        makeBranchesRec(arr, middle, right, row + 1);
    }

    private static void display(char[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }

    private static void fill(char[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = '-';
            }
        }
    }

    public static void main(String[] args) {
        int rowLength = 64;
        int levels = (int) (Math.log(rowLength) / Math.log(2)) + 1;

        char[][] arr = new char[levels][rowLength];

        fill(arr);
        System.out.println("Empty array:");
        display(arr);
        makeBranches(arr);
        System.out.println("Array with branches:");
        display(arr);
    }
}
