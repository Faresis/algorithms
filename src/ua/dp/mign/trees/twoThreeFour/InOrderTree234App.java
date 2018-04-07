package ua.dp.mign.trees.twoThreeFour;

import java.io.IOException;

////////////////////////////////////////////////////////////////
class InOrderTree234App {
    public static void main(String[] args) throws IOException {
        Tree234 theTree = new Tree234();

        theTree.insert(50);
        theTree.insert(40);
        theTree.insert(60);
        theTree.insert(30);
        theTree.insert(70);
        theTree.insert(20);
        theTree.insert(19);
        theTree.insert(18);
        theTree.insert(17);
        theTree.insert(16);
        theTree.insert(15);
        theTree.insert(14);
        theTree.insert(6);
        theTree.insert(7);
        theTree.insert(8);
        theTree.insert(9);
        theTree.insert(10);
        theTree.insert(11);
        theTree.insert(12);
        theTree.insert(2);

        theTree.inOrder(System.out::println);
    }  // end main()
}  // end class Tree234App
////////////////////////////////////////////////////////////////

