package ua.dp.mign.sorting.measurements;

// insertSort.java
// demonstrates insertion sort
// to run this program: C>java InsertSortApp
//--------------------------------------------------------------
class ArrayIns {
    private long[] a;                 // ref to array a
    private int nElems;               // number of data items

    //--------------------------------------------------------------
    public ArrayIns(int max)          // constructor
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
    public void insertionSort() {
        int in, out;

        for (out = 1; out < nElems; out++)     // out is dividing line
        {
            long temp = a[out];            // remove marked item
            in = out;                      // start shifts at out
            while (in > 0 && a[in - 1] >= temp) // until one is smaller,
            {
                a[in] = a[in - 1];            // shift item to right
                --in;                       // go left one position
            }
            a[in] = temp;                  // insert marked item
        }  // end for
    }  // end insertionSort()
//--------------------------------------------------------------
}  // end class ArrayIns

////////////////////////////////////////////////////////////////
class InsertSortApp {
    public static void main(String[] args) {
        int maxSize = 100_000;            // array size
        // inverse data
        ArrayIns inverse = new ArrayIns(maxSize);
        fillInversely(maxSize, inverse);
        System.out.println("Inverse data.");
        measure(inverse);

        // random data
        ArrayIns random = new ArrayIns(maxSize);  // create the array
        fillRandomly(maxSize, random);
        System.out.println("Random data.");
        measure(random);

        // sorted data
        ArrayIns sortedData = new ArrayIns(maxSize);
        fillSorted(maxSize, sortedData);
        System.out.println("Sorted data.");
        measure(sortedData);
    }  // end main()

    private static void fillInversely(int maxSize, ArrayIns inverse) {
        for (int i = maxSize; i > 0; --i) {
            inverse.insert(i);
        }
    }

    private static void fillRandomly(int maxSize, ArrayIns random) {
        for (int j = 0; j < maxSize; j++) { // fill array with
            // random numbers
            long n = (long) (Math.random() * (maxSize - 1));
            random.insert(n);
        }
    }

    private static void fillSorted(int maxSize, ArrayIns sorted) {
        for (int i = 0; i < maxSize; i++) {
            sorted.insert(i);
        }
    }

    private static void measure(ArrayIns arr) {
        long start = System.nanoTime();
        arr.insertionSort();   // sort them
        long end = System.nanoTime();

        System.out.printf("Time elapsed: %d milliseconds.\n", (end - start) / 1_000_000);
    }
}  // end class InsertSortApp
