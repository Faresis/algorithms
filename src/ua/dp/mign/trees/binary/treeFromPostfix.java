package ua.dp.mign.trees.binary;// tree.java

import java.io.IOException;
import java.util.Stack;

////////////////////////////////////////////////////////////////
class PostfixNode {
    public String sData;              // data item (key)
    public PostfixNode leftChild;         // this node's left child
    public PostfixNode rightChild;        // this node's right child

    public PostfixNode(char c, PostfixNode left, PostfixNode right) {
        this(c);
        this.leftChild = left;
        this.rightChild = right;
    }

    public PostfixNode(char c) {
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
class PostfixTree {
    private PostfixNode root;             // first node of tree

    // -------------------------------------------------------------
    public PostfixTree(PostfixNode node)                  // constructor
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
                inOrder(root, false);
                break;
            case 3:
                System.out.print("\nPostorder traversal: ");
                postOrder(root);
                break;
        }
        System.out.println();
    }

    // -------------------------------------------------------------
    private void preOrder(PostfixNode localRoot) {
        if (localRoot != null) {
            System.out.print(localRoot.sData + " ");
            preOrder(localRoot.leftChild);
            preOrder(localRoot.rightChild);
        }
    }

    // -------------------------------------------------------------
    private void inOrder(PostfixNode localRoot, boolean hasParent) {
        if (localRoot != null) {

            boolean parenthesis = hasParent && !Character.isLetterOrDigit(localRoot.sData.charAt(0));

            if (parenthesis) {
                System.out.print("( ");
            }
            inOrder(localRoot.leftChild, true);
            System.out.print(localRoot.sData + " ");
            inOrder(localRoot.rightChild, true);
            if (parenthesis) {
                System.out.print(") ");
            }
        }
    }

    // -------------------------------------------------------------
    private void postOrder(PostfixNode localRoot) {
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
                PostfixNode temp = (PostfixNode) globalStack.pop();
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
class PostfixTreeApp {
    public static void main(String[] args) throws IOException {
        String theString = "ABC+*";

        PostfixTree tree = buildTree(theString);
        tree.displayTree();

        tree.traverse(1);
        tree.traverse(2);
        tree.traverse(3);
    }  // end main()

    private static PostfixTree buildTree(String string) {
        Stack<PostfixNode> nodes = new Stack<>();
        for (char c : string.toCharArray()) {
            PostfixNode node = new PostfixNode(c);
            if (!Character.isLetterOrDigit(c)) {
                node.rightChild = nodes.pop();
                node.leftChild = nodes.pop();
            }
            nodes.push(node);
        }
        return new PostfixTree(nodes.pop());
    }
}  // end class TreeApp
////////////////////////////////////////////////////////////////
