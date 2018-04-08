package ua.dp.mign.trees.twoThree;

import java.io.IOException;

////////////////////////////////////////////////////////////////
class MinTree23App {
    public static void main(String[] args) throws IOException {
        long value;
        Tree23 theTree = new Tree23();

        theTree.insert(50);
        theTree.insert(40);
        theTree.insert(60);
        theTree.insert(30);
        theTree.insert(70);

        theTree.displayTree();

        System.out.println("Min value: " + theTree.min());
        theTree.insert(20);
        System.out.println("Min value: " + theTree.min());
        theTree.insert(19);
        System.out.println("Min value: " + theTree.min());
        theTree.insert(18);
        System.out.println("Min value: " + theTree.min());
        theTree.insert(17);
        System.out.println("Min value: " + theTree.min());
        theTree.insert(16);
        System.out.println("Min value: " + theTree.min());
        theTree.insert(15);
        System.out.println("Min value: " + theTree.min());
        theTree.insert(14);
        System.out.println("Min value: " + theTree.min());
        theTree.insert(6);
        System.out.println("Min value: " + theTree.min());
        theTree.insert(7);
        System.out.println("Min value: " + theTree.min());
        theTree.insert(8);
        System.out.println("Min value: " + theTree.min());
        theTree.insert(9);
        System.out.println("Min value: " + theTree.min());
        theTree.insert(10);
        System.out.println("Min value: " + theTree.min());
        theTree.insert(11);
        System.out.println("Min value: " + theTree.min());
        theTree.insert(12);
        System.out.println("Min value: " + theTree.min());
        theTree.insert(2);
        System.out.println("Min value: " + theTree.min());
    }  // end main()
}  // end class Tree234App
////////////////////////////////////////////////////////////////

