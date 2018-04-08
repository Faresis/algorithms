package ua.dp.mign.trees.twoThree;

import java.io.IOException;

////////////////////////////////////////////////////////////////
class InsertTree23App {
    public static void main(String[] args) throws IOException {
        Tree23 theTree = new Tree23();

        theTree.insert(10);
        theTree.insert(20);
        theTree.insert(30);
        theTree.insert(40);
        theTree.insert(50);
        theTree.insert(60);
        theTree.insert(70);
        theTree.insert(80);
        theTree.insert(90);

        theTree.displayTree();
    }  // end main()
}  // end class Tree234App
////////////////////////////////////////////////////////////////

