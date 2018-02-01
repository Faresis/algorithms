package ua.dp.mign.sorting.advanced;

import java.util.Arrays;

class ArrayMed {
    private long[] theArray;          // ref to array theArray
    private int nElems;               // number of data items

    //--------------------------------------------------------------
    public ArrayMed(int max)          // constructor
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
}  // end class ArrayMed
public final class medianByPartition {
    public static void main(String[] args) {
        int maxSize = 101;
        ArrayMed arr;                 // reference to array
        arr = new ArrayMed(maxSize);  // create the array

        for (int j = 0; j < maxSize; j++)  // fill array with
        {                          // random numbers
            long n = (int) (java.lang.Math.random() * 199);
            arr.insert(n);
        }
        arr.display();                // display unsorted array
    }
}
