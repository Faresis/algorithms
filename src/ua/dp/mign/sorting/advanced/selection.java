package ua.dp.mign.sorting.advanced;

import java.util.Arrays;

class ArraySel {
    private long[] theArray;          // ref to array theArray
    private int nElems;               // number of data items

    //--------------------------------------------------------------
    public ArraySel(int max)          // constructor
    {
        theArray = new long[max];      // create the array
        nElems = 0;                    // no items yet
    }

    //--------------------------------------------------------------
    public void insert(long value)    // put element into array
    {
        theArray[nElems] = value;      // insert it
        nElems++;                      // increment size
    }

    //--------------------------------------------------------------
    public int size()                 // return number of items
    {
        return nElems;
    }

    //--------------------------------------------------------------
    public void display()             // displays array contents
    {
        System.out.print("A=");
        for (int j = 0; j < nElems; j++)    // for each element,
            System.out.print(theArray[j] + " ");  // display it
        System.out.println("");
    }

    //--------------------------------------------------------------
    public int partitionIt(int left, int right) {
        long pivot = theArray[right];
        int leftPtr = left - 1;           // right of first elem
        int rightPtr = right;             // left of pivot
        while (true) {
            while(theArray[++leftPtr] < pivot)
                ;  // (nop)

            while (rightPtr > 0 &&       // find smaller item
                    theArray[--rightPtr] > pivot)
                ;  // (nop)
            if (leftPtr >= rightPtr)        // if pointers cross,
                break;                      //    partition done
            else                           // not crossed, so
                swap(leftPtr, rightPtr);    //    swap elements
        }  // end while(true)
        swap(leftPtr, right);
        return leftPtr;                   // return partition
    }  // end partitionIt()

    //--------------------------------------------------------------
    public void swap(int dex1, int dex2)  // swap two elements
    {
        long temp;
        temp = theArray[dex1];             // A into temp
        theArray[dex1] = theArray[dex2];   // B into A
        theArray[dex2] = temp;             // temp into B
    }  // end swap()
    //--------------------------------------------------------------
    public long selectByPartition(int idx) {
        return selectByPartitionRec(0, nElems - 1, idx);
    }

    public long selectBySorting(int idx) {
        Arrays.sort(theArray);
        return theArray[idx];
    }

    private long selectByPartitionRec(int left, int right, int idx) {
        int pivot = partitionIt(left, right);

        if (pivot == idx) {

            return theArray[pivot];

        } else if (pivot > idx) {

            return selectByPartitionRec(left, --pivot, idx);

        } else { // pivot < idx

            return selectByPartitionRec(++pivot, right, idx);

        }

    }
}  // end class ArraySel

class SelectionApp {
    public static void main(String[] args) {
        int maxSize = 101;
        ArraySel arr;                 // reference to array
        arr = new ArraySel(maxSize);  // create the array

        for (int j = 0; j < maxSize; j++)  // fill array with
        {                          // random numbers
            long n = (int) (Math.random() * 199);
            arr.insert(n);
        }
        arr.display();                // display unsorted array

        int secondSmallest = 0 + 1;
        int seventhLargest = maxSize - 7;

        System.out.println("Second smallest by partition is :" + arr.selectByPartition(secondSmallest));
        System.out.println("Seventh largest by partition is :" + arr.selectByPartition(seventhLargest));

        arr.display();

        System.out.println("Second smallest by sorting is :" + arr.selectBySorting(secondSmallest));
        System.out.println("Seventh largest by sorting is :" + arr.selectBySorting(seventhLargest));

        arr.display();

        System.out.println("Handling of a small array.");
        maxSize = 2;
        arr = new ArraySel(maxSize);
        for (int j = 0; j < maxSize; j++)  // fill array with
        {                          // random numbers
            long n = (int) (Math.random() * 199);
            arr.insert(n);
        }
        arr.display();                // display unsorted array

        int firstLargest = maxSize - 1;
        System.out.println("Second smallest by partition is :" + arr.selectByPartition(secondSmallest));
        System.out.println("First largest by partition is :" + arr.selectByPartition(firstLargest));

        arr.display();

        System.out.println("Second smallest by sorting is :" + arr.selectBySorting(secondSmallest));
        System.out.println("First largest by sorting is :" + arr.selectBySorting(firstLargest));
    }
}
