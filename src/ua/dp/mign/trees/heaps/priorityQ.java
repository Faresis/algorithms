package ua.dp.mign.trees.heaps;

// priorityQ.java
// demonstrates priority queue
// to run this program: C>java PriorityQApp
////////////////////////////////////////////////////////////////
class InternalHeapNode {
    private long lData;             // data item (key)

    // -------------------------------------------------------------
    public InternalHeapNode(long key)           // constructor
    {
        lData = key;
    }

    // -------------------------------------------------------------
    public long getKey() {
        return lData;
    }

    // -------------------------------------------------------------
    public void setKey(long id) {
        lData = id;
    }
// -------------------------------------------------------------
}  // end class Node
////////////////////////////////////////////////////////////////

class InternalHeap {
    private InternalHeapNode[] heapArray;
    private int maxSize;           // size of array
    private int currentSize;       // number of nodes in array

    // -------------------------------------------------------------
    public InternalHeap(int mx)            // constructor
    {
        maxSize = mx;
        currentSize = 0;
        heapArray = new InternalHeapNode[maxSize];  // create array
    }

    // -------------------------------------------------------------
    public boolean isEmpty() {
        return currentSize == 0;
    }

    public boolean isFull() {
        return currentSize == maxSize;
    }

    // -------------------------------------------------------------
    public boolean insert(long key) {
        if (currentSize == maxSize)
            return false;
        InternalHeapNode newNode = new InternalHeapNode(key);
        heapArray[currentSize] = newNode;
        trickleUp(currentSize++);
        return true;
    }  // end insert()

    // -------------------------------------------------------------
    public void trickleUp(int index) {
        int parent = (index - 1) / 2;
        InternalHeapNode bottom = heapArray[index];

        while (index > 0 &&
                heapArray[parent].getKey() < bottom.getKey()) {
            heapArray[index] = heapArray[parent];  // move it down
            index = parent;
            parent = (parent - 1) / 2;
        }  // end while
        heapArray[index] = bottom;
    }  // end trickleUp()

    // -------------------------------------------------------------
    public InternalHeapNode remove()           // delete item with max key
    {                           // (assumes non-empty list)
        InternalHeapNode root = heapArray[0];
        heapArray[0] = heapArray[--currentSize];
        trickleDown(0);
        return root;
    }  // end remove()


    public InternalHeapNode peek()
    {
        return heapArray[0];
    }

    // -------------------------------------------------------------
    public void trickleDown(int index) {
        int largerChild;
        InternalHeapNode top = heapArray[index];       // save root
        while (index < currentSize / 2)       // while node has at
        {                               //    least one child,
            int leftChild = 2 * index + 1;
            int rightChild = leftChild + 1;
            // find larger child
            if (rightChild < currentSize &&  // (rightChild exists?)
                    heapArray[leftChild].getKey() <
                            heapArray[rightChild].getKey())
                largerChild = rightChild;
            else
                largerChild = leftChild;
            // top >= largerChild?
            if (top.getKey() >= heapArray[largerChild].getKey())
                break;
            // shift child up
            heapArray[index] = heapArray[largerChild];
            index = largerChild;            // go down
        }  // end while
        heapArray[index] = top;            // root to index
    }  // end trickleDown()

    // -------------------------------------------------------------
    public boolean change(int index, long newValue) {
        if (index < 0 || index >= currentSize)
            return false;
        long oldValue = heapArray[index].getKey(); // remember old
        heapArray[index].setKey(newValue);  // change to new

        if (oldValue < newValue)             // if raised,
            trickleUp(index);                // trickle it up
        else                                // if lowered,
            trickleDown(index);              // trickle it down
        return true;
    }  // end change()

    // -------------------------------------------------------------
    public void displayHeap() {
        System.out.print("heapArray: ");    // array format
        for (int m = 0; m < currentSize; m++)
            if (heapArray[m] != null)
                System.out.print(heapArray[m].getKey() + " ");
            else
                System.out.print("-- ");
        System.out.println();
        // heap format
        int nBlanks = 32;
        int itemsPerRow = 1;
        int column = 0;
        int j = 0;                          // current item
        String dots = "...............................";
        System.out.println(dots + dots);      // dotted top line

        while (currentSize > 0)              // for each heap item
        {
            if (column == 0)                  // first item in row?
                for (int k = 0; k < nBlanks; k++)  // preceding blanks
                    System.out.print(' ');
            // display item
            System.out.print(heapArray[j].getKey());

            if (++j == currentSize)           // done?
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
// -------------------------------------------------------------
}  // end class Heap

////////////////////////////////////////////////////////////////
class PriorityQ {
    // array in sorted order, from max at 0 to min at size-1
    private final InternalHeap heap;

    //-------------------------------------------------------------
    public PriorityQ(int s)          // constructor
    {
        this.heap = new InternalHeap(s);
    }

    //-------------------------------------------------------------
    public void insert(long item)    // insert item
    {
        this.heap.insert(item);
    }  // end insert()

    //-------------------------------------------------------------
    public long remove()             // remove minimum item
    {
        return heap.remove().getKey();
    }

    //-------------------------------------------------------------
    public long peek()
    {
        return heap.peek().getKey();
    }

    //-------------------------------------------------------------
    public boolean isEmpty()         // true if queue is empty
    {
        return heap.isEmpty();
    }

    //-------------------------------------------------------------
    public boolean isFull()          // true if queue is full
    {
        return heap.isFull();
    }
//-------------------------------------------------------------
}  // end class PriorityQ

////////////////////////////////////////////////////////////////
class PriorityQApp {
    public static void main(String[] args) {
        PriorityQ thePQ = new PriorityQ(5);
        thePQ.insert(30);
        thePQ.insert(50);
        thePQ.insert(10);
        thePQ.insert(40);
        thePQ.insert(20);

        while (!thePQ.isEmpty()) {
            long item = thePQ.remove();
            System.out.print(item + " ");  // 50, 40, 30, 20, 10
        }  // end while
        System.out.println("");
    }  // end main()
//-------------------------------------------------------------
}  // end class PriorityQApp
////////////////////////////////////////////////////////////////
