package ua.dp.mign.sorting.simple.measurements;

// bubbleSort.java
// demonstrates bubble sort
// to run this program: C>java BubbleSortApp
////////////////////////////////////////////////////////////////
class ArrayBub {
    private long[] a;                 // ref to array a
    private int nElems;               // number of data items

    //--------------------------------------------------------------
    public ArrayBub(int max)          // constructor
    {
        a = new long[max];                 // create the array
        nElems = 0;                        // no items yet
    }

    //--------------------------------------------------------------
    public void insert(long value)    // put element into array
    {
        a[nElems] = value;             // insert it
        nElems++;                      // increment size
    }

    //--------------------------------------------------------------
    public void display()             // displays array contents
    {
        for (int j = 0; j < nElems; j++)       // for each element,
            System.out.print(a[j] + " ");  // display it
        System.out.println("");
    }

    //--------------------------------------------------------------
    public void bubbleSort() {
        int out, in;

        for (out = nElems - 1; out > 1; out--)   // outer loop (backward)
            for (in = 0; in < out; in++)        // inner loop (forward)
                if (a[in] > a[in + 1])       // out of order?
                    swap(in, in + 1);          // swap them
    }  // end bubbleSort()

    //--------------------------------------------------------------
    private void swap(int one, int two) {
        long temp = a[one];
        a[one] = a[two];
        a[two] = temp;
    }
//--------------------------------------------------------------
}  // end class ArrayBub

////////////////////////////////////////////////////////////////
class BubbleSortApp {
    public static void main(String[] args) {
        int maxSize = 100_000;            // array size
        ArrayBub arr;                 // reference to array
        arr = new ArrayBub(maxSize);  // create the array

        for (int j = 0; j < maxSize; j++) { // fill array with
            // random numbers
            long n = (long) (java.lang.Math.random() * (maxSize - 1));
            arr.insert(n);
        }

        long start = System.nanoTime();
        arr.bubbleSort();   // bubble sort them
        long end = System.nanoTime();

        System.out.printf("Time elapsed: %d milliseconds.\n", (end - start) / 1_000_000);
    }  // end main()
}  // end class BubbleSortApp
////////////////////////////////////////////////////////////////
