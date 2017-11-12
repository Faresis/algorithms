package ua.dp.mign.sorting.simple.measurements;

// selectSort.java
// demonstrates selection sort
// to run this program: C>java SelectSortApp
////////////////////////////////////////////////////////////////
class ArraySel {
    private long[] a;                 // ref to array a
    private int nElems;               // number of data items

    //--------------------------------------------------------------
    public ArraySel(int max)          // constructor
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
    public void selectionSort() {
        int out, in, min;

        for (out = 0; out < nElems - 1; out++)   // outer loop
        {
            min = out;                     // minimum
            for (in = out + 1; in < nElems; in++) // inner loop
                if (a[in] < a[min])         // if min greater,
                    min = in;               // we have a new min
            swap(out, min);                // swap them
        }  // end for(out)
    }  // end selectionSort()

    //--------------------------------------------------------------
    private void swap(int one, int two) {
        long temp = a[one];
        a[one] = a[two];
        a[two] = temp;
    }
//--------------------------------------------------------------
}  // end class ArraySel

////////////////////////////////////////////////////////////////
class SelectSortApp {
    public static void main(String[] args) {
        int maxSize = 100_000;            // array size

        // inverse data
        ArraySel inverse = new ArraySel(maxSize);
        fillInversely(maxSize, inverse);
        System.out.println("Inverse data.");
        measure(inverse);

        // random data
        ArraySel random = new ArraySel(maxSize);  // create the array
        fillRandomly(maxSize, random);
        System.out.println("Random data.");
        measure(random);

        // sorted data
        ArraySel sortedData = new ArraySel(maxSize);
        fillSorted(maxSize, sortedData);
        System.out.println("Sorted data.");
        measure(sortedData);
    }  // end main()

    private static void fillInversely(int maxSize, ArraySel inverse) {
        for (int i = maxSize; i > 0; --i) {
            inverse.insert(i);
        }
    }

    private static void fillRandomly(int maxSize, ArraySel random) {
        for (int j = 0; j < maxSize; j++) { // fill array with
            // random numbers
            long n = (long) (Math.random() * (maxSize - 1));
            random.insert(n);
        }
    }

    private static void fillSorted(int maxSize, ArraySel sorted) {
        for (int i = 0; i < maxSize; i++) {
            sorted.insert(i);
        }
    }

    private static void measure(ArraySel arr) {
        long start = System.nanoTime();
        arr.selectionSort();   // sort them
        long end = System.nanoTime();

        System.out.printf("Time elapsed: %d milliseconds.\n", (end - start) / 1_000_000);
    }
}  // end class SelectSortApp
////////////////////////////////////////////////////////////////
