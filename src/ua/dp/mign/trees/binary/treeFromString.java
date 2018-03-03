package ua.dp.mign.trees.binary;// tree.java

import java.io.IOException;
import java.util.Stack;

////////////////////////////////////////////////////////////////
class Node {
    public String sData;              // data item (key)
    public Node leftChild;         // this node's left child
    public Node rightChild;        // this node's right child

    public Node(char c, Node left, Node right) {
        this(c);
        this.leftChild = left;
        this.rightChild = right;
    }

    public Node(char c) {
        this.sData = String.valueOf(c);
    }

    public void displayNode()      // display ourself
    {
        System.out.print('{');
        System.out.print(sData);
        System.out.print("} ");
    }
}  // end class Node

////////////////////////////////////////////////////////////////
class Tree {
    private Node root;             // first node of tree

    // -------------------------------------------------------------
    public Tree(Node node)                  // constructor
    {
        root = node;
    }            // no nodes in tree yet

    // -------------------------------------------------------------
    public void traverse(int traverseType) {
        switch (traverseType) {
            case 1:
                System.out.print("\nPreorder traversal: ");
                preOrder(root);
                break;
            case 2:
                System.out.print("\nInorder traversal:  ");
                inOrder(root);
                break;
            case 3:
                System.out.print("\nPostorder traversal: ");
                postOrder(root);
                break;
        }
        System.out.println();
    }

    // -------------------------------------------------------------
    private void preOrder(Node localRoot) {
        if (localRoot != null) {
            System.out.print(localRoot.sData + " ");
            preOrder(localRoot.leftChild);
            preOrder(localRoot.rightChild);
        }
    }

    // -------------------------------------------------------------
    private void inOrder(Node localRoot) {
        if (localRoot != null) {
            inOrder(localRoot.leftChild);
            System.out.print(localRoot.sData + " ");
            inOrder(localRoot.rightChild);
        }
    }

    // -------------------------------------------------------------
    private void postOrder(Node localRoot) {
        if (localRoot != null) {
            postOrder(localRoot.leftChild);
            postOrder(localRoot.rightChild);
            System.out.print(localRoot.sData + " ");
        }
    }

    // -------------------------------------------------------------
    public void displayTree() {
        Stack globalStack = new Stack();
        globalStack.push(root);
        int nBlanks = 32;
        boolean isRowEmpty = false;
        System.out.println(
                "......................................................");
        while (isRowEmpty == false) {
            Stack localStack = new Stack();
            isRowEmpty = true;

            for (int j = 0; j < nBlanks; j++)
                System.out.print(' ');

            while (globalStack.isEmpty() == false) {
                Node temp = (Node) globalStack.pop();
                if (temp != null) {
                    System.out.print(temp.sData);
                    localStack.push(temp.leftChild);
                    localStack.push(temp.rightChild);

                    if (temp.leftChild != null ||
                            temp.rightChild != null)
                        isRowEmpty = false;
                } else {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < nBlanks * 2 - 2; j++)
                    System.out.print(' ');
            }  // end while globalStack not empty
            System.out.println();
            nBlanks /= 2;
            while (localStack.isEmpty() == false)
                globalStack.push(localStack.pop());
        }  // end while isRowEmpty is false
        System.out.println(
                "......................................................");
    }  // end displayTree()
// -------------------------------------------------------------
}  // end class Tree

////////////////////////////////////////////////////////////////
class TreeApp {
    public static void main(String[] args) throws IOException {
        String theString = "ABCDEFGHIJ";

        Tree tree = buildTree(theString);
        tree.displayTree();
    }  // end main()

    private static Tree buildTree(String string) {
        Node root = new Node(string.charAt(0));
        initNodesRec(root, string.toCharArray(), 1);
        return new Tree(root);
    }

    private static void initNodesRec(Node node, char[] chars, int num) {
        int rightIdx = 2 * num;
        int leftIdx = rightIdx - 1;
        if (chars.length > leftIdx) {
            Node left = new Node(chars[leftIdx]);
            node.leftChild = left;
            initNodesRec(left, chars, ++leftIdx);
        }
        if (chars.length > rightIdx) {
            Node right = new Node(chars[rightIdx]);
            node.rightChild = right;
            initNodesRec(right, chars, ++rightIdx);
        }
    }
}  // end class TreeApp
////////////////////////////////////////////////////////////////
