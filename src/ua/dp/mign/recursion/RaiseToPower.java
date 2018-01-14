package ua.dp.mign.recursion;

public final class RaiseToPower {
    /**
     * Raises x to a power of y.
     * @param x number to raise to a power
     * @param y power to raise to
     * @return result of raising x to a power of y
     */
    public static int power(int x, int y) {
        if (y == 1)
            return x;

        int result = power(x * x, y / 2);
        return y % 2 == 0 ? result : x * result;
    }

    public static void main(String[] args) {
        System.out.println("2 ^ 2: " + power(2, 2));
        System.out.println("10 ^ 3: " + power(10, 3));
        System.out.println("3 ^ 18: " + power(3, 18));
        System.out.println("2 ^ 8: " + power(2, 8));
    }
}
