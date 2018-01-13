package ua.dp.mign.recursion;

public final class MultiplicationOverAddition {

    public static long mult(int x, int y) {
        return y == 1 ? x : x + mult(x, --y);
    }

    public static void main(String[] args) {
        System.out.println("2 x 2 = " + mult(2, 2));
        System.out.println("3 x 6 = " + mult(3, 6));
        System.out.println("9 x 8 = " + mult(9, 8));
        System.out.println("25 x 24 = " + mult(25, 24));
    }
}
