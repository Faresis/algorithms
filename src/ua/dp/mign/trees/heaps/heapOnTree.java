package ua.dp.mign.trees.heaps;// heap.java
// demonstrates heaps
// to run this program: C>java HeapApp

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;

////////////////////////////////////////////////////////////////
class HeapOnTreeNode {
    private int iData;
    private HeapOnTreeNode parent;
    private HeapOnTreeNode leftChild;
    private HeapOnTreeNode rightChild;

    // -------------------------------------------------------------
    public HeapOnTreeNode(int key, HeapOnTreeNode parent) {
        iData = key;
        this.parent = parent;
    }

    public int getKey() {
        return iData;
    }

    public void setKey(int id) {
        iData = id;
    }

    public HeapOnTreeNode getParent() {
        return parent;
    }

    public HeapOnTreeNode getLeftChild() {
        return leftChild;
    }

    public HeapOnTreeNode getRightChild() {
        return rightChild;
    }

    public void setLeftChild(HeapOnTreeNode leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild(HeapOnTreeNode rightChild) {
        this.rightChild = rightChild;
    }

    public boolean hasChildren() {
        return leftChild != null || rightChild != null;
    }

    public HeapOnTreeNode getLargestChild(Comparator<Integer> comparator) {
        if (leftChild != null && rightChild != null) {
            if (comparator.compare(leftChild.getKey(), rightChild.getKey()) < 0) {
                return rightChild;
            } else {
                return leftChild;
            }
        } else if (!hasChildren()) {
            return null;
        } else {
            return leftChild;
        }
    }

    public void disconnect(HeapOnTreeNode child) {
        if (child == rightChild) {
            rightChild = null;
        } else {
            leftChild = null;
        }
    }
}

////////////////////////////////////////////////////////////////
class HeapOnTree {
    private HeapOnTreeNode root;
    private int currentSize;       // number of nodes in array
    private final Comparator<Integer> comparator;

    public HeapOnTree(Comparator<Integer> comparator) {
        currentSize = 0;
        this.comparator = comparator;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public void insert(int key) {
        trickleUp(createNode(++currentSize, key));
    }

    public void toss(int key) {
        createNode(++currentSize, key);
    }

    public void restoreHeap() {
        int current = 1;
        int lastRightParent = current;
        while (current <= currentSize) {
            lastRightParent = current;
            current = current * 2 + 1;
        }

        for (int i = lastRightParent; i >= 1; --i) {
            trickleDown(getNode(i));
        }
    }

    public void trickleUp(HeapOnTreeNode node) {
        int bottom = node.getKey();

        HeapOnTreeNode current = node;
        while (current.getParent() != null && comparator.compare(current.getParent().getKey(), bottom) < 0) {
            current.setKey(current.getParent().getKey());
            current = current.getParent();
        }
        current.setKey(bottom);
    }

    // -------------------------------------------------------------
    public int remove() {
        if (currentSize == 0) {
            throw new IllegalStateException("Heap is empty.");
        }

        int result = root.getKey();
        HeapOnTreeNode last = getNode(currentSize);
        root.setKey(last.getKey());
        disconnect(last);
        currentSize--;
        if (root != null) {
            trickleDown(root);
        }
        return result;
    }

    public void trickleDown(HeapOnTreeNode node) {
        int top = node.getKey();

        HeapOnTreeNode current = node;
        while (current.hasChildren()) {
            HeapOnTreeNode largestChild = current.getLargestChild(comparator);

            if (comparator.compare(top, largestChild.getKey()) >= 0)
                break;

            current.setKey(largestChild.getKey());
            current = largestChild;
        }
        current.setKey(top);
    }

    public void change(int index, int newValue) {
        if (index < 1 || index > currentSize) {
            throw new IllegalStateException("Index out of heap bounds.");
        }

        HeapOnTreeNode node = getNode(index);
        int oldValue = node.getKey();
        node.setKey(newValue);

        if (comparator.compare(oldValue, newValue) < 0) {
            trickleUp(node);
        } else {
            trickleDown(node);
        }
    }

    // -------------------------------------------------------------
    public void displayHeap() {
        System.out.print("heapArray: ");    // array format
        for (int m = 1; m <= currentSize; m++)
            if (getNode(m) != null)
                System.out.print(getNode(m).getKey() + " ");
            else
                System.out.print("-- ");
        System.out.println();
        // heap format
        int nBlanks = 32;
        int itemsPerRow = 1;
        int column = 0;
        int j = 1;                          // current item
        String dots = "...............................";
        System.out.println(dots + dots);      // dotted top line

        while (currentSize > 0)              // for each heap item
        {
            if (column == 0)                  // first item in row?
                for (int k = 0; k < nBlanks; k++)  // preceding blanks
                    System.out.print(' ');
            // display item
            System.out.print(getNode(j).getKey());

            if (j++ == currentSize)           // done?
                break;

            if (++column == itemsPerRow)        // end of row?
            {
                nBlanks /= 2;                 // half the blanks
                itemsPerRow *= 2;             // twice the items
                column = 0;                   // start over on
                System.out.println();         //    new row
            } else                             // next item on row
                for (int k = 0; k < nBlanks * 2 - 2; k++)
                    System.out.print(' ');     // interim blanks
        }  // end for
        System.out.println("\n" + dots + dots); // dotted bottom line
    }  // end displayHeap()

    public HeapOnTreeNode getNode(int index) {
        if (index < 1 || index > currentSize) {
            throw new IllegalStateException("Index out of heap bounds.");
        }

        if (index == 1) {
            return root;
        }

        boolean left = index % 2 == 0;
        HeapOnTreeNode node = getNode(index / 2);
        return left ? node.getLeftChild() : node.getRightChild();
    }

    private HeapOnTreeNode createNode(int index, int key) {
        if (index < 1 || index > currentSize) {
            throw new IllegalStateException("Index out of heap bounds.");
        }

        if (index == 1) {
            if (root == null) {
                root = new HeapOnTreeNode(key, null);
            }
            return root;
        }

        boolean left = (index % 2) == 0;
        HeapOnTreeNode node = createNode(index / 2, key);
        HeapOnTreeNode child = left ? node.getLeftChild() : node.getRightChild();
        if (child != null) {
            return child;
        } else {
            child = new HeapOnTreeNode(key, node);
            if (left) {
                node.setLeftChild(child);
            } else {
                node.setRightChild(child);
            }
            return child;
        }
    }

    private void disconnect(HeapOnTreeNode node) {
        HeapOnTreeNode parent = node.getParent();
        if (parent != null) {
            parent.disconnect(node);
        } else {
            root = null;
        }
    }
}  // end class Heap

////////////////////////////////////////////////////////////////
class HeapOnTreeApp {
    public static void main(String[] args) throws IOException {
        HeapOnTree theHeap = new HeapOnTree(Integer::compareTo);  // make a Heap; max size 31

        theHeap.toss(70);           // insert 10 items
        theHeap.toss(40);
        theHeap.toss(50);
        theHeap.toss(20);
        theHeap.toss(60);
        theHeap.toss(100);
        theHeap.toss(80);
        theHeap.toss(30);
        theHeap.toss(10);
        theHeap.toss(90);

        while (true)                   // until [Ctrl]-[C]
        {
            System.out.print("Enter first letter of ");
            System.out.print("show, insert, remove, change, toss, heap, get: ");
            int choice = getChar();
            switch (choice) {
                case 's':                        // show
                    theHeap.displayHeap();
                    break;
                case 'h':                        // heap
                    theHeap.restoreHeap();
                    break;
                case 'i':                        // insert
                    System.out.print("Enter value to insert: ");
                    theHeap.insert(getInt());
                    break;
                case 't':                        // toss
                    System.out.print("Enter value to toss: ");
                    theHeap.toss(getInt());
                    break;
                case 'r':                        // remove
                    if (!theHeap.isEmpty())
                        System.out.println("Removed: " + theHeap.remove());
                    else
                        System.out.println("Can't remove; heap empty");
                    break;
                case 'c':                        // change
                    System.out.print("Enter current index of item: ");
                    int value = getInt();
                    System.out.print("Enter new key: ");
                    int value2 = getInt();
                    theHeap.change(value, value2);
                    break;
                case 'g':                        // get
                    System.out.print("Enter index to get: ");
                    System.out.println(theHeap.getNode(getInt()).getKey());
                    break;
                default:
                    System.out.println("Invalid entry\n");
            }  // end switch
        }  // end while
    }  // end main()

    //-------------------------------------------------------------
    public static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }

    //-------------------------------------------------------------
    public static char getChar() throws IOException {
        String s = getString();
        return s.charAt(0);
    }

    //-------------------------------------------------------------
    public static int getInt() throws IOException {
        String s = getString();
        return Integer.parseInt(s);
    }
//-------------------------------------------------------------
}  // end class HeapApp
////////////////////////////////////////////////////////////////
