package ua.dp.mign.trees.twoThreeFour;

import java.io.*;

////////////////////////////////////////////////////////////////
class MinTree234App {
    public static void main(String[] args) throws IOException {
        long value;
        Tree234 theTree = new Tree234();

        theTree.insert(50);
        theTree.insert(40);
        theTree.insert(60);
        theTree.insert(30);
        theTree.insert(70);

        theTree.displayTree();
    }  // end main()
}  // end class Tree234App
////////////////////////////////////////////////////////////////

