package ua.dp.mign.sorting.measurements;

// mergeSort.java
// demonstrates recursive merge sort
// to run this program: C>java MergeSortApp
////////////////////////////////////////////////////////////////
class DArray {
    private long[] theArray;          // ref to array theArray
    private int nElems;               // number of data items

    //-----------------------------------------------------------
    public DArray(int max)            // constructor
    {
        theArray = new long[max];      // create array
        nElems = 0;
    }

    //-----------------------------------------------------------
    public void insert(long value)    // put element into array
    {
        theArray[nElems] = value;      // insert it
        nElems++;                      // increment size
    }

    //-----------------------------------------------------------
    public void display()             // displays array contents
    {
        for (int j = 0; j < nElems; j++)    // for each element,
            System.out.print(theArray[j] + " ");  // display it
        System.out.println("");
    }

    //-----------------------------------------------------------
    public void mergeSort()           // called by main()
    {                              // provides workspace
        long[] workSpace = new long[nElems];
        recMergeSort(workSpace, 0, nElems - 1);
    }

    //-----------------------------------------------------------
    private void recMergeSort(long[] workSpace, int lowerBound,
                              int upperBound) {
        if (lowerBound == upperBound)            // if range is 1,
            return;                              // no use sorting
        else {                                    // find midpoint
            int mid = (lowerBound + upperBound) / 2;
            // sort low half
            recMergeSort(workSpace, lowerBound, mid);
            // sort high half
            recMergeSort(workSpace, mid + 1, upperBound);
            // merge them
            merge(workSpace, lowerBound, mid + 1, upperBound);
        }  // end else
    }  // end recMergeSort()

    //-----------------------------------------------------------
    private void merge(long[] workSpace, int lowPtr,
                       int highPtr, int upperBound) {
        int j = 0;                             // workspace index
        int lowerBound = lowPtr;
        int mid = highPtr - 1;
        int n = upperBound - lowerBound + 1;       // # of items

        while (lowPtr <= mid && highPtr <= upperBound)
            if (theArray[lowPtr] < theArray[highPtr])
                workSpace[j++] = theArray[lowPtr++];
            else
                workSpace[j++] = theArray[highPtr++];

        while (lowPtr <= mid)
            workSpace[j++] = theArray[lowPtr++];

        while (highPtr <= upperBound)
            workSpace[j++] = theArray[highPtr++];

        for (j = 0; j < n; j++)
            theArray[lowerBound + j] = workSpace[j];
    }  // end merge()
    //-----------------------------------------------------------
}  // end class DArray

////////////////////////////////////////////////////////////////
class MergeSortApp {
    public static void main(String[] args) {
        int maxSize = 100_000;            // array size

        // inverse data
        DArray inverse = new DArray(maxSize);
        fillInversely(maxSize, inverse);
        System.out.println("Inverse data.");
        measure(inverse);

        // random data
        DArray random = new DArray(maxSize);  // create the array
        fillRandomly(maxSize, random);
        System.out.println("Random data.");
        measure(random);

        // sorted data
        DArray sortedData = new DArray(maxSize);
        fillSorted(maxSize, sortedData);
        System.out.println("Sorted data.");
        measure(sortedData);
    }  // end main()

    private static void fillInversely(int maxSize, DArray inverse) {
        for (int i = maxSize; i > 0; --i) {
            inverse.insert(i);
        }
    }

    private static void fillRandomly(int maxSize, DArray random) {
        for (int j = 0; j < maxSize; j++) { // fill array with
            // random numbers
            long n = (long) (Math.random() * (maxSize - 1));
            random.insert(n);
        }
    }

    private static void fillSorted(int maxSize, DArray sorted) {
        for (int i = 0; i < maxSize; i++) {
            sorted.insert(i);
        }
    }

    private static void measure(DArray arr) {
        long start = System.nanoTime();
        arr.mergeSort();   // sort them
        long end = System.nanoTime();

        System.out.printf("Time elapsed: %d milliseconds.\n", (end - start) / 1_000_000);
    }
}  // end class MergeSortApp
////////////////////////////////////////////////////////////////
