package ua.dp.mign.trees.heaps;

// priorityQ.java
// demonstrates priority queue
// to run this program: C>java PriorityQApp
////////////////////////////////////////////////////////////////

import java.util.Stack;

class InternalTreeNode {
    public long lData;              // data item (key)
    public InternalTreeNode leftChild;         // this node's left child
    public InternalTreeNode rightChild;        // this node's right child

    public void displayNode()      // display ourself
    {
        System.out.print('{');
        System.out.print(lData);
        System.out.print("} ");
    }
}  // end class Node

////////////////////////////////////////////////////////////////
class InternalTree {
    private InternalTreeNode root;             // first node of tree

    // -------------------------------------------------------------
    public InternalTree()                  // constructor
    {
        root = null;
    }            // no nodes in tree yet

    // -------------------------------------------------------------
    public InternalTreeNode find(int key)      // find node with given key
    {                           // (assumes non-empty tree)
        InternalTreeNode current = root;               // start at root
        while (current.lData != key)        // while no match,
        {
            if (key < current.lData)         // go left?
                current = current.leftChild;
            else                            // or go right?
                current = current.rightChild;
            if (current == null)             // if no child,
                return null;                 // didn't find it
        }
        return current;                    // found it
    }  // end find()

    // -------------------------------------------------------------
    public void insert(long id) {
        InternalTreeNode newNode = new InternalTreeNode();    // make new node
        newNode.lData = id;           // insert data
        if (root == null)                // no node in root
            root = newNode;
        else                          // root occupied
        {
            InternalTreeNode current = root;       // start at root
            InternalTreeNode parent;
            while (true)                // (exits internally)
            {
                parent = current;
                if (id < current.lData)  // go left?
                {
                    current = current.leftChild;
                    if (current == null)  // if end of the line,
                    {                 // insert on left
                        parent.leftChild = newNode;
                        return;
                    }
                }  // end if go left
                else                    // or go right?
                {
                    current = current.rightChild;
                    if (current == null)  // if end of the line
                    {                 // insert on right
                        parent.rightChild = newNode;
                        return;
                    }
                }  // end else go right
            }  // end while
        }  // end else not root
    }  // end insert()

    // -------------------------------------------------------------
    public boolean delete(int key) // delete node with given key
    {                           // (assumes non-empty list)
        InternalTreeNode current = root;
        InternalTreeNode parent = root;
        boolean isLeftChild = true;

        while (current.lData != key)        // search for node
        {
            parent = current;
            if (key < current.lData)         // go left?
            {
                isLeftChild = true;
                current = current.leftChild;
            } else                            // or go right?
            {
                isLeftChild = false;
                current = current.rightChild;
            }
            if (current == null)             // end of the line,
                return false;                // didn't find it
        }  // end while
        // found node to delete

        // if no children, simply delete it
        if (current.leftChild == null &&
                current.rightChild == null) {
            if (current == root)             // if root,
                root = null;                 // tree is empty
            else if (isLeftChild)
                parent.leftChild = null;     // disconnect
            else                            // from parent
                parent.rightChild = null;
        }

        // if no right child, replace with left subtree
        else if (current.rightChild == null)
            if (current == root)
                root = current.leftChild;
            else if (isLeftChild)
                parent.leftChild = current.leftChild;
            else
                parent.rightChild = current.leftChild;

            // if no left child, replace with right subtree
        else if (current.leftChild == null)
            if (current == root)
                root = current.rightChild;
            else if (isLeftChild)
                parent.leftChild = current.rightChild;
            else
                parent.rightChild = current.rightChild;

        else  // two children, so replace with inorder successor
        {
            // get successor of node to delete (current)
            InternalTreeNode successor = getSuccessor(current);

            // connect parent of current to successor instead
            if (current == root)
                root = successor;
            else if (isLeftChild)
                parent.leftChild = successor;
            else
                parent.rightChild = successor;

            // connect successor to current's left child
            successor.leftChild = current.leftChild;
        }  // end else two children
        // (successor cannot have a left child)
        return true;                                // success
    }  // end delete()

    // -------------------------------------------------------------
    // returns node with next-highest value after delNode
    // goes to right child, then right child's left descendents
    private InternalTreeNode getSuccessor(InternalTreeNode delNode) {
        InternalTreeNode successorParent = delNode;
        InternalTreeNode successor = delNode;
        InternalTreeNode current = delNode.rightChild;   // go to right child
        while (current != null)               // until no more
        {                                 // left children,
            successorParent = successor;
            successor = current;
            current = current.leftChild;      // go to left child
        }
        // if successor not
        if (successor != delNode.rightChild)  // right child,
        {                                 // make connections
            successorParent.leftChild = successor.rightChild;
            successor.rightChild = delNode.rightChild;
        }
        return successor;
    }

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
    private void preOrder(InternalTreeNode localRoot) {
        if (localRoot != null) {
            System.out.print(localRoot.lData + " ");
            preOrder(localRoot.leftChild);
            preOrder(localRoot.rightChild);
        }
    }

    // -------------------------------------------------------------
    private void inOrder(InternalTreeNode localRoot) {
        if (localRoot != null) {
            inOrder(localRoot.leftChild);
            System.out.print(localRoot.lData + " ");
            inOrder(localRoot.rightChild);
        }
    }

    // -------------------------------------------------------------
    private void postOrder(InternalTreeNode localRoot) {
        if (localRoot != null) {
            postOrder(localRoot.leftChild);
            postOrder(localRoot.rightChild);
            System.out.print(localRoot.lData + " ");
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
                InternalTreeNode temp = (InternalTreeNode) globalStack.pop();
                if (temp != null) {
                    System.out.print(temp.lData);
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

    public boolean isEmpty() {
        return root == null;
    }

    public long removeMax() {
        InternalTreeNode max = root;
        InternalTreeNode parent = null;

        while (max.rightChild != null) {
            parent = max;
            max = max.rightChild;
        }

        if (parent == null) {
            root = max.leftChild;
        } else {
            parent.rightChild = max.leftChild;
        }

        return max.lData;
    }

    public long getMax() {
        InternalTreeNode max = root;
        while (max.rightChild != null) {
            max = max.rightChild;
        }
        return max.lData;
    }
// -------------------------------------------------------------
}  // end class Tree

////////////////////////////////////////////////////////////////
class PriorityQueueOnTree {

    private final InternalTree tree;

    //-------------------------------------------------------------
    public PriorityQueueOnTree()          // constructor
    {
        this.tree = new InternalTree();
    }

    //-------------------------------------------------------------
    public void insert(long item)    // insert item
    {
        this.tree.insert(item);
    }  // end insert()

    //-------------------------------------------------------------
    public long remove()             // remove max item
    {
        return this.tree.removeMax();
    }

    //-------------------------------------------------------------
    public long peekMin()            // peek max item
    {
        return this.tree.getMax();
    }

    //-------------------------------------------------------------
    public boolean isEmpty()         // true if queue is empty
    {
        return this.tree.isEmpty();
    }

    //-------------------------------------------------------------
    public boolean isFull()          // true if queue is full
    {
        return false;
    }

    public void print() {
        this.tree.displayTree();
    }
    //-------------------------------------------------------------
}  // end class PriorityQ

////////////////////////////////////////////////////////////////
class PriorityQueueTreeApp {
    public static void main(String[] args) {
        PriorityQueueOnTree thePQ = new PriorityQueueOnTree();
        thePQ.insert(30);
        thePQ.insert(50);
        thePQ.insert(10);
        thePQ.insert(40);
        thePQ.insert(39);
        thePQ.insert(41);
        thePQ.insert(20);
        thePQ.insert(54);
        thePQ.insert(56);
        thePQ.insert(51);
        thePQ.insert(49);
        thePQ.insert(8);
        thePQ.insert(1);
        thePQ.insert(4);
        thePQ.insert(3);
        thePQ.insert(7);

        thePQ.print();

        while (!thePQ.isEmpty()) {
            long item = thePQ.remove();
            System.out.print(item + " ");  // 56, 54, 51, 50, 49, 41, 40, 39, 30, 20, 10, 8, 7, 4, 3, 1
        }  // end while
        System.out.println("");

        thePQ.print();
    }  // end main()
//-------------------------------------------------------------
}  // end class PriorityQApp
////////////////////////////////////////////////////////////////
